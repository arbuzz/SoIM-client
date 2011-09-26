package ru.arbuzz.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class Message {

    @Element
    private String body;
    @Attribute
    private String to;
    @Attribute
    private String from;

    public Message() {}

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
