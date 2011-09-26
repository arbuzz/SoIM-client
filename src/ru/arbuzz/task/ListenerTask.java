package ru.arbuzz.task;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import ru.arbuzz.model.Message;
import ru.arbuzz.util.MessageHandler;
import ru.arbuzz.util.XMLUtil;
import ru.arbuzz.util.XMPPService;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.Calendar;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class ListenerTask extends AsyncTask<Void, Void, Void> {

    private Socket socket;
    private Context context;

    public ListenerTask(Socket socket, Context context) {
        this.socket = socket;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            int length = dis.readInt();
            if (length == 0) {
                return null;
            }
            byte[] data = new byte[length];
            dis.read(data);
            String message = new String(data);
            Message msg = (Message) XMLUtil.parse(message);

            MessageHandler.getInstance().messageReceived(msg);
        } catch (Exception e) {
            Log.e("asd", "asd", e);
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
        cal.add(Calendar.SECOND, 5);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    }
}
