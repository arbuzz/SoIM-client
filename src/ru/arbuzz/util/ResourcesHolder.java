package ru.arbuzz.util;

import android.content.res.Resources;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class ResourcesHolder {

    private static Resources resources;
    private static String login;
    private static String status;

    public static void init(Resources res) {
        resources = res;
    }

    public static Resources getResources() {
        return resources;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        ResourcesHolder.login = login;
    }

    public static void setStatus(String status) {
        ResourcesHolder.status = status;
    }

    public static String getStatus() {
        return status;
    }
}
