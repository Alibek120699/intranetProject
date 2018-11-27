package Course;

import Users.*;

import java.io.*;
import java.util.HashMap;
import java.util.Vector;

public class Course implements Serializable {
    private int credits;
    private String id;
    private String name;
    private int yearOfStudy;
    private Vector<CourseFile> courseFiles;
    private Vector<Teacher> teachers;

    public Course(String id, String name, int credits, int yearOfStudy) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.yearOfStudy = yearOfStudy;
        courseFiles = new Vector<CourseFile>();
        teachers = new Vector<Teacher>();
    }

    public Vector<CourseFile> getCourseFiles() {
        return courseFiles;
    }

    public void addTeacher(Teacher t) {
        teachers.add(t);
    }

    public void addCourseFile(CourseFile cf) {
        courseFiles.add(cf);
    }

    public Vector<Teacher> getTeachers() {
        return teachers;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public String toString() {
        return String.format("ID: %s; %s, %d credits", id, name, credits);
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

}
