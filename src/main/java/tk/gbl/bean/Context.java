package tk.gbl.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 事件上下文
 *
 * Date: 2017/4/7
 * Time: 19:04
 *
 * @author Tian.Dong
 */
public class Context {
    long fromUserId;
    long toUserId;

    Map<String, Object> param = new HashMap<>();

    public long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    public long getToUserId() {
        return toUserId;
    }

    public void setToUserId(long toUserId) {
        this.toUserId = toUserId;
    }
}
