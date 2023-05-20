package tk.gbl.core;

import tk.gbl.bean.*;
import tk.gbl.config.GameConfig;
import tk.gbl.config.GameParam;
import tk.gbl.constant.ProtocolConstant;
import tk.gbl.constant.ServerInfo;
import tk.gbl.core.fightai.QingliuAi;
import tk.gbl.core.listener.*;
import tk.gbl.core.script.DoDoScriptAction;
import tk.gbl.show.ShowUtil;
import tk.gbl.util.ItemIdUtil;
import tk.gbl.util.OutputUtil;
import tk.gbl.util.PathFindingUtil;
import tk.gbl.util.ZHConverter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 101.大将军*123.59.201.31
 * 104.大都督*123.59.201.32
 * <p/>
 * Date: 2017/3/27
 * Time: 19:28
 *
 * @author Tian.Dong
 */
public class GameClient {
    GameTeam gameTeam;
    String serverPrefix = "wp";
    int serverIndex = 1;
    Socket socket;
    InputStream inputStream;
    OutputStream outputStream;
    SocketReadThread socketReadThread;
    GameConfig gameConfig = new GameConfig();
    GameParam gameParam = new GameParam();
    int index;
    String username;
    long userId;
    String password = "";
    boolean isStartFightMonitorThread = false;
    boolean isStartGetItemMonitorThread = false;
    boolean isClose = false;
    boolean reConnect = true;
    boolean consoleOutput = true;
    boolean fileOutput = true;
    boolean isNewRole;
    boolean isLoginSuccess;
    boolean isInGame;

    int moveIndex;
    BagItem[] sceneItems = new BagItem[256];
    BagItem[] bagItems;
    BagItem[] equipItems = new BagItem[7];

    long tradeMoney = 0;
    List<Integer> tradeItemIndexList = new ArrayList<>();
    RoleInfo roleInfo = new RoleInfo();
    RoleInfo pet = null;
    RoleInfo[] petList = new RoleInfo[5];

    int currentMapId;
    Point point = new Point();
    int initMapId;
    String preNickname;

    boolean isJoinTeam;
    boolean isTeamLeader;
    long teamLeaderId;
    Set<Long> teamUsers = new HashSet<>();
    List<Long> allowTeamUsers = new ArrayList<>();

    Queue<Integer> chooseQueue = new LinkedList<>();

    Date fightTime = new Date();
    Date getItemTime = new Date();
    Lock fightLock = new ReentrantLock();
    Lock moveLock = new ReentrantLock();
    boolean fighting = false;
    Position position = new Position(3, 2);
    FightInfo fightInfo = new FightInfo();


    Map<String, BaseListener> listenerMap = new HashMap<>();
    Map<String, String> target = new HashMap<>();
    final Object moveFarObj = new Object();

    public void init() {
        try {
            if (isClose) {
                OutputUtil.output("连接已关闭", this, true);
                return;
            }
            OutputUtil.output("开始连接", this, true);
            if (this.socket == null) {
                this.socket = new Socket();
            }
            this.socket.connect(new InetSocketAddress(ServerInfo.getServerIp(serverPrefix, serverIndex), 6414), 5000);
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
            this.userId = Long.parseLong(username.substring(2));
            GameClientMap.getGameClientMap().put(userId, this);
            connect();
            login();
            OutputUtil.output("发起登录成功", this, true);
            try {
                socketReadThread = new SocketReadThread(this);
                socketReadThread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (getGameConfig().isNotFightReconnect()) {
                if (!isStartFightMonitorThread) {
                    new FightMonitorThread(this).start();
                    isStartFightMonitorThread = true;
                }
            }
            if (getGameConfig().isNotGetItemReconnect()) {
                if (!isStartGetItemMonitorThread) {
                    new GetItemMonitorThread(this).start();
                    isStartGetItemMonitorThread = true;
                }
            }
            fightTime = new Date();
            getItemTime = new Date();

            getListenerMap().put("loginSuccess", new LoginSuccessListener());
            getListenerMap().put("fightStart", new FightStartListener());
            getListenerMap().put("fightEnd", new FightEndListener());
            getListenerMap().put("roundStart", new RoundStartListener());
            getListenerMap().put("joinTeam", new JoinTeamRequestListener());
            getListenerMap().put("joinTeamConfirm", new JoinTeamConfirmListener());
            getListenerMap().put("joinTeamSuccess", new JoinTeamSuccessListener());
            getListenerMap().put("teamDismiss", new TeamDismissListener());

            getListenerMap().put("tradeRequest", new TradeItemListener());
            getListenerMap().put("tradePetRequest", new TradePetListener());

            getListenerMap().put("itemAppear", new ItemAppearListener());

            getListenerMap().put("chat", new ChatListener());
        } catch (Exception e) {
            e.printStackTrace();
            if (!reConnect) {
                return;
            }
            OutputUtil.output("连接失败，10秒后重试", this, true);
            this.clear();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            init();
        }
    }

    public synchronized void close() {
        this.isClose = true;
        OutputUtil.output("close!", this, true);
        this.setReConnect(false);
        clear();
    }

    public synchronized void clear() {
        OutputUtil.output("clear!", this, true);
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        this.fightTime = new Date();
        this.getItemTime = new Date();
        this.inputStream = null;
        this.outputStream = null;
        this.socket = null;
        this.bagItems = null;
        this.currentMapId = 0;
        this.isJoinTeam = false;
        this.teamUsers.clear();
        for (int i = 0; i < petList.length; i++) {
            petList[i] = null;
        }
        this.isNewRole = false;
        this.isLoginSuccess = false;
        this.fighting = false;
        this.isJoinTeam = false;
        this.getTeamUsers().clear();
        try {
            this.fightLock.unlock();
        } catch (Exception e) {
        }
        OutputUtil.output("clear释放fightLock锁", this, false);
        try {
            this.moveLock.unlock();
        } catch (Exception e) {
        }
        OutputUtil.output("clear释放moveLock锁", this, false);
        synchronized (moveFarObj) {
            moveFarObj.notify();
        }
    }

    public void mergeBag() throws IOException {
        if (bagItems == null) {
            return;
        }
        BagItem[] copyBagItems = new BagItem[26];
        for (int i = 1; i <= 25; i++) {
            BagItem bagItem = bagItems[i];
            if (bagItem == null) {
                continue;
            }
            BagItem copyBagItem = new BagItem();
            copyBagItem.setId(bagItem.getId());
            copyBagItem.setCount(bagItem.getCount());
            copyBagItems[i] = copyBagItem;
        }
        for (int i = 1; i <= 25; i++) {
            BagItem bagItem = copyBagItems[i];
            if (bagItem == null) {
                continue;
            }
            if (bagItem.getCount() == 50) {
                continue;
            }
            Goods goods = ItemIdUtil.getGoods(bagItem.getId());
            if (goods != null) {
                if (goods.getType() < 15) {
                    //小于15不能合并
                    continue;
                }
            }
            for (int j = 25; j >= 1; j--) {
                BagItem descBagItem = copyBagItems[j];
                if (descBagItem == null) {
                    continue;
                }
                if (descBagItem.getCount() == 0) {
                    continue;
                }
                if (j <= i) {
                    break;
                }
                if (bagItem.getCount() == 50) {
                    break;
                }
                if (descBagItem.getId() == bagItem.getId()) {
                    //合并背包
                    int count = 50 - bagItem.getCount();
                    if (count == 0) {
                        break;
                    }
                    if (count > descBagItem.getCount()) {
                        count = descBagItem.getCount();
                    }
                    splitItem(j, i, count);
                    bagItem.setCount(bagItem.getCount() + count);
                    descBagItem.setCount(descBagItem.getCount() - count);
                    if (descBagItem.getCount() == 0) {
                        copyBagItems[j] = null;
                    }
                }
            }
        }
        //空的
        for (int i = 1; i <= 24; i++) {
            BagItem bagItem = copyBagItems[i];
            if (bagItem != null && bagItem.getCount() > 0) {
                continue;
            }
            for (int j = i + 1; j <= 25; j++) {
                BagItem descBagItem = copyBagItems[j];
                if (descBagItem == null || descBagItem.getCount() == 0) {
                    continue;
                }
                splitItem(j, i, descBagItem.getCount());
                descBagItem.setCount(0);
                copyBagItems[j] = null;
                BagItem bagItem1 = new BagItem();
                bagItem1.setId(descBagItem.getId());
                bagItem1.setCount(descBagItem.getCount());
                copyBagItems[i] = bagItem1;
                break;
            }
        }
    }

    public void connect() throws IOException {
        //F4 44 01 00 00
        byte[] bytes = new byte[5];
        fillLeft(bytes);
        bytes[2] = 0x01;
        bytes[3] = 0;
        bytes[4] = (byte) ProtocolConstant.connect.getId();
        send(bytes);
    }

    public void login() throws IOException {
        login(username, password);
    }

    public void login(String username, String password) throws IOException {
        //59 E9 BD AD AC AB AA 1F AC AD DA C3 7C AD 9B 9B 9B 9B 9B 9B 59 E9 A6 AD EC AC AD AD AD AD AD AD AD AD AD
        //F4 44 10 00 01 06 07 B2 01 00 77 6E D1 00 36 36 36 36 36 36 F4 44 0B 00 41 01 00 00 00 00 00 00 00 00 00
        //ô  D                 ²        w  n  Ñ     6  6  6  6  6  6  ô  D        A
        //                  05F5E0FF
        //F4 44 10 00 01 06 FF E0 F5 05 77 6E D1 00 36 36 36 36 36 36 F4 44 0B 00 41 01 00 00 00 00 00 00 00 00 00
        //ô  D              ÿ  à  õ     w  n  Ñ     6  6  6  6  6  6  ô  D        A
        //F4 44 10 00 01 06 A0 BA 01 00 77 70 D1 00 35 35 36 36 38 38
        //F4 44 14 00 01 0A 86 C2 01 00 77 70 D1 00 4B 4A 52 4B 37 48 43 42 4A 54
        //F4 44 14 00 01 0A E4 8A 00 00 77 70 D1 00 77 74 6C 38 32 33 34 39 37 35 F4 44 0B 00 41 01 00 00 00 00 00 00 00 00 00
        username = username.toLowerCase();
        byte[] bytes = new byte[password.length() + 14];
//        byte[] bytes = new byte[password.length() + 29];
//        byte[] bytes = new byte[loginRequest.getPassword().length() + 29];
        fillLeft(bytes);
        bytes[2] = (byte) (password.length() + 10);//长度位
        bytes[3] = 0x00;
        bytes[4] = (byte) ProtocolConstant.login.getId();
        bytes[5] = (byte) (password.length());
        long userId = Long.parseLong(username.substring(2));
        bytes[6] = (byte) ((userId >> 0) & 0xFF);
        bytes[7] = (byte) ((userId >> 8) & 0xFF);
        bytes[8] = (byte) ((userId >> 16) & 0xFF);
        bytes[9] = (byte) ((userId >> 24) & 0xFF);
        //wp
        bytes[10] = (byte) username.substring(0, 1).charAt(0);
        bytes[11] = (byte) username.substring(1, 2).charAt(0);
        bytes[12] = (byte) 0xD1;
        bytes[13] = 0x00;
        //D1 00 36 36 36 36 36 36 F4 44 0B 00 41 01 00 00 00 00 00 00 00 00 00
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            bytes[14 + i] = (byte) ch;
        }

        //F4 44 0B 00 41 01 00 00 00 00 00 00 00 00 00
//        int passwordEndPos = 14 + password.length();
//        bytes[passwordEndPos] = (byte) 0xF4;
//        bytes[passwordEndPos + 1] = (byte) 0x44;
//        bytes[passwordEndPos + 2] = (byte) 0x0B;
//        bytes[passwordEndPos + 3] = (byte) 0x00;
//        bytes[passwordEndPos + 4] = (byte) 0x41;
//        bytes[passwordEndPos + 5] = (byte) 0x01;
//        bytes[passwordEndPos + 14] = (byte) 0x00;
        send(bytes);
    }


    /**
     * 检查昵称是否被占用
     * F4 44 08 00 09 02 B9 C8 A4 6A BF DF
     * F4 44 0C 00 09 02 61 62 63 64 65 66 67 31 32 33
     */
    public void checkName(String nickname) throws IOException {
        //转成繁体
        nickname = ZHConverter.convert(nickname, ZHConverter.TRADITIONAL);
        byte[] nicknameBytes = nickname.getBytes("big5");
        byte[] bytes = new byte[6 + nicknameBytes.length];
        fillLeft(bytes);
        bytes[2] = (byte) (nicknameBytes.length + 2);
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.checkName.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.checkName.getId() & 0xFF);
        for (int i = 0; i < nicknameBytes.length; i++) {
            bytes[6 + i] = nicknameBytes[i];
        }
        send(bytes);
    }

    /**
     * 设置性别 属性 密码 暗码
     * F4 44 23 00 09 01 01 00 00 00 1C AF 7D 1A 3E AE 7D 1A 01 01 02 03 00 00 00 06 31 32 33 34 35 36 06 31 32 33 34 35 36
     * 0           4  5  6           10       13 14 15       18    20             25
     * F4 44 24 00 09 01 02 00 06 00 BC 35 7F 1A 1C AF 7D 1A 04 00 00 00 00 00 06 06 31 32 33 34 35 36 07 31 32 33 34 35 36 37
     * ô  D  $                       ¼  5          ¯  }                             1  2  3  4  5  6     1  2  3  4  5  6  7
     * 自己:
     * F4 44 24 00 09 01 02 00 06 00 BC 35 7F 1A 1C AF 7D 1A 03 00 00 00 00 00 06 06 31 32 33 34 35 36 07 31 32 33 34 35 36 37
     */
    public void setPropertiesAndInit(String password, String secretPassword) throws IOException {
        //byte[] nicknameBytes = nickname.getBytes("big5");
        byte[] bytes = new byte[password.length() + secretPassword.length() + 2 + 6 + 19];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.setNameAndInit.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.setNameAndInit.getId() & 0xFF);

        bytes[6] = 0x02;//男 女
        bytes[7] = 0x00;
        bytes[8] = 0x06;
        bytes[9] = 0x00;
        bytes[10] = (byte) 0xBC;
        bytes[11] = (byte) 0x35;
        bytes[12] = (byte) 0x7F;
        bytes[13] = (byte) 0x1A;
        bytes[14] = (byte) 0x1C;
        bytes[15] = (byte) 0xAF;
        bytes[16] = (byte) 0x7D;
        bytes[17] = (byte) 0x1A;
        bytes[18] = (byte) 0x04;//地 水 火 风

        bytes[19] = (byte) 0x00;
        bytes[20] = (byte) 0x00;
        bytes[21] = (byte) 0x00;
        bytes[22] = (byte) 0x06;//体质
        bytes[23] = (byte) 0x00;
        bytes[24] = (byte) 0x00;//敏

        bytes[25] = (byte) password.length();
        for (int i = 0; i < password.length(); i++) {
            bytes[26 + i] = (byte) password.charAt(i);
        }
        bytes[26 + password.length()] = (byte) secretPassword.length();
        for (int i = 0; i < secretPassword.length(); i++) {
            bytes[27 + password.length() + i] = (byte) secretPassword.charAt(i);
        }
        send(bytes);
        this.password = password;
    }

    /**
     * 0  1              6
     * F4 44 11 00 23 02 06 31 32 33 34 35 36 07 31 32 33 34 35 36 37
     */
    /*public void delete1() throws IOException {
        String password = "123456";
        String secretPassword = "1234567";
        byte[] bytes = new byte[8 + password.length() + secretPassword.length()];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.delete.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.delete.getId() & 0xFF);
        bytes[6] = (byte) password.length();
        for (int i = 0; i < password.length(); i++) {
            bytes[7 + i] = (byte) password.charAt(i);
        }
        bytes[7 + password.length()] = (byte) secretPassword.length();
        for (int i = 0; i < secretPassword.length(); i++) {
            bytes[8 + password.length() + i] = (byte) secretPassword.charAt(i);
        }
        send(bytes);
    }*/
    public void confirmInit() throws IOException {
        byte[] bytes = new byte[6];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.confirmInit.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.confirmInit.getId() & 0xFF);
        send(bytes);

        //F4 44 10 00 25 01 31 31 34 2E 31 31 32 2E 39 37 2E 31 33 37
        String serverIp = "114.112.97.137";
        byte[] bytes2 = new byte[serverIp.length() + 6];
        fillLeft(bytes2);
        bytes2[2] = (byte) (serverIp.length() + 2); //长度
        bytes2[3] = 0x00;
        bytes2[4] = (byte) (ProtocolConstant.serverIp.getId() >> 8 & 0xFF);
        bytes2[5] = (byte) (ProtocolConstant.serverIp.getId() & 0xFF);
        for (int i = 0; i < serverIp.length(); i++) {
            bytes2[6 + i] = (byte) serverIp.charAt(i);
        }
        send(bytes2);

        //F4 44 12 00 27 13 E6 05 00 00 E9 05 00 00 D0 06 00 00 F2 06 00 00
        //0  1  2  3  4  5  6           10          14 15       18
        byte[] unknown2713Bytes = new byte[22];
        fillLeft(unknown2713Bytes);
        unknown2713Bytes[2] = 0x12;//长度
        unknown2713Bytes[3] = 0x00;//
        unknown2713Bytes[4] = (byte) (ProtocolConstant.unknown2713.getId() >> 8 & 0xFF);
        unknown2713Bytes[5] = (byte) (ProtocolConstant.unknown2713.getId() & 0xFF);
        unknown2713Bytes[6] = (byte) 0xE6;
        unknown2713Bytes[7] = 0x05;
        unknown2713Bytes[8] = 0x00;
        unknown2713Bytes[9] = 0x00;
        unknown2713Bytes[10] = (byte) 0xE9;
        unknown2713Bytes[11] = 0x05;
        unknown2713Bytes[12] = 0x00;
        unknown2713Bytes[13] = 0x00;
        unknown2713Bytes[14] = (byte) 0xD0;
        unknown2713Bytes[15] = 0x06;
        unknown2713Bytes[16] = 0x00;
        unknown2713Bytes[17] = 0x00;
        unknown2713Bytes[18] = (byte) 0xF2;
        unknown2713Bytes[19] = 0x06;
        unknown2713Bytes[20] = 0x00;
        unknown2713Bytes[21] = 0x00;
        send(unknown2713Bytes);

        //F4 44 03 00 19 29 7B
        byte[] unknown1929Bytes = new byte[7];
        fillLeft(unknown1929Bytes);
        unknown1929Bytes[2] = 0x03;//长度
        unknown1929Bytes[3] = 0x00;//
        unknown1929Bytes[4] = (byte) (ProtocolConstant.unknown1929.getId() >> 8 & 0xFF);
        unknown1929Bytes[5] = (byte) (ProtocolConstant.unknown1929.getId() & 0xFF);
        unknown1929Bytes[6] = (byte) 0x7B;
        send(unknown1929Bytes);
    }

    public void setIdCard() throws IOException {
        //451324199012135918
        //F4 44 1F 00 47 01 06 B9 C8 A4 6A BF DF C6 07 0C 0D 34 35 31 33 32 34 31 39 39 30 31 32 31 33 35 39 31 38
        //ô  D        G        ¹  È  ¤  j  ¿  ß  Æ           4  5  1  3  2  4  1  9  9  0  1  2  1  3  5  9  1  8
        //F4 44 1D 00 47 01 04 B0 DA B0 DA C9 07 0A 16 36 33 32 36 32 35 31 39 39 33 31 30 32 32 39 36 39 36
        //ô  D        G        °  Ú  °  Ú  É           6  3  2  6  2  5  1  9  9  3  1  0  2  2  9  6  9  6
        //F4 44 1F 00 47 01 06 B9 C8 A4 6A BF DF C6 07 0C 0D 34 35 31 33 32 34 31 39 39 30 31 32 31 33 35 39 31 38
        //ô  D        G        ¹  È  ¤  j  ¿  ß  Æ           4  5  1  3  2  4  1  9  9  0  1  2  1  3  5  9  1  8
        String name = "嗷大猫";
        name = ZHConverter.convert(name, ZHConverter.TRADITIONAL);
        byte[] nameBytes = name.getBytes("big5");
        String idCard = "451324199012135918";
        byte[] bytes = new byte[22 + nameBytes.length + 1 + 6];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4);
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.setIdCard.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.setIdCard.getId() & 0xFF);

        bytes[6] = (byte) nameBytes.length;
        for (int i = 0; i < nameBytes.length; i++) {
            bytes[7 + i] = nameBytes[i];
        }
        bytes[7 + nameBytes.length] = (byte) 0xC6;
        bytes[8 + nameBytes.length] = (byte) 0x07; //年份 7C6 = 1990
        bytes[9 + nameBytes.length] = (byte) 0x0C;//月
        bytes[10 + nameBytes.length] = (byte) 0x0D;//日
        for (int i = 0; i < idCard.length(); i++) {
            bytes[11 + nameBytes.length + i] = (byte) idCard.charAt(i);
        }
        send(bytes);
    }

    /**
     * F4 44 08 00 02 02 31 32 33 34 35 36
     * <p/>
     * F4 44 0C 00 02 03 76 A2 00 00 31 32 33 34 35 36
     */
    public void chat(String chatString) throws IOException {
        chatString = ZHConverter.convert(chatString, ZHConverter.TRADITIONAL);
        byte[] chatBytes = chatString.getBytes("big5");
        byte[] bytes = new byte[6 + chatBytes.length];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.chat.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.chat.getId() & 0xFF);
        for (int i = 0; i < chatBytes.length; i++) {
            bytes[6 + i] = chatBytes[i];
        }
        send(bytes, true);
    }

    /**
     * 0                 6  7  8  9
     * F4 44 0C 00 02 03 76 A2 00 00 31 32 33 34 35 36
     */
    public void chatMi(long userId, String chatString) throws IOException {
        chatString = ZHConverter.convert(chatString, ZHConverter.TRADITIONAL);
        byte[] chatBytes = chatString.getBytes("big5");
        byte[] bytes = new byte[10 + chatBytes.length];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.chatMi.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.chatMi.getId() & 0xFF);
        bytes[6] = (byte) (userId & 0xFF);
        bytes[7] = (byte) (userId >> 8 & 0xFF);
        bytes[8] = (byte) (userId >> 16 & 0xFF);
        bytes[9] = (byte) (userId >> 24 & 0xFF);
        for (int i = 0; i < chatBytes.length; i++) {
            bytes[10 + i] = chatBytes[i];
        }
        send(bytes, false);
    }

    public synchronized void clickNPC(int npcId) throws IOException {
        getFightLock().lock();
        byte[] bytes = new byte[8];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.clickNPC.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.clickNPC.getId() & 0xFF);
        bytes[6] = (byte) npcId;
        bytes[7] = 0x00;
        try {
            send(bytes);
        } catch (IOException e) {
            getFightLock().unlock();
            this.clear();
            throw e;
        }
        getFightLock().unlock();
    }

    /**
     * 0           4  5  6  7  8  9     11
     * F4 44 09 00 0B 02 03 07 2F 00 00 01 00
     * <p/>
     * F4 44 09 00 0B 02 03 07 2F 00 00 01 00
     * ô  D                    /
     * F4 44 09 00 0B 02 03 88 2B 00 00 01 00
     * ô  D                   +
     * F4 44 09 00 0B 02 03 E6 36 00 00 03 00
     * ô  D                 æ  6
     */
    public void pkNPC(long npcId, int npcIndex) throws IOException {
        getFightLock().lock();
        byte[] bytes = new byte[13];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.pk.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.pk.getId() & 0xFF);
        bytes[6] = 0x03;
        //npcId
        bytes[7] = (byte) (npcId & 0xFF);
        bytes[8] = (byte) (npcId >> 8 & 0xFF);
        bytes[9] = (byte) (npcId >> 16 & 0xFF);
        bytes[10] = (byte) (npcId >> 24 & 0xFF);
        bytes[11] = (byte) npcIndex;
        try {
            send(bytes, true);
        } catch (IOException e) {
            getFightLock().unlock();
            throw e;
        }
        getFightLock().unlock();
    }

    public void simpleChoose(int selectId) throws IOException {
        getFightLock().lock();
        try {
            choose(selectId - 1 + 30);
            eventOk();
        } catch (IOException e) {
            getFightLock().unlock();
            throw e;
        }
        getFightLock().unlock();
    }

    public void choose(int selectId) throws IOException {
        byte[] bytes = new byte[7];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.choose.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.choose.getId() & 0xFF);
        bytes[6] = (byte) selectId;
        send(bytes);
    }

    public void eventOk(int count) throws IOException {
        for (int i = 0; i < count; i++) {
            eventOk();
        }
    }

    /**
     * F4 44 02 00 1E 08
     */
    public void closeDialog() throws IOException {
        byte[] bytes = new byte[6];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.closeDialog.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.closeDialog.getId() & 0xFF);
        send(bytes);
    }

    /**
     * F4 44 02 00 14 06
     */
    public void eventOk() throws IOException {
        byte[] bytes = new byte[6];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.eventOk.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.eventOk.getId() & 0xFF);
        send(bytes);
    }

    /**
     * F4 44 02 00 49 06
     */
    public void event4906() throws IOException {
        byte[] bytes = new byte[6];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.event4906.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.event4906.getId() & 0xFF);
        send(bytes);
    }

    /**
     * F4 44 02 00 1F 01
     */
    public void eventU1F01() throws IOException {
        byte[] bytes = new byte[6];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.event1F01.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.event1F01.getId() & 0xFF);
        send(bytes);
    }

    /**
     * F4 44 04 00 17 03 01 01
     */
    public void throwAway(int index, int count) throws IOException {
        fightLock.lock();
        byte[] bytes = new byte[8];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.throwAway.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.throwAway.getId() & 0xFF);
        bytes[6] = (byte) index;
        bytes[7] = (byte) count;
        try {
            send(bytes);
        } catch (IOException e) {
            fightLock.unlock();
            throw e;
        }
        fightLock.unlock();
    }

    /**
     * F4 44 04 00 17 02 01 00
     * F4 44 04 00 17 02 03 00
     */
    public void pickUp(int index) throws IOException {
        byte[] bytes = new byte[8];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.pickUp.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.pickUp.getId() & 0xFF);
        bytes[6] = (byte) index;
        bytes[7] = 0x00;
        send(bytes, false);
    }

    public void pickUpAll() throws IOException {
        for (int i = 0; i <= 255; i++) {
            pickUp(i);
        }
    }

    /**
     * 拆分物品
     * 0  1        4  5  6  7  8
     * F4 44 05 00 17 0A 04 04 01
     * F4 44 05 00 17 0A 07 06 02
     */
    public void splitItem(int fromIndex, int toIndex, int count) throws IOException {
        byte[] bytes = new byte[9];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.splitItem.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.splitItem.getId() & 0xFF);
        bytes[6] = (byte) fromIndex;
        bytes[7] = (byte) count;
        bytes[8] = (byte) toIndex;
        send(bytes);
    }

    /**
     * 装备物品
     * 00          4  5  6
     * F4 44 03 00 17 0B 0D
     * <p/>
     * F4 44 03 00 17 0B 08
     */
    public void equipItem(int index) throws IOException {
        byte[] bytes = new byte[7];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.equipItem.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.equipItem.getId() & 0xFF);
        bytes[6] = (byte) index;
        send(bytes);
    }

    public void equipItemWithId(int itemId) throws IOException {
        int equipIndex = -1;
        for (int i = 1; i <= 25; i++) {
            BagItem bagItem = bagItems[i];
            if (bagItem == null) {
                continue;
            }
            if (bagItem.getId() == itemId) {
                equipIndex = i;
            }
        }
        if (equipIndex == -1) {
            return;
        }
        equipItem(equipIndex);
    }


    /**
     * 卸下物品
     * 00          4  5  6
     * F4 44 04 00 17 0C 01 12 头
     * F4 44 04 00 17 0C 02 15 衣
     * F4 44 04 00 17 0C 03 14 武器
     * F4 44 04 00 17 0C 04 13 手
     * F4 44 04 00 17 0C 05 16 鞋
     * F4 44 04 00 17 0C 06 17 饰品
     */
    public void unequipItem(int equipType, int bagIndex) throws IOException {
        byte[] bytes = new byte[8];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.unequipItem.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.unequipItem.getId() & 0xFF);
        bytes[6] = (byte) equipType;
        bytes[7] = (byte) bagIndex;
        send(bytes);
    }

    public void unequipItem(int equipType) throws IOException {
        int emptyIndex = -1;
        for (int i = 1; i <= 25; i++) {
            BagItem bagItem = bagItems[i];
            if (bagItem == null) {
                emptyIndex = i;
                break;
            }
        }
        if (emptyIndex == -1) {
            return;
        }
        unequipItem(equipType, emptyIndex);
    }

    /**
     * 武将装备物品
     * 0           4  5  6  7
     * F4 44 04 00 17 11 03 0D
     */
    public void equipItemPet(int petIndex, int itemIndex) throws IOException {
        byte[] bytes = new byte[8];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.equipItemPet.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.equipItemPet.getId() & 0xFF);
        bytes[6] = (byte) petIndex;
        bytes[7] = (byte) itemIndex;
        send(bytes);
    }

    public void unequipItemPet(int petIndex, int equipType) throws IOException {
        int emptyIndex = -1;
        for (int i = 1; i <= 25; i++) {
            BagItem bagItem = bagItems[i];
            if (bagItem == null) {
                emptyIndex = i;
                break;
            }
        }
        if (emptyIndex == -1) {
            return;
        }
        unequipItemPet(petIndex,equipType, emptyIndex);
    }

    /**
     * 武将卸下物品
     * 0           4  5  6  7
     * F4 44 05 00 17 12 02 02 10
     * F4 44 05 00 17 12 02 05 13
     */
    public void unequipItemPet(int petIndex, int equipType, int bagIndex) throws IOException {
        byte[] bytes = new byte[9];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.unequipItemPet.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.unequipItemPet.getId() & 0xFF);
        bytes[6] = (byte) petIndex;
        bytes[7] = (byte) equipType;
        bytes[8] = (byte) bagIndex;
        send(bytes);
    }

    /**
     * 0  1        4  5  6     8
     * F4 44 08 00 17 0E 12 01 13 01 00 00
     */
    public void compoundItem(int index1, int count1, int index2, int count2) throws IOException {
        byte[] bytes = new byte[12];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.compoundItem.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.compoundItem.getId() & 0xFF);
        bytes[6] = (byte) index1;
        bytes[7] = (byte) count1;
        bytes[8] = (byte) index2;
        bytes[9] = (byte) count2;
        try {
            send(bytes, false);
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 0  1        4  5  6     8
     * F4 44 08 00 17 0E 12 01 13 01 00 00
     * F4 44 08 00 17 0E 02 01 03 01 04 01
     */
    public void compoundItem(int index1, int count1, int index2, int count2, int index3, int count3) throws IOException {
        byte[] bytes = new byte[12];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.compoundItem.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.compoundItem.getId() & 0xFF);
        bytes[6] = (byte) index1;
        bytes[7] = (byte) count1;
        bytes[8] = (byte) index2;
        bytes[9] = (byte) count2;
        bytes[10] = (byte) index3;
        bytes[11] = (byte) count3;
        try {
            send(bytes, false);
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 使用物品
     * 0           4  5
     * F4 44 06 00 17 0F 03 21 00 00
     *
     * F4 44 06 00 17 0F 07 01 00 00
     * <p/>
     * F4 44 06 00 17 0F 11 02 03 00 给蒋济吃
     * <p/>
     * F4 44 06 00 17 0F 16 02 01 00 给巴豆妖吃
     */
    public void useItem(int index, int count, int petIndex) throws IOException {
        fightLock.lock();
        byte[] bytes = new byte[10];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.useItem.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.useItem.getId() & 0xFF);
        bytes[6] = (byte) index;
        bytes[7] = (byte) count;
        bytes[8] = (byte) petIndex;
        try {
            send(bytes);
        } catch (IOException e) {
            fightLock.unlock();
            throw e;
        }
        fightLock.unlock();
    }

    public void useItem(int index, int count) throws IOException {
        useItem(index, count, 0);
    }

    /**
     * 捐献物品
     * 0                 6  7  8  9
     * F4 44 08 00 27 0F 00 00 00 00 06 07
     */
    public void contributeItem(List<Integer> indexList) throws IOException {
        byte[] bytes = new byte[10 + indexList.size()];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.contributeItem.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.contributeItem.getId() & 0xFF);
        bytes[6] = 0;
        bytes[7] = 0;
        bytes[8] = 0;
        bytes[9] = 0;
        bytes[9] = 0;
        bytes[9] = 0;
        for (int i = 0; i < indexList.size(); i++) {
            int index = indexList.get(i);
            bytes[10 + i] = (byte) index;
        }
        send(bytes);
    }

    public void segmentMoveTo(int x, int y) throws IOException {
        segmentMoveTo(x, y, 1000);
    }

    public void segmentMoveTo(int x, int y, long sleep) throws IOException {
        if (point.getX() == x && point.getY() == y) {
            return;
        }
        if (!isLoginSuccess() && !isNewRole()) {
            this.waitForLoginSuccess();
        }
        int xSeg = (int) Math.ceil(Math.abs(x - point.getX()) * 1.0 / 400);
        int ySeg = (int) Math.ceil(Math.abs(y - point.getY()) * 1.0 / 400);
        int seg = Math.max(xSeg, ySeg);
        if (seg == 0) {
            return;
        }
        int xPos = (x - point.getX()) / seg;
        int yPos = (y - point.getY()) / seg;
        if (seg == 1) {
            moveTo(x, y);
        } else {
            for (int i = 1; i < seg; i++) {
                moveTo(point.getX() + xPos, point.getY() + yPos);
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            moveTo(x, y);
        }
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void moveTo(int x, int y) throws IOException {
        if (!isLoginSuccess() && !isNewRole()) {
            this.waitForLoginSuccess();
        }
        fightLock.lock();
        byte[] bytes = new byte[13];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.moveTo.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.moveTo.getId() & 0xFF);
        bytes[6] = 0x04;

        bytes[7] = (byte) (x & 0xFF);
        bytes[8] = (byte) (x >> 8 & 0xFF);

        bytes[9] = (byte) (y & 0xFF);
        bytes[10] = (byte) (y >> 8 & 0xFF);

        point.setX(x);
        point.setY(y);
        try {
            OutputUtil.output("移动到" + x + "," + y, this);
            send(bytes);
        } catch (IOException e) {
            fightLock.unlock();
            throw e;
        }
        fightLock.unlock();
    }

    public void moveTrigger(int index) throws IOException {
        byte[] bytes = new byte[8];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.moveTrigger.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.moveTrigger.getId() & 0xFF);
        bytes[6] = (byte) index;
        bytes[7] = 0x00;
        send(bytes);
    }

    public void move(int index) throws IOException {
        moveIndex = index;
        fightLock.lock();
        byte[] moveBytes = new byte[8];
        fillLeft(moveBytes);
        moveBytes[2] = (byte) (moveBytes.length - 4); //长度
        moveBytes[3] = 0x00;
        moveBytes[4] = (byte) (ProtocolConstant.move.getId() >> 8 & 0xFF);
        moveBytes[5] = (byte) (ProtocolConstant.move.getId() & 0xFF);
        moveBytes[6] = (byte) index;
        moveBytes[7] = 0x00;
        try {
            send(moveBytes);
        } catch (IOException e) {
            fightLock.unlock();
        }
        fightLock.unlock();
    }
    /**
     * F4 44 02 00 17 2D
     * F4 44 02 00 17 30
     * F4 44 02 00 0C 01
     * F4 44 04 00 14 08 04 00
     */
    public void shangtian() throws IOException {
        u1402();
        u1402();
        u1402();

        byte[] unknown1730Bytes = new byte[6];
        fillLeft(unknown1730Bytes);
        unknown1730Bytes[2] = (byte) (unknown1730Bytes.length - 4); //长度
        unknown1730Bytes[3] = 0x00;
        unknown1730Bytes[4] = (byte) (ProtocolConstant.unknown172D.getId() >> 8 & 0xFF);
        unknown1730Bytes[5] = (byte) (ProtocolConstant.unknown172D.getId() & 0xFF);
        send(unknown1730Bytes);

        this.moveAfter();
        this.move(4);
    }

    public void u1402() throws IOException {
        byte[] unknown1402Bytes = new byte[8];
        fillLeft(unknown1402Bytes);
        unknown1402Bytes[2] = (byte) (unknown1402Bytes.length - 4); //长度
        unknown1402Bytes[3] = 0x00;
        unknown1402Bytes[4] = (byte) (ProtocolConstant.unknown1402.getId() >> 8 & 0xFF);
        unknown1402Bytes[4] = (byte) (ProtocolConstant.unknown1402.getId() >> 8 & 0xFF);
        unknown1402Bytes[6] = (byte)0x02;
        send(unknown1402Bytes);
    }

    public void moveAfter() throws IOException {
        fightLock.lock();
        try {
            byte[] unknown1730Bytes = new byte[6];
            fillLeft(unknown1730Bytes);
            unknown1730Bytes[2] = (byte) (unknown1730Bytes.length - 4); //长度
            unknown1730Bytes[3] = 0x00;
            unknown1730Bytes[4] = (byte) (ProtocolConstant.unknown1730.getId() >> 8 & 0xFF);
            unknown1730Bytes[5] = (byte) (ProtocolConstant.unknown1730.getId() & 0xFF);
            send(unknown1730Bytes);

            byte[] unknown0C01Bytes = new byte[6];
            fillLeft(unknown0C01Bytes);
            unknown0C01Bytes[2] = (byte) (unknown0C01Bytes.length - 4); //长度
            unknown0C01Bytes[3] = 0x00;
            unknown0C01Bytes[4] = (byte) (ProtocolConstant.unknown0C01.getId() >> 8 & 0xFF);
            unknown0C01Bytes[5] = (byte) (ProtocolConstant.unknown0C01.getId() & 0xFF);
            send(unknown0C01Bytes);

            eventOk();
        } catch (IOException e) {
            fightLock.unlock();
        }
        fightLock.unlock();
    }

    public void simpleMove(int index) throws IOException {
        synchronized (moveFarObj) {
            move(index);
            try {
                moveFarObj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void simpleMove(int index, long waitTime) throws IOException {
        synchronized (moveFarObj) {
            move(index);
            try {
                moveFarObj.wait(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void moveFar(int toMapId) {
        if (!isLoginSuccess() && !isNewRole()) {
            this.waitForLoginSuccess();
        }
        if (currentMapId == 0) {
            return;
        }
        moveLock.lock();
        if (currentMapId == toMapId) {
            moveLock.unlock();
            return;
        }
        try {
            List<Integer> enterList = PathFindingUtil.getMapPath(currentMapId, toMapId);
            OutputUtil.output("moveFar:" + PathFindingUtil.showPath(currentMapId, enterList), this);
            for (Integer enter : enterList) {
                synchronized (moveFarObj) {
                    move(enter);
                    try {
                        moveFarObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    moveAfter();
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            if (this.getCurrentMapId() != toMapId) {
                moveFar(toMapId);
            }
        } catch (IOException e) {
            moveLock.unlock();
        }
        moveLock.unlock();
    }

    public void moveFar_old(int toMapId) {
        moveLock.lock();
        if (currentMapId == toMapId) {
            moveLock.unlock();
            return;
        }
        try {
            List<Integer> enterList = PathFindingUtil.getMapPath(currentMapId, toMapId);
            OutputUtil.output("moveFar:" + PathFindingUtil.showPath(currentMapId, enterList), this, true);
            for (Integer enter : enterList) {
                synchronized (moveFarObj) {
                    move(enter);
                    try {
                        moveFarObj.wait(50000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    moveAfter();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int retryCount = 10;
            while (this.getCurrentMapId() != toMapId) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                retryCount--;
                if (retryCount <= 0) {
                    break;
                }
            }
        } catch (IOException e) {
            moveLock.unlock();
        }
        moveLock.unlock();
    }

    public void moveFarInGame(int toMapId) {
        moveLock.lock();
        if (currentMapId == toMapId) {
            moveLock.unlock();
            return;
        }
        try {
            List<Integer> enterList = PathFindingUtil.getMapPath(currentMapId, toMapId);
            OutputUtil.output("moveFar:" + PathFindingUtil.showPath(currentMapId, enterList), this, true);
            for (Integer enter : enterList) {
                synchronized (moveFarObj) {
                    move(enter);
                    try {
                        moveFarObj.wait(50000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            moveLock.unlock();
        }
        moveLock.unlock();
    }

    /**
     * 第0个东西买4个
     * F4 44 05 00 1B 01 00 04 00
     * 第1个东西买3个
     * F4 44 05 00 1B 01 01 03 00
     */
    public void buyItem(int index, int count) throws IOException {
        byte[] bytes = new byte[9];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.buyItem.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.buyItem.getId() & 0xFF);
        bytes[6] = (byte) index;
        bytes[7] = (byte) count;
        send(bytes);
    }

    public void sell(int index, int count) throws IOException {
        byte[] bytes = new byte[8];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.sell.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.sell.getId() & 0xFF);
        bytes[6] = (byte) index;
        bytes[7] = (byte) count;
        send(bytes);
    }

    /**
     * F4 44 07 00 1E 01 01 02 03 04 05
     * F4 44 09 00 1E 01 01 02 0B 0C 0D 0E 0F
     */
    public void storeTake() {

    }

    /**
     * F4 44 04 00 1E 02 01 06
     * F4 44 06 00 1E 02 07 08 09 0A
     */
    public void storePut() {

    }

    public void genTradeItemIndexList(List<Integer> storeItemList){
        //登录成功,开始交易
        List<Integer> itemIndexList = new ArrayList<>();
        List<Integer> tradeItemIdList = new ArrayList<>();
        BagItem[] bagItems = this.getBagItems();
        for (int i = 1; i <= 25; i++) {
            BagItem bagItem = bagItems[i];
            if (bagItem == null) {
                continue;
            }
            if (storeItemList.contains(bagItem.getId()) && !tradeItemIdList.contains(bagItem.getId())) {
                itemIndexList.add(i);
                tradeItemIdList.add(bagItem.getId());
            }
        }
        this.setTradeItemIndexList(itemIndexList);
    }

    /**
     * 0  1              6
     * F4 44 06 00 19 01 40 BE 01 00
     */
    public void tradeRequest(long userId) throws IOException {
        byte[] bytes = new byte[10];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.tradeRequest.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.tradeRequest.getId() & 0xFF);
        bytes[6] = (byte) ((userId >> 0) & 0xFF);
        bytes[7] = (byte) ((userId >> 8) & 0xFF);
        bytes[8] = (byte) ((userId >> 16) & 0xFF);
        bytes[9] = (byte) ((userId >> 24) & 0xFF);
        send(bytes);
    }

    //F4 44 06 00 19 0A D3 C7 01 00
    public void tradePetRequest(long userId) throws IOException {
        byte[] bytes = new byte[10];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.tradePetRequest.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.tradePetRequest.getId() & 0xFF);
        bytes[6] = (byte) ((userId >> 0) & 0xFF);
        bytes[7] = (byte) ((userId >> 8) & 0xFF);
        bytes[8] = (byte) ((userId >> 16) & 0xFF);
        bytes[9] = (byte) ((userId >> 24) & 0xFF);
        send(bytes);
    }

    /**
     * 0  1              6
     * F4 44 07 00 19 02 00 00 00 00 03
     * <p/>
     * F4 44 09 00 19 02 00 00 00 00 01 02 05
     * <p/>
     * 交易金币
     * F4 44 06 00 19 02 20 AA 44 00
     */
    public void tradeItem(List<Integer> tradeItemIndexList) throws IOException {
        byte[] bytes = new byte[10 + tradeItemIndexList.size()];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.tradeItem.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.tradeItem.getId() & 0xFF);
        bytes[6] = 0;
        bytes[7] = 0;
        bytes[8] = 0;
        bytes[9] = 0;
        for (int i = 0; i < tradeItemIndexList.size(); i++) {
            int index = tradeItemIndexList.get(i);
            bytes[10 + i] = (byte) index;
        }
        send(bytes);
    }

    public void tradeItem(long money, List<Integer> tradeItemIndexList) throws IOException {
        byte[] bytes = new byte[10 + tradeItemIndexList.size()];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.tradeItem.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.tradeItem.getId() & 0xFF);
        bytes[6] = (byte) ((money >> 0) & 0xFF);
        bytes[7] = (byte) ((money >> 8) & 0xFF);
        bytes[8] = (byte) ((money >> 16) & 0xFF);
        bytes[9] = (byte) ((money >> 24) & 0xFF);
        for (int i = 0; i < tradeItemIndexList.size(); i++) {
            int index = tradeItemIndexList.get(i);
            bytes[10 + i] = (byte) index;
        }
        send(bytes);
    }

    /**
     * 交易金币
     * F4 44 06 00 19 02 20 AA 44 00
     */
    public void tradeMoney(long money) throws IOException {
        byte[] bytes = new byte[10];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.tradeItem.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.tradeItem.getId() & 0xFF);
        bytes[6] = (byte) ((money >> 0) & 0xFF);
        bytes[7] = (byte) ((money >> 8) & 0xFF);
        bytes[8] = (byte) ((money >> 16) & 0xFF);
        bytes[9] = (byte) ((money >> 24) & 0xFF);
        send(bytes);
    }

    //F4 44 07 00 19 0B 00 00 00 00 04
    public void tradePet(int index) throws IOException {
        byte[] bytes = new byte[11];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.tradePet.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.tradePet.getId() & 0xFF);
        bytes[6] = 0;
        bytes[7] = 0;
        bytes[8] = 0;
        bytes[9] = 0;
        bytes[10] = (byte) index;
        send(bytes);
    }

    /**
     * F4 44 03 00 19 03 01
     */
    public void tradeConfirm() throws IOException {
        byte[] bytes = new byte[7];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.tradeConfirm.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.tradeConfirm.getId() & 0xFF);
        bytes[6] = 0x01;
        send(bytes);
    }

    //F4 44 03 00 19 0C 01
    public void tradePetConfirm() throws IOException {
        byte[] bytes = new byte[7];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.tradePetConfirm.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.tradePetConfirm.getId() & 0xFF);
        bytes[6] = 0x01;
        send(bytes);
    }

    public void joinTeam(long userId) throws IOException {
        byte[] bytes = new byte[10];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.joinTeam.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.joinTeam.getId() & 0xFF);
        bytes[6] = (byte) ((userId >> 0) & 0xFF);
        bytes[7] = (byte) ((userId >> 8) & 0xFF);
        bytes[8] = (byte) ((userId >> 16) & 0xFF);
        bytes[9] = (byte) ((userId >> 24) & 0xFF);
        send(bytes);
    }

    /**
     * 01接受 02拒绝
     * yn
     * F4 44 07 00 0D 03 02 C2 BA 00 00
     */
    public void acceptTeam(long userId) throws IOException {
        byte[] bytes = new byte[11];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.acceptTeam.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.acceptTeam.getId() & 0xFF);
        bytes[6] = 0x01;
        bytes[7] = (byte) ((userId >> 0) & 0xFF);
        bytes[8] = (byte) ((userId >> 8) & 0xFF);
        bytes[9] = (byte) ((userId >> 16) & 0xFF);
        bytes[10] = (byte) ((userId >> 24) & 0xFF);
        send(bytes);
    }

    /**
     * 退出队伍
     * F4 44 06 00 0D 04 C3 BA 00 00
     * F4 44 06 00 0D 04 C1 BA 00 00
     */
    public void exitTeam() throws IOException {
        byte[] bytes = new byte[10];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.exitTeam.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.exitTeam.getId() & 0xFF);
        bytes[6] = (byte) ((userId >> 0) & 0xFF);
        bytes[7] = (byte) ((userId >> 8) & 0xFF);
        bytes[8] = (byte) ((userId >> 16) & 0xFF);
        bytes[9] = (byte) ((userId >> 24) & 0xFF);
        send(bytes);
    }

    public void confirmTeamAdviser(long userId) throws IOException {
        byte[] bytes = new byte[10];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.confirmTeamAdviser.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.confirmTeamAdviser.getId() & 0xFF);
        bytes[6] = (byte) ((userId >> 0) & 0xFF);
        bytes[7] = (byte) ((userId >> 8) & 0xFF);
        bytes[8] = (byte) ((userId >> 16) & 0xFF);
        bytes[9] = (byte) ((userId >> 24) & 0xFF);
        send(bytes);
    }

    /**
     * F4 44 06 00 0F 04 73 46 00 00
     */
    public void shangma(long horseId) throws IOException {
        byte[] bytes = new byte[10];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.shangma.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.shangma.getId() & 0xFF);
        bytes[6] = (byte) ((horseId >> 0) & 0xFF);
        bytes[7] = (byte) ((horseId >> 8) & 0xFF);
        bytes[8] = (byte) ((horseId >> 16) & 0xFF);
        bytes[9] = (byte) ((horseId >> 24) & 0xFF);
        send(bytes);
    }

    //F4 44 06 00 13 01 DB B0 00 00
    public void chuzhan(int npcId) throws IOException {
        byte[] bytes = new byte[10];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.chuzhan.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.chuzhan.getId() & 0xFF);
        bytes[6] = (byte) (npcId & 0xFF);
        bytes[7] = (byte) (npcId >> 8 & 0xFF);
        bytes[8] = (byte) (npcId >> 16 & 0xFF);
        bytes[9] = (byte) (npcId >> 24 & 0xFF);
        send(bytes);
    }

    //F4 44 02 00 13 02
    public void wujaingxiuxi() throws IOException {
        byte[] bytes = new byte[6];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.wujaingxiuxi.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.wujaingxiuxi.getId() & 0xFF);
        send(bytes);
    }


    /**
     * F4 44 0A 00 32 01 03 02 00 02 E9 32 45 DE
     * F4 44 0A 00 32 02 02 02 03 02 1E 67 04 F0
     * 霸王酒
     * <p/>
     * F4 44 0A 00 32 01 03 02 00 02 10 27 29 ED
     * ô  D        2                    '  )  í
     * F4 44 0A 00 32 01 03 02 00 02 10 27 00 0E
     * ô  D        2                    '
     * F4 44 0A 00 32 01 03 02 00 02 10 27 18 64
     * ô  D        2                    '     d
     * F4 44 0A 00 32 01 03 02 00 02 10 27 76 C3
     * 0  1  2  3  4  5  6  7  8  9
     */
    public void fight(Position fromPosition, Position toPosition, int skillId) throws IOException {
        byte[] bytes = new byte[14];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.fight.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.fight.getId() & 0xFF);
        bytes[6] = (byte) fromPosition.getRow();
        bytes[7] = (byte) fromPosition.getColumn();
        bytes[8] = (byte) toPosition.getRow();//第几排
        bytes[9] = (byte) toPosition.getColumn();//第几个
        bytes[10] = (byte) (skillId & 0xFF);
        bytes[11] = (byte) (skillId >> 8 & 0xFF);
        send(bytes);
    }

    /**
     * 0                 6  7  8  9
     * F4 44 0A 00 32 02 02 02 03 02 1E 67 04 F0
     * 霸王酒
     */
    public void fightUseItem(Position fromPosition, Position toPosition, int itemId) throws IOException {
        byte[] bytes = new byte[14];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.fightUseItem.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.fightUseItem.getId() & 0xFF);
        bytes[6] = (byte) fromPosition.getRow();
        bytes[7] = (byte) fromPosition.getColumn();
        bytes[8] = (byte) toPosition.getRow();//第几排
        bytes[9] = (byte) toPosition.getColumn();//第几个
        bytes[10] = (byte) (itemId & 0xFF);
        bytes[11] = (byte) (itemId >> 8 & 0xFF);
        send(bytes);
    }

    public void fight(int fromRow, int fromColumn, int toRow, int toColumn, int skillId) throws IOException {
        Position fromPosition = new Position(fromRow, fromColumn);
        Position toPosition = new Position(toRow, toColumn);
        fight(fromPosition, toPosition, skillId);
    }

    //F4 44 02 00 10 21
    public void u1021() throws IOException {
        byte[] bytes = new byte[6];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.u1021.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.u1021.getId() & 0xFF);
        send(bytes);
    }

    //F4 44 03 00 20 02 0A
    //F4 44 03 00 20 02 3A
    public void u2002(int number) throws IOException {
        byte[] bytes = new byte[7];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.u1021.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.u1021.getId() & 0xFF);
        bytes[6] = (byte) number;
        send(bytes);
    }

    /**
     * 11004清流
     * F4 44 0B 00 17 14 02 FC 2A 02 00 1F 6A 00 00
     * *                     清流        ID
     * F4 44 0B 00 17 14 02 FC 2A 02 00 76 A2 00 00
     * ô  D                 ü  *        v  ¢
     * F4 44 0B 00 17 14 02 FC 2A 02 00 C4 79 01 00
     * ô  D                 ü  *        Ä  y
     * F4 44 0B 00 17 14 02 FC 2A 04 03 5D 46 00 00 //汗血
     * ô  D                 ü  *        ]  F
     * F4 44 0B 00 17 14 02 FC 2A 04 02 0D 56 00 00 //背包
     * 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14
     * itemId
     * F4 44 0B 00 17 14 01 58 67 04 02 0F 56 00 00
     */
    public void useSkill(int skillId, boolean isPet, int petIndex, long id) throws IOException {
        byte[] bytes = new byte[15];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.useSkill.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.useSkill.getId() & 0xFF);
        bytes[6] = 0x02;
        bytes[7] = (byte) (skillId & 0xFF);
        bytes[8] = (byte) ((skillId >> 8) & 0xFF);
        if (isPet) {
            bytes[9] = 0x04;
            bytes[10] = (byte) petIndex;
        } else {
            bytes[9] = 0x02;
            bytes[10] = 0;
        }
        bytes[11] = (byte) ((id >> 0) & 0xFF);
        bytes[12] = (byte) ((id >> 8) & 0xFF);
        bytes[13] = (byte) ((id >> 16) & 0xFF);
        bytes[14] = (byte) ((id >> 24) & 0xFF);
        send(bytes);
    }

    /*
     * 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14
     *                      itemId
     * F4 44 0B 00 17 14 01 58 67 04 02 0F 56 00 00
     */
    public void useItemPet(int itemId, int petIndex, int id) throws IOException {
        byte[] bytes = new byte[15];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.useSkill.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.useSkill.getId() & 0xFF);
        bytes[6] = 0x01;
        bytes[7] = (byte) (itemId >> 8 & 0xFF);
        bytes[8] = (byte) (itemId & 0xFF);
        bytes[9] = 0x04;
        bytes[10] = (byte) petIndex;
        bytes[11] = (byte) ((id >> 0) & 0xFF);
        bytes[12] = (byte) ((id >> 8) & 0xFF);
        bytes[13] = (byte) ((id >> 16) & 0xFF);
        bytes[14] = (byte) ((id >> 24) & 0xFF);
        send(bytes);
    }

    //F4 44 06 00 10 01 01 00 00 00
    public void answer(int index) throws IOException {
        byte[] bytes = new byte[10];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.answer.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.answer.getId() & 0xFF);
        bytes[6] = (byte) index;
        send(bytes);
    }

    /**
     * 0  1        4  5
     * F4 44 0B 00 41 01 43 41 44 42 00 00 00 00 00
     */
    public void openJiguan() throws IOException {
        byte[] bytes = new byte[15];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.openJiguan.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.openJiguan.getId() & 0xFF);
        bytes[6] = 0x43;
        bytes[7] = 0x41;
        bytes[8] = 0x44;
        bytes[9] = 0x42;
        send(bytes);
    }

    //F4 44 02 00 41 02
    public void pauseJiguan() throws IOException {
        byte[] bytes = new byte[6];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.pauseJiguan.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.pauseJiguan.getId() & 0xFF);
        send(bytes);
    }

    //F4 44 02 00 42 03
    public void unknowOpenJiguan() throws IOException {
        byte[] bytes = new byte[6];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.openJiguan.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.openJiguan.getId() & 0xFF);
        send(bytes);
    }

    /**
     * 0            4  5  6  7  8  9
     * F4 44 06 00 1C 02 04 F9 2E 02
     */
    public void addSkill(int petIndex, int skillId, int level) throws IOException {
        byte[] bytes = new byte[10];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.addSkill.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.addSkill.getId() & 0xFF);
        bytes[6] = (byte) petIndex;
        //12025
        bytes[7] = (byte) (skillId & 0xFF);
        bytes[8] = (byte) (skillId >> 8 & 0xFF);
        bytes[9] = (byte) level;
        send(bytes);
    }

    /**
     * 0            4  5  6  7  8  9
     * F4 44 0A 00 08 01 00 00 1F 01 00 00 00 00 1体
     * F4 44 0A 00 08 01 00 00 1F 02 00 00 00 00 2体
     * F4 44 0A 00 08 01 00 00 1F 03 00 00 00 00 3体
     * <p/>
     * F4 44 0A 00 08 01 00 00 1B 01 00 00 00 00 1智
     * F4 44 0A 00 08 01 00 00 1C 01 00 00 00 00 1攻
     * F4 44 0A 00 08 01 00 00 1D 01 00 00 00 00 1防
     * F4 44 0A 00 08 01 00 00 20 01 00 00 00 00 1能
     * F4 44 0A 00 08 01 00 00 1E 01 00 00 00 00 1敏
     */
    public void addShuxing(int sxType, int count) throws IOException {
        byte[] bytes = new byte[14];
        fillLeft(bytes);
        bytes[2] = (byte) (bytes.length - 4); //长度
        bytes[3] = 0x00;
        bytes[4] = (byte) (ProtocolConstant.addShuxing.getId() >> 8 & 0xFF);
        bytes[5] = (byte) (ProtocolConstant.addShuxing.getId() & 0xFF);
        bytes[8] = (byte) sxType;
        bytes[9] = (byte) count;
        send(bytes);
    }

    public void fire(String eventName) throws IOException {
        fire(eventName, null);
    }

    public void fire(String eventName, Context context) throws IOException {
        BaseListener listener = listenerMap.get(eventName);
        if (listener != null) {
            listener.doAction(this, context);
        }
    }

    private void fillLeft(byte[] bytes) {
        bytes[0] = (byte) 0xF4;
        bytes[1] = (byte) 0x44;
    }

    public void send(byte[] bytes, boolean isShowSendBytes) throws IOException {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] ^ 0xAD);
        }
        if (isShowSendBytes) {
            OutputUtil.output(userId + " req:" + ShowUtil.show(bytes), this, true);
        }
        OutputStream os = outputStream;
        os.write(bytes);
        os.flush();
    }

    public void send(byte[] bytes) throws IOException {
        try {
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (bytes[i] ^ 0xAD);
            }
            OutputUtil.output(userId + "req:" + ShowUtil.show(bytes), this, consoleOutput);
            OutputStream os = outputStream;
            os.write(bytes);
            os.flush();
        } catch (IOException e) {
            this.clear();
            throw e;
        }
    }

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void simpleSleep() {
        this.sleep(2000);
        while (this.isFighting()) {
            this.sleep(3000);
        }
        this.sleep(2000);
    }

    public void simpleSleep2() {
        this.sleep(2000);
        while (this.isFighting()) {
            this.sleep(1000);
        }
    }

    public void simpleSleep3() {
        while (this.isFighting()) {
            this.sleep(100);
        }
    }

    public boolean waitForLoginSuccess() {
        if (isLoginSuccess()) {
            return true;
        }
        while (!isLoginSuccess()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(isClose) {
                return false;
            }
        }
        OutputUtil.output("waitForLoginSuccess登录成功", this, true);
        return true;
    }

    public void waitJoinTeam() {
        GameClient gameClient = this;
        if (gameClient.getGameTeam() == null || gameClient.getGameTeam().getGameClientList() == null) {
            return;
        }
        gameClient.waitForLoginSuccess();
        List<GameClient> teamUserList = gameClient.getGameTeam().getGameClientList();
        boolean isZJGC = false;
        for (GameClient teamUser : teamUserList) {
            teamUser.waitForLoginSuccess();
            if (teamUser.getCurrentMapId() == 12003) {
                isZJGC = true;
            }
            if(gameClient.getCurrentMapId() != teamUser.getCurrentMapId()) {
                isZJGC = true;
            }
        }
        if(isZJGC) {
            try {
                if(gameClient.getCurrentMapId() > 13000) {
                    gameClient.move(255);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (GameClient teamUser : teamUserList) {
                try {
                    if(teamUser.getCurrentMapId() > 13000) {
                        teamUser.move(255);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameClient.waitForLoginSuccess();
        for (GameClient teamUser : teamUserList) {
            teamUser.waitForLoginSuccess();
            if (teamUser.getCurrentMapId() != gameClient.getCurrentMapId()) {
                teamUser.moveFar(gameClient.getCurrentMapId());
            }
        }

        //组队
        while (gameClient.getTeamUsers().size() != gameClient.getAllowTeamUsers().size()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            QingliuAi.qingliu(gameClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBag() {
        StringBuilder bagString = new StringBuilder();
        for (int i = 1; i <= 25; i++) {
            BagItem bagItem = bagItems[i];
            if (bagItem == null) {
                continue;
            }
            bagString.append(i + " " + bagItems[i].getId() + "(" + ItemIdUtil.getItemName(bagItems[i].getId()) + ") " + bagItems[i].getCount() + ";");
        }
        OutputUtil.output("物品加载完毕:" + bagString.toString(), this);
    }

    public void autoUseAndThrow() {
        if (userId == 114244) {
            return;
        }
        if (!isLoginSuccess()) {
            return;
        }
        BagItem[] bagItems = this.getBagItems();
        if (bagItems != null) {
            for (int i = 0; i < bagItems.length; i++) {
                BagItem bagItem = bagItems[i];
                if (bagItem == null) {
                    continue;
                }
                if (bagItem.getCount() == 0) {
                    continue;
                }
                if (this.getGameConfig().getJiguanchongIdList().contains(bagItem.getId())) {
                    //46397,46398,46399,46400
                    if (this.getRoleInfo().getType() + 46397 - 1 == bagItem.getId()) {
                        OutputUtil.output("使用机关宠" + bagItem.getId(), this, true);
                        try {
                            this.useItem(i, 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            this.openJiguan();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        OutputUtil.output("吃完机关宠，打开机关盒!", this, true);
                    } else {
                        OutputUtil.output("丢弃机关宠" + bagItem.getId(), this, true);
                        try {
                            this.throwAway(i, bagItem.getCount());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if (this.getGameConfig().getAutoThrowItemIdList().contains(bagItem.getId())) {
                    OutputUtil.output("自动扔掉物品" + i + " " + bagItem.getId() + "(" + ItemIdUtil.getItemName(bagItem.getId()) + ")" + " " + bagItem.getCount(), this, true);
                    try {
                        this.throwAway(i, bagItem.getCount());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            for (int i = 0; i < bagItems.length; i++) {
                BagItem bagItem = bagItems[i];
                if (bagItem == null) {
                    continue;
                }
                if (bagItem.getCount() == 0) {
                    continue;
                }
                if (this.getGameConfig().getAutoUseItemIdList().contains(bagItem.getId())) {
                    OutputUtil.output("自动使用物品" + i + " " + bagItem.getId() + " " + bagItem.getCount(), this, true);
                    try {
                        this.useItem(i, bagItem.getCount());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            int bdyllCount = 0;
            for (int i = 0; i < bagItems.length; i++) {
                BagItem bagItem = bagItems[i];
                if (bagItem == null) {
                    continue;
                }
                if (bagItem.getCount() == 0) {
                    continue;
                }
                if(bagItem.getId() == 26456) {
                    bdyllCount++;
                }
            }
            if(bdyllCount > 2) {
                int tempIndex = 0;
                for (int i = 0; i < bagItems.length; i++) {
                    BagItem bagItem = bagItems[i];
                    if (bagItem == null) {
                        continue;
                    }
                    if (bagItem.getCount() == 0) {
                        continue;
                    }
                    if(bagItem.getId() == 26456) {
                        tempIndex++;
                        if(tempIndex > 2) {
                            OutputUtil.output("自动扔掉物品" + i + " " + bagItem.getId() + "(" + ItemIdUtil.getItemName(bagItem.getId()) + ")" + " " + bagItem.getCount(), this, true);
                            try {
                                this.throwAway(i, bagItem.getCount());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public int getPetCount() {
        int count = 0;
        for (int i = 0; i < getPetList().length; i++) {
            RoleInfo pet = getPetList()[i];
            if (pet == null) {
                continue;
            }
            count++;
        }
        return count;
    }

    public int getPetIndex(int npcId) {
        int petIndex = -1;
        for (int i = 0; i < getPetList().length; i++) {
            RoleInfo pet = getPetList()[i];
            if (pet == null) {
                continue;
            }
            if (pet.getId() == npcId) {
                petIndex = i;
                break;
            }
        }
        return petIndex;
    }

    public int getItemCount(int zhiId) {
        GameClient gameClient = this;
        int itemCount = 0;
        for (int i = 1; i <= 25; i++) {
            BagItem bagItem = gameClient.getBagItems()[i];
            if (bagItem == null) {
                continue;
            }
            if (bagItem.getCount() == 0) {
                continue;
            }
            if (bagItem.getId() == zhiId) {
                itemCount += bagItem.getCount();
            }
        }
        return itemCount;
    }

    public void refreshMainFrame() {
        refreshMainFrame(null);
    }

    public void refreshMainFrame(String str) {
        GameClient gameClient = this;
        if(gameClient.getTeamLeaderId() == gameClient.getUserId()) {
            if(gameClient.getGameTeam() != null) {
                gameClient.getGameTeam().refreshMainFrame(str);
            }
        }
    }

    public void execute(String cmd) {
        execute(cmd.split(" "));
    }

    public void execute(String[] cmd) {
        String methodName = cmd[0];
        int paramCount = cmd.length - 1;
        for (Method method : this.getClass().getMethods()) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (method.getName().equals(methodName) && parameterTypes.length == paramCount) {
                Object[] args = new Object[parameterTypes.length];
                for (int i = 0; i < parameterTypes.length; i++) {
                    String param = cmd[1 + i];
                    Class parameterType = parameterTypes[i];
                    if (parameterType.equals(int.class) || parameterType.equals(Integer.class)) {
                        args[i] = Integer.parseInt(param);
                    } else if (parameterType.equals(long.class) || parameterType.equals(Long.class)) {
                        args[i] = Long.parseLong(param);
                    } else {
                        args[i] = param;
                    }
                }
                try {
                    method.invoke(this, args);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BagItem[] getBagItems() {
        return bagItems;
    }

    public void setBagItems(BagItem[] bagItems) {
        this.bagItems = bagItems;
    }

    public boolean isNewRole() {
        return isNewRole;
    }

    public void setNewRole(boolean isNewRole) {
        this.isNewRole = isNewRole;
    }

    public void doScriptAction(ScriptAction scriptAction) throws IOException {
        scriptAction.doAction(this);
    }

    public void doDoDoScriptAction(String scriptString) throws IOException {
        DoDoScriptAction doDoScriptAction = new DoDoScriptAction(scriptString);
        try {
            this.doScriptAction(doDoScriptAction);
        } catch (Exception e) {
            e.printStackTrace();
            OutputUtil.output("脚本执行异常：" + scriptString, this, true);
        }
    }

    public void doStepsDoDoScriptAction(String steps) throws IOException {
        for(String step:steps.split("\n")) {
            this.doDoDoScriptAction(step);
        }
    }

    public int getCurrentMapId() {
        return currentMapId;
    }

    public void setCurrentMapId(int currentMapId) {
        this.currentMapId = currentMapId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (!username.trim().toLowerCase().startsWith("wp") && !username.trim().toLowerCase().startsWith("wn")) {
            username = "wp" + username.trim();
        }
        this.username = username;
        this.userId = Long.parseLong(username.substring(2));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPreNickname(String preNickname) {
        this.preNickname = preNickname;
    }

    public String getPreNickname() {
        return preNickname;
    }


    public Map<String, String> getTarget() {
        return target;
    }

    public void setTarget(Map<String, String> target) {
        this.target = target;
    }

    public Map<String, BaseListener> getListenerMap() {
        return listenerMap;
    }

    public void setListenerMap(Map<String, BaseListener> listenerMap) {
        this.listenerMap = listenerMap;
    }

    public Set<Long> getTeamUsers() {
        return teamUsers;
    }

    public void setTeamUsers(Set<Long> teamUsers) {
        this.teamUsers = teamUsers;
    }

    public long getTeamLeaderId() {
        return teamLeaderId;
    }

    public void setTeamLeaderId(long teamLeaderId) {
        this.teamLeaderId = teamLeaderId;
    }

    public Lock getFightLock() {
        return fightLock;
    }

    public void setFightLock(Lock fightLock) {
        this.fightLock = fightLock;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isFighting() {
        return fighting;
    }

    public void setFighting(boolean fighting) {
        this.fighting = fighting;
    }

    public Lock getMoveLock() {
        return moveLock;
    }

    public void setMoveLock(Lock moveLock) {
        this.moveLock = moveLock;
    }

    public boolean isReConnect() {
        return reConnect;
    }

    public void setReConnect(boolean reConnect) {
        this.reConnect = reConnect;
    }

    public boolean isJoinTeam() {
        return isJoinTeam;
    }

    public void setJoinTeam(boolean isJoinTeam) {
        this.isJoinTeam = isJoinTeam;
    }

    public List<Long> getAllowTeamUsers() {
        return allowTeamUsers;
    }

    public void setAllowTeamUsers(List<Long> allowTeamUsers) {
        this.allowTeamUsers = allowTeamUsers;
    }

    public int getInitMapId() {
        return initMapId;
    }

    public void setInitMapId(int initMapId) {
        this.initMapId = initMapId;
    }

    public GameTeam getGameTeam() {
        return gameTeam;
    }

    public void setGameTeam(GameTeam gameTeam) {
        this.gameTeam = gameTeam;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Object getMoveFarObj() {
        return moveFarObj;
    }

    public RoleInfo getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(RoleInfo roleInfo) {
        this.roleInfo = roleInfo;
    }

    public FightInfo getFightInfo() {
        return fightInfo;
    }

    public void setFightInfo(FightInfo fightInfo) {
        this.fightInfo = fightInfo;
    }

    public GameConfig getGameConfig() {
        return gameConfig;
    }

    public void setGameConfig(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }


    public RoleInfo getPet() {
        return pet;
    }

    public void setPet(RoleInfo pet) {
        this.pet = pet;
    }

    public RoleInfo[] getPetList() {
        return petList;
    }

    public void setPetList(RoleInfo[] petList) {
        this.petList = petList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setConsoleOutput(boolean consoleOutput) {
        this.consoleOutput = consoleOutput;
    }

    public boolean isConsoleOutput() {
        return consoleOutput;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public SocketReadThread getSocketReadThread() {
        return socketReadThread;
    }

    public void setSocketReadThread(SocketReadThread socketReadThread) {
        this.socketReadThread = socketReadThread;
    }

    public boolean isFileOutput() {
        return fileOutput;
    }

    public void setFileOutput(boolean fileOutput) {
        this.fileOutput = fileOutput;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public boolean isLoginSuccess() {
        return isLoginSuccess;
    }

    public void setLoginSuccess(boolean isLoginSuccess) {
        this.isLoginSuccess = isLoginSuccess;
    }

    public Queue<Integer> getChooseQueue() {
        return chooseQueue;
    }

    public void setChooseQueue(Queue<Integer> chooseQueue) {
        this.chooseQueue = chooseQueue;
    }

    public List<Integer> getTradeItemIndexList() {
        return tradeItemIndexList;
    }

    public void setTradeItemIndexList(List<Integer> tradeItemIndexList) {
        this.tradeItemIndexList = tradeItemIndexList;
    }

    public boolean isTeamLeader() {
        return isTeamLeader;
    }

    public void setTeamLeader(boolean isTeamLeader) {
        this.isTeamLeader = isTeamLeader;
    }

    public Date getFightTime() {
        return fightTime;
    }

    public void setFightTime(Date fightTime) {
        this.fightTime = fightTime;
    }

    public boolean isInGame() {
        return isInGame;
    }

    public void setInGame(boolean isInGame) {
        this.isInGame = isInGame;
    }

    public BagItem[] getEquipItems() {
        return equipItems;
    }

    public void setEquipItems(BagItem[] equipItems) {
        this.equipItems = equipItems;
    }

    public BagItem[] getSceneItems() {
        return sceneItems;
    }

    public void setSceneItems(BagItem[] sceneItems) {
        this.sceneItems = sceneItems;
    }

    public long getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(long tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public int getMoveIndex() {
        return moveIndex;
    }

    public void setMoveIndex(int moveIndex) {
        this.moveIndex = moveIndex;
    }

    public String getServerPrefix() {
        return serverPrefix;
    }

    public void setServerPrefix(String serverPrefix) {
        this.serverPrefix = serverPrefix;
    }

    public int getServerIndex() {
        return serverIndex;
    }

    public void setServerIndex(int serverIndex) {
        this.serverIndex = serverIndex;
    }

    public boolean isStartFightMonitorThread() {
        return isStartFightMonitorThread;
    }

    public void setStartFightMonitorThread(boolean isStartFightMonitorThread) {
        this.isStartFightMonitorThread = isStartFightMonitorThread;
    }

    public boolean isStartGetItemMonitorThread() {
        return isStartGetItemMonitorThread;
    }

    public void setStartGetItemMonitorThread(boolean isStartGetItemMonitorThread) {
        this.isStartGetItemMonitorThread = isStartGetItemMonitorThread;
    }

    public boolean isClose() {
        return isClose;
    }

    public void setClose(boolean isClose) {
        this.isClose = isClose;
    }

    public Date getGetItemTime() {
        return getItemTime;
    }

    public void setGetItemTime(Date getItemTime) {
        this.getItemTime = getItemTime;
    }

    public GameParam getGameParam() {
        return gameParam;
    }

    public void setGameParam(GameParam gameParam) {
        this.gameParam = gameParam;
    }


}
