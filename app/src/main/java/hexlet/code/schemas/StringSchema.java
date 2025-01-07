package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private boolean isMinLength = false;
    private int newLength = 0;
    private String checkContains = null;

    @Override
    public boolean isValid(String value) {
        if (!super.isValid(value)) {
            return false;
        }
        if (value == null) {
            return !isRequired;
        }
        if (isRequired && value.isEmpty()) {
            return false;
        }
        if (checkContains != null && !value.contains(checkContains)) {
            return false;
        }
        if (isMinLength && value.length() < newLength) {
            return false;
        }
        return true;
    }

    public StringSchema minLength(int length) {
        isMinLength = true;
        newLength = length;
        return this;
    }

    public StringSchema contains(String checkContains) {
        this.checkContains = checkContains;
        return this;
    }
}
