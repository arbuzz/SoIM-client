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
public class FindTask extends AsyncTask<Void, Void, FindResponse> {

    private ListActivity context;
    private FindRequest request;
    private ProgressDialog dialog;

    public FindTask(ListActivity context, FindRequest request) {
        this.context = context;
        this.request = request;
        this.dialog = new ProgressDialog(context);
        this.dialog.setMessage(context.getString(R.string.loading_text));
    }

    @Override
    protected void onPreExecute() {
        dialog.show();
    }

    @Override
    protected FindResponse doInBackground(Void... voids) {
        try {
            SocketUtil.write(request);
            return SocketUtil.read(FindResponse.class);
        } catch (Exception e) {
            Log.e("Find", "Error in find task", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(FindResponse response) {
        if (response != null) {
            if (response.getUsers() != null) {
                context.setListAdapter(new FindAdapter(context, response.getUsers()));
            } else {
                Toast.makeText(context, R.string.find_no_result, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, R.string.find_error_text, Toast.LENGTH_SHORT).show();
        }
        dialog.dismiss();
    }
}
