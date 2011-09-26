package ru.arbuzz.activity;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ru.arbuzz.R;
import ru.arbuzz.model.Message;
import ru.arbuzz.util.MessageHandler;
import ru.arbuzz.util.SocketFactory;
import ru.arbuzz.util.XMLUtil;

import java.io.*;
import java.util.Observable;
import java.util.Observer;

public class HomeActivity extends Activity implements Observer {

    private EditText messageText;
    private TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        messageText = (EditText) findViewById(R.id.message_text);
        text = (TextView) findViewById(R.id.text);
    }

    public void onSendClick(View view) {
        Button button = (Button) view;
        try {
            PrintWriter out = new PrintWriter(SocketFactory.getSocket().getOutputStream());
            Message msg = new Message();
            msg.setBody(messageText.getText().toString());
            msg.setFrom("Kostya");
            msg.setTo("server");
            out.print(XMLUtil.serialize(msg));
            out.flush();
        } catch (Exception e) {
            button.setText("Exception!");
            Log.e("socket", "", e);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        MessageHandler.getInstance().addObserver(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MessageHandler.getInstance().deleteObserver(this);
    }

    public void update(Observable o, Object arg) {
        final Message msg = (Message) arg;
        runOnUiThread(new Runnable() {
            public void run() {
                text.setText(msg.getBody());
            }
        });
    }
}
