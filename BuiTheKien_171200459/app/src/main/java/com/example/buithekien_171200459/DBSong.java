package com.example.buithekien_171200459;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBSong extends SQLiteOpenHelper {
    // Phiên bản
    private static final int DATABASE_VERSION = 1;

    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "DBSong1";

    // Tên bảng: Word.
    private static final String TABLE_SONG = "Song";

    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME_SONG = "NameSong";
    private static final String COLUMN_NAME_SINGER = "NameSinger";
    private static final String COLUMN_TIME = "Time";

    public DBSong(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "CREATE TABLE " + TABLE_SONG + "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " + COLUMN_NAME_SONG + " TEXT, "
                + COLUMN_NAME_SINGER + " TEXT, " + COLUMN_TIME + " TEXT" + ")";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);

        // Và tạo lại.
        onCreate(db);
    }

    public void createDefaultNotesIfNeed() {
        int count = this.getSongsCount();
        if (count == 0) {
            Song song = new Song(1, "Phút cuối", "Bằng Kiều", "3:27");
            Song song2 = new Song(2, "Bông hồng thủy tinh", "Bức tường", "4:18");
            Song song3 = new Song(3, "Hà Nội mùa thu", "Mỹ Linh", "4:11");
            Song song4 = new Song(4, "Bà tôi", "Tùng Dương", "3:51");
            Song song5 = new Song(5, "Gót hồng", "Quang Dũng", "4:01");
            Song song6 = new Song(6, "Đêm đông", "Bằng Kiều", "4:12");

            this.addSong(song);
            this.addSong(song2);
            this.addSong(song3);
            this.addSong(song4);
            this.addSong(song5);
            this.addSong(song6);

        }
    }

    public void addSong(Song word) {
//        Log.i(TAG, "DBWord.addNote ... " + word.getOriginal_Text());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, word.getId());
        values.put(COLUMN_NAME_SONG, word.getName_song());
        values.put(COLUMN_NAME_SINGER, word.getName_singer());
        values.put(COLUMN_TIME, word.getTime());


        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_SONG, null, values);
        // Đóng kết nối database.
        db.close();
    }

    public int getSongsCount() {
//        Log.i(TAG, "DBWord.getWordsCount ... ");

        String countQuery = "SELECT * FROM " + TABLE_SONG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public void deleteWord(Song word) {
//        Log.i(TAG, "DBWord.updateWord ... " + word.getOriginal_Text());

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SONG, COLUMN_ID + " = ?",
                new String[]{String.valueOf(word.getId())});
        db.close();
    }

    public ArrayList<Song> getAllSongs() {
//        Log.i(TAG, "DBWord.getAllWords ... ");

        ArrayList<Song> wordList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SONG;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                Song word = new Song();
                word.setId(cursor.getInt(0));
                word.setName_song(cursor.getString(1));
                word.setName_singer(cursor.getString(2));
                word.setTime(cursor.getString(3));

                // Thêm vào danh sách.
                wordList.add(word);
            } while (cursor.moveToNext());
        }
        return wordList;
    }

    // Sửa dữ liệu
    public long Update(int id, String name_song, String name_singer, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_NAME_SONG, name_song);
        contentValues.put(COLUMN_NAME_SINGER, name_singer);
        contentValues.put(COLUMN_TIME, time);
        String where = COLUMN_ID + " = " + id;
        return db.update(TABLE_SONG, contentValues, where, null);
    }
}
