package com.plana.plana;

/**
 * Created by ABHAYPRATAP on 16/08/2017.
 */

public class TodayItem {
    private int _id;
    private String courseName;
    private float bunk_cnt;
    private String roomNum;
    private float total_lec;

    public TodayItem(int _id, String courseName, float bunk_cnt, float total_lec, String roomNum) {
        this._id = _id;
        this.courseName = courseName;
        this.bunk_cnt = bunk_cnt;
        this.total_lec = total_lec;
        this.roomNum = roomNum;
    }

    public String getAttendance(){
        Float attendance =(Float) ((total_lec - bunk_cnt)/total_lec) * 100;
        return String.format("%.2f", attendance);
    }
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getBunk_cnt() {
        return (int) bunk_cnt;
    }

    public void setBunk_cnt(int bunk_cnt) {
        this.bunk_cnt = bunk_cnt;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public int getTotal_lec() {
        return (int)total_lec;
    }

    public void setTotal_lec(int total_lec) {
        this.total_lec = total_lec;
    }
}
