package ru.arbuzz.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ru.arbuzz.R;
import ru.arbuzz.model.RegistrationRequest;
import ru.arbuzz.task.RegistrationTask;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class RegistrationDialog extends Dialog {

    private Activity context;

    public RegistrationDialog(Activity context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_dialog);

        Button registrationBtn = (Button) findViewById(R.id.register_btn);
        registrationBtn.setOnClickListener(new OnRegistrationBtnClickListener());
    }

    private class OnRegistrationBtnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            EditText login = (EditText) findViewById(R.id.login);
            EditText password = (EditText) findViewById(R.id.password);
            new RegistrationTask(context, RegistrationDialog.this,
                    new RegistrationRequest(login.getText().toString(), password.getText().toString())).execute();
        }
    }
}
