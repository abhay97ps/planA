package com.plana.plana;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import static com.plana.plana.R.layout.activity_course;
import static com.plana.plana.R.layout.content_main;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DataBaseHelper dbHelper;
    List<TodayItem> TodayItemList;
    ListView lvToday;
    TodayListAdapter adapter;
    public static final String DB_NAME = "externalDB.db";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        lvToday = (ListView)findViewById(R.id.todaylist_view);
        File database = getApplicationContext().getDatabasePath(DataBaseHelper.DB_NAME);
        dbHelper = new DataBaseHelper(this);
        dbHelper.getReadableDatabase();

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

        TodayItemList = dbHelper.getTodayListProduct();
        adapter = new TodayListAdapter(this, TodayItemList);
        lvToday.setAdapter(adapter);

    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_today) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_courses) {
            Intent intent = new Intent(this, Course.class);
            startActivity(intent);

        } else if (id == R.id.nav_timetable) {

        } else if (id == R.id.nav_imp_date) {

        } else if (id == R.id.nav_imp_info) {

        } else if (id == R.id.nav_special_lec) {

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_exit) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
