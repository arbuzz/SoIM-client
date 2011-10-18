package ru.arbuzz.adapter;

import android.app.ListActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ru.arbuzz.R;
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
            view.setTag(holder);
        } else {
            view = convertView;
        }
        ContactListViewHolder holder = (ContactListViewHolder) view.getTag();
        holder.getName().setText(element.getName());
        return view;
    }
}
