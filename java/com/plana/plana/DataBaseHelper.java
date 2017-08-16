package com.plana.plana;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ABHAYPRATAP on 13/08/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "externalDB.db";
    public static final String DB_LOCATION = "/data/data/com.plana/databases/";

    private Context mContext;
    private SQLiteDatabase db;

    public DataBaseHelper(Context context){
        super(context, DB_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase(){
        String dbPath = mContext.getDatabasePath(DB_NAME).getPath();
        if (db != null && db.isOpen()){
            return;
        }

        db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase(){
        if (db != null){
            db.close();
        }
    }

    public List<CourseListItems> getCourseListProduct(){
        CourseListItems product = null;
        List<CourseListItems> productList = new ArrayList<>();
        openDatabase();
        Cursor cursor = db.rawQuery("select * from course", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            product = new CourseListItems(cursor.getInt(0), cursor.getString(1),cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5));
            productList.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return productList;

    }

    public List<TodayItem> getTodayListProduct(){
        TodayItem product = null;
        List<TodayItem> productList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Cursor cursor = null;
        openDatabase();
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
            cursor = db.rawQuery("select * from today where Mon = '1'", null);
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
            cursor = db.rawQuery("select * from today where Tue = '1'", null);
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY){
            cursor = db.rawQuery("select * from today where Wed = '1'", null);
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){
            cursor = db.rawQuery("select * from today where Thu = '1'", null);
        }
        else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
            cursor = db.rawQuery("select * from today where Fri = '1'", null);
        }


        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String CourseName = cursor.getString(1);
            Cursor c1 = db.rawQuery("select * from course where course_name = '"+ CourseName +"' ", null);
            c1.moveToFirst();
            product = new TodayItem(cursor.getInt(0), cursor.getString(1), c1.getFloat(4), c1.getFloat(5), cursor.getString(2));
            productList.add(product);
            c1.close();
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return productList;

    }

    static boolean copyDatabase(Context context){
        try{


            //Open your local db as the input stream

            InputStream myInput = context.getAssets().open(DataBaseHelper.DB_NAME);

            // Path to the just created empty db
            String outFileName = context.getDatabasePath(DataBaseHelper.DB_NAME).toString();

            //Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            //transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }

            //Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();


            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
