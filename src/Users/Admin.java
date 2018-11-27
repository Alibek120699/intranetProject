package Users;

import Enums.AccessRights;
import Enums.TeacherPosition;
import Exceptions.InvalidYearException;
import Storage.Storage;

import java.io.*;
import java.util.*;

public class Admin extends User implements Serializable {

    public Admin(String id, String name, String surname, AccessRights accessRights) {
        super(id, name, surname, accessRights);
    }

    public void addUser() {
        User user;
        double salary;
        int choice = 0;

        String id;
        String name;
        String surname;
        AccessRights userAccess = AccessRights.STUDENT;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write down user's id: ");
        id = scanner.next();

        System.out.println("Write down user's name: ");
        name = scanner.next();

        System.out.println("Write down user's surname: ");
        surname = scanner.next();

        System.out.println("Please, choose user's Access Rights:");
        System.out.println("[1] - Student");
        System.out.println("[2] - Teacher");
        System.out.println("[3] - Manager");
        System.out.println("[4] - Tech Support Guy");
        System.out.println("[5] - Admin");

        choice = scanner.nextInt();
        while (!(choice >= 1 && choice <= 5)) {
            choice = scanner.nextInt();
            System.out.println("Wrong input format, try again");
        }

        switch (choice) {
            case 1:
                userAccess = AccessRights.STUDENT;
                System.out.println("Please, enter student's year of study:");
//                System.out.println("[1]");
//                System.out.println("[2]");
//                System.out.println("[3]");
//                System.out.println("[4]");
                choice = scanner.nextInt();
//                while (!(choice >= 1 && choice <= 4)) {
//                    choice = scanner.nextInt();
//                    System.out.println("Wrong input format, try again");
//                }
                Student student = null;
                try {
                    student = new Student(id, name, surname, userAccess, choice);
                } catch (InvalidYearException iye){
                    System.out.println(iye.getMessage());
                    return;
                }
                Storage.students.put(id, student);
                user = student;
                break;

            case 2:
                userAccess = AccessRights.TEACHER;
                System.out.println("Please Enter teacher's salary");
                salary = scanner.nextDouble();
                while (salary <= 0) {
                    System.out.println("Wrong format, change salary");
                    salary = scanner.nextDouble();
                }
                TeacherPosition teacherPosition = TeacherPosition.TUTOR;
                System.out.println("Please, choose teacher's position:");
                System.out.println("[1] - Tutor");
                System.out.println("[2] - Lector");
                System.out.println("[3] - Senior Lector");
                System.out.println("[4] - Professor");
                choice = scanner.nextInt();
                while (!(choice >= 1 && choice <= 4)) {
                    choice = scanner.nextInt();
                    System.out.println("Wrong input format, try again");
                }
                switch(choice){
                    case 1:
                        teacherPosition = TeacherPosition.TUTOR;
                        break;
                    case 2:
                        teacherPosition = TeacherPosition.LECTOR;
                        break;
                    case 3:
                        teacherPosition = TeacherPosition.SENIOR_LECTOR;
                        break;
                    case 4:
                        teacherPosition = TeacherPosition.PROFESSOR;
                        break;

                }
                Teacher teacher = new Teacher(id, name, surname, userAccess, salary, teacherPosition);
                Storage.teachers.put(id, teacher);
                user = teacher;
                break;

            case 3:
                userAccess = AccessRights.MANAGER;
                System.out.println("Please Enter manager's salary");
                salary = scanner.nextDouble();
                while (salary <= 0) {
                    System.out.println("Wrong format, change salary");
                    salary = scanner.nextDouble();
                }
                Manager manager = new Manager(id, name, surname, userAccess, salary);
                Storage.managers.put(id, manager);
                user = manager;
                break;
            case 4:
                userAccess = AccessRights.TECHSUPPORT;
                System.out.println("Please Enter tech support's salary");
                salary = scanner.nextDouble();
                while (salary <= 0) {
                    System.out.println("Wrong format, change salary");
                    salary = scanner.nextDouble();
                }
                TechSupport techSupport = new TechSupport(id, name, surname, userAccess, salary);
                Storage.techSupportGuys.put(id, techSupport);
                user = techSupport;
                break;
            case 5:
                userAccess = AccessRights.ADMIN;
                Admin admin = new Admin(id, name, surname, userAccess);
                Storage.admins.put(id, admin);
                user = admin;
                break;
            default:
                user = new User("000", "Error", "Error", userAccess);

        }

        Storage.users.put(id, user);

        System.out.println(user + " is added to the system");
        Storage.writeLog(String.format("%s New user %s %s, %s is added into the system at %s", user.getId(), user.getName(), user.getSurname(), user.getAccessRights(), new Date()));

    }

    public void removeUser() {

        Map<String, User> mp = Storage.users;
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " " + pair.getValue().toString());
        }

        System.out.println("Please, enter user's id");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();
        while (!Storage.users.containsKey(id)) {
            System.out.println("User's didn't found, try again");
            System.out.println("Please, enter user's id");
            id = scanner.next();
        }

        if (Storage.users.containsKey(id)) {
            switch (Storage.users.get(id).getAccessRights()) {
                case TECHSUPPORT:
                    Storage.techSupportGuys.remove(id);
                    break;
                case TEACHER:
                    Storage.teachers.remove(id);
                    break;
                case MANAGER:
                    Storage.managers.remove(id);
                    break;
                case STUDENT:
                    Storage.students.remove(id);
                    break;
                case ADMIN:
                    Storage.admins.remove(id);
                    break;
                default:
                    break;

            }
            User u = Storage.users.get(id);
            Storage.users.remove(id);
            System.out.println(u.toString() + " is removed from the system");
            Storage.writeLog(String.format("%s - %s is removed from the system", new Date(), u.toString()));
        } else {
            System.out.println("ID don't match!\nPlease, try again");
        }
    }

    public void updateUser() {
        int choice = 0;
        int pos = 1;
        Scanner scanner = new Scanner(System.in);

        HashMap<String, User> mp = Storage.users;
        if (mp.isEmpty()) {
            System.out.println("There is no any users\n");
            return;
        }
        Iterator it = mp.entrySet().iterator();
        User user;
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            id = (String) pair.getKey();
            user = mp.get(id);
            System.out.println(String.format("[%d] - ID: %s; %s: %s %s", pos++, id, user.getAccessRights(), user.getName(), user.getSurname()));
        }

        System.out.println("Choose number of user which you want update");

        choice = scanner.nextInt();
        while (!(choice >= 1 && choice <= pos)) {
            System.out.println("Wrong input format, try again");
            choice = scanner.nextInt();
        }

        pos = 1;
        it = mp.entrySet().iterator();
        String userId = "";
        while (pos <= choice && it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (pos++ == choice) {
                userId = pair.getKey() + "";
                System.out.println(userId + " id");
                break;
            }
        }

        System.out.println("Choose what you want to change");

        switch (Storage.users.get(userId).accessRights) {
            case ADMIN:
                Functions.ChangeFunctions.adminChange(userId);
                break;
            case MANAGER:
                Functions.ChangeFunctions.managerChange(userId);
                break;
            case STUDENT:
                Functions.ChangeFunctions.studentChange(userId);
                break;
            case TEACHER:
                Functions.ChangeFunctions.teacherChange(userId);
                break;
            case TECHSUPPORT:
                Functions.ChangeFunctions.techSupportGuyChange(userId);
                break;
        }
        user = Storage.users.get(userId);
    }

    public void printUsers() {
        System.out.println("Choose category: ");
        System.out.println("[1] - Admins");
        System.out.println("[2] - Students");
        System.out.println("[3] - Managers");
        System.out.println("[4] - Teachers");
        System.out.println("[5] - Tech Support Guys");
        System.out.println("[6] - All users");
        System.out.println("[7] - Back");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        if (choice < 1 || choice > 7) {
            System.out.println("Wrong input format\n");
            printUsers();
            return;
        }

        int pos = 1;
        String id = null;
        switch (choice) {
            case 1:
                HashMap<String, Admin> mp1 = Storage.admins;
                if (mp1.isEmpty()) {
                    System.out.println("There is no admins\n");
                    return;
                }
                Iterator it1 = mp1.entrySet().iterator();
                Admin user1;
                while (it1.hasNext()) {
                    HashMap.Entry pair = (HashMap.Entry) it1.next();
                    id = (String) pair.getKey();
                    user1 = mp1.get(id);
                    System.out.println(String.format("[%d] - ID: %s; %s: %s %s", pos++, id, user1.getAccessRights(), user1.getName(), user1.getSurname()));
                }
                break;
            case 2:
                HashMap<String, Student> mp2 = Storage.students;
                if (mp2.isEmpty()) {
                    System.out.println("There is no students");
                    System.out.println();
                    return;
                }
                Iterator it2 = mp2.entrySet().iterator();
                Student user2;
                while (it2.hasNext()) {
                    HashMap.Entry pair = (HashMap.Entry) it2.next();
                    id = (String) pair.getKey();
                    user2 = mp2.get(id);
                    System.out.println(String.format("[%d] - ID: %s; %s: %s %s", pos++, id, user2.getAccessRights(), user2.getName(), user2.getSurname()));
                }
                break;
            case 3:
                HashMap<String, Manager> mp3 = Storage.managers;
                if (mp3.isEmpty()) {
                    System.out.println("There is no managers\n");
                    return;
                }
                Iterator it3 = mp3.entrySet().iterator();
                Manager user3;
                while (it3.hasNext()) {
                    HashMap.Entry pair = (HashMap.Entry) it3.next();
                    id = (String) pair.getKey();
                    user3 = mp3.get(id);
                    System.out.println(String.format("[%d] - ID: %s; %s: %s %s", pos++, id, user3.getAccessRights(), user3.getName(), user3.getSurname()));
                }
                break;
            case 4:
                HashMap<String, Teacher> mp4 = Storage.teachers;
                if (mp4.isEmpty()) {
                    System.out.println("There is no teachers\n");
                    return;
                }
                Iterator it4 = mp4.entrySet().iterator();
                Teacher user4;
                while (it4.hasNext()) {
                    HashMap.Entry pair = (HashMap.Entry) it4.next();
                    id = (String) pair.getKey();
                    user4 = mp4.get(id);
                    System.out.println(String.format("[%d] - ID: %s; %s: %s %s", pos++, id, user4.getAccessRights(), user4.getName(), user4.getSurname()));
                }
                break;
            case 5:
                HashMap<String, TechSupport> mp5 = Storage.techSupportGuys;
                if (mp5.isEmpty()) {
                    System.out.println("There is no tech support guys\n");
                    return;
                }
                Iterator it5 = mp5.entrySet().iterator();
                TechSupport user5;
                while (it5.hasNext()) {
                    HashMap.Entry pair = (HashMap.Entry) it5.next();
                    id = (String) pair.getKey();
                    user5 = mp5.get(id);
                    System.out.println(String.format("[%d] - ID: %s; %s: %s %s", pos++, id, user5.getAccessRights(), user5.getName(), user5.getSurname()));
                }
                break;
            case 6:
                HashMap<String, User> mp6 = Storage.users;
                if (mp6.isEmpty()) {
                    System.out.println("There is no any users\n");
                    return;
                }
                Iterator it6 = mp6.entrySet().iterator();
                User user6;
                while (it6.hasNext()) {
                    HashMap.Entry pair = (HashMap.Entry) it6.next();
                    id = (String) pair.getKey();
                    user6 = mp6.get(id);
                    System.out.println(String.format("[%d] - ID: %s; %s: %s %s", pos++, id, user6.getAccessRights(), user6.getName(), user6.getSurname()));
                }
                break;
            case 7:
                return;
        }
        System.out.println();
    }

    public void getUserActions() {
        HashMap<String, User> mp = Storage.users;
        if (mp.isEmpty()) {
            System.out.println("There is no any users\n");
            return;
        }
        System.out.println("Existing users: ");
        Iterator it = mp.entrySet().iterator();
        User user;
        int pos = 1;
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            id = (String) pair.getKey();
            user = mp.get(id);
            System.out.println(String.format("[%d] - %s: %s %s", pos++, user.getAccessRights(), user.getName(), user.getSurname()));
        }

        System.out.println("Choose number of user: ");

        Scanner scanner = new Scanner(System.in);

        int choice = scanner.nextInt();
        while (!(choice >= 1 && choice <= pos)) {
            System.out.println("Wrong input format, try again");
            System.out.println("Choose number of user: ");

            choice = scanner.nextInt();
        }

        pos = 1;
        it = mp.entrySet().iterator();
        String userId = null;
        while (pos <= choice && it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (pos++ == choice) {
                userId = pair.getKey() + "";
                System.out.println(userId + " id");
                break;
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader("src/Files/logs.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String curId = line.substring(0, line.indexOf(" "));
                if (curId.equals(userId)) {
                    System.out.println(line.substring(line.indexOf(" ") + 1));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
