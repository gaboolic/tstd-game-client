package tk.gbl.constant;

/**
 * Date: 2017/3/27
 * Time: 14:50
 *
 * @author Tian.Dong
 */
public enum ProtocolConstant {
    connect(0x00),
    login(0x01),
    chat(0x0202),
    chatMi(0x0203),
    chatTuan(0x0206),
    delete(0x2302),

    addShuxing(0x0801),
    addSkill(0x1C02),


    checkName(0x0902),
    setNameAndInit(0x0901),
    confirmInit(0x0301),
    serverIp(0x2501),
    unknown2713(0x2713),
    unknown1929(0x1929),

    setIdCard(0x4701),
    clickNPC(0x1401),
    unknown1402(0x1402),// F4 44 04 00 14 02 24 00
    moveTo(0x0601),
    moveTrigger(0x1404),
    move(0x1408),
    unknown172D(0x172D),
    unknown1730(0x1730),
    unknown0C01(0x0C01),
    eventOk(0x1406),
    event4906(0x4906),//打完boss拿东西
    event1F01(0x1F01),
    closeDialog(0x1E08),
    choose(0x1409),

    fangzhu(0x0F02),//F4 44 03 00 0F 02 02
    shangma(0x0F04),
    xiama(0x0F05),

    chuzhan(0x1301),//F4 44 06 00 13 01 DB B0 00 00
    wujaingxiuxi(0x1302),//F4 44 02 00 13 02

    pickUp(0x1702),//捡东西
    throwAway(0x1703),//扔东西
    splitItem(0x170A),//拆分物品
    equipItem(0x170B),//装备物品
    unequipItem(0x170C),//卸下物品
    equipItemPet(0x1711),//装备武将物品
    unequipItemPet(0x1712),//卸下武将物品
    useSkill(0x1714),//非战斗使用技能 宠物使用物品
    compoundItem(0x170E),//合成
    useItem(0x170F),//使用东西
    buyItem(0x1B01),//买东西
    sell(0x1B02),//卖东西
    storeTake(0x1E01),//从仓库拿东西
    storePut(0x1E02),//从仓库放东西




    tradeRequest(0x1901),//交易发起
    tradeItem(0x1902),//交易物品
    tradeConfirm(0x1903),//交易确认
    sendGoods(0x1914),//邮寄东西

    tradePetRequest(0x190A),//交易武将发起
    tradePet(0x190B),//交易武将
    tradePetConfirm(0x190C),//交易确认

    contributeItem(0x270F),//捐献物品

    pk(0x0B02),
    fight(0x3201),
    fightUseItem(0x3202),
    answer(0x1001),
    u1021(0x1021),//战斗结束后发?
    u2002(0x2002),

    joinTeam(0x0D01),
    acceptTeam(0x0D03),
    confirmTeamAdviser(0x0D05),//军师
    inviteTeam(0x0D07),//邀请
    exitTeam(0x0D04),//退队

    openJiguan(0x4101),
    pauseJiguan(0x4102),
    u4203(0x4203),//打开商场

    ;

    private int id;

    ProtocolConstant(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
