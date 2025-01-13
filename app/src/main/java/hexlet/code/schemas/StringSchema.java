package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private boolean isMinLength = false;
    private int minLengthValue = 0;
    private String containsSubstring = null;

    @Override
    public boolean isValid(String value) {
        if (!super.isValid(value)) {
            return false;
        }
        if (value == null) {
            return !isRequired;
        }
        if (isMinLength && value.length() < minLengthValue) {
            return false;
        }
        if (containsSubstring != null && !value.contains(containsSubstring)) {
            return false;
        }
        return true;
    }

    @Override
    public StringSchema minLength(int length) {
        isMinLength = true;
        minLengthValue = length;
        return this;
    }

    @Override
    public StringSchema contains(String substring) {
        containsSubstring = substring;
        return this;
    }
}
