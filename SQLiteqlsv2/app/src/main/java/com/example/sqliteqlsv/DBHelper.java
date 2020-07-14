package com.example.sqliteqlsv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBName = "mydb.db";
    private static final int VERSION = 1;

    private static final String TABLE_NAME = "SinhVien";
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String YEAROB = "yearob";

    private SQLiteDatabase myDB;

    public DBHelper(Context context)
    {
        super(context, DBName, null, VERSION);
    }

    public static String getID()
    {
        return ID;
    }

    public static String getNAME()
    {
        return NAME;
    }

    public static String getYEAROB()
        {
        return YEAROB;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTable = "CREATE TABLE " +
                TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT NOT NULL, " +
                YEAROB + " INTEGER NOT NULL" + ")";
        db.execSQL(queryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void OpenDB() {
        myDB = getWritableDatabase();
    }

    public void Close() {
        if (myDB != null && myDB.isOpen()) {
            myDB.close();
        }

    }

    public long Insert(int id, String name, int yearob) {
        ContentValues Values = new ContentValues();
        Values.put(ID ,id);
        Values.put(NAME,name);
        Values.put(YEAROB,yearob);
        return myDB.insert(TABLE_NAME,null,Values);
    }
    public long Update(int id , String name , int yearob){
        ContentValues Values = new ContentValues();
        Values.put(ID,id);
        Values.put(NAME, name);
        Values.put(YEAROB,yearob);
        String where  = ID + " = "+ id;
        return myDB.update(TABLE_NAME, Values, where,null)  ;
    }
    public long Delete(int id) {
        String where = ID + " = " + id;
        return myDB.delete(TABLE_NAME,where,null);
    }
    public Cursor getAllRecord()

    {
        String query = " SELECT * FROM " + TABLE_NAME;
        return myDB.rawQuery(query,null);
    }
}


