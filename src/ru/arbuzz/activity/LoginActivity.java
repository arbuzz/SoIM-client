package ru.arbuzz.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ru.arbuzz.R;
import ru.arbuzz.model.Auth;
import ru.arbuzz.task.AuthTask;

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
        loginBtn.setOnClickListener(new OnLoginBtnClickListener());

        Button registerBtn = (Button) findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(new OnRegisterBtnClickListener());
    }

    private class OnLoginBtnClickListener implements View.OnClickListener {

        public void onClick(View view) {
            EditText login = (EditText) findViewById(R.id.login);
            EditText password = (EditText) findViewById(R.id.password);
            new AuthTask(LoginActivity.this, new Auth(login.getText().toString(), password.getText().toString())).execute();
        }
    }

    private class OnRegisterBtnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Dialog registerDialog = new RegistrationDialog(LoginActivity.this);
            registerDialog.setTitle(LoginActivity.this.getString(R.string.register_dialog_title));
            registerDialog.show();
        }
    }
}
