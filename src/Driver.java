import Storage.*;
import Users.*;

import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class Driver {
    private static Scanner in = new Scanner(System.in);

    public static void login() {
        while (true) {
            int choice = 0;
            String msg = "";
            do {
                System.out.println(msg);
                System.out.println("[1] - Log in");
                System.out.println("[2] - Quit");
                choice = in.nextInt();
                msg = "Wrong input format, try again\n";
            }
            while (choice < 1 || choice > 2);

            switch (choice) {
                case 1:
                    String username, password;
                    System.out.println("Please, write your username: ");
                    username = in.next();
                    System.out.println("Please, write your password: ");
                    password = in.next();
                    if (Storage.users.containsKey(username) && Storage.users.get(username).getPassword().equals(password)) {
                        run(Storage.users.get(username));
                        try {
                            Storage.serializeAll();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        System.out.println("Wrong username or password\n");
                    }
                    break;
                case 2:
                    return;
            }
        }
    }

    private static void run(User user) {
        switch (user.getAccessRights()) {
            case ADMIN:
                runAdmin(user.getId());
                break;
            case STUDENT:
                runStudent(user.getId());
                break;
            case MANAGER:
                runManager(user.getId());
                break;
            case TEACHER:
                runTeacher(user.getId());
                break;
            case TECHSUPPORT:
                runTechSupport(user.getId());
                break;
        }
    }

    private static void runAdmin(String id) {
        Admin admin = Storage.admins.get(id);
        Storage.writeLog(String.format("%s %s %s, %s logged into system at %s", admin.getId(), admin.getName(), admin.getSurname(), admin.getAccessRights(), new Date()));
        System.out.println(String.format("You're welcome, %s %s", admin.getName(), admin.getSurname()));
        while (true) {
            int choice = 0;
            String msg = "";
            do {
                System.out.println(msg);
                System.out.println("[1] - Add user");
                System.out.println("[2] - Delete user");
                System.out.println("[3] - Update user");
                System.out.println("[4] - Print users");
                System.out.println("[5] - See history of user");
                System.out.println("[6] - Change password");
                System.out.println("[7] - Log out");
                choice = in.nextInt();
                msg = "Wrong input format, try again\n";
            }
            while (choice < 1 || choice > 7);

            switch (choice) {
                case 1:
                    admin.addUser();
                    Storage.writeLog(String.format("%s Added new user at %s", admin.getId(), new Date()));
                    break;
                case 2:
                    admin.removeUser();
                    Storage.writeLog(String.format("%s Deleted user at %s", admin.getId(), new Date()));

                    break;
                case 3:
                    admin.updateUser();
                    Storage.writeLog(String.format("%s Updated user at %s", admin.getId(), new Date()));
                    break;
                case 4:
                    admin.printUsers();
                    break;
                case 5:
                    admin.getUserActions();
                    break;
                case 6:
                    admin.changePassword();
                    Storage.users.put(admin.getId(), admin);
                    Storage.admins.put(admin.getId(), admin);
                    break;
                case 7:
                    return;
            }
        }
    }

    private static void runManager(String id) {
        Manager manager = Storage.managers.get(id);
        Storage.writeLog(String.format("%s %s %s, %s logged into system at %s", manager.getId(), manager.getName(), manager.getSurname(), manager.getAccessRights(), new Date()));
        System.out.println(String.format("You're welcome, %s %s", manager.getName(), manager.getSurname()));
        while (true) {
            int choice = 0;
            String msg = "";

            do {
                System.out.println(msg);
                msg = "Wrong input format, try again\n";

                System.out.println("[1] - Add course");
                System.out.println("[2] - Delete course");
                System.out.println("[3] - View courses");
                System.out.println("[4] - View teachers");
                System.out.println("[5] - View students");
                System.out.println("[6] - Add teacher to the course");
                System.out.println("[7] - Send message to the teacher");
                System.out.println("[8] - Change password");
                System.out.println("[9] - Log out");
                choice = in.nextInt();
            }
            while (choice < 1 || choice > 9);

            switch (choice) {
                case 1:
                    manager.addCourse();
                    break;
                case 2:
                    manager.removeCourse();
                    break;
                case 3:
                    manager.viewCourses();
                    break;
                case 4:
                    manager.viewTeachers();
                    break;
                case 5:
                    manager.viewStudents();
                    break;
                case 6:
                    manager.addTeacherToCourse();
                    break;
                case 7:
                    manager.sendMessage();
                    break;
                case 8:
                    manager.changePassword();
                    Storage.users.put(manager.getId(), manager);
                    Storage.managers.put(manager.getId(), manager);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Wrong input format!\n");
                    break;
            }
        }
    }

    private static void runTechSupport(String id) {
        TechSupport curTechSup = Storage.techSupportGuys.get(id);
        Storage.writeLog(String.format("%s %s %s, %s logged into system at %s", curTechSup.getId(), curTechSup.getName(), curTechSup.getSurname(), curTechSup.getAccessRights(), new Date()));
        System.out.println(String.format("You're welcome, %s %s", curTechSup.getName(), curTechSup.getSurname()));
        while (true) {
            int choice = 0;
            String msg = "";

            do {
                System.out.println(msg);
                msg = "Wrong input format, try again\n";

                System.out.println("[1] - View all orders");
                System.out.println("[2] - View done orders");
                System.out.println("[3] - View undone orders");
                System.out.println("[4] - Change password");
                System.out.println("[5] - Log out");
                choice = in.nextInt();
            }
            while (choice < 1 || choice > 5);

            switch (choice) {
                case 1:
                    curTechSup.viewAllOrders();
                    break;
                case 2:
                    curTechSup.viewDoneOrders();
                    break;
                case 3:
                    curTechSup.viewUndoneOrders();
                    break;
                case 4:
                    curTechSup.changePassword();
                    Storage.users.put(curTechSup.getId(), curTechSup);
                    Storage.techSupportGuys.put(curTechSup.getId(), curTechSup);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Wrong input format!\n");
                    break;
            }
        }
    }

    private static void runStudent(String id) {
        Student student = Storage.students.get(id);
        Storage.writeLog(String.format("%s %s %s, %s logged into system at %s", student.getId(), student.getName(), student.getSurname(), student.getAccessRights(), new Date()));
        System.out.println(String.format("You're welcome, %s %s", student.getName(), student.getSurname()));
        while (true) {
            int choice = 0;
            String msg = "";

            do {
                System.out.println(msg);
                msg = "Wrong input format, try again\n";

                System.out.println("[1] - Register to course");
                System.out.println("[2] - Drop course");
                System.out.println("[3] - View marks by course");
                System.out.println("[4] - View transcript");
                System.out.println("[5] - View files");
                System.out.println("[6] - Change password");
                System.out.println("[7] - Log out");
                choice = in.nextInt();
            }
            while (choice < 1 || choice > 7);


            switch (choice) {
                case 1:
                    student.registerCourse();
                    break;
                case 2:
                    student.dropCourse();
                    break;
                case 3:
                    student.viewMarks();
                    break;
                case 4:
                    student.viewTranscript();
                    break;
                case 5:
                    student.viewFiles();
                    break;
                case 6:
                    student.changePassword();
                    Storage.users.put(student.getId(), student);
                    Storage.students.put(student.getId(), student);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Wrong input format!\n");
                    break;
            }
        }
    }

    private static void runTeacher(String id) {
        Teacher teacher = Storage.teachers.get(id);
        System.out.println(String.format("You're welcome, %s %s", teacher.getName(), teacher.getSurname()));
        Storage.writeLog(String.format("%s %s %s, %s logged into system at %s", teacher.getId(), teacher.getName(), teacher.getSurname(), teacher.getAccessRights(), new Date()));
        while (true) {
            int choice = 0;
            String msg = "";
            do {
                System.out.println(msg);
                msg = "Wrong input format, try again\n";

                System.out.println("[1] - Put mark");
                System.out.println("[2] - View emails");
                System.out.println("[3] - Send order");
                System.out.println("[4] - Add course file");
                System.out.println("[5] - View courses");
                System.out.println("[6] - View students");
                System.out.println("[7] - Change password");
                System.out.println("[8] - Log out");
                choice = in.nextInt();
            }
            while (choice < 1 || choice > 8);

            switch (choice) {
                case 1:
                    teacher.putMark();
                    break;
                case 2:
                    teacher.viewEmails();
                    break;
                case 3:
                    teacher.sendOrder();
                    break;
                case 4:
                    teacher.addCourseFiles();
                    break;
                case 5:
                    teacher.viewCourses();
                    break;
                case 6:
                    teacher.viewStudents();
                    break;
                case 7:
                    teacher.changePassword();
                    Storage.users.put(teacher.getId(), teacher);
                    Storage.teachers.put(teacher.getId(), teacher);
                    break;
                case 8:
                    return;
            }
        }
    }
}
