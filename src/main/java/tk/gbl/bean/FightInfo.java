package tk.gbl.bean;

/**
 * Date: 2017/4/18
 * Time: 14:30
 *
 * @author Tian.Dong
 */
public class FightInfo {
    RoleInfo[][] roleArray = new RoleInfo[4][5];

    String question;

    int roundIndex = 1;

    public RoleInfo[][] getRoleArray() {
        return roleArray;
    }

    public void setRoleArray(RoleInfo[][] roleArray) {
        this.roleArray = roleArray;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getRoundIndex() {
        return roundIndex;
    }

    public void setRoundIndex(int roundIndex) {
        this.roundIndex = roundIndex;
    }
}
