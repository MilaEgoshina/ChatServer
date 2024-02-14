package bot;

import client.Client;
import lombok.Setter;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

@Setter
public class BotTimerTask extends TimerTask {

    private List<Client> clients;
    final Random random = new Random(0);

    @Override
    public void run() {

        completeTask();
    }

    private void completeTask(){
        int index = 0;
        if(clients.size() > 1){
            index = random.nextInt(clients.size() - 1);
        }try {
            clients.get(index).sendMessage("random message " + String.valueOf(index));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
