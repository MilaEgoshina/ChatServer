package common.validate;


import common.Service;
import common.transport.TransportFactory;
import common.transport.TransportListener;

import java.io.IOException;

/**
 * The NotUsedPortsValidator class extends the BaseValidator class and implements the ValidatorInterface.
 * It validates if a port is not already in use by creating a transport listener.
 */
public class NotUsedPortsValidator extends BaseValidator implements ValidatorInterface{

    TransportFactory transportFactory;

    /**
     * Constructs a NotUsedPortsValidator with the specified value and transport factory.
     * @param value the value representing a port number to validate
     * @param transportFactory the TransportFactory object for creating listeners
     */
    public NotUsedPortsValidator(String value, TransportFactory transportFactory) {
        super(value);
        this.transportFactory = transportFactory;
    }

    /**
     * Validates if the specified port is not already in use by creating a transport listener on that port.
     * @return true if the port is available, false if the port is already in use
     */
    @Override
    public boolean validate() {

        int port = Integer.parseInt(value);
        boolean result = true;
        TransportListener transportListener = null;
        try {
            transportListener = transportFactory.createListener(port, Service.getInstance().getMaxCountConnections(),
                    Service.getInstance().getEncoding());
;        } catch (IOException e) {
            result = false;
        }finally {
            if(transportListener != null){
                try {
                    transportListener.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
