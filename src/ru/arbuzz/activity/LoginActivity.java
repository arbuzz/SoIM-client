package ru.arbuzz.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import ru.arbuzz.R;
import ru.arbuzz.model.Auth;
import ru.arbuzz.model.BaseResponse;
import ru.arbuzz.task.AuthTask;
import ru.arbuzz.util.Config;
import ru.arbuzz.util.MessageHandler;
import ru.arbuzz.util.ResourcesHolder;

import java.util.Observable;
import java.util.Observer;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class LoginActivity extends Activity implements Observer {

    private ProgressDialog dialog;
    private Auth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new OnLoginBtnClickListener());

        Button registerBtn = (Button) findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(new OnRegisterBtnClickListener());

        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.loading_text));
    }

    @Override
    protected void onStart() {
        super.onStart();
        MessageHandler.getInstance().addObserver(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MessageHandler.getInstance().deleteObserver(this);
    }

    private class OnLoginBtnClickListener implements View.OnClickListener {

        public void onClick(View view) {
            EditText login = (EditText) findViewById(R.id.login);
            EditText password = (EditText) findViewById(R.id.password);
            dialog.show();
            auth = new Auth(login.getText().toString(), password.getText().toString());
            new AuthTask(auth).execute();
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

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof BaseResponse) {
            BaseResponse response = (BaseResponse) o;
            if (response.getResultCode() == BaseResponse.OK) {
                ResourcesHolder.setLogin(auth.getLogin());
                Intent intent = new Intent(this, ContactListActivity.class);
                intent.putExtra(Config.LOGIN, auth.getLogin());
                ResourcesHolder.setLogin(auth.getLogin());
                startActivity(intent);
            } else {
                makeToast(R.string.login_failed);
            }
            dialog.dismiss();
        }
    }

    protected void makeToast(final int textId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, getString(textId), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
