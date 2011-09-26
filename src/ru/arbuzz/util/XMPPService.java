package ru.arbuzz.util;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import ru.arbuzz.task.ListenerTask;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class XMPPService extends Service {

    @Override
    public void onStart(Intent intent, int startId) {
        ListenerTask task = new ListenerTask(SocketFactory.getSocket(), getApplicationContext());
        task.execute();

        stopSelf();
        super.onStart(intent, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
