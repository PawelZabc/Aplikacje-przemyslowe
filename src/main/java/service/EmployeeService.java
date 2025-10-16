package service;

import model.CompanyStatistics;
import model.Employee;
import model.Position;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {
    private List<Employee> employees;
    public EmployeeService() {
        employees = new ArrayList<Employee>();
    }


    public boolean addEmployee(Employee employee){

        if (employee != null && employee.isValid()&&isEmailUnique(employee.getEmail())){
            employees.add(employee);
            return true;
        }
        return false;
    }

    public boolean isEmailUnique(String email) {
        if (email == null || email.isEmpty()){return false;}
        for (Employee employee : employees) {
            if (employee.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public Employee getEmployeeByEmail(String email) {
        if (email == null || email.isEmpty()){return null;}
        for (Employee employee : employees) {
            if (employee.getEmail().equals(email)) {
                return employee;
            }
        }
        return null;
    }

    public List<Employee>getEmployeesByCompanyName(String companyName){
        if (companyName == null || companyName.isEmpty()){return null;}
        return employees.stream().filter
                (employee -> employee.getCompanyName().equals(companyName)).collect(Collectors.toList());
    }

    public void printEmployees(){
        System.out.println(this.toString());
    }

    public String toString() {
        String employeeList = "";
        for (int i = 0; i < employees.size(); i++) {
            employeeList += (i+1) + ":" + employees.get(i).toString() ;
            if (i != employees.size() - 1) {
                employeeList+="\n";
            }
        }
        return employeeList;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Map<Position,List<Employee>> getEmployeePositionMap(){
        Map<Position,List<Employee>> map = new HashMap<>();
        for (Position position : Position.values()) {
            map.put(position, new ArrayList<>());
        }
        for (Employee employee : employees) {
            map.get(employee.getPosition()).add(employee);
        }
        return map;
    }

    public void sortEmployeesBySurname(){
        this.employees.sort(Employee.bySurname);
    }

    public Map<Position,Integer> getPositionCountMap(){
        Map<Position,Integer> map = new HashMap<>();
        for (Position position : Position.values()) {
            map.put(position, 0);
        }
        for (Employee employee : employees) {
            map.put(employee.getPosition(), map.get(employee.getPosition()) + 1);
        }
        return map;
    }

    public double getAverageSalaryForCompany(String companyName){
        if (companyName == null || companyName.isEmpty()){return 0;}
        double sum = 0;
        List<Employee> companyEmployees = getEmployeesByCompanyName(companyName);
        if (companyEmployees.size() == 0){
            return 0;
        }
        for (Employee employee : companyEmployees) {
            sum+=employee.getSalary();
        }
        return sum/companyEmployees.size();
    }

    public double GetAverageSalary(){
        double sum = 0;
        if (employees.size() == 0){
            return 0;
        }
        for (Employee employee : employees) {
            sum+=employee.getSalary();
        }
        return sum/employees.size();
    }

    public List<Employee> validateSalaryConsistency (){
        return employees.stream().filter(employee -> employee.getSalary() < employee.getPosition().salary).collect(Collectors.toList());
    }

    public Map<String,CompanyStatistics>getCompanyStatistics(){
        Map<String,CompanyStatistics> statisticsMap = new HashMap<>();
        employees.forEach(employee -> {
            String company = employee.getCompanyName();
            if (statisticsMap.get(company) == null) {
                statisticsMap.put(company, new CompanyStatistics(employee));
            } else {
                if (statisticsMap.get(company).getEmployeeWithHighestSalary().getSalary() < employee.getSalary()) {
                    statisticsMap.get(company).setEmployeeWithHighestSalary(employee);
                }
                statisticsMap.get(company).addToAverageSalary(employee.getSalary());
                statisticsMap.get(company).incrementNumberOfEmployees();
            }
        });
        statisticsMap.forEach((key, value) -> {statisticsMap.get(key).setAverageSalary(value.getAverageSalary()/value.getNumberOfEmployees());});
        return statisticsMap;
    }

    public Optional<Employee> getEmployeeWithHighestSalary(){
        return employees.stream().max(Employee.bySalary);
    }

    public static void main(String[] args) {
        EmployeeService system = new EmployeeService();

        Employee emp1 = new Employee("name1", "surname1", "mail1", "company1", Position.Prezes);
        Employee emp2 = new Employee("name2", "aurname2", "mail2", "company1", Position.Wiceprezes);
        Employee emp3 = new Employee("name3", "durname3", "mail3", "company1", Position.Manager);
        Employee emp4 = new Employee("name4", "gurname4", "mail4", "company1", Position.Prezes);
        Employee emp5 = new Employee("name5", "turname5", "mail5", "company2", Position.Stażysta);
        Employee emp6 = new Employee("name6", "eurname6", "mail1", "company2", Position.Programista);
        Employee emp7 = new Employee("name7", "purname7", "mail7", "company2", Position.Manager);
        Employee emp8 = new Employee("name8", "kurname8", "mail8", "company3", Position.Stażysta);
        Employee emp9 = new Employee("name9", "nurname9", "mail9", "company3", Position.Wiceprezes);
        system.addEmployee(emp1);
        system.addEmployee(emp2);
        system.addEmployee(emp3);
        system.addEmployee(emp4);
        system.addEmployee(emp5);
        system.addEmployee(emp6);
        system.addEmployee(emp7);
        system.addEmployee(emp8);
        system.addEmployee(emp9);
        system.printEmployees();
        system.sortEmployeesBySurname();
        system.printEmployees();
        System.out.println(system.getEmployeePositionMap());
        System.out.println(system.getPositionCountMap());
        EmployeeService system2 = new EmployeeService();
        System.out.println(system.getEmployeeWithHighestSalary());
        System.out.println(system2.getEmployeeWithHighestSalary());



    }

}
