package model;

import java.util.ArrayList;
import java.util.List;

public class ImportSummary {
    private List<Exception> exceptions;
    private int numberOfAddedEmployees;
    public ImportSummary() {
        this.exceptions = new ArrayList<Exception>();
        this.numberOfAddedEmployees = 0;
    }
    public List<Exception> getExceptions() {
        return exceptions;
    }
    public int getNumberOfAddedEmployees() {
        return numberOfAddedEmployees;
    }
    public void addException(Exception exception) {
        exceptions.add(exception);
    }
    public void employeeAdded() {
        numberOfAddedEmployees++;
    }
    public String toString() {
        return "Employees added: " + numberOfAddedEmployees + "\nExceptions: " + exceptions;
    }
}
