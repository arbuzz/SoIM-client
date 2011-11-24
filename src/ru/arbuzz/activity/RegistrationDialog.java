package ru.arbuzz.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import ru.arbuzz.R;
import ru.arbuzz.model.BaseResponse;
import ru.arbuzz.model.RegistrationRequest;
import ru.arbuzz.task.RegistrationTask;
import ru.arbuzz.util.MessageHandler;

import java.util.Observable;
import java.util.Observer;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class RegistrationDialog extends Dialog implements Observer {

    private LoginActivity context;
    private ProgressDialog dialog;

    public RegistrationDialog(LoginActivity context) {
        super(context);
        this.context = context;
        dialog = new ProgressDialog(context);
        dialog.setMessage(context.getString(R.string.loading_text));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_dialog);

        Button registrationBtn = (Button) findViewById(R.id.register_btn);
        registrationBtn.setOnClickListener(new OnRegistrationBtnClickListener());

        MessageHandler.getInstance().deleteObserver(context);
        MessageHandler.getInstance().addObserver(this);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                MessageHandler.getInstance().deleteObserver(RegistrationDialog.this);
                MessageHandler.getInstance().addObserver(context);
            }
        });
    }

    private class OnRegistrationBtnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            EditText login = (EditText) findViewById(R.id.login);
            EditText password = (EditText) findViewById(R.id.password);
            dialog.show();
            new RegistrationTask(new RegistrationRequest(login.getText().toString(), password.getText().toString())).execute();
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof BaseResponse) {
            BaseResponse response = (BaseResponse) o;
            if (response.getResultCode() == BaseResponse.OK) {
                context.makeToast(R.string.register_toast_succeed_text);
            } else {
                context.makeToast(R.string.register_toast_failed_text);
            }
            dialog.dismiss();
            MessageHandler.getInstance().deleteObserver(this);
            MessageHandler.getInstance().addObserver(context);
            this.dismiss();
        }
    }


}
