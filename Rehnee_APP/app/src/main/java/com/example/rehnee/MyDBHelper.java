package com.example.rehnee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int Database_version = 1;

    // Database Name
    private static final String Database_name = "Rehnee";

    //Table Names
    private static final String Table_patient = "patient";
    private static final String Table_medical_order = "medical_order";
    private static final String Table_record = "record";
    private static final String Table_chat = "chat";
    private static final String Table_faq = "faq";

    //Table_patient - column names
    private static final String patient_column_1 = "Id";
    private static final String patient_column_2 = "password";

    //Table_medical_order - column names
    private static final String medical_order_column_1 = "Id";
    private static final String medical_order_column_2 = "date";
    private static final String medical_order_column_3 = "content";


    //Table_record - column names
    private static final String record_column_1 = "Id";
    private static final String record_column_2 = "medical_date";
    private static final String record_column_3 = "medical_type";
    private static final String record_column_4 = "medical_angle";
    private static final String record_column_5 = "medical_frequency";
    private static final String record_column_6 = "finish_date";
    private static final String record_column_7 = "finish_time";
    private static final String record_column_8 = "spend_time";

    //Table_chat - column names
    private static final String chat_column_1 = "Id";
    private static final String chat_column_2 = "sender";
    private static final String chat_column_3 = "content";
    private static final String chat_column_4 = "date";
    private static final String chat_column_5 = "time";

    //Table_faq - column names
    private static final String faq_column_1 = "type";
    private static final String faq_column_2 = "title";
    private static final String faq_column_3 = "content";

    //Table_patient
    private static final String create_table_patient = "CREATE TABLE " + Table_patient +
            "(" +
            patient_column_1 + " TEXT," +
            patient_column_2 + " TEXT" +
            ");";

    //Table_medical_order
    private static final String create_table_medical_order = "CREATE TABLE " + Table_medical_order +
            "(" +
            medical_order_column_1 + " TEXT," +
            medical_order_column_2 + " TEXT," +
            medical_order_column_3 + " TEXT" +
            ");";

    //Table_record
    private static final String create_table_record = "CREATE TABLE " + Table_record +
            "(" +
            record_column_1 + " TEXT," +
            record_column_2 + " TEXT," +
            record_column_3 + " TEXT," +
            record_column_4 + " TEXT," +
            record_column_5 + " TEXT," +
            record_column_6 + " TEXT," +
            record_column_7 + " TEXT," +
            record_column_8 + " TEXT" +
            ");";

    //Table_record
    private static final String create_table_chat = "CREATE TABLE " + Table_chat +
            "(" +
            chat_column_1 + " TEXT," +
            chat_column_2 + " TEXT," +
            chat_column_3 + " TEXT," +
            chat_column_4 + " TEXT," +
            chat_column_5 + " TEXT" +
            ");";

    //Table_patient
    private static final String create_table_faq = "CREATE TABLE " + Table_faq +
            "(" +
            faq_column_1 + " TEXT," +
            faq_column_2 + " TEXT," +
            faq_column_3 + " TEXT" +
            ");";


    public MyDBHelper(Context context) {
        super(context, Database_name, null, Database_version);
    }


    // 建立應用程式需要的表格

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table_patient);
        db.execSQL(create_table_medical_order);
        db.execSQL(create_table_record);
        db.execSQL(create_table_chat);
        db.execSQL(create_table_faq);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + Table_patient);
        db.execSQL("DROP TABLE IF EXISTS " + Table_medical_order);
        db.execSQL("DROP TABLE IF EXISTS " + Table_record);
        db.execSQL("DROP TABLE IF EXISTS " + Table_chat);
        db.execSQL("DROP TABLE IF EXISTS " + Table_faq);
        // create new tables
        onCreate(db);
    }
}
