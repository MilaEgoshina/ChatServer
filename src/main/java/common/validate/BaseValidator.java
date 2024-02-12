package common.validate;

import lombok.AllArgsConstructor;

/**
 * Base class for value-based validations
 */

@AllArgsConstructor
public abstract class BaseValidator {

    protected String value;
}
