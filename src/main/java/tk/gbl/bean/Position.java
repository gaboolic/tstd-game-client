package tk.gbl.bean;

/**
 * Date: 2017/4/19
 * Time: 18:10
 *
 * @author Tian.Dong
 */
public class Position implements Configable {
    int row;
    int column;

    public Position() {

    }

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (column != position.column)
            return false;
        if (row != position.row)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    @Override
    public String toConfigString() {
        return this.row + "," + this.column;
    }

    /**
     * 0,0
     */
    public static Position fromConfigString(String configString) {
        if (configString == null || configString.equals("")) {
            return null;
        }
        int row = Integer.parseInt(configString.split(",")[0]);
        int column = Integer.parseInt(configString.split(",")[1]);
        Position position = new Position(row, column);
        return position;
    }
}
