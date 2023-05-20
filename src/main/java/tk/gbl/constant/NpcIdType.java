package tk.gbl.constant;

/**
 * Date: 2017/4/19
 * Time: 17:12
 *
 * @author Tian.Dong
 */
public enum NpcIdType {
    /**
     * 可疑蛮兵
     */
    keyimanbing(17771),

    /**
     * 高定
     */
    gaoding(14650),

    /**
     * 鄂焕
     */
    ehuan(14649),
    beigou(15322),
    ;

    private int id;

    NpcIdType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
