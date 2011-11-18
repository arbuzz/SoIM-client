package ru.arbuzz.adapter;

import android.widget.TextView;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class ContactListViewHolder {

    private TextView name;
    private TextView unreadMessages;

    public ContactListViewHolder() {}

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public TextView getUnreadMessages() {
        return unreadMessages;
    }

    public void setUnreadMessages(TextView unreadMessages) {
        this.unreadMessages = unreadMessages;
    }
}
