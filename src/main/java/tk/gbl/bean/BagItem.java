package tk.gbl.bean;

/**
 * Date: 2017/4/4
 * Time: 10:32
 *
 * @author Tian.Dong
 */
public class BagItem {
    int id;
    int count;
    int index;
    int x;
    int y;
    int wastage;//损耗

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getWastage() {
        return wastage;
    }

    public void setWastage(int wastage) {
        this.wastage = wastage;
    }

    @Override
    public String toString() {
        return "BagItem{" +
                "id=" + id +
                ", count=" + count +
                ", index=" + index +
                ", x=" + x +
                ", y=" + y +
                ", wastage=" + wastage +
                '}';
    }
}
