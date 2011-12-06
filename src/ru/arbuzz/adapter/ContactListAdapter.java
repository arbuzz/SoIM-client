package ru.arbuzz.adapter;

import android.app.ListActivity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ru.arbuzz.R;
import ru.arbuzz.model.Message;
import ru.arbuzz.model.Presence;
import ru.arbuzz.model.Roster;
import ru.arbuzz.model.RosterElement;

import java.util.Collections;
import java.util.List;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class ContactListAdapter extends ArrayAdapter<RosterElement> {

    private ListActivity context;
    private List<RosterElement> items;

    public ContactListAdapter(ListActivity context, List<RosterElement> objects) {
        super(context, R.layout.contact_list_item, objects);
        this.context = context;
        this.items = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        RosterElement element = getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.contact_list_item, null);
            ContactListViewHolder holder = new ContactListViewHolder();
            holder.setName((TextView) view.findViewById(R.id.name));
            holder.setUnreadMessages((TextView) view.findViewById(R.id.unread_messages));
            holder.setStatus((TextView) view.findViewById(R.id.status));
            view.setTag(holder);
        } else {
            view = convertView;
        }
        ContactListViewHolder holder = (ContactListViewHolder) view.getTag();
        holder.getName().setText(element.getName());
        if (element.isOnline()) {
            holder.getName().setTextColor(Color.GREEN);
        } else {
            holder.getName().setTextColor(Color.RED);
        }
        if (element.getMessagesUnread().size() > 0) {
            holder.getUnreadMessages().setVisibility(View.VISIBLE);
            holder.getUnreadMessages().setText(Integer.toString(element.getMessagesUnread().size()));
        } else {
            holder.getUnreadMessages().setVisibility(View.GONE);
            holder.getUnreadMessages().setText("");
        }
        if (element.getTextStatus() != null && !element.getTextStatus().equals("")) {
            holder.getStatus().setText(element.getTextStatus());
            holder.getStatus().setVisibility(View.VISIBLE);
        } else {
            holder.getStatus().setVisibility(View.GONE);
        }
        return view;
    }

    public void setStatus(Presence presence) {
        for (int i = 0; i < getCount(); i++) {
            RosterElement element = getItem(i);
            if (element.getName().equals(presence.getName())) {
                element.setOnline(presence.getStatus() == Presence.ONLINE);
                element.setTextStatus(presence.getTextStatus());
                Collections.sort(items, new Roster.RosterElementComparator());
                notifyDataSetChanged();
                return;
            }
        }
    }

    public void setUnreadMessage(Message message) {
        for (int i = 0; i < getCount(); i++) {
            RosterElement element = getItem(i);
            if (element.getName().equals(message.getFrom())) {
                element.messageReceived(message);
                Collections.sort(items, new Roster.RosterElementComparator());
                notifyDataSetChanged();
                return;
            }
        }
    }
}
