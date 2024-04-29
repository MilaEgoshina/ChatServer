package common.input;

import common.Service;
import common.transport.TransportFactory;
import common.validate.ValidatorFactory;
import common.validate.ValidatorInterface;
import lombok.Getter;
import lombok.Setter;

/**
 * Class representing an input port value.
 * Extends Input class and specializes it for Integer type.
 */
@Getter
@Setter
public class InputPort extends Input<Integer>{

    /**
     * Flag indicating if validation should be used.
     */
    boolean isUsedValidation;

    /**
     * Factory for creating Transport objects.
     */
    TransportFactory transportFactory;

    /**
     * Retrieves and returns a ValidatorInterface object for validating the input value.
     * Uses port validation rules and settings from Service class.
     *
     * @param text The input text to be validated.
     * @return ValidatorInterface object for input validation.
     */
    @Override
    protected ValidatorInterface getValidator(String text) {
        return ValidatorFactory.getPortValidation(text, Service.getInstance().getMinPort(),
                Service.getInstance().getMaxPort(), isUsedValidation, transportFactory);
    }

    /**
     * Retrieves and returns the validated Integer value from the input text.
     * @return The validated Integer value from the input text.
     */
    @Override
    public Integer getValue() {
        return Integer.parseInt(super.getValueAfterValidation());
    }
}
