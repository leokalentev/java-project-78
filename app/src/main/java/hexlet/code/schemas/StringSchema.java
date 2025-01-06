package hexlet.code.schemas;

public class StringSchema {
    private  boolean isRequired = false;
    private boolean isMinLength = false;
    private int newLength = 0;
    private String checkContains = null;

    public void required() {
        isRequired = true;
    }

    public boolean isValid(String value) {
        var notEmpty = value != null && !value.isEmpty();
        if (isRequired) {
            return notEmpty && (checkContains == null || value.contains(checkContains))
                    && (!isMinLength || value.length() >= newLength);
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
