import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    private Validator v = new Validator();
    private MapSchema schema = v.map();

    @Test
    void mapSchemaTest1() {
        assertTrue(schema.isValid(null));
    }

    @Test
    void mapSchemaTest2() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void mapSchemaTest3() {
        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        schema.sizeof(2);
        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    void mapSchemaTest4() {
        MapSchema schema = v.map();
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required().contains("hn"));
        schemas.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));
    }

}
