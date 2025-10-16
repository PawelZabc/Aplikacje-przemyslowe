
import model.Employee;
import model.ImportSummary;
import service.ApiService;
import service.EmployeeService;
import service.ImportService;
import com.google.gson.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ApiService apiService = new ApiService();
        try {
            List<Employee> employees = apiService.fetchEmployeesFromApi("https://jsonplaceholder.typicode.com/users");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }catch(Exception e){};
        EmployeeService employeeService = new EmployeeService();
        ImportService importService = new ImportService(employeeService);
        ImportSummary importSummary = importService.importFromCsv("employees.csv");
        System.out.println(importSummary);
        System.out.println(employeeService);
        System.out.println(employeeService.getCompanyStatistics());

    }
}
