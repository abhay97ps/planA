package com.plana.plana;

/**
 * Created by ABHAYPRATAP on 13/08/2017.
 */

class CourseListItems {
    private int id;
    private String courseCode;
    private String courseName;
    private String faculty;
    private int bunk_count;
    private int total_num_lec;
    private float attendance;

    public CourseListItems(int id, String courseCode, String courseName, String faculty, int bunk_count, int total_num_lec) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.faculty = faculty;
        this.bunk_count = bunk_count;
        this.total_num_lec = total_num_lec;
    }

    public int getTotal_num_lec() {
        return total_num_lec;
    }

    public void setTotal_num_lec(int total_num_lec) {
        this.total_num_lec = total_num_lec;
    }

    public int getBunk_count() {
        return bunk_count;
    }

    public void setBunk_count(int bunk_count) {
        this.bunk_count = bunk_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getAttendance(){
        this.attendance = ((total_num_lec-(float)bunk_count)/total_num_lec)*100;
        return String.format("%.2f", attendance);
    }
}


