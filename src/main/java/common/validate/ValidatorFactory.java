package common.validate;


import common.transport.TransportFactory;

/**
 * The ValidatorFactory class provides static methods for creating instances of various Validators.
 */
public class ValidatorFactory {

    /**
     * Creates a RangeNumberValidation Validator with the specified value, minimum, and maximum values.
     *
     * @param value the value to validate
     * @param min the minimum value of the range
     * @param max the maximum value of the range
     * @return a RangeNumberValidation Validator
     */
    public static ValidatorInterface getRangeNumberValidation(String value,int min, int max){

        return new RangeNumberValidation(value,min,max);
    }

    /**
     * Creates an ValidateIntegerNumber Validator with the specified value.
     *
     * @param value the value to validate as an integer number
     * @return an ValidateIntegerNumber Validator
     */
    public static ValidatorInterface getIntegerNumberValidator(String value){

        return new ValidateIntegerNumber(value);
    }

    /**
     * Creates an IpValidator Validator with the specified value.
     *
     * @param value the IP address value to validate
     * @return an IpValidator Validator
     */
    public static ValidatorInterface getIpValidator(String value){

        return new IpValidator(value);
    }

    /**
     * Creates a NotUsedPortsValidator Validator with the specified value and transport factory.
     *
     * @param value the value representing a port number to validate
     * @param transportFactory the TransportFactory object for creating listeners
     * @return a NotUsedPortsValidator Validator
     */
    public static ValidatorInterface getNotUsedPortValidation(String value, TransportFactory transportFactory){

        return new NotUsedPortsValidator(value,transportFactory);
    }

    /**
     * Creates a compound PortValidation Validator with integer number, range and not used port validation.
     *
     * @param value the value representing a port number to validate
     * @param minPort the minimum port number allowed
     * @param maxPort the maximum port number allowed
     * @param withValidationIsUsed flag to indicate whether to perform not used port validation
     * @param transportFactory the TransportFactory object for creating listeners
     * @return a validation chain of PortValidation Validator
     */
    public static ValidatorInterface getPortValidation(String value, int minPort, int maxPort, Boolean withValidationIsUsed,
                                                       TransportFactory transportFactory){

        ListValidation portValidation = new ListValidation();
        portValidation.add(getIntegerNumberValidator(value));
        portValidation.add(getRangeNumberValidation(value,minPort,maxPort));
        if(withValidationIsUsed) portValidation.add(getNotUsedPortValidation(value,transportFactory));
        return portValidation;
    }
}
