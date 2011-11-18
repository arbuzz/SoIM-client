package ru.arbuzz.adapter;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ru.arbuzz.R;
import ru.arbuzz.model.Message;
import ru.arbuzz.model.Presence;
import ru.arbuzz.model.RosterElement;

import java.util.List;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class ContactListAdapter extends ArrayAdapter<RosterElement> {

    private ListActivity context;

    public ContactListAdapter(ListActivity context, List<RosterElement> objects) {
        super(context, R.layout.contact_list_item, objects);
        this.context = context;
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
        if (element.getMessagesUnread() > 0) {
            holder.getUnreadMessages().setVisibility(View.VISIBLE);
            holder.getUnreadMessages().setText(Integer.toString(element.getMessagesUnread()));
        } else {
            holder.getUnreadMessages().setVisibility(View.GONE);
            holder.getUnreadMessages().setText("");
        }
        return view;
    }

    public void setStatus(Presence presence) {
        for (int i = 0; i < getCount(); i++) {
            RosterElement element = getItem(i);
            if (element.getName().equals(presence.getName())) {
                element.setOnline(presence.getStatus() == Presence.ONLINE);
                notifyDataSetChanged();
                return;
            }
        }
    }

    public void setUnreadMessage(Message message) {
        for (int i = 0; i < getCount(); i++) {
            RosterElement element = getItem(i);
            if (element.getName().equals(message.getFrom())) {
                element.messageRecieved();
                notifyDataSetChanged();
                return;
            }
        }
    }
}
