package ru.arbuzz.util;

import android.content.res.Resources;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class ResourcesHolder {

    private static Resources resources;

    public static void init(Resources res) {
        resources = res;
    }

    public static Resources getResources() {
        return resources;
    }
}
