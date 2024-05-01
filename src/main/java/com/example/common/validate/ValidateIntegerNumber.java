package com.example.common.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The ValidateIntegerNumber class extends the BaseValidator class and implements the ValidatorInterface for
 * validating integer numbers.
 */
public class ValidateIntegerNumber extends BaseValidator implements ValidatorInterface{

    /**
     * Constructs a ValidateIntegerNumber object with the specified value.
     * @param value the value to validate as an integer number
     */
    public ValidateIntegerNumber(String value) {
        super(value);
    }

    /**
     * Validates the value as an integer number using a regular expression pattern.
     * @return true if the value is a valid integer number, false otherwise
     */
    @Override
    public boolean validate() {
        Pattern p = Pattern.compile("[0-9]{4,5}");
        Matcher m = p.matcher(value);
        return m.matches();
    }
}
