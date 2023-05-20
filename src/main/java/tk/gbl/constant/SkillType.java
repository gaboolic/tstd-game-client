package tk.gbl.constant;

/**
 * Date: 2017/4/19
 * Time: 17:12
 *
 * @author Tian.Dong
 */
public enum SkillType {
    roubo(10000),//肉搏
    jiejie(10010),//结界
    jing(10015),//镜
    heituyanwangsha(10034),//黑屠岩王杀
    qingliu(11004),//清流
    quanhuimo(11009),//全回魔
    quanzhiliao(11010),//全治疗
    fuhuoshu(11013),//复活术
    miaoshuihouchun(11026),//妙手回春
    tianjiangganlin(11030),//天降甘霖
    shuanghandonglie(11023),//霜寒冻裂
    zhanqilinyishan(11033),//斩骑
    huojian(12003),//火箭
    sanmeizhenhuo(12013),//三昧真火
    fenghuoliaoyuan(12014),//烽火燎原
    zhurong(12032),//祝融
    juelongbaliezhan(12033),//爵龙霸烈斩
    longwang(12034),//龙王
    lieshuhaohuo(12028),//烈术豪火
    shanduo(13003),//闪躲
    yinshen(13005),//隐身
    lianji(13010),//连击
    benglei(13026),//崩雷
    yinfenshen(13032),//隐分身
    zhendian(13033),//震电
    zhenchashu(14001),//侦察术
    duntaoshu(14002),//遁逃术
    jianwujiutian(14037),//箭舞九天
    bayi(14040),//霸意
    wangluo(15002),//网罗
    wangluoSuccess(15003),
    fangyu(17001),//防御
    taopao(18001),//逃跑
    taopaoFail(18002),
    shujingxixue(20001),//树精吸血
    guangmaoshu(22001),//光矛术
    foguangzhang(22002),//佛光掌
    anrenzhan(23002),//闇刃斩
    heixuanzhan(23003),//黑旋斩
    ;

    private int id;

    SkillType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
