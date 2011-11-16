package ru.arbuzz.activity;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import ru.arbuzz.R;
import ru.arbuzz.adapter.ContactListAdapter;
import ru.arbuzz.adapter.ContactListViewHolder;
import ru.arbuzz.model.Roster;
import ru.arbuzz.model.RosterElement;
import ru.arbuzz.model.RosterRequest;
import ru.arbuzz.task.ContactListTask;
import ru.arbuzz.util.Config;
import ru.arbuzz.util.MenuUtil;
import ru.arbuzz.util.MessageHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class ContactListActivity extends BaseListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list);

        String login = getIntent().getStringExtra(Config.LOGIN);

        showProgressDialog();
        new ContactListTask(new RosterRequest(login)).execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = MenuUtil.menuItemClicked(this, item);
        if (!result) {
            result = super.onOptionsItemSelected(item);
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contacts_menu, menu);
        return true;
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        ContactListViewHolder holder = (ContactListViewHolder) view.getTag();
        String contactName = (String) holder.getName().getText();
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(ChatActivity.USER_CHAT_KEY, contactName);
        startActivity(intent);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof Roster) {
            final Roster roster = (Roster) o;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    List<RosterElement> contacts;
                    if (roster != null && roster.getContacts() != null) {
                        contacts = roster.getContacts();
                    } else {
                        contacts = new ArrayList<RosterElement>();
                    }
                    setListAdapter(new ContactListAdapter(ContactListActivity.this, contacts));
                }
            });
            dismissProgressDialog();
            MessageHandler.getInstance().deleteObserver(this);
        }
    }

}
