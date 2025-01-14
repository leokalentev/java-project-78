package hexlet.code.schemas;
public abstract class BaseSchema<T> {
    protected boolean isRequired = false;
    public abstract boolean isValid(T value);
    protected boolean basicValidation(T value) {
        if (isRequired) {
            if (value == null) {
                return false;
            }
            if (value instanceof String && ((String) value).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public abstract BaseSchema<T> required();
}

