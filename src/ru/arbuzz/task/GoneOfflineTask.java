package ru.arbuzz.task;

import android.os.AsyncTask;
import android.util.Log;
import ru.arbuzz.model.Presence;
import ru.arbuzz.util.ResourcesHolder;
import ru.arbuzz.util.SocketUtil;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class GoneOfflineTask extends AsyncTask<Void, Void, Void> {


    public GoneOfflineTask() {}

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            SocketUtil.write(new Presence(ResourcesHolder.getLogin(), Presence.OFFLINE));
            ResourcesHolder.setLogin(null);
        } catch (Exception e) {
            Log.e("Gone offline", "Error while gone offline", e);
        }
        return null;
    }
}
