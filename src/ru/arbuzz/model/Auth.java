package ru.arbuzz.model;


import android.text.TextUtils;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "auth")
public class Auth {

    @Attribute
    private String login;

    @Attribute
    private String password;

    public Auth() {}

    public Auth(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
