package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean isPositive = false;
    private boolean isRange = false;
    private Integer bottomBorder = 0;
    private Integer topBorder = 0;

    @Override
    public boolean isValid(Integer number) {
        if (number == null) {
            return !isRequired;
        }
        if (!super.isValid(number)) {
            return false;
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
        bottomBorder = a;
        topBorder = b;
        return this;
    }
}
