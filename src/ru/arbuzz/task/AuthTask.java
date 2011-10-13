package ru.arbuzz.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import ru.arbuzz.R;
import ru.arbuzz.model.Auth;
import ru.arbuzz.model.BaseResponse;
import ru.arbuzz.util.SocketFactory;
import ru.arbuzz.util.XMLUtil;

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
            SocketFactory.getSocket().getOutputStream().write(XMLUtil.serialize(auth).getBytes());
            return null;
        } catch (Exception e) {
            // TODO really catch exception!
            return null;
        }
    }
}
