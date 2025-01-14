package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    private boolean isMinLength = false;
    private int minLengthValue = 0;
    private String containsSubstring = null;

    @Override
    public StringSchema required() {
        isRequired = true;
        return this;
    }


    @Override
    public boolean isValid(String value) {
        if (value == null) {
            return !isRequired;
        }
        if (isRequired && value.isEmpty()) {
            return false;
        }
        if (isMinLength && value.length() < minLengthValue) {
            return false;
        }
        if (containsSubstring != null && !value.contains(containsSubstring)) {
            return false;
        }
        return true;
    }


    public final StringSchema minLength(int length) {
        this.isMinLength = true;
        this.minLengthValue = length;
        return this;
    }

    public final StringSchema contains(String substring) {
        this.containsSubstring = substring;
        return this;
    }
}
