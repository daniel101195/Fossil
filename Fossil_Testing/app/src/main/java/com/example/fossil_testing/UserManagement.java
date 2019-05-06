package com.example.fossil_testing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class UserManagement extends SQLiteOpenHelper {

    public UserManagement(Context context) {
        super(context, "User", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table usersof " +
                "( " +
                "_id integer primary key autoincrement, " +
                "displayname text, " +
                "location text," +
                "reputation text," +
                "lastaccessdate text," +
                "url text," +
                "userid integer" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists userSOF");
        onCreate(db);
    }

    public void ThemUser(User ngdung)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("displayname", ngdung.display_name);
        value.put("location", ngdung.location);
        value.put("reputation", ngdung.reputation);
        value.put("lastaccessdate", ngdung.last_access_date);
        value.put("url", ngdung.profile_image);
        value.put("userid", ngdung.user_id);
        db.insert("usersof", null, value);
    }

    public void XoaUser(int userid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("usersof", "userid=?", new String[]{userid + ""});
    }

    public ArrayList<User> LayCongviec()
    {
        ArrayList<User> ds = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from userSOF", null);

        if (c != null && c.moveToFirst()) { //Khi làm việc với Cursor, luôn luôn kt null và movetofirst
            do {
                String tenhienthi = c.getString(1);
                String diadiem = c.getString(2);
                String dotinnhiem = c.getString(3);
                String ngaydangnhapcuoi = c.getString(4);
                String linkhinh = c.getString(5);
                Integer userid = c.getInt(6);
                User user_bookmark = new User(tenhienthi, diadiem, dotinnhiem, linkhinh, ngaydangnhapcuoi, userid);
                ds.add(user_bookmark);
            } while (c.moveToNext());
        }
        return ds;
    }
}
