package com.example.server.lastmessages;

import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.BufferUtils;
import org.apache.commons.collections.buffer.CircularFifoBuffer;

import java.util.Collection;


/**
 * Implementation of working with a list of last messages via CircularFifoBuffer
 */

public class CircularFifoBufferLastMessages implements LastMessages{

    private Buffer lastMessages;

    public CircularFifoBufferLastMessages(int lastMessagesLength){

        lastMessages = BufferUtils.synchronizedBuffer(new CircularFifoBuffer(lastMessagesLength));
    }

    @Override
    public void clear() {

        lastMessages.clear();
    }

    @Override
    public void add(String message) {

        lastMessages.add(message);
    }

    @Override
    public Collection<String> getLastMessages() {
        return lastMessages;
    }
}
