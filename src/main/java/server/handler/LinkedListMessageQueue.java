package server.handler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Queue for message processing
 */
public class LinkedListMessageQueue<T> implements MessageQueue<T>{

    private LinkedList<T> queue = new LinkedList<>();
    private int minThreads;
    private int maxThreads;
    private int currentThreads = 0;
    private List<HandleThread> threadPool = new ArrayList<>();
    private boolean running = true;
    private MessageHandler<T> messageHandler;

    public LinkedListMessageQueue(int minThreads, int maxThreads, MessageHandler<T> messageHandler) {
        this.minThreads = minThreads;
        this.maxThreads = maxThreads;
        this.messageHandler = messageHandler;
        currentThreads = this.minThreads;

        for(int i = 0; i < this.minThreads; i++){

            createHandleThread();
        }
    }

    /**
     * Add new message - request in processing
     */
    @Override
    public synchronized void add(T message) {

        //add message to queue
        queue.addLast(message);

        //search for a free thread to process requests
        boolean availableThread = false;
        for(HandleThread thread : threadPool){
            if(!thread.isProcessing()){
                availableThread = true;
                break;
            }
        }
        //if there is not any free thread, so we need to create a new one
        if(!availableThread){
            if(currentThreads < this.maxThreads)
                createHandleThread();
        }

        notifyAll();
    }

    /**
     * Get the request from queue
     */
    @Override
    public T getNextMessage() {
        //if queue is empty, put the thread to sleep
        while (queue.isEmpty()){
            try {

                if(!running)
                    return null;
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        //get request from queue
        return queue.removeFirst();
    }

    /**
     * Stop the queue processing
     */
    @Override
    public void shutdown(){

        running = false;

        for (HandleThread thread : threadPool){

            thread.killThread();

        }

        threadPool.clear();

        //wake up sleeping threads
        notifyAll();
    }

    /**
     * Get number of threads in pool
     */
    @Override
    public int getCountThreads() {
        return threadPool.size();
    }

    /**
     * Create new thread
     */
    private void createHandleThread(){

        HandleThread thread = new SimpleHandleThread(this,messageHandler);
        thread.start();
        threadPool.add(thread);

    }
}
