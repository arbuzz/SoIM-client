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
public class ContactListTask extends AsyncTask<Void, Void, Roster> {

    private ListActivity context;
    private final RosterRequest request;
    private ProgressDialog dialog;

    public ContactListTask(ListActivity context, RosterRequest request) {
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
    protected Roster doInBackground(Void... voids) {
        try {
            SocketUtil.write(request);
            return SocketUtil.read(Roster.class);
        } catch (Exception e) {
            Log.e("Contact List", "Error while executing contact list task", e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Roster roster) {
        if (roster != null) {
            if (context.getListAdapter() == null) {
                context.setListAdapter(new ContactListAdapter(context, roster.getContacts()));
            }
        }
        dialog.dismiss();
    }
}
