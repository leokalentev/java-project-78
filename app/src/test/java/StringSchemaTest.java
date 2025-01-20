import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public final class StringSchemaTest {

    private final Validator validator = new Validator();
    private final StringSchema schema = validator.string();

    @Test
    void testNullAndEmptyValidation() {
        boolean resultForNull = schema.isValid(null);
        boolean resultForEmpty = schema.isValid("");

        assertTrue(resultForNull, "Null should be valid by default");
        assertTrue(resultForEmpty, "Empty string should be valid by default");
    }

    @Test
    void testRequiredValidation() {
        schema.required();

        assertFalse(schema.isValid(null), "Null should be invalid when required");
        assertFalse(schema.isValid(""), "Empty string should be invalid when required");
        assertTrue(schema.isValid("hexlet"), "Non-empty string should be valid when required");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/string_csv.csv", numLinesToSkip = 1)
    void testContainsValidation(String input, String substring, boolean expected) {
        schema.required().contains(substring);
        boolean result = schema.isValid(input);

        assertEquals(expected, result, "Validation failed for input: '" + input + "', substring: '" + substring + "'");
    }

    @Test
    void testMinLengthValidation() {
        schema.required();

        assertFalse(schema.minLength(10).isValid("Hexlet"), "String shorter than minLength should be invalid");
        assertTrue(schema.minLength(4).isValid("Hexlet"), "String longer than minLength should be valid");
    }
}
