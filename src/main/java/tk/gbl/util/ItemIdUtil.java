package tk.gbl.util;

import tk.gbl.bean.Goods;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2017/4/30
 * Time: 17:39
 *
 * @author Tian.Dong
 */
public class ItemIdUtil {

    static Map<Integer, Goods> itemNameMapping = new HashMap<>();

    static {
        InputStream is = PathFindingUtil.class.getResourceAsStream("/item.ini");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(is,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            if (bufferedReader != null) {
                while ((line = bufferedReader.readLine()) != null) {
                    String[] infos = line.split("\t");
                    //10000	问号	47	ATK+0
                    int id = Integer.parseInt(infos[0]);
                    String name = infos[1];
                    int type = Integer.parseInt(infos[2]);

                    Goods goods = new Goods();
                    goods.setId(id);
                    goods.setName(name);
                    goods.setType(type);
                    if(infos.length > 4) {
                        String equipType = infos[4];
                        goods.setEquipType(equipType);
                    }
                    itemNameMapping.put(id, goods);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getItemName(int id) {
        Goods goods = itemNameMapping.get(id);
        if (goods != null) {
            return goods.getName();
        }
        return "";
    }
    public static Goods getGoods(int id) {
        Goods goods = itemNameMapping.get(id);
        return goods;
    }
}
