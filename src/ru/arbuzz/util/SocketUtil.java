package ru.arbuzz.util;

import android.util.Log;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.params.BasicHttpParams;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.w3c.dom.Text;
import ru.arbuzz.R;
import ru.arbuzz.model.BaseResponse;
import ru.arbuzz.model.Message;
import ru.arbuzz.model.Roster;

import java.io.DataInputStream;
import java.io.StringWriter;
import java.net.Socket;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class SocketUtil {

    private static Socket socket;
    private static Serializer serializer = new Persister();

    public static void init() {
        String host = ResourcesHolder.getResources().getString(R.string.host);
        int port = ResourcesHolder.getResources().getInteger(R.integer.port);
        try {
            socket = new PlainSocketFactory().createSocket();
            new PlainSocketFactory().connectSocket(socket, host, port, null, 0, new BasicHttpParams());
        } catch (Exception e) {
            Log.e("Socket", "Error while connecting", e);
        }
    }

    public static void write(Object object) throws Exception {
        socket.getOutputStream().write(serialize(object).getBytes());
    }

    public static <T extends Object> T read(Class<T> clazz) throws Exception {
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        int length = dis.readInt();
        if (length == 0) {
            return null;
        }
        byte[] data = new byte[length];
        dis.readFully(data);
        return (T) parse(new String(data));
    }

//    public static Socket getSocket() {
//        if (socket == null) {
//            String host = ResourcesHolder.getResources().getString(R.string.host);
//            int port = ResourcesHolder.getResources().getInteger(R.integer.port);
//            try {
//                socket = new Socket(host, port);
//            } catch (Exception e) {
//                Log.e("Socket", "Error while connecting", e);
//            }
//        }
//        return socket;
//    }

    private static Object parse(String message) throws Exception {
        Class clazz = null;
        if (message.startsWith("<message"))
            clazz = Message.class;
        if (message.startsWith("<response"))
            clazz = BaseResponse.class;
        if (message.startsWith("<roster"))
            clazz = Roster.class;
        return serializer.read(clazz, message);
    }

    private static String serialize(Object obj) throws Exception {
        StringWriter writer = new StringWriter();
        serializer.write(obj, writer);
        return writer.toString();
    }
}
