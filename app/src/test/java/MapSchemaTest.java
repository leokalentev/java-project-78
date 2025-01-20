import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

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
    @CsvFileSource(resources = "/map1_csv.csv", numLinesToSkip = 1)
    void testSizeValidation(int mapSize, boolean expected) {
        schema.sizeof(2);
        Map<String, Object> data = new HashMap<>();
        for (int i = 1; i <= mapSize; i++) {
            data.put("key" + i, "value" + i);
        }
        boolean result = schema.isValid(data);

        assertEquals(expected, result, "Map size validation failed");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/map2_csv.csv", numLinesToSkip = 1)
    void testShapeValidationFromCsv(String firstName, String lastName, boolean expected) {
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required().contains("hn"));
        schemas.put("lastName", validator.string().required().minLength(2));
        schema.shape(schemas);

        Map<String, Object> data = new HashMap<>();
        data.put("firstName", firstName);
        data.put("lastName", lastName);

        boolean result = schema.isValid(data);
        assertEquals(expected, result, "Validation failed for input: firstName=" + firstName + ", lastName="
                + lastName);
    }
}
