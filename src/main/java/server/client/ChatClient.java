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

    private String nickname;
    private String ip;
    private int port;
}
