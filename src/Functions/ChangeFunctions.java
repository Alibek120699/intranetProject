package Functions;

import java.util.Date;
import java.util.Scanner;

import Enums.*;
import Storage.*;
import Users.*;
import Users.Student;
import Users.User;

public class ChangeFunctions {

    public static void adminChange(String id) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("[1] - ID");
        System.out.println("[2] - Name");
        System.out.println("[3] - Surname");
        System.out.println("[4] - Password");

        int choice = scanner.nextInt();
        if (choice < 1 || choice > 4) {
            adminChange(id);
            return;
        }
        String newKey = id;
        String newName = "Abraam";
        String newSurname = "Avraam";
        String newPassword = "Autist";
        switch (choice) {
            case 1:
                System.out.println("Enter new ID:");
                newKey = scanner.next();

                Storage.admins.put(newKey, Storage.admins.get(id));
                Storage.admins.get(newKey).setId(newKey);
                Storage.admins.remove(id);

                Storage.users.put(newKey, Storage.users.get(id));
                Storage.users.get(newKey).setId(newKey);
                Storage.users.remove(id);

                id = newKey;
                break;
            case 2:
                System.out.println("Enter new name: ");
                newName = scanner.next();

                Storage.admins.get(newKey).setName(newName);
                Storage.users.get(newKey).setName(newName);
                break;
            case 3:
                System.out.println("Enter new surname: ");
                newSurname = scanner.next();

                Storage.admins.get(newKey).setSurname(newSurname);
                Storage.users.get(newKey).setSurname(newSurname);
                break;
            case 4:
                System.out.println("Enter new password: ");
                newPassword = scanner.next();

                Storage.admins.get(newKey).setPassword(newPassword);
                Storage.users.get(newKey).setPassword(newPassword);
                break;
            case 5:
                break;
        }
    }

    public static void managerChange(String id) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("[1] - ID");
        System.out.println("[2] - Name");
        System.out.println("[3] - Surname");
        System.out.println("[4] - Password");
        System.out.println("[5] - Salary");

        int choice = scanner.nextInt();
        if (choice < 1 || choice > 5) {
            managerChange(id);
            return;
        }
        String newKey = id;
        String newName = "Abraam";
        String newSurname = "Avraam";
        String newPassword = "Autist";
        Double newSalary = 0.0;

        switch (choice) {
            case 1:
                System.out.println("Enter new ID");
                newKey = scanner.next();
                Storage.writeLog(String.format("%s - %s is updated", new Date(), Storage.users.get(id).toString()));

                Storage.managers.put(newKey, Storage.managers.get(id));
                Storage.managers.get(newKey).setId(newKey);
                Storage.managers.remove(id);

                Storage.users.put(newKey, Storage.users.get(id));
                Storage.users.get(newKey).setId(newKey);
                Storage.users.remove(id);

                break;
            case 2:
                System.out.println("Enter new Name");
                newName = scanner.next();
                Storage.writeLog(String.format("%s - %s is updated", new Date(), Storage.users.get(id).toString()));

                Storage.managers.get(newKey).setName(newName);
                Storage.users.get(newKey).setName(newName);
                break;
            case 3:
                System.out.println("Enter new Surname");
                newSurname = scanner.next();
                Storage.writeLog(String.format("%s - %s is updated", new Date(), Storage.users.get(id).toString()));

                Storage.managers.get(newKey).setSurname(newSurname);
                Storage.users.get(newKey).setSurname(newSurname);
                break;
            case 4:
                System.out.println("Enter new Password");
                newPassword = scanner.next();
                Storage.writeLog(String.format("%s - %s is updated", new Date(), Storage.users.get(id).toString()));

                Storage.managers.get(newKey).setPassword(newPassword);
                Storage.users.get(newKey).setPassword(newPassword);
                break;
            case 5:
                System.out.println("Enter new Salary");
                newSalary = scanner.nextDouble();
                Storage.writeLog(String.format("%s - %s is updated", new Date(), Storage.users.get(id).toString()));

                Storage.managers.get(newKey).setSalary(newSalary);
                break;
            case 6:
                break;
        }
        User user = Storage.users.get(newKey);
        Storage.writeLog(String.format("%s User %s %s, %s is updated at %s", user.getId(), user.getName(), user.getSurname(), user.getAccessRights(), new Date()));
    }

    public static void studentChange(String id) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("[1] - ID");
        System.out.println("[2] - Name");
        System.out.println("[3] - Surname");
        System.out.println("[4] - Password");
        System.out.println("[5] - Change Year of study");

        int choice = scanner.nextInt();
        if (choice < 1 || choice > 5) {
            studentChange(id);
            return;
        }
        String newKey = id;
        String newName = "Abraam";
        String newSurname = "Avraam";
        String newPassword = "Autist";
        int newYear = 1;
        switch (choice) {
            case 1:
                System.out.println("Enter new ID");
                newKey = scanner.next();
                Storage.writeLog(String.format("%s - %s is updated", new Date(), Storage.users.get(id).toString()));

                Storage.students.put(newKey, Storage.students.get(id));
                Storage.students.get(newKey).setId(newKey);
                Storage.students.remove(id);

                Storage.users.put(newKey, Storage.users.get(id));
                Storage.users.get(newKey).setId(newKey);
                Storage.users.remove(id);
                break;
            case 2:
                System.out.println("Enter new Name");
                newName = scanner.next();
                Storage.writeLog(String.format("%s - %s is updated", new Date(), Storage.users.get(id).toString()));

                Storage.students.get(newKey).setName(newName);
                Storage.users.get(newKey).setName(newName);
                break;
            case 3:
                System.out.println("Enter new Surname");
                newSurname = scanner.next();
                Storage.writeLog(String.format("%s - %s is updated", new Date(), Storage.users.get(id).toString()));

                Storage.students.get(newKey).setSurname(newSurname);
                Storage.users.get(newKey).setSurname(newSurname);
                break;
            case 4:
                System.out.println("Enter new Password");
                newPassword = scanner.next();
                Storage.writeLog(String.format("%s - %s is updated", new Date(), Storage.users.get(id).toString()));

                Storage.students.get(newKey).setPassword(newPassword);
                Storage.users.get(newKey).setPassword(newPassword);
                break;
            case 5:
                System.out.println("Select new year of study");
                System.out.println("[1]");
                System.out.println("[2]");
                System.out.println("[3]");
                System.out.println("[4]");
                newYear = scanner.nextInt();
                while (newYear < 0 || newYear > 4) {
                    System.out.println("Wrong input format,try again!");
                    newYear = scanner.nextInt();
                }
                Storage.writeLog(String.format("s - %s is updated", new Date(), Storage.users.get(id).toString()));

                Storage.students.get(newKey).setYearOfStudy(newYear);
                break;
        }
    }

    public static void teacherChange(String id) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("[1] - ID");
        System.out.println("[2] - Name");
        System.out.println("[3] - Surname");
        System.out.println("[4] - Password");
        System.out.println("[5] - Salary");

        int choice = scanner.nextInt();
        if (choice < 1 || choice > 5) {
            teacherChange(id);
            return;
        }
        String newKey = id;
        String newName = "Abraam";
        String newSurname = "Avraam";
        String newPassword = "Autist";
        Double newSalary = 0.0;

        switch (choice) {
            case 1:
                System.out.println("Enter new ID");
                newKey = scanner.next();

                Storage.teachers.put(newKey, Storage.teachers.get(id));
                Storage.teachers.get(newKey).setId(newKey);
                Storage.teachers.remove(id);

                Storage.users.put(newKey, Storage.users.get(id));
                Storage.users.get(newKey).setId(newKey);
                Storage.users.remove(id);
                break;
            case 2:
                System.out.println("Enter new Name");
                newName = scanner.next();

                Storage.teachers.get(newKey).setName(newName);
                Storage.users.get(newKey).setName(newName);
                break;
            case 3:
                System.out.println("Enter new Surname");
                newSurname = scanner.next();

                Storage.teachers.get(newKey).setSurname(newSurname);
                Storage.users.get(newKey).setSurname(newSurname);
                break;
            case 4:
                System.out.println("Enter new Password");
                newPassword = scanner.next();

                Storage.teachers.get(newKey).setPassword(newPassword);
                Storage.users.get(newKey).setPassword(newPassword);
                break;
            case 5:
                System.out.println("Enter new Salary");
                newSalary = scanner.nextDouble();

                Storage.teachers.get(newKey).setSalary(newSalary);
                break;
        }
    }

    public static void techSupportGuyChange(String id) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("[1] - ID");
        System.out.println("[2] - Name");
        System.out.println("[3] - Surname");
        System.out.println("[4] - Password");
        System.out.println("[5] - Salary");

        int choice = scanner.nextInt();
        if (choice < 1 || choice > 5) {
            techSupportGuyChange(id);
            return;
        }
        String newKey = id;
        String newName = "Abraam";
        String newSurname = "Avraam";
        String newPassword = "Autist";
        Double newSalary = 0.0;

        switch (choice) {
            case 1:
                System.out.println("Enter new ID");
                newKey = scanner.next();
                Storage.writeLog(String.format("%s - %s is updated", new Date(), Storage.users.get(id).toString()));

                // TechSupport oldtc = Storage.techSupportGuys.get(id);
                //TechSupport newtc = new TechSupport(newKey, oldtc.getName(), oldtc.getSurname(), oldtc.getAccessRights(), oldtc.getSalary());

                Storage.techSupportGuys.put(newKey, Storage.techSupportGuys.get(id));
                Storage.techSupportGuys.get(newKey).setId(newKey);
                Storage.techSupportGuys.remove(id);

                Storage.users.put(newKey, Storage.users.get(id));
                Storage.users.get(newKey).setId(newKey);
                Storage.users.remove(id);

                /*
                Storage.techSupportGuys.put(newKey,newtc);
                Storage.techSupportGuys.remove(id);

                Storage.users.put(newKey,newtc);
                Storage.users.remove(id);*/
                break;
            case 2:
                System.out.println("Enter new Name");
                newName = scanner.next();
                Storage.writeLog(String.format("%s - %s is updated", new Date(), Storage.users.get(id).toString()));

                Storage.techSupportGuys.get(newKey).setName(newName);
                Storage.users.get(newKey).setName(newName);
                break;
            case 3:
                System.out.println("Enter new Surname");
                newSurname = scanner.next();
                Storage.writeLog(String.format("%s - %s is updated", new Date(), Storage.users.get(id).toString()));

                Storage.techSupportGuys.get(newKey).setSurname(newSurname);
                Storage.users.get(newKey).setSurname(newSurname);
                break;
            case 4:
                System.out.println("Enter new Password");
                newPassword = scanner.next();
                Storage.writeLog(String.format("%s - %s is updated", new Date(), Storage.users.get(id).toString()));

                Storage.techSupportGuys.get(newKey).setPassword(newPassword);
                Storage.users.get(newKey).setPassword(newPassword);
                break;
            case 5:
                System.out.println("Enter new Salary");
                newSalary = scanner.nextDouble();
                Storage.writeLog(String.format("%s - %s is updated", new Date(), Storage.users.get(id).toString()));

                Storage.techSupportGuys.get(newKey).setSalary(newSalary);
                break;
        }
    }
}

