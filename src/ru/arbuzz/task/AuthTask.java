package ru.arbuzz.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import ru.arbuzz.R;
import ru.arbuzz.activity.ContactListActivity;
import ru.arbuzz.model.Auth;
import ru.arbuzz.model.AuthResponse;
import ru.arbuzz.util.Config;
import ru.arbuzz.util.SocketFactory;
import ru.arbuzz.util.XMLUtil;

import java.io.DataInputStream;
import java.net.Socket;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class AuthTask extends AsyncTask<Void, Void, AuthResponse> {

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
    protected AuthResponse doInBackground(Void... voids) {
        try {
            Socket socket = SocketFactory.getSocket();
            socket.getOutputStream().write(XMLUtil.serialize(auth).getBytes());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            int length = dis.readInt();
            if (length == 0) {
                return null;
            }
            byte[] data = new byte[length];
            dis.readFully(data);
            AuthResponse response = (AuthResponse) XMLUtil.parse(new String(data));
            return response;
        } catch (Exception e) {
            Log.e("Auth", "Error while authorization", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(AuthResponse response) {
        if (response != null && response.getResultCode() == AuthResponse.OK) {
            Intent intent = new Intent(context, ContactListActivity.class);
            intent.putExtra(Config.LOGIN, auth.getLogin());
            context.startActivity(intent);
        }
        dialog.dismiss();
    }
}
