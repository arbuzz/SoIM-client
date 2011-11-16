package ru.arbuzz.task;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import ru.arbuzz.R;
import ru.arbuzz.adapter.FindAdapter;
import ru.arbuzz.model.FindRequest;
import ru.arbuzz.model.FindResponse;
import ru.arbuzz.util.SocketUtil;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class FindTask extends AsyncTask<Void, Void, Void> {

    private FindRequest request;

    public FindTask(FindRequest request) {
        this.request = request;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            SocketUtil.write(request);
        } catch (Exception e) {
            Log.e("Find", "Error in find task", e);
        }
        return null;
    }
}
