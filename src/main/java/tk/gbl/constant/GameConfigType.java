package tk.gbl.constant;

/**
 * Date: 2017/4/19
 * Time: 10:08
 *
 * @author Tian.Dong
 */
public enum GameConfigType {
    gainLevel(0),
    work(1),
    catchPet(2);

    private int type;

    GameConfigType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
