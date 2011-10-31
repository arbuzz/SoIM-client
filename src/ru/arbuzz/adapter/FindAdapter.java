package ru.arbuzz.adapter;

import android.app.ListActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import org.simpleframework.xml.stream.Position;
import ru.arbuzz.R;

import java.util.List;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class FindAdapter extends ArrayAdapter<String> {

    private ListActivity context;

    public FindAdapter(ListActivity context, List<String> objects) {
        super(context, R.layout.find_people_list_item, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        String item = getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.find_people_list_item, null);
        } else {
            view = convertView;
        }
        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(item);
        return view;
    }
}
