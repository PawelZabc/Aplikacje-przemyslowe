package service;

import exception.InvalidDataException;
import model.Employee;
import model.ImportSummary;
import model.Position;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImportService {
    private service.EmployeeService employeeService;

    public ImportService(service.EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public ImportService() {}

    public service.EmployeeService getEmployeeService() {
        return employeeService;
    }
    public void setEmployeeService(service.EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public ImportSummary importFromCsv(String path){
        if(path == null || path.isEmpty()||employeeService == null){
            return null;
        }
        ImportSummary importSummary = new ImportSummary();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine();

            int count = 1;
            while ((line = br.readLine()) != null) {
                count++;
                try{
                String[] fields = line.split(",");
                if (fields.length == 6) {
                    Employee employee = new Employee();
                    employee.setName(fields[0].trim());
                    employee.setSurname(fields[1].trim());
                    employee.setEmail(fields[2].trim());
                    employee.setCompanyName(fields[3].trim());
                    try {
                        Position position = Position.valueOf(fields[4].trim());
                    } catch (IllegalArgumentException e) {
                        throw new InvalidDataException("Line "+count+":"+e.getMessage());
                    }

                    employee.setPosition(Position.valueOf(fields[4].trim()));
                    employee.setSalary(Double.parseDouble(fields[5].trim()));
                    if (employeeService.addEmployee(employee)) {
                        importSummary.employeeAdded();
                    }
                }else{
                    throw new InvalidDataException("Line "+count+":Wrong number of fields");
                }
                }catch (Exception e){
                    importSummary.addException(e);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //import
        return importSummary;
    }
}
