package Users;

import Course.*;
import Enums.AccessRights;
import Enums.TeacherPosition;
import Storage.Storage;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class Teacher extends Employee implements Serializable {
    private TeacherPosition teacherPosition;
    private HashMap<Course, Vector<Student>> studentsForTeacher;
    private Vector<Email> emails;


    public Teacher(String id, String name, String surname, AccessRights accessRights, double salary, TeacherPosition teacherPosition) {
        super(id, name, surname, accessRights, salary);
        this.teacherPosition = teacherPosition;
        studentsForTeacher = new HashMap<Course, Vector<Student>>();
        emails = new Vector<Email>();
    }

    public void openCourse(Course course) {
        studentsForTeacher.put(course, new Vector<Student>());
        System.out.println(studentsForTeacher.size());
    }

    public void dropStudent(String idc, String ids) {
        Course c = Storage.courses.get(idc);
        Student st = Storage.students.get(ids);
        studentsForTeacher.get(c).remove(st);
    }

    public void addStudent(String idc, String ids) {
        Course c = Storage.courses.get(idc);
        Student st = Storage.students.get(ids);
        Vector<Student> v = studentsForTeacher.get(c);
        v.add(st);
        studentsForTeacher.put(c, v);
    }

    public void addEmail(Email email) {
        emails.add(email);
    }

    public void viewEmails() {
        Scanner in = new Scanner(System.in);
        Vector<Email> emails = this.emails;
        if (emails.size() == 0) {
            System.out.println("No emails!");
            return;
        }
        System.out.println("[1] - New emails");
        System.out.println("[2] - Old emails");

        int choice = in.nextInt();
        while (choice < 1 || choice > 2) {
            System.out.println("Wrong input format!\n");
            System.out.println("[1] - New emails");
            System.out.println("[2] - All emails");
            choice = in.nextInt();
        }

        switch (choice) {
            case 1:
                viewNewEmails();
                break;
            case 2:
                viewOldEmails();
                break;
        }
    }

    public void addCourseFiles() {
        System.out.println("Choose the course");

        Iterator it = studentsForTeacher.entrySet().iterator();
        if (studentsForTeacher.isEmpty()) {
            System.out.println("There is no teachers\n");
            return;
        }
        Course course = null;
        String id;
        int pos = 1;
        Vector<Course> coursesFromHash = new Vector<Course>();

        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            course = (Course) pair.getKey();
            coursesFromHash.add(course);
            System.out.println(String.format("[%d] - %s", pos++, course.getName()));
        }
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        while (choice < 1 || choice > coursesFromHash.size()) {
            System.out.println("Wrong input format");
            System.out.println("Try again: ");
            choice = scanner.nextInt();
        }
        course = coursesFromHash.elementAt(choice - 1);

        String name;
        System.out.println("Enter the name");
        scanner.nextLine();
        name = scanner.nextLine();
        System.out.println("Enter the data");
        String data = scanner.nextLine();
        CourseFile cs = new CourseFile(name, data);
        Storage.courses.get(course.getId()).addCourseFile(cs);
    }

    public void viewNewEmails() {
        Scanner in = new Scanner(System.in);
        int count = 1;
        Vector<Email> eml = new Vector<>();
        for (int i = 0; i < emails.size(); i++) {
            if (emails.elementAt(i).getReadOrNot()) continue;
            eml.add(emails.elementAt(i));
        }

        if (eml.isEmpty()) {
            System.out.println("You haven't new emails");
            return;
        }
        System.out.println("New emails: ");
        for (int i = 0; i < eml.size(); i++) {
            System.out.println(String.format("[%d] - %s", count++, eml.elementAt(i).getTitle(), eml.elementAt(i).getDate()));
        }

        System.out.println("Choose email number:");
        int choice = in.nextInt();
        while (choice < 1 || choice > eml.size()) {
            System.out.println("Wrong input format!\n");
            System.out.println("Choose email number:");
            choice = in.nextInt();
        }

        Email email = eml.elementAt(choice - 1);
        emails.elementAt(emails.indexOf(email)).setRead();

        System.out.format("%s\n", email.getTitle());
        System.out.format("From: %s\n", email.getFrom());
        System.out.format("At: %s\n", email.getDate());
        System.out.format("%s\n", email.getText());
        System.out.println();
    }

    public void viewOldEmails() {
        Scanner in = new Scanner(System.in);
        int count = 1;
        Vector<Email> eml = new Vector<>();
        for (int i = 0; i < emails.size(); i++) {
            if (!emails.elementAt(i).getReadOrNot()) continue;
            eml.add(emails.elementAt(i));
        }

        if (eml.isEmpty()) {
            System.out.println("You haven't old emails");
            return;
        }
        System.out.println("Old emails: ");
        for (int i = 0; i < eml.size(); i++) {
            System.out.println(String.format("[%d] - %s", count++, eml.elementAt(i).getTitle(), eml.elementAt(i).getDate()));
        }

        System.out.println("Choose email number:");
        int choice = in.nextInt();
        while (choice < 1 || choice > eml.size()) {
            System.out.println("Wrong input format!\n");
            System.out.println("Choose email number:");
            choice = in.nextInt();
        }

        Email email = eml.elementAt(choice - 1);
        emails.elementAt(emails.indexOf(email)).setRead();

        System.out.format("%s\n", email.getTitle());
        System.out.format("From: %s\n", email.getFrom());
        System.out.format("At: %s\n", email.getDate());
        System.out.format("%s\n", email.getText());
        System.out.println();
    }

    public void viewCourses() {
        if (studentsForTeacher.isEmpty()) {
            System.out.println("You haven't any courses");
            return;
        }

        System.out.println("Your courses: ");
        Iterator it = studentsForTeacher.entrySet().iterator();
        Course course;
        int pos = 1;
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            course = (Course) pair.getKey();
            System.out.println(String.format("[%d] - %s", pos++, course.getName()));
        }
        System.out.println();
    }

    public void viewStudents() {
        if (studentsForTeacher.isEmpty()) {
            System.out.println("You haven't any courses");
            return;
        }

        Scanner in = new Scanner(System.in);
        System.out.println("Your courses: ");
        Iterator it = studentsForTeacher.entrySet().iterator();
        Course course;
        int pos = 1;
        Vector<Course> coursesFromHash = new Vector<Course>();

        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            course = (Course) pair.getKey();
            coursesFromHash.add(course);
            System.out.println(String.format("[%d] - %s", pos++, course.getName()));
        }
        System.out.println("Choose course's number: ");
        int choice = in.nextInt();
        while (choice < 1 || choice > coursesFromHash.size()) {
            System.out.println("Wrong input format!\n");
            System.out.println("Choose course's number: ");
            choice = in.nextInt();
        }
        course = coursesFromHash.elementAt(choice - 1);

        if (studentsForTeacher.get(course).isEmpty()) {
            System.out.println("You haven't any students for this course");
            return;
        }

        System.out.format("Your students for %s course:\n", course.getName());
        for (int i = 0; i < studentsForTeacher.get(course).size(); i++) {
            System.out.format("[%d] - %s\n", i + 1, studentsForTeacher.get(course).elementAt(i));
        }

        System.out.println();

    }

    public void putMark() {
        if (studentsForTeacher.isEmpty()) {
            System.out.println("You haven't any courses");
            return;
        }

        Scanner in = new Scanner(System.in);
        System.out.println("Your courses: ");
        Iterator it = studentsForTeacher.entrySet().iterator();
        Course course;
        int pos = 1;
        Vector<Course> coursesFromHash = new Vector<Course>();

        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            course = (Course) pair.getKey();
            coursesFromHash.add(course);
            System.out.println(String.format("[%d] - %s", pos++, course.getName()));
        }
        System.out.println("Choose course's number: ");
        int choice = in.nextInt();
        while (choice < 1 || choice > coursesFromHash.size()) {
            System.out.println("Wrong input format!\n");
            System.out.println("Choose course's number: ");
            choice = in.nextInt();
        }
        course = coursesFromHash.elementAt(choice - 1);

        if (studentsForTeacher.get(course).isEmpty()) {
            System.out.println("You haven't any students for this course");
            return;
        }

        System.out.format("Your students for %s course:\n", course.getName());
        for (int i = 0; i < studentsForTeacher.get(course).size(); i++) {
            System.out.format("[%d] - %s\n", i + 1, studentsForTeacher.get(course).elementAt(i));
        }

        System.out.println("Choose student's number:");
        choice = in.nextInt();
        while (choice < 1 || choice > studentsForTeacher.get(course).size()) {
            System.out.println("Wrong input format!\n");
            System.out.println("Choose course's number: ");
            choice = in.nextInt();
        }
        Student student = studentsForTeacher.get(course).elementAt(choice - 1);
        System.out.println("Write your mark: ");
        choice = in.nextInt();
        student.addMark(course.getId(), choice);
    }

    public void sendOrder() {
        Scanner scanner = new Scanner(System.in);
        String title;
        String text;
        String from;
        System.out.println("Write title for your order in one line:");
        title = scanner.nextLine();

        System.out.println("Choose the reciever: ");
        HashMap<String, TechSupport> mp = Storage.techSupportGuys;
        if (mp.isEmpty()) {
            System.out.println("There is no TechSupports");
            return;
        }
        Iterator it = mp.entrySet().iterator();
        TechSupport techSupport = null;
        String id;
        int pos = 1;
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            id = (String) pair.getKey();
            techSupport = mp.get(id);
            System.out.println(String.format("[%d] - ID: %s; %s: %s %s", pos++, id, techSupport.getAccessRights(), techSupport.getName(), techSupport.getSurname()));
        }

        System.out.println("Please,choose TechSupport's number");
        int choice = scanner.nextInt();
        while (choice > pos || choice < 1) {
            System.out.println("Wrong input format\n");
            choice = scanner.nextInt();
        }

        Iterator myit = mp.entrySet().iterator();
        String myid;
        int mypos = 1;
        while (myit.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) myit.next();
            if (mypos == choice) {
                myid = (String) pair.getKey();
                techSupport = mp.get(myid);
            }
            mypos++;
        }

        System.out.println("Write the text: ");
        scanner.nextLine();
        text = scanner.nextLine();

        Order order = new Order(title, text);
        techSupport.addOrder(order);

    }

}
