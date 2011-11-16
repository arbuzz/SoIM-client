package ru.arbuzz.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import ru.arbuzz.R;
import ru.arbuzz.model.AddContactRequest;
import ru.arbuzz.model.BaseResponse;
import ru.arbuzz.util.SocketUtil;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class AddContactTask extends AsyncTask<Void, Void, Void> {

    private AddContactRequest request;

    public AddContactTask(AddContactRequest request) {
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
