package ru.arbuzz.task;

import android.os.AsyncTask;
import android.util.Log;
import ru.arbuzz.util.SocketUtil;

/**
 * This code is brought to you by
 *
 * @author Olshanikov Konstantin
 */
public class WriteTask extends AsyncTask<Void, Void, Void> {

    private Object object;

    public WriteTask(Object object) {
        this.object = object;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            SocketUtil.write(object);
        } catch (Exception e) {
            Log.e("Write task", "Error while adding contact", e);
        }
        return null;
    }
}
