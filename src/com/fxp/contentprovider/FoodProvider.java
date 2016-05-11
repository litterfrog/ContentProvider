package com.fxp.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;


public class FoodProvider extends ContentProvider {
	DBHelper dBHelper;
    SQLiteDatabase db;
     
    private static final UriMatcher sMatcher;
    static{
            sMatcher = new UriMatcher(UriMatcher.NO_MATCH);
            sMatcher.addURI(ProviderConstant.AUTOHORITY,ProviderConstant.TFOOD, ProviderConstant.ITEM_TABLE);
            sMatcher.addURI(ProviderConstant.AUTOHORITY,ProviderConstant.TACCOUNT, ProviderConstant.ITEM_TABLE);
            sMatcher.addURI(ProviderConstant.AUTOHORITY,ProviderConstant.TUSERINFO, ProviderConstant.ITEM_TABLE);
            sMatcher.addURI(ProviderConstant.AUTOHORITY,ProviderConstant.TCOMMENT, ProviderConstant.ITEM_TABLE);
            sMatcher.addURI(ProviderConstant.AUTOHORITY,ProviderConstant.TLIKE, ProviderConstant.ITEM_TABLE);
            sMatcher.addURI(ProviderConstant.AUTOHORITY,ProviderConstant.TVISITED, ProviderConstant.ITEM_TABLE);
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
            // TODO Auto-generated method stub
            db = dBHelper.getWritableDatabase();
            int count = 0;
            switch (sMatcher.match(uri)) {
            case ProviderConstant.ITEM_TABLE:
                    String table = uri.getPathSegments().get(0);
                    count = db.delete(table,selection, selectionArgs);
                break;
            default:
                    throw new IllegalArgumentException("Unknown URI"+uri);
            }
            getContext().getContentResolver().notifyChange(uri, null);
            return count;
    }

    @Override
    public String getType(Uri uri) {
            // TODO Auto-generated method stub
            switch (sMatcher.match(uri)) {
            case ProviderConstant.ITEM:
                    return ProviderConstant.CONTENT_TYPE;
            case ProviderConstant.ITEM_TABLE:
                return ProviderConstant.CONTENT_ITEM_TYPE;
            default:
                    throw new IllegalArgumentException("Unknown URI"+uri);
            }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
    	Log.i("test", "insertinsertinsertinsertinsert");
            long rowId;
            if(sMatcher.match(uri)!=ProviderConstant.ITEM_TABLE){
                    throw new IllegalArgumentException("Unknown URI"+uri);
            }
            String table = uri.getPathSegments().get(0);
            if(null==dBHelper){
            	return null;
            }
            rowId = dBHelper.add(table,values);
               if(rowId>0){
                       Uri noteUri=ContentUris.withAppendedId(uri, rowId);
                       getContext().getContentResolver().notifyChange(noteUri, null);
                       return noteUri;
               }
               throw new IllegalArgumentException("Unknown URI"+uri);
    }

    @Override
    public boolean onCreate() {
            this.dBHelper = new DBHelper(this.getContext());
//            db = dBlite.getWritableDatabase();
//            return (db == null)?false:true;
            return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                    String[] selectionArgs, String sortOrder) { 
    	Log.i("test", "queryqueryqueryqueryqueryqueryqueryquery");
            Cursor c;
            Log.d("-------", String.valueOf(sMatcher.match(uri)));
            switch (sMatcher.match(uri)) {
//            case ProviderConstant.ITEM:
//                    c = db.query(RuiXin.TNAME, projection, selection, selectionArgs, null, null, null);
//             
//                    break;
            case ProviderConstant.ITEM_TABLE:
                    String table = uri.getPathSegments().get(0);
                    c = dBHelper.query(table, projection,selection,selectionArgs,sortOrder);
                break;
            default:
                    Log.d("!!!!!!", "Unknown URI"+uri);
                    throw new IllegalArgumentException("Unknown URI"+uri);
            }
            c.setNotificationUri(getContext().getContentResolver(), uri);
            return c;
    }
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                    String[] selectionArgs) {
    	int update=-1;
        switch (sMatcher.match(uri)) {
      case ProviderConstant.ITEM_TABLE:
              String table = uri.getPathSegments().get(0);
              update = dBHelper.update(table, values, selection, selectionArgs);
          break;
      default:
              Log.d("!!!!!!", "Unknown URI"+uri);
              throw new IllegalArgumentException("Unknown URI"+uri);
      }
    	
            return update;
    }
}
