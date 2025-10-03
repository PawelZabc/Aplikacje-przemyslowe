import java.util.*;
import java.util.stream.Collectors;

public class EmployeeSystem {
    private List<Employee> employees;
    public EmployeeSystem() {
        employees = new ArrayList<Employee>();
    }



    public void AddEmployee(Employee employee){
        if (IsEmailUnique(employee.getEmail())){
            employees.add(employee);
        }
    }

    public boolean IsEmailUnique(String email) {
        for (Employee employee : employees) {
            if (employee.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }
    public Employee GetEmployeeByEmail(String email) {
        for (Employee employee : employees) {
            if (employee.getEmail().equals(email)) {
                return employee;
            }
        }
        return null;
    }
    public List<Employee>GetEmployeesByCompanyName(String companyName){
        return employees.stream().filter
                (employee -> employee.getCompanyName().equals(companyName)).collect(Collectors.toList());
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
    public List<Employee> GetEmployees() {
        return employees;
    }


    public Map<Employee.Position,List<Employee>> getEmployeePositionMap(){
        Map<Employee.Position,List<Employee>> map = new HashMap<>();
        for (Employee.Position position : Employee.Position.values()) {
            map.put(position, new ArrayList<>());
        }
        for (Employee employee : employees) {
            map.get(employee.getPosition()).add(employee);
        }
        return map;
//        return employees.stream().collect(Collectors.groupingBy(Employee::getPosition));
    }

    public Map<Employee.Position,Integer> getPositionCountMap(){
        Map<Employee.Position,Integer> map = new HashMap<>();
        for (Employee.Position position : Employee.Position.values()) {
            map.put(position, 0);
        }
        for (Employee employee : employees) {
            map.put(employee.getPosition(), map.get(employee.getPosition()) + 1);
        }
        return map;
    }

    public double GetAverageSalaryForCompany(String companyName){
        double sum = 0;
        List<Employee> companyEmployees = GetEmployeesByCompanyName(companyName);
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


    public Optional<Employee> GetEmployeeWithHighestSalary(){

        return employees.stream().max(Employee.bySalary);
    }


    public static void main(String[] args) {
        EmployeeSystem system = new EmployeeSystem();

        Employee emp1 = new Employee("name1", "surname1", "mail1", "company1", Employee.Position.Prezes);
        Employee emp2 = new Employee("name2", "aurname2", "mail2", "company1", Employee.Position.Wiceprezes);
        Employee emp3 = new Employee("name3", "durname3", "mail3", "company1", Employee.Position.Manager);
        Employee emp4 = new Employee("name4", "gurname4", "mail4", "company1", Employee.Position.Prezes);
        Employee emp5 = new Employee("name5", "turname5", "mail5", "company2", Employee.Position.Stażysta);
        Employee emp6 = new Employee("name6", "eurname6", "mail1", "company2", Employee.Position.Programista);
        Employee emp7 = new Employee("name7", "purname7", "mail7", "company2", Employee.Position.Manager);
        Employee emp8 = new Employee("name8", "kurname8", "mail8", "company3", Employee.Position.Stażysta);
        Employee emp9 = new Employee("name9", "nurname9", "mail9", "company3", Employee.Position.Wiceprezes);
        system.AddEmployee(emp1);
        system.AddEmployee(emp2);
        system.AddEmployee(emp3);
        system.AddEmployee(emp4);
        system.AddEmployee(emp5);
        system.AddEmployee(emp6);
        system.AddEmployee(emp7);
        system.AddEmployee(emp8);
        system.AddEmployee(emp9);
        System.out.println(system);
//        System.out.println(system.getEmployees());
        system.employees.sort(Employee.bySurname);
        System.out.println(system);
        System.out.println(system.getEmployeePositionMap());
        System.out.println(system.getPositionCountMap());
        EmployeeSystem system2 = new EmployeeSystem();
        System.out.println(system.GetEmployeeWithHighestSalary());
        System.out.println(system2.GetEmployeeWithHighestSalary());



//        system.AddEmployee();
    }

}
