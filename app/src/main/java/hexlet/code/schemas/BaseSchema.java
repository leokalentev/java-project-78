package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;
    public boolean isValid(T value) {
        if (isRequired) {
            return value != null && (!(value instanceof String) || !((String) value).isEmpty());
        } else {
            return true;
        }
    }

    public void required() {
        isRequired = true;
    }
}
