package Users;

import Enums.AccessRights;

import java.io.Serializable;

public class Employee extends User implements Serializable {
    protected double salary;

    public Employee(String id, String name, String surname, AccessRights accessRights, double salary) {
        super(id, name, surname, accessRights);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
