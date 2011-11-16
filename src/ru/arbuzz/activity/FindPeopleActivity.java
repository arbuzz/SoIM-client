package ru.arbuzz.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import ru.arbuzz.R;
import ru.arbuzz.adapter.FindAdapter;
import ru.arbuzz.model.AddContactRequest;
import ru.arbuzz.model.BaseResponse;
import ru.arbuzz.model.FindRequest;
import ru.arbuzz.model.FindResponse;
import ru.arbuzz.task.AddContactTask;
import ru.arbuzz.task.FindTask;
import ru.arbuzz.util.MessageHandler;
import ru.arbuzz.util.ResourcesHolder;

import java.util.Observable;
import java.util.Observer;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class FindPeopleActivity extends BaseListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_people);

        Button findBtn = (Button) findViewById(R.id.find_button);
        findBtn.setOnClickListener(new OnFindClickListener(false));

        Button findAllBtn = (Button) findViewById(R.id.find_all_button);
        findAllBtn.setOnClickListener(new OnFindClickListener(true));

        registerForContextMenu(getListView());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.find_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.find_context_add_user:
                showProgressDialog();
                new AddContactTask(new AddContactRequest(ResourcesHolder.getLogin(),
                        (String) getListView().getAdapter().getItem(info.position))).execute();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    private class OnFindClickListener implements View.OnClickListener {

        private boolean all;

        public OnFindClickListener(boolean all) {
            this.all = all;
        }

        @Override
        public void onClick(View view) {
            String criteria = null;
            if (!all) {
                EditText criteriaText = (EditText) findViewById(R.id.find_criteria);
                criteria = criteriaText.getText().toString();
            }
            showProgressDialog();
            new FindTask(new FindRequest(criteria)).execute();
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof FindResponse) {
            final FindResponse response = (FindResponse) o;
            if (response.getUsers() != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setListAdapter(new FindAdapter(FindPeopleActivity.this, response.getUsers()));
                    }
                });
            } else {
                makeToast(R.string.find_no_result);
            }
        } else if (o instanceof BaseResponse) {
            BaseResponse response = (BaseResponse) o;
            if (response != null && response.getResultCode() == BaseResponse.OK) {
                makeToast(R.string.add_contact_success_text);
            } else {
                makeToast(R.string.add_contact_failure_text);
            }
        } else if (o == null) {
            makeToast(R.string.find_error_text);
        }
        dismissProgressDialog();
    }
}
