package tk.gbl.constant;

/**
 * Date: 2017/5/4
 * Time: 17:00
 *
 * @author Tian.Dong
 */
public enum ChatCommandConstant {
    givemesomemoney("givemesomemoney"),
    givemeallmoney("givemeallmoney"),
    //蛮兵
    givememanbing("givememanbing"),
    //许干
    givemexugan("givemexugan"),
    //卑枸
    givemebeigou("givemebeigou"),
    //2个石田螺
    givemeshitianluo("givemeshitianluo"),
    //5个黑松木
    givemeheisongmu("givemeheisongmu"),
    //5个轻染布
    givemeqingranbu("givemeqingranbu"),
    //1个硬厚皮
    givemeyinghoupi("givemeyinghoupi"),
    //14个天竹纸
    givemetianzhuzhi("givemetianzhuzhi"),

    //治傷丸x3
    givemezhishangwan("givemezhishangwan"),

    //將軍酒x3
    givemejiangjunjiu("givemejiangjunjiu"),

    //5 扬铜屑
    givemeyangtongxie("givemeyangtongxie"),

    //5 烙铜屑
    givemelaotongxie("givemelaotongxie"),

    //1 栖凤木
    givemeqifengmu("givemeqifengmu"),

    //1 山越樟木
    givemeshanyuezhangmu("givemeshanyuezhangmu"),

    //1 千年栋梁
    givemeqianniandongliang("givemeqianniandongliang"),

    //1 宫廷酒
    givemegongtingjiu("givemegongtingjiu"),

    //1 看守长眼蟹
    givemekanshouchangyanxie("givemekanshouchangyanxie"),

    //1 戒空药水
    givemejiekongyaoshui("givemejiekongyaoshui"),

    //1 疗伤膏
    givemeliaoshanggao("givemeliaoshanggao"),

    //1 大水梨
    givemedashuili("givemedashuili"),
    ;


    private String command;

    ChatCommandConstant(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
