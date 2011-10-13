package ru.arbuzz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ru.arbuzz.R;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button loginBtn = (Button) findViewById(R.id.login_btn);

    }

    private class OnLoginBtnClickListener implements View.OnClickListener {

        public void onClick(View view) {
            EditText login = (EditText) findViewById(R.id.login);
            EditText password = (EditText) findViewById(R.id.password);

        }
    }
}
