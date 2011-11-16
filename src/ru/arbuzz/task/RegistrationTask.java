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

    public RegistrationTask(RegistrationRequest request) {
        this.request = request;
    }

    @Override
    protected BaseResponse doInBackground(Void... voids) {
        try {
            SocketUtil.write(request);
        } catch (Exception e) {
            Log.e("Registration", "Error while registration", e);
        }
        return null;
    }
}
