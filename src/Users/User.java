package Users;

import Enums.AccessRights;
import Storage.Storage;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class User implements Serializable {
    protected String id;
    protected String password;
    protected String name;
    protected String surname;
    protected AccessRights accessRights;

    public User(String id, String name, String surname, AccessRights accessRights) {
        this.id = id;
        this.password = "123";
        this.name = name;
        this.surname = surname;
        this.accessRights = accessRights;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword() {
        Scanner in = new Scanner(System.in);
        String oldpass, newpass;
        System.out.println("Enter old password:");
        oldpass = in.next();
        System.out.println("Enter new password:");
        newpass = in.next();
        if (oldpass.equals(this.getPassword())) {
            password = newpass;
            System.out.println("Password is successfully changed!\n");
            Storage.writeLog(String.format("%s %s %s %s changed password %s",id, name, surname, accessRights, new Date()));

        } else {
            System.out.println("Sorry, passwords don't match\nPassword didn't change\n");
        }

    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public AccessRights getAccessRights() {
        return accessRights;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccessRights(AccessRights accessRights) {
        this.accessRights = accessRights;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("ID: %s Access rights: %s Name: %s Surname: %s", this.id, this.accessRights, this.name, this.surname);
    }
}
