package com.example.somnathyadav.quizlistingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Somnath Yadav on 11-09-2018.
 */

public class Database_Ques extends SQLiteOpenHelper {


    //DB version, table and database name
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "quiz";
    private static final String DB_TABLE = "quiztable";
    private static final String TABLE_SIGNUP = "signuptable";

    //table column names
    private static final String KEY_ID = "id";
    private static final String KEY_QUES = "quizque";
    private static final String KEY_ANSWER = "quizanswer";
    private static final String KEY_OPTA = "optionA";
    private static final String KEY_OPTB = "optionB";
    private static final String KEY_OPTC = "optionC";
    private static final String KEY_OPTD = "optionD";
    private static final String KEY_OPTE = "optionE";

    //table signup
    private static final String NAME = "userename";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private SQLiteDatabase dbase;
    private int rowCount = 0;

    public Database_Ques(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String createtable = "CREATE TABLE " + DB_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, " + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT , " + KEY_OPTD + " TEXT, " + KEY_OPTE + " TEXT);";
        String createtable2 = "CREATE TABLE " + TABLE_SIGNUP + "(" + NAME + " TEXT, " + EMAIL + " TEXT, " + PASSWORD + " TEXT);";


        db.execSQL(createtable);
        db.execSQL(createtable2);
        addQuestions_Objs();
    }

    private void addQuestions_Objs() {
        Questions_Obj q1 = new Questions_Obj("What is 4+4", "7", "8", "19", "41", "80", "8");
        this.addQuestions_ObjToDB(q1);
        Questions_Obj q2 = new Questions_Obj("What is 5+4", "90", "92", "10", "9", "80", "9");
        this.addQuestions_ObjToDB(q2);
        Questions_Obj q3 = new Questions_Obj("What is 40-4", "44", "36", "90", "1", "80", "36");
        this.addQuestions_ObjToDB(q3);
        Questions_Obj q4 = new Questions_Obj("What is 50+4", "8", "96", "54", "-1", "80", "54");
        this.addQuestions_ObjToDB(q4);
        Questions_Obj q5 = new Questions_Obj("What is 4+34", "83", "94", "38", "-1", "80", "38");
        this.addQuestions_ObjToDB(q5);
        Questions_Obj q6 = new Questions_Obj("What is 40+34", "73", "29", "38", "74", "80", "74");
        this.addQuestions_ObjToDB(q6);
        Questions_Obj q7 = new Questions_Obj("What is 14+34", "95", "93", "48", "32", "8", "48");
        this.addQuestions_ObjToDB(q7);
        Questions_Obj q8 = new Questions_Obj("What is 34+34", "68", "90", "98", "91", "80", "68");
        this.addQuestions_ObjToDB(q8);
        Questions_Obj q9 = new Questions_Obj("What is 44+44", "68", "44", "88", "91", "80", "88");
        this.addQuestions_ObjToDB(q9);
        Questions_Obj q10 = new Questions_Obj("What is 55+55", "60", "110", "308", "93", "80", "110");
        this.addQuestions_ObjToDB(q10);
        Questions_Obj q11 = new Questions_Obj("What is 34+30", "64", "94", "28", "91", "80", "64");
        this.addQuestions_ObjToDB(q11);


    }

    public void addQuestions_ObjToDB(Questions_Obj q) {
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, q.getQue());
        values.put(KEY_ANSWER, q.getAnswer());
        values.put(KEY_OPTA, q.getOptA());
        values.put(KEY_OPTB, q.getOptB());
        values.put(KEY_OPTC, q.getOptC());
        values.put(KEY_OPTD, q.getOptD());
        values.put(KEY_OPTE, q.getOptE());
        dbase.insert(DB_TABLE, null, values);
    }

    public void addUser(String Username, String email, String password) {
        dbase=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, Username);
        contentValues.put(EMAIL, email);
        contentValues.put(PASSWORD, password);
        dbase.insert(TABLE_SIGNUP, null, contentValues);
        dbase.close();

    }

    public List<Questions_Obj> getAllQuestions_Objs() {

        List<Questions_Obj> Questions_ObjList = new ArrayList<Questions_Obj>();
        dbase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DB_TABLE;
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        rowCount = cursor.getCount();

        if (cursor.moveToFirst()) {
            do {
                Questions_Obj q = new Questions_Obj();
                q.setId(cursor.getInt(0));
                q.setQue(cursor.getString(1));
                q.setAnswer(cursor.getString(2));
                q.setOptA(cursor.getString(3));
                q.setOptB(cursor.getString(4));
                q.setOptC(cursor.getString(5));
                q.setOptD(cursor.getString(6));
                q.setOptE(cursor.getString(7));

                Questions_ObjList.add(q);

            } while (cursor.moveToNext());
        }
        return Questions_ObjList;
    }

    public String returnName() {

        String name="";
        dbase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_SIGNUP;
        Cursor cursor = dbase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                name=cursor.getString(0);
            } while (cursor.moveToNext());
        }

        return  name;

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
        onCreate(db);
    }

    public int getRowCount(){
        return rowCount;
    }
}



