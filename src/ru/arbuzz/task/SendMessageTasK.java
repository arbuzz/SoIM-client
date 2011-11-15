package ru.arbuzz.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import ru.arbuzz.model.Message;
import ru.arbuzz.util.SocketUtil;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class SendMessageTask extends AsyncTask<Void, Void, Void> {

    private Message message;
    private Activity context;

    public SendMessageTask(Activity context, Message message) {
        this.message = message;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            SocketUtil.write(message);
        } catch (Exception e) {
            Log.e("Error", "Error while sending message", e);
            Toast.makeText(context, "Не вышло отправить сообщение", Toast.LENGTH_SHORT);
        }
        return null;
    }
}
