package ru.arbuzz.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "addContact")
public class AddContactRequest {

    @Attribute
    private String user;

    @Attribute
    private String contact;

    public AddContactRequest() {}

    public AddContactRequest(String user, String contact) {
        this.user = user;
        this.contact = contact;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
