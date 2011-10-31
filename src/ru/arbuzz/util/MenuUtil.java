package ru.arbuzz.util;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import ru.arbuzz.R;
import ru.arbuzz.activity.FindPeopleActivity;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class MenuUtil {

    public static boolean menuItemClicked(Activity context, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_find_people:
                findPeopleSelected(context);
                return true;
            default:
                return false;
        }
    }

    public static void findPeopleSelected(Activity context) {
        Intent intent = new Intent(context, FindPeopleActivity.class);
        context.startActivity(intent);
    }
}
