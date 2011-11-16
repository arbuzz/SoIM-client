package ru.arbuzz.task;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import ru.arbuzz.R;
import ru.arbuzz.adapter.ContactListAdapter;
import ru.arbuzz.model.Roster;
import ru.arbuzz.model.RosterRequest;
import ru.arbuzz.util.SocketUtil;

import java.io.DataInputStream;
import java.net.Socket;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class ContactListTask extends AsyncTask<Void, Void, Void> {

    private final RosterRequest request;

    public ContactListTask(RosterRequest request) {
        this.request = request;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            SocketUtil.write(request);
        } catch (Exception e) {
            Log.e("Contact List", "Error while executing contact list task", e);
        }
        return null;
    }
}
