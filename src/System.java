import java.util.*;

public class System {
    private List<Employee> employees;
    public System() {
        employees = new ArrayList<Employee>();
    }

    public void AddEmployee(Employee employee){
        if (isEmailUnique(employee.getEmail())){
            employees.add(employee);
        }
    }

    public boolean isEmailUnique(String email) {
        for (Employee employee : employees) {
            if (employee.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System system = new System();
//        Employee emp1 = Employee()
//        system.AddEmployee();
    }

}
