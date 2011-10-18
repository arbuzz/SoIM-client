package ru.arbuzz.activity;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import ru.arbuzz.R;
import ru.arbuzz.model.RosterRequest;
import ru.arbuzz.task.ContactListTask;
import ru.arbuzz.util.Config;

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
}
