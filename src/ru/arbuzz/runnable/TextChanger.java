package ru.arbuzz.runnable;

import android.widget.TextView;
import ru.arbuzz.model.Message;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class TextChanger implements Runnable {

    private TextView text;
    private Message message;

    public TextChanger(TextView text, Message message) {
        this.text = text;
        this.message = message;
    }

    public void run() {
        text.setText(message.getBody());
    }
}
