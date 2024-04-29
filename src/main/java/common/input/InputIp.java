package common.input;

import common.validate.ValidatorFactory;
import common.validate.ValidatorInterface;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents an input for IP addresses.
 * It provides functionality for input validation.
 */
@Getter
@Setter
public class InputIp extends Input<String>{

    /**
     * Returns the validator for the given IP address.
     *
     * @param text The IP address to be validated
     * @return The IP validator
     */
    @Override
    protected ValidatorInterface getValidator(String text) {

        return ValidatorFactory.getIpValidator(text);
    }

    /**
     * Returns the validated IP address value.
     *
     * @return The validated IP address value
     */
    @Override
    public String getValue() {
        return super.getValueAfterValidation();
    }
}
