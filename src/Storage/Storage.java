package Storage;

import Enums.AccessRights;
import Users.*;
import Course.*;

import java.io.*;
import java.util.HashMap;

public class Storage {
    public static HashMap<String, User> users = new HashMap<>();
    public static HashMap<String, TechSupport> techSupportGuys = new HashMap<>();
    public static HashMap<String, Teacher> teachers = new HashMap<>();
    public static HashMap<String, Student> students = new HashMap<>();
    public static HashMap<String, Manager> managers = new HashMap<>();
    public static HashMap<String, Admin> admins = new HashMap<>();
    public static HashMap<String, Course> courses = new HashMap<String, Course>();


    public static void deserializeAll() throws IOException, ClassNotFoundException {
        users = getUsers();
        admins = getAdmins();
        students = getStudents();
        teachers = getTeachers();
        managers = getManagers();
        techSupportGuys = getTechSupportGuys();
        courses = getCourses();
    }

    public static void serializeAll() throws IOException {
        saveUsers(users);
        saveAdmins(admins);
        saveStudents(students);
        saveTeachers(teachers);
        saveManagers(managers);
        saveTechSupportGuys(techSupportGuys);
        saveCourses(courses);
    }

    public static void writeLog(String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/Files/logs.txt", true))) {
            bw.write(message + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void saveUsers(HashMap<String, User> users) throws IOException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/Files/Users.out"));
            oos.writeObject(users);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static HashMap<String, User> getUsers() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/Files/Users.out"));
            HashMap<String, User> users = (HashMap<String, User>) ois.readObject();
            ois.close();
            return users;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new HashMap<String, User>();
        }
    }

    private static HashMap<String, Admin> getAdmins() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/Files/Admins.out"));
            HashMap<String, Admin> admins = (HashMap<String, Admin>) ois.readObject();
            ois.close();
            return admins;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new HashMap<String, Admin>();
        }
    }

    private static void saveAdmins(HashMap<String, Admin> admins) throws IOException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/Files/Admins.out"));
            oos.writeObject(admins);
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Check path");
        }
    }

    private static HashMap<String, Student> getStudents() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/Files/Students.out"));
            HashMap<String, Student> students = (HashMap<String, Student>) ois.readObject();
            ois.close();
            return students;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new HashMap<String, Student>();
        }
    }

    private static void saveStudents(HashMap<String, Student> Students) throws IOException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/Files/Students.out"));
            oos.writeObject(Students);
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Check path");
        }
    }

    private static HashMap<String, Teacher> getTeachers() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/Files/Teachers.out"));
            HashMap<String, Teacher> teachers = (HashMap<String, Teacher>) ois.readObject();
            ois.close();
            return teachers;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new HashMap<String, Teacher>();
        }
    }

    private static void saveTeachers(HashMap<String, Teacher> teachers) throws IOException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/Files/Teachers.out"));
            oos.writeObject(teachers);
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Check path");
        }
    }

    private static HashMap<String, Manager> getManagers() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/Files/Managers.out"));
            HashMap<String, Manager> managers = (HashMap<String, Manager>) ois.readObject();
            ois.close();
            return managers;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new HashMap<String, Manager>();
        }
    }

    private static void saveManagers(HashMap<String, Manager> managers) throws IOException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/Files/Managers.out"));
            oos.writeObject(managers);
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Check path");
        }
    }

    private static HashMap<String, TechSupport> getTechSupportGuys() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/Files/TechSupportGuys.out"));
            HashMap<String, TechSupport> techSupportGuys = (HashMap<String, TechSupport>) ois.readObject();
            ois.close();
            return techSupportGuys;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new HashMap<String, TechSupport>();
        }
    }

    private static void saveTechSupportGuys(HashMap<String, TechSupport> techSupportGuys) throws IOException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/Files/TechSupportGuys.out"));
            oos.writeObject(techSupportGuys);
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Check path");
        }
    }

    private static HashMap<String, Course> getCourses() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/Files/Courses.out"));
            HashMap<String, Course> courses = (HashMap<String, Course>) ois.readObject();
            ois.close();
            return courses;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new HashMap<String, Course>();
        }
    }

    private static void saveCourses(HashMap<String, Course> courses) throws IOException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/Files/Courses.out"));
            oos.writeObject(courses);
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Check path");
        }
    }
}
