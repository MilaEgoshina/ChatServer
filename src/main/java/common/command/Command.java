package common.command;


import common.enums.Status;
import common.json.bodymessage.BodyMessage;

import java.io.IOException;

/**
 * Interface for working with commands
 */
public interface Command {

    Status execute(BodyMessage bodyMessage) throws IOException;

    String getName();

    String getDescription();

}
