package tk.gbl.constant;

/**
 * Date: 2017/4/19
 * Time: 17:12
 *
 * @author Tian.Dong
 */
public enum MapIdType {
    roubo(10000),//肉搏
    huojian(12003),//火箭
    zhurong(12032),//祝融
    longwang(12034),//龙王
    benglei(13026),//崩雷
    zhendian(13033),//震电
    wangluo(15002),//网罗
    wangluoSuccess(15003),
    fangyu(17001),//防御
    taopao(18001),//逃跑
    taopaoFail(18002),;

    private int id;

    MapIdType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
