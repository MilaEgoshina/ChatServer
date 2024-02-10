package common.json.bodymessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BodyMessage {

    private String command;
    private String nickname;
    private String ip;
    private String text;
    private int port;
}
