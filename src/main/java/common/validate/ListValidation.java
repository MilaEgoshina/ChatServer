package common.validate;

import java.util.ArrayList;
import java.util.List;

/**
 * The ListValidation class implements the ValidatorInterface to validate a list of validators.
 */
public class ListValidation implements ValidatorInterface{

    private List<ValidatorInterface> listValidator = new ArrayList<>();

    /**
     * Validates each validator in the list.
     * @return true if all validators pass, false if any validator fails
     */
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
     * @param validator the validator to add
     */
    public void add(ValidatorInterface validator){
        this.listValidator.add(validator);
    }


    /**
     * Removes a validator from the list at the specified index.
     * @param index the index of the validator to remove
     */
    public void remove(int index){

        this.listValidator.remove(index);
    }

    /**
     * Returns the number of validators in the list.
     * @return the size of the validator list
     */
    public int size(){

        return this.listValidator.size();
    }

}
