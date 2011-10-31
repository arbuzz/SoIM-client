package ru.arbuzz.activity;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import ru.arbuzz.R;
import ru.arbuzz.model.RosterRequest;
import ru.arbuzz.task.ContactListTask;
import ru.arbuzz.util.Config;
import ru.arbuzz.util.MenuUtil;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class ContactListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list);

        String login = getIntent().getStringExtra(Config.LOGIN);

        new ContactListTask(this, new RosterRequest(login)).execute();
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
}
