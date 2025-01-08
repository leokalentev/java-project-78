import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    private Validator v = new Validator();
    private MapSchema schema = new MapSchema();
    @Test
    void MapSchemaTest1() {
        assertTrue(schema.isValid(null));
    }

    @Test
    void MapSchemaTest2() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void MapSchemaTest3() {
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        schema.isValid(data);
        schema.sizeof(2);
        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }
}
