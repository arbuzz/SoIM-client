package ru.arbuzz;

import android.util.Log;
import android.widget.Button;
import org.msgpack.MessagePack;

import java.io.*;
import java.net.Socket;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class ListenerThread extends Thread {

    private Socket socket;
    private Button button;

    public ListenerThread(Socket socket, Button button) {
        this.socket = socket;
        this.button = button;
    }

    @Override
    public void run() {
        try {
            for (; ;) {
                InputStream in = socket.getInputStream();
                long length = in.available();
                if (length == 0)
                    return;
                byte[] data = new byte[(int) length];
                String str = MessagePack.unpack(data, String.class);
//                char[] data = new char[c];
//                reader.read(data);
//                String message = new String(data);
                Log.e("ASDASD", str);
            }
        } catch (Exception e) {
            Log.e("asd", "asd", e);
        }
    }

    public static String readInputStreamAsString(InputStream in)
            throws IOException {

        BufferedInputStream bis = new BufferedInputStream(in);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result = bis.read();
        while (result != -1) {
            byte b = (byte) result;
            buf.write(b);
            result = bis.read();
        }
        return buf.toString();
    }
}
