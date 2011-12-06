package ru.arbuzz.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ru.arbuzz.R;
import ru.arbuzz.model.Presence;
import ru.arbuzz.model.SetStatusRequest;
import ru.arbuzz.task.WriteTask;
import ru.arbuzz.util.ResourcesHolder;

/**
 * This code is brought to you by
 *
 * @author Olshanikov Konstantin
 */
public class StatusDialog extends Dialog {

    public StatusDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_dialog);

        final EditText status = (EditText) findViewById(R.id.status);
        status.setText(ResourcesHolder.getStatus());
        Button confirm = (Button) findViewById(R.id.confirm);

        if (ResourcesHolder.getStatus() != null)
            status.setText(ResourcesHolder.getStatus());

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResourcesHolder.setStatus(status.getText().toString());
                new WriteTask(new SetStatusRequest(ResourcesHolder.getLogin(), status.getText().toString())).execute();
                dismiss();
            }
        });
    }
}
