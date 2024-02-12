package common.validate;

/**
 * Class - validator for checking whether a number is in a range
 */
public class RangeNumberValidation extends BaseValidator implements ValidatorInterface{


    private int min;
    private int max;

    public RangeNumberValidation(String value, int min, int max) {
        super(value);

        this.min = min;
        this.max = max;
    }

    @Override
    public boolean validate() {

        int val = Integer.parseInt(value);
        return (val >= min) && (val <= max);
    }
}
