package ru.arbuzz.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "find")
public class FindResponse {

    @ElementList(inline = true, entry = "user", required = false)
    private List<String> users;

    public FindResponse() {}

    public FindResponse(List<String> users) {
        this.users = users;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
