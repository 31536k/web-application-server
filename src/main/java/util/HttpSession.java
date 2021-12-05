package util;

import java.util.HashMap;
import java.util.Map;

public class HttpSession {

    private final String id;
    private final Map<String, Object> valueMap = new HashMap<>();

    public HttpSession(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setAttribute(String name, Object value) {
        valueMap.put(name, value);
    }

    public Object getAttribute(String name) {
        return valueMap.get(name);
    }

    public void removeAttribute(String name) {
        valueMap.remove(name);
    }

    public void invalidate() {
        HttpSessions.remove(id);
    }
}
