package common.input;

import common.validate.ValidatorInterface;

/**
 * Class for working with IP input and validation
 */
public class InputIp extends Input<String>{
    @Override
    protected ValidatorInterface getValidator(String text) {
        return null;
    }

    @Override
    public String getValue() {
        return null;
    }
}
