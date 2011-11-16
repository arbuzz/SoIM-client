package ru.arbuzz.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class Message {

    @Text
    private String body;
    @Attribute
    private String to;
    @Attribute
    private String from;
    @Attribute
    private String date;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
