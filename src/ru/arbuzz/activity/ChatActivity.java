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
import ru.arbuzz.adapter.MessageAdapter;
import ru.arbuzz.model.Message;
import ru.arbuzz.task.SendMessageTask;
import ru.arbuzz.util.MessageHandler;
import ru.arbuzz.util.ResourcesHolder;
import ru.arbuzz.util.XMPPService;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class ChatActivity extends BaseListActivity implements View.OnClickListener {

    public static final String USER_CHAT_KEY = "user-chat";
    public static final String MESSAGES_UNREAD_KEY = "messages-unread";

    private String userTo;

    private List<Message> messages = new ArrayList<Message>();
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        ArrayList<Message> messages = (ArrayList<Message>) getIntent().getSerializableExtra(MESSAGES_UNREAD_KEY);
        if (messages != null) {
            this.messages.addAll(messages);
        }

        adapter = new MessageAdapter(this, this.messages);
        setListAdapter(adapter);

        userTo = getIntent().getStringExtra(USER_CHAT_KEY);

        Button button = (Button) findViewById(R.id.send_message_btn);
        button.setOnClickListener(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (!(o instanceof Message))
            return;
        Message message = (Message) o;

        messages.add(message);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Message message = new Message();
        EditText messageText = (EditText) findViewById(R.id.message_text);
        message.setBody(messageText.getText().toString());
        message.setTo(userTo);
        message.setFrom(ResourcesHolder.getLogin());
        message.setDate(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        new SendMessageTask(this, message).execute();

        messages.add(message);
        adapter.notifyDataSetChanged();
        messageText.setText("");
    }
}
