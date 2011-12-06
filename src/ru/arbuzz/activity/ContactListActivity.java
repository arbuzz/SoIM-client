package ru.arbuzz.activity;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import ru.arbuzz.R;
import ru.arbuzz.adapter.ContactListAdapter;
import ru.arbuzz.adapter.ContactListViewHolder;
import ru.arbuzz.model.*;
import ru.arbuzz.task.ContactListTask;
import ru.arbuzz.task.DeleteContactTask;
import ru.arbuzz.task.GoneOfflineTask;
import ru.arbuzz.util.*;

import java.util.*;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class ContactListActivity extends BaseListActivity {

    private ContactListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list);

        String login = getIntent().getStringExtra(Config.LOGIN);

        showProgressDialog();
        new ContactListTask(new RosterRequest(login)).execute();

        registerForContextMenu(getListView());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contact_list_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.contacts_context_menu_delete:
                showProgressDialog();
                new DeleteContactTask(new DeleteContactRequest(ResourcesHolder.getLogin(),
                        ((RosterElement) getListView().getAdapter().getItem(info.position)).getName())).execute();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_find_people:
                Intent intent = new Intent(this, FindPeopleActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_refresh:
                showProgressDialog();
                new ContactListTask(new RosterRequest(ResourcesHolder.getLogin())).execute();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contacts_menu, menu);
        return true;
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        RosterElement element = adapter.getItem(position);
        ArrayList<Message> messagesUnread = element.getMessagesUnread();
        element.setMessagesUnread(new ArrayList<Message>());
        adapter.notifyDataSetChanged();

        ContactListViewHolder holder = (ContactListViewHolder) view.getTag();
        String contactName = (String) holder.getName().getText();
        Intent intent = new Intent(this, ChatActivity.class);
        if (messagesUnread.size() > 0) {
            intent.putExtra(ChatActivity.MESSAGES_UNREAD_KEY, messagesUnread);
        }
        intent.putExtra(ChatActivity.USER_CHAT_KEY, contactName);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        new GoneOfflineTask().execute();
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
                    Collections.sort(contacts, new Roster.RosterElementComparator());
                    adapter = new ContactListAdapter(ContactListActivity.this, contacts);
                    setListAdapter(adapter);
                }
            });
            dismissProgressDialog();
        } else if (o instanceof Presence) {
            final Presence presence = (Presence) o;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (adapter != null)
                        adapter.setStatus(presence);
                }
            });
        } else if (o instanceof Message) {
            final Message message = (Message) o;
            SQLManager.getInstance().insert(message);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (adapter != null)
                        adapter.setUnreadMessage(message);
                }
            });
        } else if (o instanceof BaseResponse) {
            dismissProgressDialog();
        }
    }

}
