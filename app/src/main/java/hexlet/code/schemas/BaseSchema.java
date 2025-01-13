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

    public BaseSchema<T> required() {
        isRequired = true;
        return this;
    }

    public BaseSchema<T> minLength(int length) {
        return this;
    }

    public BaseSchema<T> contains(String substring) {
        return this;
    }
}