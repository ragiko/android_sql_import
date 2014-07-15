package com.example.sql_csv_import;

import java.io.IOException;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private DataBaseHelper mDbHelper;  
	private SQLiteDatabase db;  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setDatabase();
		
//		Cursor cursor = findData(1);
//		cursor.moveToFirst();
//		Log.d("db", "id: 1 " + cursor.getString(1) + " " + cursor.getString(3));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void setDatabase() {  
	    mDbHelper = new DataBaseHelper(this);  
	    try {  
	        mDbHelper.createEmptyDataBase();  
	        db = mDbHelper.openDataBase();  
	    } catch (IOException ioe) {  
	        throw new Error("Unable to create database");  
	    } catch(SQLException sqle){  
	        throw sqle;  
	    }  
	}  
	
	@Override  
	public void onDestroy() {  
	    db.close();  
	    super.onDestroy();  
	}  
	
	private static final String[] COLUMNS = {"_id", "station_name", "station_yomi" };  
	  
	private Cursor findData(int id) {  
	    Cursor cursor = db.query("test", COLUMNS, "where _id=" + id, null, null, null, null);  
	    return cursor;  
	}  

}
