package service;

import com.google.gson.*;
import java.io.*;
import java.net.*;
import java.util.*;
import exception.*;
import model.*;


public class ApiService {

    public List<Employee> fetchEmployeesFromApi(String apiUrl) throws ApiException {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                throw new ApiException("HTTP error: " + statusCode);
            }

            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            reader.close();

            List<Employee> employees = new ArrayList<>();

            for (JsonElement element : jsonArray) {
                JsonObject obj = element.getAsJsonObject();

                String fullName = obj.get("name").getAsString();
                String[] names = fullName.split(" ", 2);
                String firstName = names.length > 0 ? names[0] : "";
                String lastName = names.length > 1 ? names[1] : "";

                String email = obj.get("email").getAsString();
                String company = obj.getAsJsonObject("company").get("name").getAsString();

                Employee employee = new Employee(firstName, lastName, email, company, Position.Programista);
                if (!employee.isValid()) {
                    throw new InvalidDataException("Employee not valid");
                }
                employees.add(employee);
            }

            return employees;

        } catch (Exception e) {
            throw new ApiException("Error while downloading or parsing API data");
        }
    }
}

