import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    private Validator v = new Validator();
    private NumberSchema schema = v.number();

    @Test
    void NumberSchemaTest1() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));
    }

    @Test
    void NumberSchemaTest2() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
    }

    @Test
    void NumberSchemaTest3() {
        schema.required();
        assertFalse(schema.positive().isValid(-10));
        assertFalse(schema.isValid(0));
    }

    @Test
    void NumberSchemaTest4() {
        schema.required();
        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }
}
