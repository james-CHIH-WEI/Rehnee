package com.example.rehnee;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import java.util.Arrays;


public class SQLite_Medical_order {
    private static final String DB_TABLE = "medical_order";
    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    private Activity activity;
    private ContentValues newRow = new ContentValues();

    public SQLite_Medical_order(Activity activity, Context context) {
        this.context = context;
        this.activity = activity;
    }

    public void create() {//建立資料庫、連接資料庫
        // 建立自訂的 FriendDbHelper 物件
        MyDBHelper myDBHelper = new MyDBHelper(context);

        // 取得上面指定的檔名資料庫，如果該檔名不存在就會自動建立一個資料庫檔案
        sqLiteDatabase = myDBHelper.getWritableDatabase();
    }

    public String[][] query(String Id) {
        create();
        String[][] values = new String[0][3];
        String column, from, where;
        int x;


        column = "`Id`,`date`,`content`";
        from = "`medical_order`";
        where = "`Id`= "+Id;
        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT " + column + " FROM " + from + " WHERE " + where, null);


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

    public void insert(String Id, String date, String content) {
        create();
        newRow.put("Id", Id);
        newRow.put("date", date);
        newRow.put("content", content);

        sqLiteDatabase.insert(DB_TABLE, null, newRow);// 將ContentValues中的資料，放至資料表中
        //sqLiteDatabase.delete(DB_TABLE,"Id='"+1+"'",null);
    }

    /*public void update(String Id, String date, String content) {
        create();
        newRow.put("date", date);
        newRow.put("content", content);

        sqLiteDatabase.update(DB_TABLE, newRow, "Id='" + Id + "'", null);
        close();
    }*/

    private void close() {
        sqLiteDatabase.close();
    }
}
