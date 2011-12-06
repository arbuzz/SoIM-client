package ru.arbuzz.task;

import android.os.AsyncTask;
import android.util.Log;
import ru.arbuzz.model.DeleteContactRequest;
import ru.arbuzz.util.SocketUtil;

/**
 * This code is brought to you by
 *
 * @author Olshanikov Konstantin
 */
public class DeleteContactTask extends AsyncTask<Void, Void, Void> {

    private DeleteContactRequest request;

    public DeleteContactTask(DeleteContactRequest request) {
        this.request = request;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            SocketUtil.write(request);
        } catch (Exception e) {
            Log.e("AddContact", "Error while adding contact", e);
        }
        return null;
    }
}
