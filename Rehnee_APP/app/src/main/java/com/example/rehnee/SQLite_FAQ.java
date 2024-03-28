package com.example.rehnee;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Arrays;

public class SQLite_FAQ {

    private static final String DB_TABLE = "faq";
    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    private ContentValues newRow = new ContentValues();

    public SQLite_FAQ(Context context) {
        this.context = context;
    }

    public void create() {//建立資料庫、連接資料庫
        MyDBHelper myDBHelper = new MyDBHelper(context);

        sqLiteDatabase = myDBHelper.getWritableDatabase();
    }

    public String[][] query() {
        create();
        String[][] values = new String[0][3];
        int x;
        String column, from, where;
        column = "`faq`.`type`,`faq`.`title`,`faq`.`content`";
        from = "`faq`";
        where = "1";

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT " + column + " FROM " + from + " WHERE " + where, null);

        //輸出欄位為("type","content")

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            values = Arrays.copyOf(values, values.length + 1);//把陣列長度加一
            values[values.length - 1] = new String[3];//新增陣列空間
            for (x = 0; x < 3; x++) {
                values[0][x] = cursor.getString(x);
            }

            while (cursor.moveToNext()) {
                values = Arrays.copyOf(values, values.length + 1);//把陣列長度加一
                values[values.length - 1] = new String[3];//新增陣列空間
                for (x = 0; x < 3; x++) {
                    values[values.length - 1][x] = cursor.getString(x);
                }
            }
        }
        close();
        return values;
    }

    public void insert(String type, String title, String content) {
        create();
        newRow.put("type", type);
        newRow.put("title", title);
        newRow.put("content", content);

        sqLiteDatabase.insert(DB_TABLE, null, newRow);// 將ContentValues中的資料，放至資料表中
    }

    public void clean_table() {
        create();
        sqLiteDatabase.execSQL("delete from " + DB_TABLE);
    }


    private void close() {
        sqLiteDatabase.close();
    }
}
