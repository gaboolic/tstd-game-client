package tk.gbl.constant;

/**
 * Date: 2017/5/15
 * Time: 22:15
 *
 * @author Tian.Dong
 */
public enum  EquipType {
    Head(1),//头
    Body(2),//衣
    Weapon(3),//武器
    Wrist(4),//护手 护腕
    Foot(5),//鞋
    Special(6),//饰品
    ;

    private int id;

    EquipType(int id) {
        this.id = id;
    }

    public static EquipType getByCode(int code) {
        if(code == 1) {
            return EquipType.Head;
        }
        if(code == 2) {
            return EquipType.Body;
        }
        if(code == 3) {
            return EquipType.Weapon;
        }
        if(code == 4) {
            return EquipType.Wrist;
        }
        if(code == 5) {
            return EquipType.Foot;
        }
        if(code == 6) {
            return EquipType.Special;
        }
        return EquipType.Head;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
