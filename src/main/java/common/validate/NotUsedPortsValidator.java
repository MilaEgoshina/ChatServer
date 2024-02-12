package common.validate;


import common.Service;
import common.transport.TransportFactory;
import common.transport.TransportListener;

import java.io.IOException;

/**
 * Class - validator for checking if the port is already busy
 */
public class NotUsedPortsValidator extends BaseValidator implements ValidatorInterface{


    TransportFactory transportFactory;

    public NotUsedPortsValidator(String value, TransportFactory transportFactory) {
        super(value);
        this.transportFactory = transportFactory;
    }

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
