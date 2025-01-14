package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, Object>> {
    private int minSize = 0;
    private Map<String, BaseSchema<?>> shapeSchema = new HashMap<>();

    @Override
    public MapSchema required() {
        isRequired = true;
        return this;
    }

    @Override
    public boolean isValid(Map<String, Object> value) {
        if (value == null) {
            return !isRequired;
        }

        if (value.size() < minSize) {
            return false;
        }

        for (Map.Entry<String, BaseSchema<?>> entry : shapeSchema.entrySet()) {
            String key = entry.getKey();
            BaseSchema<?> schema = entry.getValue();
            Object mapValue = value.get(key);
            if (!validateValueWithSchema(mapValue, schema)) {
                return false;
            }
        }
        return true;
    }

    private <T> boolean validateValueWithSchema(Object value, BaseSchema<T> schema) {
        try {
            return schema.isValid((T) value);
        } catch (ClassCastException e) {
            return false;
        }
    }


    public MapSchema sizeof(int size) {
        this.minSize = size;
        return this;
    }

    public void shape(Map<String, BaseSchema<?>> schemas) {
        this.shapeSchema = new HashMap<>(schemas);
    }
}
