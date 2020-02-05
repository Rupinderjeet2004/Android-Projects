package com.example.android.librarymanagement;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gamer on 4/1/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Student.db";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String TABLE_NAME = "admin";
        String USERNAME = "username";
        String PASSWORD = "password";
        sqLiteDatabase.execSQL("create table if not exists "+TABLE_NAME+"(username text ,password text);");
        ContentValues values = new ContentValues();
        values.put(USERNAME,"admin");
        values.put(PASSWORD,"admin");
        sqLiteDatabase.insert(TABLE_NAME,null,values);

        addNewBooks("0","0","0","0");
    }

    public String searchAdmin(String user)
    {
        String TABLE_NAME = "admin";
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select username, password from " +TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String u,p;
        p = "";
        if(cursor.moveToFirst())
        {
            do {
                u = cursor.getString(0);
                if(u.equals(user))
                {
                    p = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return p;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        /*String TABLE_NAME = "Librarian";
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);*/
    }

    public boolean addLibrarian(String user,String pass) {
        String TABLE_NAME = "Librarian";
        String USERNAME = "username";
        String PASSWORD = "password";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("create table if not exists " +TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,username text ,password text);");
        ContentValues values = new ContentValues();
        values.put(USERNAME,user);
        values.put(PASSWORD,pass);
        long result = db.insert(TABLE_NAME, null, values);
        if(result == -1)
            return false;
        else
            return true;

    }

    public String searchLibrarian(String passL)
    {
        String TABLE_NAME = "Librarian";
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select username, password from " +TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String u,p;
        p = "";
        if(cursor.moveToFirst())
        {
            do {
                u = cursor.getString(0);
                if(u.equals(passL))
                {
                    p = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return p;
    }

    public int remLibrarian(String username)
    {
        String TABLE_NAME = "Librarian";
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"username = ?",new String[] {username});
    }

    public Cursor getLibrarianList(){
        String TABLE_NAME = "Librarian";
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select ID, username from "+TABLE_NAME;
        Cursor res = db.rawQuery(query,null);
        return res;
    }


    public boolean addNewBooks(String id,String title,String author,String date){
        String TABLE_NAME = "NewBooks";
        String ID = "Id";
        String TITLE = "Title";
        String AUTHOR = "Author";
        String DATE = "Date";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("create table if not exists " +TABLE_NAME+ "(ID INTEGER PRIMARY KEY,TITLE TEXT ,AUTHOR TEXT,DATE TEXT);");
        ContentValues values = new ContentValues();
        values.put(ID,id);
        values.put(TITLE,title);
        values.put(AUTHOR,author);
        values.put(DATE,date);
        long result = db.insert(TABLE_NAME, null, values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getnewAddedBooks(){
        String TABLE_NAME = "NewBooks";
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select ID, TITLE,AUTHOR,DATE from "+TABLE_NAME;
        Cursor res = db.rawQuery(query,null);
        return res;
    }

    public int remBooks(String id,String title)
    {
        String TABLE_NAME = "NewBooks";
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ? and TITLE = ?",new String[] {id,title});
    }

    public boolean issueBooks(String name,String rollno,String getTrade,String getsem,String phone,String bookid,String booktitle,
                              String bookauthor, String date)
    {
        String TABLE_NAME = "IssueBooks";
        String NAME = "Name";
        String ROLLNO = "RollNo";
        String TRADE = "Trade";
        String SEMESTER = "Semester";
        String PHONE = "Phone";
        String BOOKID = "BookID";
        String BOOKTITLE = "BookTitle";
        String AUTHOR = "BookAuthor";
        String DATE = "IssueDate";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("create table if not exists " +TABLE_NAME+ "(NAME TEXT,ROLLNO INTEGER,TRADE TEXT,SEMESTER TEXT,PHONE INTEGER," +
                "BOOKID INTEGER PRIMARY KEY,BOOKTITLE TEXT,BOOKAUTHOR TEXT,ISSUEDATE TEXT,RETURNDATE TEXT,REISSUEDATE TEXT,FINE INTEGER );");
        ContentValues values = new ContentValues();
        values.put(NAME,name);
        values.put(ROLLNO,rollno);
        values.put(TRADE,getTrade);
        values.put(SEMESTER,getsem);
        values.put(PHONE,phone);
        values.put(BOOKID,bookid);
        values.put(BOOKTITLE,booktitle);
        values.put(AUTHOR,bookauthor);
        values.put(DATE,date);
        long result = db.insert(TABLE_NAME, null, values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean onReissue(String rno,String getTrade,String getsem,String bookid,String date){
        String TABLE_NAME = "IssueBooks";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_NAME+" SET REISSUEDATE = '"+date+"' where ROLLNO='"+rno+"' and TRADE='"+getTrade+"' and SEMESTER='"+getsem+"' and BOOKID='"+bookid+"' ");
        return true;
        }

    public Cursor getIssuedBooks(){
        String TABLE_NAME = "IssueBooks";
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select NAME,ROLLNO,TRADE,SEMESTER,PHONE,BOOKID,BOOKTITLE,BOOKAUTHOR,ISSUEDATE,RETURNDATE,REISSUEDATE,FINE from "+TABLE_NAME;
        Cursor res = db.rawQuery(query,null);
        return res;
    }

    public boolean onReturn(String rno,String getTrade,String getsem,String bookid,String date){
        String TABLE_NAME = "IssueBooks";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_NAME+" SET RETURNDATE = '"+date+"' where ROLLNO='"+rno+"' and TRADE='"+getTrade+"' and SEMESTER='"+getsem+"' and BOOKID='"+bookid+"' ");
        return true;
    }

    public boolean Fine(String rno,String getTrade,String getsem,String bookid,String fine){
        String TABLE_NAME = "IssueBooks";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_NAME+" SET FINE = '"+fine.concat("Rs")+"' where ROLLNO='"+rno+"' and TRADE='"+getTrade+"' and SEMESTER='"+getsem+"' and BOOKID='"+bookid+"' ");
        return true;
    }


}
