package common.input;

import common.Service;
import common.transport.TransportFactory;
import common.validate.ValidatorFactory;
import common.validate.ValidatorInterface;
import lombok.Getter;
import lombok.Setter;

/**
 * Class for working with input and port validation
 */

@Getter
@Setter
public class InputPort extends Input<Integer>{

    boolean isUsedValidation;
    TransportFactory transportFactory;
    @Override
    protected ValidatorInterface getValidator(String text) {
        return ValidatorFactory.getPortValidation(text, Service.getInstance().getMinPort(),
                Service.getInstance().getMaxPort(), isUsedValidation, transportFactory);
    }

    @Override
    public Integer getValue() {
        return Integer.parseInt(super.getValueAfterValidation());
    }
}
