package ru.arbuzz.util;

import android.util.Log;
import ru.arbuzz.R;

import java.net.Socket;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class SocketFactory {

    private static Socket socket;

    public static Socket getSocket() {
        if (socket == null) {
            String host = ResourcesHolder.getResources().getString(R.string.host);
            int port = ResourcesHolder.getResources().getInteger(R.integer.port);
            try {
                socket = new Socket(host, port);
            } catch (Exception e) {
                Log.e("Socket", "Error while connecting", e);
            }
        }
        return socket;
    }
}
