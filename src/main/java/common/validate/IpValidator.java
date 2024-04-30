package common.validate;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The IpValidator class extends the BaseValidator class and implements the ValidatorInterface for validating IP addresses.
 */
public class IpValidator extends BaseValidator implements ValidatorInterface{

    /**
     * Constructs an IpValidator object with the specified value.
     * @param value the IP address value to validate
     */
    public IpValidator(String value) {
        super(value);
    }

    /**
     * Validates the IP address value using a regular expression pattern.
     * @return true if the IP address is valid, false otherwise
     */
    @Override
    public boolean validate() {
        Pattern p = Pattern.compile("((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)");
        Matcher m = p.matcher(value);
        return m.matches();
    }
}
