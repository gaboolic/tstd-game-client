package tk.gbl.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Date: 2017/4/14
 * Time: 18:48
 *
 * @author Tian.Dong
 */
public class RoleInfo {
    private long id;

    /**
     * 宠物序号
     */
    private int index;
    /**
     * 地水火风
     * 1 2 3 4
     */
    private int type;

    private int hp;
    private int hpMax;
    private int mp;
    private int mpMax;
    private int level;
    private long exp;

    /**
     * 技能点
     */
    private int jn;

    /**
     * 属性点
     */
    private int sx;

    private int zl;
    private int atk;
    private int def;
    private int agi;
    private int hpx;
    private int spx;

    private long money;

    private List<Integer> skillList = new ArrayList<>();

    private Position position;

    private SkillSpecial skillSpecial;

    private BagItem[] equipItems = new BagItem[7];

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if (hp < 0) {
            hp = 0;
        }
        this.hp = hp;
    }

    public int getHpMax() {
        return hpMax;
    }

    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMpMax() {
        return mpMax;
    }

    public void setMpMax(int mpMax) {
        this.mpMax = mpMax;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public int getJn() {
        return jn;
    }

    public void setJn(int jn) {
        this.jn = jn;
    }

    public int getSx() {
        return sx;
    }

    public void setSx(int sx) {
        this.sx = sx;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public SkillSpecial getSkillSpecial() {
        return skillSpecial;
    }

    public void setSkillSpecial(SkillSpecial skillSpecial) {
        this.skillSpecial = skillSpecial;
    }

    public List<Integer> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Integer> skillList) {
        this.skillList = skillList;
    }

    public int getZl() {
        return zl;
    }

    public void setZl(int zl) {
        this.zl = zl;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getAgi() {
        return agi;
    }

    public void setAgi(int agi) {
        this.agi = agi;
    }

    public int getHpx() {
        return hpx;
    }

    public void setHpx(int hpx) {
        this.hpx = hpx;
    }

    public int getSpx() {
        return spx;
    }

    public void setSpx(int spx) {
        this.spx = spx;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BagItem[] getEquipItems() {
        return equipItems;
    }

    public void setEquipItems(BagItem[] equipItems) {
        this.equipItems = equipItems;
    }

    @Override
    public String toString() {
        return "RoleInfo{" +
                "id=" + id +
                ", index=" + index +
                ", type=" + type +
                ", hp=" + hp +
                ", hpMax=" + hpMax +
                ", mp=" + mp +
                ", mpMax=" + mpMax +
                ", level=" + level +
                ", exp=" + exp +
                ", jn=" + jn +
                ", sx=" + sx +
                ", zl=" + zl +
                ", atk=" + atk +
                ", def=" + def +
                ", agi=" + agi +
                ", hpx=" + hpx +
                ", spx=" + spx +
                ", money=" + money +
                ", skillList=" + skillList +
                ", position=" + position +
                ", skillSpecial=" + skillSpecial +
                ", equipItems=" + Arrays.toString(equipItems) +
                '}';
    }
}
