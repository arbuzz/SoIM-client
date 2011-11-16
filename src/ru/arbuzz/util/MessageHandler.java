package ru.arbuzz.util;

import ru.arbuzz.model.Auth;
import ru.arbuzz.model.Message;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class MessageHandler extends Observable {

    public static Map<String, List<Message>> messages;

    private static MessageHandler instance;

    public MessageHandler() {}

    public static MessageHandler getInstance() {
        if (instance == null) {
            instance = new MessageHandler();
        }
        return instance;
    }

    public void messageReceived(Object message) {
        setChanged();
        notifyObservers(message);
    }
}
