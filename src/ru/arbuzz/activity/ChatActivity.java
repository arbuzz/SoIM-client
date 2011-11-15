package ru.arbuzz.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ru.arbuzz.R;
import ru.arbuzz.model.Message;
import ru.arbuzz.task.SendMessageTask;
import ru.arbuzz.util.MessageHandler;
import ru.arbuzz.util.XMPPService;

import java.util.Observable;
import java.util.Observer;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class ChatActivity extends Activity implements Observer, View.OnClickListener {

    public static final String USER_CHAT_KEY = "user-chat";

    private String userTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        userTo = getIntent().getStringExtra(USER_CHAT_KEY);

        Button button = (Button) findViewById(R.id.send_message_btn);
        button.setOnClickListener(this);

        MessageHandler.getInstance().addObserver(this);

        Intent intent = new Intent(getApplicationContext(), XMPPService.class);
        startService(intent);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (!(o instanceof Message))
            return;
        Message message = (Message) o;

        TextView messageText = (TextView) findViewById(R.id.text);
        messageText.setText("Не " + message.getBody() + ", а говно");
    }

    @Override
    public void onClick(View view) {
        Message message = new Message();
        EditText messageText = (EditText) findViewById(R.id.message_text);
        message.setBody(messageText.getText().toString());
        message.setTo(userTo);
        message.setFrom("bababa");
        new SendMessageTask(this, message).execute();
    }
}
