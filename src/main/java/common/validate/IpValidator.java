package common.validate;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class - validator for checking correct input of IP address
 */
public class IpValidator extends BaseValidator implements ValidatorInterface{


    public IpValidator(String value) {
        super(value);
    }

    @Override
    public boolean validate() {
        Pattern p = Pattern.compile("((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)");
        Matcher m = p.matcher(value);
        return m.matches();
    }
}
