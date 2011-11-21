package ru.arbuzz.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "contact")
public class RosterElement {

    @Text
    private String name;
    @Attribute
    private boolean online;

    private ArrayList<Message> messagesUnread = new ArrayList<Message>();

    public RosterElement() {}

    public RosterElement(String name, boolean online) {
        this.name = name;
        this.online = online;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public ArrayList<Message> getMessagesUnread() {
        return messagesUnread;
    }

    public void setMessagesUnread(ArrayList<Message> messagesUnread) {
        this.messagesUnread = messagesUnread;
    }

    public void messageReceived(Message message) {
        messagesUnread.add(message);
    }
}
