package Users;

import Course.*;
import Enums.AccessRights;
import Exceptions.InvalidYearException;
import Interfaces.View;
import Storage.Storage;
import javafx.util.Pair;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class Student extends User implements Serializable, View {
    private int yearOfStudy;
    private double gpa;
    private HashMap<Course, Vector<Integer>> marks;
    private HashMap<Course, Teacher> courses;


    public Student(String id, String name, String surname, AccessRights accessRights, int yearOfStudy) throws InvalidYearException {
        super(id, name, surname, accessRights);
        if (yearOfStudy < 0) {
            throw new InvalidYearException("The year of study field can not be negative!");
        } else {
            this.yearOfStudy = yearOfStudy;
            marks = new HashMap<Course, Vector<Integer>>();
            courses = new HashMap<Course, Teacher>();
        }
    }

    public void addMark(String idc, int mark) {
        Course course = Storage.courses.get(idc);
        marks.get(course).add(mark);
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public void registerCourse() {
        Course course;
        Teacher teacher;
        int pos = 1;
        Vector<Course> myCourses = new Vector<Course>();

        Iterator it = Storage.courses.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            String id = (String) pair.getKey();
            course = Storage.courses.get(id);
            if (course.getYearOfStudy() == this.getYearOfStudy()) {
                myCourses.add(course);
            }
        }
        if (myCourses.isEmpty()) {
            System.out.println("There is no available courses\n");
            return;
        }

        for (Course c : myCourses) {
            System.out.println(String.format("[%d] - ID: %s; %s, %d credits", pos++, c.getId(), c.getName(), c.getCredits()));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter course's number: ");
        int choice = scanner.nextInt();
        while (choice < 1 || choice > myCourses.size()) {
            System.out.println("Course didn't found, try again");
            System.out.println("Please, enter course's number: ");
            choice = scanner.nextInt();
        }

        System.out.println();

        course = myCourses.elementAt(choice - 1);
        pos = 1;

        System.out.println("Teachers for chosen course: ");
        for (Teacher t : course.getTeachers()) {
            System.out.println(String.format("[%d] - %s %s", pos++, t.getName(), t.getSurname()));
        }
        System.out.println("Please, enter number of chosen teacher: ");
        choice = scanner.nextInt();
        while (choice < 1 || choice > course.getTeachers().size()) {
            System.out.println("Wrong input format, try again");
            System.out.println("Please, enter number of chosen teacher: ");
            choice = scanner.nextInt();
        }
        teacher = course.getTeachers().elementAt(choice - 1);

        courses.put(course, teacher);
        marks.put(course, new Vector<>());
        teacher.addStudent(course.getId(), id);
    }

    public void dropCourse() {
        Course course;
        Teacher teacher;
        int pos = 1;
        Vector<Course> myCourses = new Vector<Course>();

        Iterator it = courses.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            course = (Course) pair.getKey();
            myCourses.add(course);
        }

        if (myCourses.isEmpty()) {
            System.out.println("There is no available courses\n");
            return;
        }

        for (Course c : myCourses) {
            System.out.println(String.format("[%d] - ID: %s; %s, %d credits", pos++, c.getId(), c.getName(), c.getCredits()));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter course's number: ");
        int choice = scanner.nextInt();
        while (choice < 1 || choice > myCourses.size()) {
            System.out.println("Course didn't found, try again");
            System.out.println("Please, enter course's number: ");
            choice = scanner.nextInt();
        }

        System.out.println();

        course = myCourses.elementAt(choice - 1);
        courses.get(course).dropStudent(course.getId(), this.id);

        courses.remove(course);
        marks.remove(course);

        System.out.println(String.format("%s is successfully removed", Storage.courses.get(course.getId()).getName()));
        System.out.println();
    }

    @Override
    public void viewMarks() {
        Course course;
        int pos = 1;

        if (marks.isEmpty()) {
            System.out.println("There is no available courses\n");
            return;
        }
        Vector<Course> myCourses = new Vector<Course>();
        Iterator it = marks.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            course = (Course) pair.getKey();
            System.out.println(String.format("[%d] - ID: %s; %s, %d credits", pos++, course.getId(), course.getName(), course.getCredits()));
            myCourses.add(course);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter course's number: ");
        int choice = scanner.nextInt();
        while (choice < 1 || choice > myCourses.size()) {
            System.out.println("Course didn't found, try again");
            System.out.println("Please, enter course's number: ");
            choice = scanner.nextInt();
        }

        course = myCourses.elementAt(choice - 1);
        if (marks.get(course).isEmpty()) {
            System.out.println("There is no marks for this course!");
            return;
        }

        System.out.print(String.format("ID: %s - %s: ", course.getId(), course.getName()));
        Vector<Integer> m = marks.get(course);
        for (int i = 0; i < m.size(); i++) {
            System.out.print(m.elementAt(i) + ", ");
        }
        System.out.println("\n");
    }

    @Override
    public void viewTranscript() {
        //marks
        Iterator it = marks.entrySet().iterator();
        if (marks.isEmpty()) {
            System.out.println("There is no available courses\n");
            return;
        }
        Course course;
        int pos = 1;
        Double calc_gpa = 0.0;
        Double gpaMultiply = 0.0;
        Integer sumOfCredits = 0;

        System.out.format("%-10s%-10s%-10s%-10s%-10s%-10s\n", "ID", "Name", "Credits", "Points", "Mark", "GPA");
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            String id;
            String name;
            int credits;
            int points = 0;
            String pointsEqual = "F";
            course = (Course) pair.getKey();
            id = course.getId();
            name = course.getName();
            credits = course.getCredits();
            pointsEqual = getMark(course).getKey();
            calc_gpa = getMark(course).getValue();
            gpaMultiply += calc_gpa * credits * 1.0;
            sumOfCredits += credits;
            System.out.format("%-10s%-10s%-10d%-10d%-10s%-10f\n", id, name, credits, points, pointsEqual, calc_gpa);
        }
        this.gpa = (gpaMultiply) / (sumOfCredits * 1.0);
        System.out.format("GPA:  %.3f", this.gpa);

    }

    private Pair<String, Double> getMark(Course course) {
        Vector<Integer> vector = marks.get(course);
        int finalPoint = 0;
        for (int i = 0; i < vector.size(); ++i) {
            finalPoint += vector.elementAt(i);
        }
        String equevivalence = null;
        Double gpaMark = 0.0;
        if (finalPoint >= 95) {
            equevivalence = "A";
            gpaMark = 4.00;
        } else if (finalPoint >= 90) {
            equevivalence = "A-";
            gpaMark = 3.67;
        } else if (finalPoint >= 85) {
            equevivalence = "B+";
            gpaMark = 3.33;
        } else if (finalPoint >= 80) {
            equevivalence = "B";
            gpaMark = 3.00;
        } else if (finalPoint >= 75) {
            equevivalence = "B-";
            gpaMark = 2.67;
        } else if (finalPoint >= 70) {
            equevivalence = "C+";
            gpaMark = 2.33;
        } else if (finalPoint >= 65) {
            equevivalence = "C";
            gpaMark = 2.00;
        } else if (finalPoint >= 60) {
            equevivalence = "C-";
            gpaMark = 1.67;
        } else if (finalPoint >= 55) {
            equevivalence = "D";
            gpaMark = 1.33;
        } else {
            equevivalence = "F";
            gpaMark = 0.0;
        }

        return new Pair<>(equevivalence, gpaMark);
    }

    @Override
    public void viewFiles() {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Course> mp = Storage.courses;
        if (mp.isEmpty()) {
            System.out.println("There is no courses yet\n");
            return;
        }
        Iterator it = mp.entrySet().iterator();
        Course course = null;
        String id;
        int pos = 1;
        int isHere = 1;
        Vector<CourseFile> idCourses = new Vector<CourseFile>();

        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            id = (String) pair.getKey();
            course = mp.get(id);
            if (course.getCourseFiles().isEmpty()) continue;
            System.out.println(String.format("ID: %s; %s", id, course.getName()));
            Vector<CourseFile> courseFiles = course.getCourseFiles();

            for (int i = 0; i < courseFiles.size(); ++i) {
                System.out.println("[" + isHere++ + "]" + " - " + courseFiles.elementAt(i).getName());
                idCourses.add(courseFiles.elementAt(i));
            }
        }

        if (idCourses.size() == 0) {
            System.out.println("No files!");
            return;
        }

        System.out.println("Choose file,which you want to read");
        int choice = scanner.nextInt();
        while (choice < 0 || choice > idCourses.size() + 1) {
            System.out.println("Wrong input format");
            System.out.println("Try again!");
            choice = scanner.nextInt();
        }
        System.out.println("Text:");
        System.out.println(idCourses.elementAt(choice - 1).getText());

    }

}