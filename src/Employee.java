import java.util.*;


public class Employee {
    private String name;
    private String surname;
    private String email;
    private String companyName;
    private Position position;
    private double salary;


    static Comparator<Employee> bySurname = (emp1,emp2) -> emp1.getSurname().compareTo(emp2.getSurname());
    static Comparator<Employee> bySalary = (emp1,emp2) -> emp1.getSalary()>emp2.getSalary() ? -1 : 1;
    public enum Position {
        Prezes("Prezes",25000.0,1),
        Wiceprezes("Wiceprezes",18000.0,2),
        Manager("Manager",12000.0,3),
        Programista("Programista",8000.0,4),
        Stażysta("Stażysta",3000.0,5);
        public final String name;
        public final double salary;
        public final int hierarchy;

        private Position(String name, double salary, int hierarchy) {
            this.name = name;
            this.salary = salary;
            this.hierarchy = hierarchy;
        }

    }
    @Override public boolean equals(Object o) {
        if (this.email.equals(((Employee) o).email)) {
            return true;
        }
        return false;
    }

    @Override public int hashCode() {
        return email.hashCode();
    }

    Employee(String name, String surname, String email, String companyName, Position position) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.companyName = companyName;
        this.position = position;
        this.salary = position.salary;
    }
    Employee(String name, String surname, String email, String companyName, Position position, double salary) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.companyName = companyName;
        this.position = position;
        this.salary = salary;
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

    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
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
