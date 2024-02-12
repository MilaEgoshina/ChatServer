package common.input;


import common.stream.StreamIO;
import common.validate.ValidatorInterface;
import lombok.Getter;
import lombok.Setter;

/**
 * Base class for working with input and value validation
 */

@Getter
@Setter
public abstract class Input<T> {

    String headerWelcomeMessage;

    String headerErrorMessage;

    StreamIO streamIO;

    /**
     * Read value
     * @param headerMessage
     * @return string with message
     */
    protected String getInputValue(String headerMessage){

        streamIO.print(headerMessage);
        return streamIO.read();
    }

    /**
     * Do some validations and extract values
     * @return text after validation
     */
    protected String getValueAfterValidation(){

        String text = getInputValue(headerErrorMessage);
        while (true){

            if(getValidator(text).validate()){

                break;
            }
            text = getInputValue(headerErrorMessage);
        }

        return text;
    }

    /**
     * @return instance of class - validator to check values
     */
    protected abstract ValidatorInterface getValidator(String text);

    /**
     * @return final value with definite type
     */
    public abstract T getValue();

}
