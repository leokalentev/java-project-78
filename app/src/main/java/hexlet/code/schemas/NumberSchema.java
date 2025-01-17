package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    private boolean isPositive = false;
    private boolean isRange = false;
    private Integer bottomBorder = Integer.MIN_VALUE;
    private Integer topBorder = Integer.MAX_VALUE;

    @Override
    public NumberSchema required() {
        isRequired = true;
        return this;
    }

    @Override
    public boolean isValid(Integer number) {
        if (number == null) {
            return !isRequired;
        }
        if (isPositive && number <= 0) {
            return false;
        }
        if (isRange && (number < bottomBorder || number > topBorder)) {
            return false;
        }
        return true;
    }

    public NumberSchema positive() {
        isPositive = true;
        return this;
    }

    public NumberSchema range(Integer a, Integer b) {
        isRange = true;
        bottomBorder = Math.min(a, b);
        topBorder = Math.max(a, b);
        return this;
    }
}
