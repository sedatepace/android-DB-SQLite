package com.sedatepace.sqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context) {
        super(context, "subjectDB", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE sdb ( "
                                + "_id INTEGER PRIMARY KEY AUTOINCREMENT , "
                                + "name TEXT, "
                                + "subject TEXT, "
                                + "score INTEGER"
                        + ");"
                    );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS sdb");
        onCreate(db);

    }
}
