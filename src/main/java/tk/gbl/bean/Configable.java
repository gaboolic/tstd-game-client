package tk.gbl.bean;

/**
 * Date: 2017/4/28
 * Time: 15:32
 *
 * @author Tian.Dong
 */
public interface Configable {
    public String separator = "|";

    public String toConfigString();
}
