package com.plana.plana;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import static android.R.attr.id;

/**
 * Created by ABHAYPRATAP on 13/08/2017.
 */

class CourseListAdapter extends BaseAdapter {

    private Context context;
    private List<CourseListItems> CourseList;

    public CourseListAdapter(Context context, List<CourseListItems> courseList) {
        this.context = context;
        CourseList = courseList;
    }

    @Override
    public int getCount() {
        return CourseList.size();
    }

    @Override
    public Object getItem(int position) {
        return CourseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return CourseList.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.course_list_item, null);

        TextView coursecode = (TextView)v.findViewById(R.id.course_code);
        TextView coursename = (TextView)v.findViewById(R.id.course_name);
        TextView faculty = (TextView)v.findViewById(R.id.faculty_name);
        TextView attd = (TextView)v.findViewById(R.id.attd_percen);
        Button bunk_button = (Button)v.findViewById(R.id.bunk_button);
        bunk_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp_bunk_cnt = CourseList.get(position).getBunk_count() + 1;
                CourseList.get(position).setBunk_count(temp_bunk_cnt);
                DataBaseHelper helper = new DataBaseHelper(context);
                SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("num_bunk",temp_bunk_cnt);
                sqLiteDatabase.update("course",cv,"_id =" +(position+1),null);
                sqLiteDatabase.close();
                notifyDataSetChanged();

            }
        });

        coursecode.setText(CourseList.get(position).getCourseCode());
        coursename.setText(CourseList.get(position).getCourseName());
        faculty.setText(CourseList.get(position).getFaculty());
        attd.setText(CourseList.get(position).getAttendance()+"%");

        return v;
    }
}


