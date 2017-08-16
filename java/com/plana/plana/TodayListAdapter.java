package com.plana.plana;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ABHAYPRATAP on 16/08/2017.
 */

class TodayListAdapter  extends BaseAdapter{

    private Context context;
    List<TodayItem> todayItemList;

    public TodayListAdapter(Context context, List<TodayItem> todayItemList) {
        this.context = context;
        this.todayItemList = todayItemList;
    }

    @Override
    public int getCount() {
        return todayItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return todayItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return todayItemList.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.today_list_item, null);

        TextView todayName = (TextView)v.findViewById(R.id.today_coursename);
        TextView todayAttd = (TextView)v.findViewById(R.id.today_attd);
        TextView todayRoom = (TextView)v.findViewById(R.id.today_roomnum);

        todayName.setText(todayItemList.get(position).getCourseName());
        todayAttd.setText((CharSequence) todayItemList.get(position).getAttendance());
        todayRoom.setText("Room Number: " +todayItemList.get(position).getRoomNum());

        return v;
    }
}
