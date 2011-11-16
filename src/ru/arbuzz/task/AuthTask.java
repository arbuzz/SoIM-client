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
import ru.arbuzz.util.ResourcesHolder;
import ru.arbuzz.util.SocketUtil;

import java.io.DataInputStream;
import java.net.Socket;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class AuthTask extends AsyncTask<Void, Void, Void> {

    private Auth auth;

    public AuthTask(Auth auth) {
        this.auth = auth;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            SocketUtil.write(auth);
        } catch (Exception e) {
            Log.e("Auth", "Error while authorization", e);
            return null;
        }
        return null;
    }

//    @Override
//    protected void onPostExecute(Void v) {
//        if (response != null && response.getResultCode() == BaseResponse.OK) {
//            ResourcesHolder.setLogin(auth.getLogin());
//            Intent intent = new Intent(context, ContactListActivity.class);
//            intent.putExtra(Config.LOGIN, auth.getLogin());
//            ResourcesHolder.setLogin(auth.getLogin());
//            context.startActivity(intent);
//        }
//        dialog.dismiss();
//    }
}
