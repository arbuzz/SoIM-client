package ru.arbuzz.adapter;

import android.widget.TextView;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class MessageViewHolder {

    private TextView from;
    private TextView date;
    private TextView body;

    public MessageViewHolder() {}

    public TextView getFrom() {
        return from;
    }

    public void setFrom(TextView from) {
        this.from = from;
    }

    public TextView getDate() {
        return date;
    }

    public void setDate(TextView date) {
        this.date = date;
    }

    public TextView getBody() {
        return body;
    }

    public void setBody(TextView body) {
        this.body = body;
    }
}
