package ru.arbuzz.activity;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Message;
import android.widget.Toast;
import ru.arbuzz.R;
import ru.arbuzz.util.MessageHandler;

import java.util.Observable;
import java.util.Observer;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public abstract class BaseListActivity extends ListActivity implements Observer {

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    @Override
    public abstract void update(Observable observable, Object o);

    public void showProgressDialog() {
        dialog.show();
    }

    public void dismissProgressDialog() {
        dialog.dismiss();
    }

    protected void makeToast(final int textId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseListActivity.this, getString(textId), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
