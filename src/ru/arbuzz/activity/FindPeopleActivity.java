package ru.arbuzz.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ru.arbuzz.R;
import ru.arbuzz.model.FindRequest;
import ru.arbuzz.task.FindTask;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class FindPeopleActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_people);



        Button findBtn = (Button) findViewById(R.id.find_button);
        findBtn.setOnClickListener(new OnFindClickListener(false));

        Button findAllBtn = (Button) findViewById(R.id.find_all_button);
        findAllBtn.setOnClickListener(new OnFindClickListener(true));
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
            new FindTask(FindPeopleActivity.this, new FindRequest(criteria)).execute();
        }
    }
}
