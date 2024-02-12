package common.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class - validator for checking a non-negative integer with a length of 4 to 5 characters
 */
public class ValidateIntegerNumber extends BaseValidator implements ValidatorInterface{



    public ValidateIntegerNumber(String value) {
        super(value);
    }

    @Override
    public boolean validate() {
        Pattern p = Pattern.compile("[0-9]{4,5}");
        Matcher m = p.matcher(value);
        return m.matches();
    }
}
