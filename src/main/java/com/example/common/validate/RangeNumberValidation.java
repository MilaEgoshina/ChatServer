package com.example.common.validate;

/**
 * The RangeNumberValidation class extends the BaseValidator class and implements the ValidatorInterface for
 * validating a number within a specific range.
 */
public class RangeNumberValidation extends BaseValidator implements ValidatorInterface{


    private int min;
    private int max;

    /**
     * Constructs a RangeNumberValidation object with the specified value, minimum, and maximum values.
     * @param value the value to validate
     * @param min the minimum value of the range
     * @param max the maximum value of the range
     */
    public RangeNumberValidation(String value, int min, int max) {
        super(value);

        this.min = min;
        this.max = max;
    }

    /**
     * Validates whether the value falls within the specified range.
     * @return true if the value is within the range, false otherwise
     */
    @Override
    public boolean validate() {

        int val = Integer.parseInt(value);
        return (val >= min) && (val <= max);
    }
}
