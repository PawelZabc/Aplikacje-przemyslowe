package model;

import java.util.*;


public class Employee {
    private String name;
    private String surname;
    private String email;
    private String companyName;
    private model.Position position;
    private double salary;

    public static Comparator<Employee> bySurname = (emp1,emp2) -> emp1.getSurname().compareTo(emp2.getSurname());
    public static Comparator<Employee> bySalary = (emp1,emp2) -> emp1.getSalary() == emp2.getSalary() ? 0 : emp1.getSalary()>emp2.getSalary() ? -1 : 1;

    @Override public boolean equals(Object o) {
        if (this.email.equals(((Employee) o).email)) {
            return true;
        }
        return false;
    }

    @Override public int hashCode() {
        return email.hashCode();
    }

    public Employee(String name, String surname, String email, String companyName, model.Position position) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.companyName = companyName;
        this.position = position;
        this.salary = position.salary;
    }
    public Employee(String name, String surname, String email, String companyName, model.Position position, double salary) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.companyName = companyName;
        this.position = position;
        this.salary = salary;
    }
    public Employee(){}

    public boolean isValid() {
        return name != null && !name.isEmpty() &&
                surname != null && !surname.isEmpty() &&
                email != null && !email.isEmpty() &&
                companyName != null && !companyName.isEmpty() &&
                position != null && salary >=0;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public model.Position getPosition() {
        return position;
    }
    public void setPosition(model.Position position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }


    public String toString() {
        return "(name:" + name +
                ", surname:" + surname +
                ", email:" + email +
                ", company name: " + companyName +
                ", position: " + position +
                ", salary: " + salary + ")";
    }


}
