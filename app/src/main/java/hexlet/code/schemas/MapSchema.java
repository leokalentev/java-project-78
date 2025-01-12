package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, String>> {
    private int mapSize = 0;
    private Map<String, BaseSchema<String>> keySchema = new HashMap<>();

    @Override
    public boolean isValid(Map<String, String> map) {
        if (map == null) {
            return !isRequired;
        }
        if (!super.isValid(map)) {
            return false;
        }
        if (map.size() < mapSize) {
            return false;
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            BaseSchema<String> schema = keySchema.get(key);
            if (schema != null && !schema.isValid(value)) {
                return false;
            }
        }

        return true;
    }

    public MapSchema sizeof(int newSize) {
        this.mapSize = newSize;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schema) {
        this.keySchema = schema;
        return this;
    }
}
