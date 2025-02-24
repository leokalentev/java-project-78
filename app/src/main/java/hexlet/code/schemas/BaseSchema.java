package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;

    public abstract boolean isValid(T value);

    public abstract BaseSchema<T> required();

    public final boolean isRequired() {
        return isRequired;
    }
}
