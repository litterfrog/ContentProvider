package com.fxp.contentprovider;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class DBHelper  extends SQLiteOpenHelper {
	SQLiteDatabase db;
	public DBHelper(Context context) {
        super(context, ProviderConstant.DBNAME, null, ProviderConstant.VERSION);
		}
	@Override
	public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
                db.execSQL("create table "+ProviderConstant.TFOOD+"(" +
                		ProviderConstant.TFOOD_ID+" integer primary key autoincrement not null,"+
                		ProviderConstant.TFOOD_NAME+" text not null UNIQUE," +
                		ProviderConstant.TFOOD_PICTRUE_PATH+" text not null," +
                		ProviderConstant.TFOOD_PHONE+" text not null,"+
                		ProviderConstant.TFOOD_ADDRESS+" text not null,"+
                		ProviderConstant.TFOOD_LABEL+" text not null,"+
                		ProviderConstant.TFOOD_SUMMARY+" text not null);");
                db.execSQL("create table "+ProviderConstant.TUSERINFO+"(" +
                		ProviderConstant.TUSERINFO_ID+" integer primary key autoincrement not null,"+
                		ProviderConstant.TUSERINFO_NAME+" text not null," +
                		ProviderConstant.TUSERINFO_SEX+" interger not null," +
                		ProviderConstant.TUSERINFO_PHONE+" text not null,"+
                		ProviderConstant.TUSERINFO_ADDRESS+" text not null,"+
                		ProviderConstant.TUSERINFO_ACCID+" interger not null UNIQUE);");
                db.execSQL("create table "+ProviderConstant.TACCOUNT+"(" +
                		ProviderConstant.TACCOUNT_ID+" integer primary key autoincrement not null,"+
                		ProviderConstant.TACCOUNT_EMAIL+" text not null UNIQUE," +
                		ProviderConstant.TACCOUNT_PASSWORD+" text not null);");
                db.execSQL("create table "+ProviderConstant.TCOMMENT+"(" +
                		ProviderConstant.TCOMMENT_ID+" integer primary key autoincrement not null,"+
                		ProviderConstant.TCOMMENT_ACCID+" interger not null," +
                		ProviderConstant.TCOMMENT_FOODID+" interger not null," +
                		ProviderConstant.TCOMMENT_COMMENT+" text not null," +
                		ProviderConstant.TCOMMENT_TIME+" text not null" +
                				");");
                db.execSQL("create table "+ProviderConstant.TLIKE+"(" +
                		ProviderConstant.TLIKE_ID+" integer primary key autoincrement not null,"+
                		ProviderConstant.TLIKE_ACCID+" interger not null," +
                		ProviderConstant.TLIKE_FOODID+" interger not null" +
                				");");
                db.execSQL("create table "+ProviderConstant.TVISITED+"(" +
                		ProviderConstant.TVISITED_ID+" integer primary key autoincrement not null,"+
                		ProviderConstant.TVISITED_ACCID+" interger not null," +
                		ProviderConstant.TVISITED_FOODID+" interger not null" +
                				");");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE"+ProviderConstant.TFOOD);
		db.execSQL("DROP TABLE"+ProviderConstant.TUSERINFO);
		db.execSQL("DROP TABLE"+ProviderConstant.TACCOUNT);
		db.execSQL("DROP TABLE"+ProviderConstant.TCOMMENT);
		db.execSQL("DROP TABLE"+ProviderConstant.TLIKE);
		db.execSQL("DROP TABLE"+ProviderConstant.TVISITED);
	}
	public long add(String table, ContentValues values){
        db = getWritableDatabase();
        return db.insert(table,"",values);
	}
	public Cursor query(String table, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        db = getWritableDatabase();
		Cursor c;
		c = db.query(table, projection, selection, selectionArgs, null, null, sortOrder);
		return c;
	}

	public int update(String table,ContentValues values,String whereClause,String[] whereArgs){
		db = getWritableDatabase();
		int update = db.update(table, values, whereClause, whereArgs);
		return update;
	}
}
