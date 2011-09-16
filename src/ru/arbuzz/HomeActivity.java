package ru.arbuzz;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class HomeActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private Socket socket;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button button = (Button) findViewById(R.id.button);
        try {
            socket = new Socket("192.168.0.102", 5222);
        } catch (Exception e) {
            button.setText("Exception");
        }
        new ListenerThread(socket, button).start();
    }

    public void onSendClick(View view) {
        Button button = (Button) view;
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.print("<message from=\"qwe\" to=\"ere\"><body>asdasd</body></message>");
            out.flush();
        } catch (Exception e) {
            button.setText("Exception!");
            Log.e("socket", "", e);
        }
    }
}
