package ru.arbuzz.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * This code is brought to you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "setStatus")
public class SetStatusRequest {

    @Attribute
    private String user;
    @Attribute
    private String status;

    public SetStatusRequest() {}

    public SetStatusRequest(String user, String status) {
        this.user = user;
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
