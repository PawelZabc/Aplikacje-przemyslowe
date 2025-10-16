package model;

public class CompanyStatistics {
    private int numberOfEmployees;
    private double averageSalary;
    private Employee employeeWithHighestSalary;

    public CompanyStatistics(int numberOfEmployees, double averageSalary, Employee employeeWithHighestSalary) {
        this.numberOfEmployees = numberOfEmployees;
        this.averageSalary = averageSalary;
        this.employeeWithHighestSalary = employeeWithHighestSalary;
    }
    public CompanyStatistics(Employee employeeWithHighestSalary) {
        this.employeeWithHighestSalary = employeeWithHighestSalary;
        this.averageSalary = employeeWithHighestSalary.getSalary();
        this.numberOfEmployees = 1;
    }
    public CompanyStatistics() {}


    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }

    public Employee getEmployeeWithHighestSalary() {
        return employeeWithHighestSalary;
    }

    public void setEmployeeWithHighestSalary(Employee employeeWithHighestSalary) {
        this.employeeWithHighestSalary = employeeWithHighestSalary;
    }

    public void incrementNumberOfEmployees(){
        this.numberOfEmployees++;
    }
    public void addToAverageSalary(double salary){
        this.averageSalary += salary;
    }

    public String toString(){
        return "Number of employees: " + numberOfEmployees + "; average salary: " + averageSalary +"; employee with highest salary:" +employeeWithHighestSalary.toString();
    }



}
