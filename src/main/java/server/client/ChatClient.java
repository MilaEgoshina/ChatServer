package server.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

/**
 * Class for interaction with client
 */

public class ChatClient {

    private String nickname;  // Nickname of the client.
    private String ip;  // IP address of the client.
    private int port;  // Port number for communication with the client.
}
