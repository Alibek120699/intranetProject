import Enums.AccessRights;
import Enums.TeacherPosition;
import Storage.*;
import Users.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class WorkSpace {
    static void initialization() {
        try
        {
            Storage.deserializeAll();
        }
        catch (ClassNotFoundException clex)
        {
            clex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    static void authorization() {
        Driver.login();
    }

    static void exit() {
        try
        {
            Storage.serializeAll();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        System.exit(0);
    }

    static void firstRun() {
        Admin admin = new Admin("001", "Dauren", "Yerbolov", AccessRights.ADMIN);
        //Admin admin1 = new Admin("002", "Alibek", "Sayak", AccessRights.ADMIN);

        Storage.users.put(admin.getId(), admin);
        //Storage.users.put(admin1.getId(), admin1);
        Storage.admins.put(admin.getId(), admin);
        //Storage.admins.put(admin1.getId(), admin1);
        try {
            Storage.serializeAll();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String [] args)
    {
        //firstRun();
        initialization();
        authorization();
        exit();

    }
}
