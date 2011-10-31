package ru.arbuzz.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import ru.arbuzz.R;
import ru.arbuzz.activity.ContactListActivity;
import ru.arbuzz.model.Auth;
import ru.arbuzz.model.BaseResponse;
import ru.arbuzz.util.Config;
import ru.arbuzz.util.SocketUtil;

import java.io.DataInputStream;
import java.net.Socket;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class AuthTask extends AsyncTask<Void, Void, BaseResponse> {

    private Activity context;
    private ProgressDialog dialog;
    private Auth auth;

    public AuthTask(Activity context, Auth auth) {
        this.context = context;
        this.dialog = new ProgressDialog(context);
        this.dialog.setMessage(context.getString(R.string.loading_text));
        this.auth = auth;
    }

    @Override
    protected void onPreExecute() {
        dialog.show();
    }

    @Override
    protected BaseResponse doInBackground(Void... voids) {
        try {
            SocketUtil.write(auth);
            return SocketUtil.read(BaseResponse.class);
        } catch (Exception e) {
            Log.e("Auth", "Error while authorization", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(BaseResponse response) {
        if (response != null && response.getResultCode() == BaseResponse.OK) {
            Intent intent = new Intent(context, ContactListActivity.class);
            intent.putExtra(Config.LOGIN, auth.getLogin());
            context.startActivity(intent);
        }
        dialog.dismiss();
    }
}
