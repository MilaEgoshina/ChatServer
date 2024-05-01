package com.example.common.input;


import com.example.common.stream.StreamIO;
import com.example.common.validate.ValidatorInterface;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents an abstract input class that handles user input.
 * It provides methods for retrieving user input and validating it using a ValidatorInterface.
 * It also contains messages for welcoming and error messages to be displayed to the user.
 */
@Getter
@Setter
public abstract class Input<T> {

    /**
     * The text to display as a welcome message when prompting the user for input.
     */
    String headerWelcomeMessage;

    /**
     * The text to display as an error message when the user input is invalid.
     */
    String headerErrorMessage;

    /**
     * Used for input and output operations.
     */
    StreamIO streamIO;

    /**
     * Retrieves input from the user and returns it as a string.
     *
     * @param headerMessage The message to display when prompting the user for input.
     * @return The user input as a string.
     */
    protected String getInputValue(String headerMessage){

        streamIO.print(headerMessage);
        return streamIO.read();
    }

    /**
     * Gets user input after validating it with the specified ValidatorInterface.
     * @return The validated user input as a string.
     */
    protected String getValueAfterValidation(){

        String text = getInputValue(headerWelcomeMessage);
        while (true){

            if(getValidator(text).validate()){

                break;
            }
            text = getInputValue(headerErrorMessage);
        }

        return text;
    }

    /**
     * Abstract method to get the appropriate validator for the input text.
     *
     * @param text The user input text to be validated.
     * @return The ValidatorInterface implementation for the input text.
     */
    protected abstract ValidatorInterface getValidator(String text);

    /**
     * Abstract method to get the user input of type T.
     * @return The user input of type T.
     */
    public abstract T getValue();

}
