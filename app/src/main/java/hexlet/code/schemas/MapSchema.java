package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private int minSize = 0;
    private Map<String, BaseSchema<?>> shapeSchema = new HashMap<>();

    @Override
    public MapSchema required() {
        isRequired = true;
        return this;
    }

    @Override
    public boolean isValid(Map<?, ?> value) {
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
            if (mapValue == null) {
                if (schema.isRequired()) {
                    return false;
                }
            } else {
                if (!isValueValid(schema, mapValue)) {
                    return false;
                }
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private <T> boolean isValueValid(BaseSchema<T> schema, Object value) {
        try {
            T castedValue = (T) value;
            return schema.isValid(castedValue);
        } catch (ClassCastException e) {
            return false;
        }
    }
    public void shape(Map<String, BaseSchema<?>> schemas) {
        this.shapeSchema.putAll(schemas);
    }
    public MapSchema sizeof(int size) {
        this.minSize = size;
        return this;
    }
}
