package ru.arbuzz.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "presence")
public class Presence {

    public static final int ONLINE = 1;
    public static final int OFFLINE = 9;

    @Attribute
    private String name;
    @Attribute
    private int status;
    @Attribute(required = false)
    private String textStatus;

    public Presence() {}

    public Presence(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public Presence(String name, String textStatus) {
        this.name = name;
        this.status = ONLINE;
        this.textStatus = textStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTextStatus() {
        return textStatus;
    }

    public void setTextStatus(String textStatus) {
        this.textStatus = textStatus;
    }
}
