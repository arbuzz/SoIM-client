package ru.arbuzz.adapter;

import android.app.ListActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ru.arbuzz.R;
import ru.arbuzz.model.Message;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class MessageAdapter extends ArrayAdapter<Message> {

    private ListActivity context;

    public MessageAdapter(ListActivity context, List<Message> objects) {
        super(context, R.layout.chat_list_item, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        Message message = getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.chat_list_item, null);
            MessageViewHolder holder = new MessageViewHolder();
            holder.setBody((TextView) view.findViewById(R.id.body));
            holder.setDate((TextView) view.findViewById(R.id.date));
            holder.setFrom((TextView) view.findViewById(R.id.from));

            view.setTag(holder);
        } else {
            view = convertView;
        }
        MessageViewHolder holder = (MessageViewHolder) view.getTag();
        holder.getDate().setText(message.getDate());
        holder.getFrom().setText(message.getFrom());
        holder.getBody().setText(message.getBody());

        return view;
    }
}
