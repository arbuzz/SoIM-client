package ru.arbuzz.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

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

    private int messagesUnread = 0;

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

    public int getMessagesUnread() {
        return messagesUnread;
    }

    public void setMessagesUnread(int messagesUnread) {
        this.messagesUnread = messagesUnread;
    }

    public void messageRecieved() {
        messagesUnread++;
    }
}
