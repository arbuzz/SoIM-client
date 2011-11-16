package ru.arbuzz.task;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import ru.arbuzz.model.Message;
import ru.arbuzz.util.MessageHandler;
import ru.arbuzz.util.SocketUtil;
import ru.arbuzz.util.XMPPService;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.Observable;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class ListenerTask extends AsyncTask<Void, Void, Void> {

    private Context context;

    public ListenerTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Object message = SocketUtil.read();
            MessageHandler.getInstance().messageReceived(message);
        } catch (Exception e) {
            Log.e("asd", "asd", e); // TODO debug
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        scheduleNextRun();
    }

    private void scheduleNextRun() {
        Intent service = new Intent(context.getApplicationContext(), XMPPService.class);
        PendingIntent pendingIntent = PendingIntent.getService(
                context.getApplicationContext(), 0, service, 0);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.add(Calendar.SECOND, 1);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    }
}
