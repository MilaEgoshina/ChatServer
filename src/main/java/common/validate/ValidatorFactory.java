package common.validate;


import common.transport.TransportFactory;

/**
 * Class - factory for validation elements
 */
public class ValidatorFactory {

    public static ValidatorInterface getRangeNumberValidation(String value,int min, int max){

        return new RangeNumberValidation(value,min,max);
    }

    public static ValidatorInterface getIntegerNumberValidator(String value){

        return new ValidateIntegerNumber(value);
    }

    public static ValidatorInterface getIpValidator(String value){

        return new IpValidator(value);
    }

    public static ValidatorInterface getNotUsedPortValidation(String value, TransportFactory transportFactory){

        return new NotUsedPortsValidator(value,transportFactory);
    }

    public static ValidatorInterface getPortValidation(String value, int minPort, int maxPort, Boolean withValidationIsUsed,
                                                       TransportFactory transportFactory){

        ListValidation portValidation = new ListValidation();
        portValidation.add(getIntegerNumberValidator(value));
        portValidation.add(getRangeNumberValidation(value,minPort,maxPort));
        if(withValidationIsUsed) portValidation.add(getNotUsedPortValidation(value,transportFactory));
        return portValidation;
    }
}
