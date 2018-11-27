package Users;

import Enums.AccessRights;


import java.io.*;

import java.util.Scanner;
import java.util.Vector;

public class TechSupport extends Employee implements Serializable {

    private Vector<Order> orders;


    public TechSupport(String id, String name, String surname, AccessRights accessRights, double salary) {
        super(id, name, surname, accessRights, salary);
        orders = new Vector<Order>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void viewAllOrders() {
        if (orders.size() == 0) {
            System.out.println("No orders!");
            return;
        }
        int count = 1;
        Scanner in = new Scanner(System.in);
        System.out.println("Existing orders: ");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(String.format("[%d] - %s", count++, orders.elementAt(i).getTitle()));
        }
        System.out.println(String.format("[%d] - Back", count));
        int choice = in.nextInt();
        while (choice < 1 || choice > count) {
            System.out.println("Wrong input format!\n");
            choice = in.nextInt();
        }

        if (choice == count) {
            return;
        }
        Order curOrd = orders.elementAt(choice - 1);
        System.out.println(String.format("%s at %s\n%s", curOrd.getTitle(), curOrd.getDate(), curOrd.getText()));
        orders.elementAt(choice - 1).setDoneOrNotTrue();
    }

    public void viewDoneOrders() {
        Vector<Order> ord = new Vector<>();
        for (int i = 0; i < orders.size(); i++) {
            if (!orders.elementAt(i).isDoneOrNot()) continue;
            ord.add(orders.elementAt(i));
        }

        if (ord.size() == 0) {
            System.out.println("No done orders!");
            return;
        }
        int count = 1;
        Scanner in = new Scanner(System.in);

        System.out.println("Existing orders: ");
        for (int i = 0; i < ord.size(); i++) {
            System.out.println(String.format("[%d] - %s", count++, orders.elementAt(i).getTitle()));
        }
        System.out.println(String.format("[%d] - Back", count));

        int choice = in.nextInt();
        while (choice < 1 || choice > count) {
            System.out.println("Wrong input format!\n");
            choice = in.nextInt();
        }

        if (choice == count) {
            return;
        }
        Order curOrd = ord.elementAt(choice - 1);
        System.out.println(String.format("%s at %s\n%s", curOrd.getTitle(), curOrd.getDate(), curOrd.getText()));
        orders.elementAt(orders.indexOf(ord.elementAt(choice - 1))).setDoneOrNotTrue();
    }

    public void viewUndoneOrders() {
        Vector<Order> ord = new Vector<>();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.elementAt(i).isDoneOrNot()) continue;
            ord.add(orders.elementAt(i));
        }

        if (ord.size() == 0) {
            System.out.println("No undone orders!");
            return;
        }
        int count = 1;
        Scanner in = new Scanner(System.in);

        System.out.println("Existing orders: ");
        for (int i = 0; i < ord.size(); i++) {
            System.out.println(String.format("[%d] - %s", count++, orders.elementAt(i).getTitle()));
        }
        System.out.println(String.format("[%d] - Back", count));

        int choice = in.nextInt();
        while (choice < 1 || choice > count) {
            System.out.println("Wrong input format!\n");
            choice = in.nextInt();
        }

        if (choice == count) {
            return;
        }

        Order curOrd = ord.elementAt(choice - 1);
        System.out.println(String.format("%s at %s\n%s", curOrd.getTitle(), curOrd.getDate(), curOrd.getText()));
        orders.elementAt(orders.indexOf(ord.elementAt(choice - 1))).setDoneOrNotTrue();
    }

}
