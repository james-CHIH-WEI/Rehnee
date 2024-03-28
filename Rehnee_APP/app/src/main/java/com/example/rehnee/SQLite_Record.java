package com.example.rehnee;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import java.util.Arrays;


public class SQLite_Record {
    private static final String DB_TABLE = "record";
    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    private Activity activity;
    private ContentValues newRow = new ContentValues();

    public SQLite_Record(Activity activity, Context context) {
        this.context = context;
        this.activity = activity;
    }

    public void create() {//建立資料庫、連接資料庫
        MyDBHelper myDBHelper = new MyDBHelper(context);

        sqLiteDatabase = myDBHelper.getWritableDatabase();
    }

    public String[][] query(String Id) {
        create();
        String[][] values = new String[0][8];
        int x;
        String column, from, where;
        column = "`Id`,`medical_date`,`medical_type`,`medical_angle`,`medical_frequency`,`finish_date`,`finish_time`,`spend_time`";
        from = "`record`";
        where = "`Id`=" + Id;

        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT " + column + " FROM " + from + " WHERE " + where, null);

        //輸出欄位為(Id, medical_date, medical_type, medical_angle, medical_frequency, finish_date, finish_time, spend_time)

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            values = Arrays.copyOf(values, values.length + 1);//把陣列長度加一
            values[values.length - 1] = new String[8];//新增陣列空間
            for (x = 0; x < 8; x++) {
                values[0][x] = cursor.getString(x);
            }

            while (cursor.moveToNext()) {
                values = Arrays.copyOf(values, values.length + 1);//把陣列長度加一
                values[values.length - 1] = new String[8];//新增陣列空間
                for (x = 0; x < 8; x++) {
                    values[values.length - 1][x] = cursor.getString(x);
                }
            }
        }
        close();
        return values;
    }

    public void insert(String Id, String medical_date, String medical_type, String medical_angle, String medical_frequency, String finish_date, String finish_time, String spend_time) {
        create();
        newRow.put("Id", Id);
        newRow.put("medical_date", medical_date);
        newRow.put("medical_type", medical_type);
        newRow.put("medical_angle", medical_angle);
        newRow.put("medical_frequency", medical_frequency);
        newRow.put("finish_date", finish_date);
        newRow.put("finish_time", finish_time);
        newRow.put("spend_time", spend_time);

        sqLiteDatabase.insert(DB_TABLE, null, newRow);// 將ContentValues中的資料，放至資料表中
    }


    private void close() {
        sqLiteDatabase.close();
    }
}
