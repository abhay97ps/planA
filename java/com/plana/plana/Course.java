package com.plana.plana;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Course extends AppCompatActivity {

    private ListView lvProduct;
    private CourseListAdapter adapter;
    private List<CourseListItems> mProductList;
    private DataBaseHelper mDbHelper;

    public static final String DB_NAME = "externalDB.db";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        lvProduct = (ListView)findViewById(R.id.listview_product);

        File database = getApplicationContext().getDatabasePath(DataBaseHelper.DB_NAME);
        mDbHelper = new DataBaseHelper(this);
        mDbHelper.getReadableDatabase();


        if (database.exists() == false){
            this.deleteDatabase(DB_NAME);
            if(DataBaseHelper.copyDatabase(this)){
                Toast.makeText(this, "copy database success", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "copy database failure", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        mProductList = mDbHelper.getCourseListProduct();
        adapter = new CourseListAdapter(this, mProductList);
        lvProduct.setAdapter(adapter);



    }




}
