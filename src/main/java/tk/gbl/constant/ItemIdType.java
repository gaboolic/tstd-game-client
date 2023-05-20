package tk.gbl.constant;

/**
 * Date: 2017/4/19
 * Time: 17:12
 *
 * @author Tian.Dong
 */
public enum ItemIdType {
    shitianluo(26118),//石田螺
    heisongmu(34004),//黑松木
    qingranbu(40004),//轻染布
    yinghoupi(39012),//硬厚皮
    tianzhuzhi(41029),//天竹纸
    nanzhongpijia(19781),//南中皮甲
    fuhujingangsheng(29138),//伏虎金刚绳
    yaonaing(26393),//瑶酿
    bdyttyl(26456),//巴豆妖特调饮料
    bawangjiu(26398),//霸王酒
    chutou(26398),//锄头
    tongtianmodou(46070),//通天魔豆
    ;

    private int id;

    ItemIdType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
