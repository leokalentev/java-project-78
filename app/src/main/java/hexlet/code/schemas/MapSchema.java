package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, String>> {
    private int minSize = 0;
    private Map<String, BaseSchema<?>> shapeSchema = new HashMap<>();

    @Override
    public MapSchema required() {
        isRequired = true;
        return this;
    }

    @Override
    public boolean isValid(Map<String, String> value) {
        if (value == null) {
            return !isRequired;
        }
        if (value.size() < minSize) {
            return false;
        }
        for (Map.Entry<String, BaseSchema<?>> entry : shapeSchema.entrySet()) {
            String key = entry.getKey();
            BaseSchema<?> schema = entry.getValue();
            String mapValue = value.get(key);

            if (mapValue == null) {
                if (schema.isRequired()) {
                    return false;
                }
            } else {
                if (!((BaseSchema<Object>) schema).isValid(mapValue)) {
                    return false;
                }
            }
        }
        return true;
    }


    public MapSchema sizeof(int size) {
        this.minSize = size;
        return this;
    }

    public void shape(Map<String, BaseSchema<?>> schemas) {
        this.shapeSchema = new HashMap<>(schemas);
    }
}
