package common.input;

import common.validate.ValidatorFactory;
import common.validate.ValidatorInterface;
import lombok.Getter;
import lombok.Setter;

/**
 * Class for working with IP input and validation
 */
@Getter
@Setter
public class InputIp extends Input<String>{
    @Override
    protected ValidatorInterface getValidator(String text) {

        return ValidatorFactory.getIpValidator(text);
    }

    @Override
    public String getValue() {
        return super.getValueAfterValidation();
    }
}
