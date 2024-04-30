package bot;

import client.Client;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

/**
 * The BotTimerTask class extends TimerTask and represents a timer task for sending messages to clients.
 */
public class BotTimerTask extends TimerTask {

    private List<Client> clients;
    final Random random = new Random(0);

    /**
     * Executes the timer task by sending random messages to clients.
     */
    @Override
    public void run() {

        completeTask();
    }

    /**
     * Sends a random message to a random client from the list of clients.
     */
    private void completeTask(){
        int index = 0;
        if (clients.size() > 1){
            index = random.nextInt(clients.size() - 1);
        }
        try {
            clients.get(index).sendMessage("random message " + String.valueOf(index));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the list of clients for the timer task.
     * @param clients the list of clients to set
     */
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    /**
     * Retrieves the list of clients associated with the timer task.
     * @return the list of clients
     */
    public List<Client> getClients() {
        return clients;
    }

}
