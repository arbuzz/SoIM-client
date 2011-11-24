package ru.arbuzz.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import ru.arbuzz.model.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * This code is brought to you by
 *
 * @author Olshanikov Konstantin
 */
public class SQLManager {

    private static SQLManager instance;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "soim.db";
    private static final String MESSAGES = "messages";

    private SQLiteDatabase db;
    private SQLiteStatement insertMessagesStmt;


    private static final String CREATE_TABLE_MESSAGES = "CREATE TABLE messages(id INTEGER PRIMARY KEY, " +
            "fromUser TEXT, toUser TEXT, body TEXT, date TEXT)";
    private static final String INSERT_INTO_MESSAGES = "INSERT INTO messages (fromUser, toUser, body, date) values (?, ?, ?, ?)";

    public static void init(Context context) {
        instance = new SQLManager(context);
    }

    public static SQLManager getInstance() {
        return instance;
    }

    public SQLManager(Context context) {
        OpenHelper helper = new OpenHelper(context);
        db = helper.getWritableDatabase();
        insertMessagesStmt = db.compileStatement(INSERT_INTO_MESSAGES);
    }

    public void insert(Message message) {
        insertMessagesStmt.bindString(1, message.getFrom());
        insertMessagesStmt.bindString(2, message.getTo());
        insertMessagesStmt.bindString(3, message.getBody());
        insertMessagesStmt.bindString(4, message.getDate());

        if (insertMessagesStmt.executeInsert() < 0) {
            Log.e("SQLite" ,"Insert message failed");
        }
    }

    public List<Message> select(String from, String to) {
        List<Message> messages = new ArrayList<Message>();
        Cursor cursor = db.query(MESSAGES, new String[] {"fromUser", "toUser", "body", "date"}, "fromUser='" + from + "' and toUser='" + to + "'",
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Message message = new Message();
                message.setFrom(cursor.getString(0));
                message.setTo(cursor.getString(1));
                message.setBody(cursor.getString(2));
                message.setDate(cursor.getString(3));

                messages.add(message);
            } while (cursor.moveToNext());
        }
        return messages;
    }

    private static class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_MESSAGES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS " + MESSAGES);
            onCreate(db);
        }
    }
}
