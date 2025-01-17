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
                @SuppressWarnings("unchecked")
                BaseSchema<Object> typedSchema = (BaseSchema<Object>) schema;
                if (!typedSchema.isValid(mapValue)) {
                    return false;
                }
            }
        }
        return true;
    }

    public <T> void shape(Map<String, BaseSchema<? extends T>> schemas) {
        for (Map.Entry<String, BaseSchema<? extends T>> entry : schemas.entrySet()) {
            @SuppressWarnings("unchecked")
            BaseSchema<Object> schema = (BaseSchema<Object>) entry.getValue();
            this.shapeSchema.put(entry.getKey(), schema);
        }
    }

    public MapSchema sizeof(int size) {
        this.minSize = size;
        return this;
    }
}
