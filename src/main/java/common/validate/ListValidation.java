package common.validate;

import java.util.ArrayList;
import java.util.List;

/**
 * Class - validator for checking layouts
 */
public class ListValidation implements ValidatorInterface{

    private List<ValidatorInterface> listValidator = new ArrayList<>();


    @Override
    public boolean validate() {

        boolean result = true;
        for(ValidatorInterface validator : listValidator){
            result = validator.validate();
            if(!result)
                break;
        }
        return result;
    }

    /**
     * Add new type of validator to common list
     * @param validator new validator
     */
    public void add(ValidatorInterface validator){
        this.listValidator.add(validator);
    }

    /**
     * Remove validator from the common list
     */
    public void remove(int index){

        this.listValidator.remove(index);
    }

    /**
     * Get the number of validators in common list
     */
    public int size(){

        return this.listValidator.size();
    }

}
