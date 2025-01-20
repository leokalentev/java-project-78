import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public final class NumberSchemaTest {

    private final Validator validator = new Validator();
    private final NumberSchema schema = validator.number();

    @Test
    void testNullValidation() {
        boolean resultWithoutPositive = schema.isValid(null);
        schema.positive();
        boolean resultWithPositive = schema.isValid(null);

        assertTrue(resultWithoutPositive, "Null should be valid by default");
        assertTrue(resultWithPositive, "Null should still be valid after applying positive() rule");
    }

    @Test
    void testRequiredValidation() {
        schema.required();

        assertFalse(schema.isValid(null), "Null should be invalid when required");
        assertTrue(schema.isValid(10), "Positive number should be valid when required");
    }

    @Test
    void testPositiveValidation() {
        schema.required().positive();

        assertFalse(schema.isValid(-10), "Negative numbers should be invalid when positive() is applied");
        assertFalse(schema.isValid(0), "Zero should be invalid when positive() is applied");
        assertTrue(schema.isValid(15), "Positive numbers should be valid when positive() is applied");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/number_csv.csv", numLinesToSkip = 1)
    void testRangeValidation(int value, boolean expected) {
        schema.required().range(5, 10);
        boolean result = schema.isValid(value);

        assertEquals(expected, result, "Range validation failed for value: " + value);
    }
}
