import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
    private Validator v = new Validator();
    private StringSchema schema = v.string();
    @Test
    void StringSchemaTest1() {
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
    }

    @Test
    void StringSchemaTest2() {
        schema.required();
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void StringSchemaTest3() {
        schema.required();
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));
        assertFalse(schema.isValid("what does the fox say"));
    }

    @Test
    void StringSchemaTest4() {
        schema.required();
        assertTrue(schema.minLength(10).minLength(4).isValid("Hexlet"));
    }
}
