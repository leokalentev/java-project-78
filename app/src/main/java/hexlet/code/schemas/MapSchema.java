package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, String>> {
    private int mapSize = 0;
    public boolean isValid(HashMap<String, String> map) {
        if (map == null) {
            return !isRequired;
        }
        if (!super.isValid(map)) {
            return false;
        }
        if (map.size() < mapSize) {
            return false;
        }
        return true;
    }

    public MapSchema sizeof(int newSize) {
        mapSize = newSize;
        return this;
    }
}
