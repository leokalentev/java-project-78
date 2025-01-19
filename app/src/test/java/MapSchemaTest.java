import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public final class MapSchemaTest {

    private final Validator validator = new Validator();
    private final MapSchema schema = validator.map();

    @Test
    void testNullValidation() {
        boolean result = schema.isValid(null);

        assertTrue(result, "Null should be valid by default");
    }

    @Test
    void testRequiredValidation() {
        schema.required();

        assertFalse(schema.isValid(null), "Null should be invalid when required");
        assertTrue(schema.isValid(new HashMap<>()), "Empty map should be valid when required");
    }

    @ParameterizedTest
    @CsvSource({
            "1, false",
            "2, true"
    })
    void testSizeValidation(int mapSize, boolean expected) {
        schema.sizeof(2);
        Map<String, Object> data = new HashMap<>();
        for (int i = 1; i <= mapSize; i++) {
            data.put("key" + i, "value" + i);
        }
        boolean result = schema.isValid(data);

        assertEquals(expected, result, "Map size validation failed");
    }

    @Test
    void testShapeValidationPositiveAndNegativeCases() {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required().contains("hn"));
        schemas.put("lastName", validator.string().required().minLength(2));
        schema.shape(schemas);

        Map<String, Object> validData = Map.of("firstName", "John", "lastName", "Smith");
        Map<String, Object> invalidData1 = new HashMap<>();
        invalidData1.put("firstName", "John");
        invalidData1.put("lastName", null);
        Map<String, Object> invalidData2 = Map.of("firstName", "Anna", "lastName", "B");

        assertTrue(schema.isValid(validData), "Valid data should pass validation");
        assertFalse(schema.isValid(invalidData1), "Invalid data with null lastName should fail validation");
        assertFalse(schema.isValid(invalidData2), "Invalid data with too short lastName should fail validation");
    }
}
