package hexlet.code.formatters;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String format(Map<String, Map<String, Object>> diffMap) {
        StringBuilder result = new StringBuilder();

        for (String key : diffMap.keySet()) {
            Map<String, Object> map = diffMap.get(key);

            if (map.get("status").equals("added")) {
                result.append(String.format("Property '%s' was added with value: %s",
                        key, getValue(map.get("value")))).append("\n");
            } else if (map.get("status").equals("deleted")) {
                result.append(String.format("Property '%s' was removed", key)).append("\n");
            } else if (map.get("status").equals("changed")) {
                result.append(String.format("Property '%s' was updated. From %s to %s",
                        key, getValue(map.get("oldValue")), getValue(map.get("newValue")))).append("\n");
            }
        }
        return result.toString().trim();
    }

    public static String getValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Map<?, ?> || value instanceof Array || value instanceof List<?>) {
            return "[complex value]";
        } else {
            return value.toString();
        }
    }
}
