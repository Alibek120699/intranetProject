package Users;

import Course.*;
import Enums.AccessRights;
import Storage.Storage;

import java.io.*;
import java.util.*;

public class Manager extends Employee implements Serializable {

    public Manager(String id, String name, String surname, AccessRights accessRights, double salary) {
        super(id, name, surname, accessRights, salary);
    }

    public void addCourse() {
        String id;
        String name;
        int credits;
        int year;
        Course course;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write down course's id: ");
        id = scanner.next();

        while (Storage.courses.containsKey(id)) {
            System.out.println("Course is already exist");
            System.out.println("Write down course's id");
            id = scanner.next();
        }

        System.out.println("Write down course's name: ");
        scanner.nextLine();
        name = scanner.nextLine();

        System.out.println("Write down course's credits: ");
        credits = scanner.nextInt();

        System.out.println("Choose course's year of study: ");
        System.out.println("[1]");
        System.out.println("[2]");
        System.out.println("[3]");
        System.out.println("[4]");
        year = scanner.nextInt();
        while (year < 1 || year > 4) {
            System.out.println("Wrong input format, try again");
            year = scanner.nextInt();
        }


        course = new Course(id, name, credits, year);
        Storage.courses.put(id, course);

        HashMap<String, Teacher> mp = Storage.teachers;
        if (mp.isEmpty()) {
            System.out.println("There is no teachers\n");
            return;
        }
        Iterator it = mp.entrySet().iterator();
        Teacher user;
        int pos = 1;
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            id = (String) pair.getKey();
            user = mp.get(id);
            System.out.println(String.format("[%d] - ID: %s; %s: %s %s", pos++, id, user.getAccessRights(), user.getName(), user.getSurname()));
        }

        System.out.println("Please, choose the Teacher");
        int choice = scanner.nextInt();
        while (choice < 1 || choice > pos) {
            System.out.println("Wrong input format");
            choice = scanner.nextInt();
        }

        Iterator it1 = mp.entrySet().iterator();
        int ps = 0;
        while (it1.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it1.next();
            id = (String) pair.getKey();
            user = mp.get(id);
            if (ps == choice - 1) {
                course.addTeacher(user);
                user.openCourse(course);
                break;
            }
            ps++;
        }

        Storage.writeLog(String.format("%s - %s course is added", new Date(), course));
    }

    public void removeCourse() {
        viewCourses();

        System.out.println("Please, enter course's id");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();
        while (!Storage.courses.containsKey(id)) {
            System.out.println("Course didn't found, try again");
            System.out.println("Please, enter courses's id");
            id = scanner.next();
        }

        if (Storage.courses.containsKey(id)) {
            Storage.writeLog(String.format("%s - %s course is removed", new Date(), Storage.courses.get(id)));
            Storage.courses.remove(id);
        }
    }

    public void viewCourses() {
        if (Storage.courses.isEmpty()) {
            System.out.println("There is no any courses\n");
            return;
        }
        HashMap<String, Course> mc = Storage.courses;
        Iterator it = mc.entrySet().iterator();
        Course course;
        String id;
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            id = (String) pair.getKey();
            course = mc.get(id);
            System.out.println(course);
        }
    }

    public void viewTeachers() {
        HashMap<String, Teacher> mt = Storage.teachers;
        if (mt.isEmpty()) {
            System.out.println("There is no teachers\n");
            return;
        }
        Iterator it = mt.entrySet().iterator();
        Teacher teacher;
        String id;
        int pos = 1;
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            id = (String) pair.getKey();
            teacher = mt.get(id);
            System.out.println(String.format("[%d] - ID: %s; %s: %s %s", pos++, id, teacher.getAccessRights(), teacher.getName(), teacher.getSurname()));
        }
    }

    public void viewStudents() {
        HashMap<String, Student> ms = Storage.students;
        if (ms.isEmpty()) {
            System.out.println("There is no students\n");
            return;
        }
        Iterator it = ms.entrySet().iterator();
        Student student;
        String id;
        int pos = 1;
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            id = (String) pair.getKey();
            student = ms.get(id);
            System.out.println(String.format("[%d] - ID: %s; %s: %s %s", pos++, id, student.getAccessRights(), student.getName(), student.getSurname()));
        }
    }

    public void addTeacherToCourse() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, Select The course");
        if (Storage.courses.isEmpty()) {
            System.out.println("There is no any courses\n");
            return;
        }
        HashMap<String, Course> mc = Storage.courses;
        Iterator itt = mc.entrySet().iterator();
        Course course;
        String idd;
        while (itt.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) itt.next();
            idd = (String) pair.getKey();
            course = mc.get(idd);
            System.out.println(course);
        }
        System.out.println("Enter id of the course");
        String s = scanner.next();
        while (!Storage.courses.containsKey(s)) {
            System.out.println("Please, enter CORRECT id of the course!");
            s = scanner.next();
        }
        course = Storage.courses.get(s);

        //razdelenie
        System.out.println("Please, Select the Teacher");
        HashMap<String, Teacher> mp = Storage.teachers;
        if (mp.isEmpty()) {
            System.out.println("There is no teachers\n");
            return;
        }
        Iterator it = mp.entrySet().iterator();
        Teacher teacher;
        String id;
        int pos = 1;
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            id = (String) pair.getKey();
            teacher = mp.get(id);
            System.out.println(String.format("[%d] - ID: %s; %s: %s %s", pos++, id, teacher.getAccessRights(), teacher.getName(), teacher.getSurname()));
        }

        int choice = scanner.nextInt();
        while (choice < 0 || choice > pos) {
            System.out.println("Wrong input format");
            choice = scanner.nextInt();
        }
        Iterator it1 = mp.entrySet().iterator();
        int ps = 1;
        String id1;
        while (it1.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it1.next();
            id1 = (String) pair.getKey();
            if (ps++ == choice) {
                teacher = mp.get(id1);
                course.addTeacher(teacher);
                teacher.openCourse(course);
            }
        }
    }

    public void sendMessage() {
        Scanner scanner = new Scanner(System.in);
        String title;
        String text;
        String from;
        System.out.println("Write title for your email");
        title = scanner.nextLine();

        System.out.println("Choose the destination teacher");
        HashMap<String, Teacher> mp = Storage.teachers;
        if (mp.isEmpty()) {
            System.out.println("There is no teachers\n");
            return;
        }
        Iterator it = mp.entrySet().iterator();
        Teacher teacher = null;
        String id;
        int pos = 1;
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            id = (String) pair.getKey();
            teacher = mp.get(id);
            System.out.println(String.format("[%d] - ID: %s; %s: %s %s", pos++, id, teacher.getAccessRights(), teacher.getName(), teacher.getSurname()));
        }

        System.out.println("Please,choose the teacher");
        int choice = scanner.nextInt();
        while (choice > pos || choice < 1) {
            System.out.println("Wrong input format\nChoose another number:\n");
            choice = scanner.nextInt();
        }

        Iterator myit = mp.entrySet().iterator();
        String myid;
        int mypos = 1;
        while (myit.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) myit.next();
            if (mypos == choice) {
                myid = (String) pair.getKey();
                teacher = mp.get(myid);
            }
            mypos++;
        }

        System.out.println("Write the text:");
        scanner.nextLine();
        text = scanner.nextLine();

        Email email = new Email(title, text, this.name + " " + this.surname);
        teacher.addEmail(email);
    }
}
