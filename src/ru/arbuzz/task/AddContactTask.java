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
public class AddContactTask extends AsyncTask<Void, Void, BaseResponse> {

    private Activity context;
    private AddContactRequest request;
    private ProgressDialog dialog;

    public AddContactTask(Activity context, AddContactRequest request) {
        this.context = context;
        this.request = request;
        dialog = new ProgressDialog(context);
        dialog.setMessage(context.getString(R.string.loading_text));
    }

    @Override
    protected void onPreExecute() {
        dialog.show();
    }

    @Override
    protected BaseResponse doInBackground(Void... voids) {
        try {
            SocketUtil.write(request);
            return SocketUtil.read(BaseResponse.class);
        } catch (Exception e) {
            Log.e("AddContact", "Error while adding contact", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(BaseResponse response) {
        if (response != null && response.getResultCode() == BaseResponse.OK) {
            Toast.makeText(context, context.getString(R.string.add_contact_success_text), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, context.getString(R.string.add_contact_failure_text), Toast.LENGTH_SHORT).show();
        }
        dialog.dismiss();
    }
}
