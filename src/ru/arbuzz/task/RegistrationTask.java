package ru.arbuzz.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import ru.arbuzz.R;
import ru.arbuzz.activity.RegistrationDialog;
import ru.arbuzz.model.BaseResponse;
import ru.arbuzz.model.RegistrationRequest;
import ru.arbuzz.util.SocketUtil;

import java.net.Socket;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class RegistrationTask extends AsyncTask<Void, Void, BaseResponse> {

    private RegistrationRequest request;
    private Activity context;
    private RegistrationDialog dialog;
    private ProgressDialog progressDialog;

    public RegistrationTask(Activity context, RegistrationDialog dialog, RegistrationRequest request) {
        this.context = context;
        this.dialog = dialog;
        this.request = request;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.loading_text));
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
    }

    @Override
    protected BaseResponse doInBackground(Void... voids) {
        try {
            SocketUtil.write(request);
            return SocketUtil.read(BaseResponse.class);
        } catch (Exception e) {
            Log.e("Registration", "Error while registration", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(BaseResponse response) {
        if (response != null && response.getResultCode() == BaseResponse.OK) {
            Toast.makeText(context, R.string.register_toast_succeed_text, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, R.string.register_toast_failed_text, Toast.LENGTH_SHORT).show();
        }
        progressDialog.dismiss();
        dialog.dismiss();
    }
}
