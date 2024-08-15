import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        // Sample dataset of employees
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 28, "Engineering", 70000),
            new Employee("Bob", 35, "Marketing", 65000),
            new Employee("Charlie", 40, "Engineering", 85000),
            new Employee("Diana", 32, "Sales", 56000),
            new Employee("Edward", 29, "Marketing", 48000)
        );

        // Function to concatenate employee name and department
        Function<Employee, String> nameAndDeptFunction = 
            employee -> employee.getName() + " (" + employee.getDepartment() + ")";

        // Generate a new collection containing concatenated strings
        List<String> nameAndDeptList = employees.stream()
            .map(nameAndDeptFunction)
            .collect(Collectors.toList());

        // Print the concatenated names and departments
        System.out.println("Employees (Name and Department):");
        nameAndDeptList.forEach(System.out::println);

        // Calculate the average salary of all employees
        double averageSalary = employees.stream()
            .mapToDouble(Employee::getSalary)
            .average()
            .orElse(0.0);

        System.out.println("\nAverage Salary of Employees: $" + averageSalary);

        // Filter employees based on age threshold (e.g., age > 30)
        int ageThreshold = 30;
        List<Employee> filteredEmployees = employees.stream()
            .filter(employee -> employee.getAge() > ageThreshold)
            .collect(Collectors.toList());

        System.out.println("\nEmployees with Age > " + ageThreshold + ":");
        filteredEmployees.forEach(emp -> 
            System.out.println(emp.getName() + " - Age: " + emp.getAge())
        );
    }
}