package ru.arbuzz;

import android.app.Application;
import android.content.Intent;
import ru.arbuzz.util.ResourcesHolder;
import ru.arbuzz.util.SQLManager;
import ru.arbuzz.util.SocketUtil;
import ru.arbuzz.util.XMPPService;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class IMApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SQLManager.init(this);

        ResourcesHolder.init(getResources());
        SocketUtil.init();

        Intent intent = new Intent(getApplicationContext(), XMPPService.class);
        startService(intent);
    }
}
