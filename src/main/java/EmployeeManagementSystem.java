import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A class that manages a collection of {@link Employee} objects, providing methods to
 * retrieve, manipulate, and analyze employee data.
 */
public class EmployeeManagementSystem {
    // Sample dataset of employees
    private List<Employee> employees;

    /**
     * Constructs an EmployeeManagementSystem object with a sample dataset of employees.
     */
    public EmployeeManagementSystem() {
        employees = Arrays.asList(
                new Employee("Alice", 28, "Engineering", 70000),
                new Employee("Bob", 35, "Marketing", 65000),
                new Employee("Charlie", 40, "Engineering", 85000),
                new Employee("Diana", 32, "Sales", 56000),
                new Employee("Edward", 29, "Marketing", 48000)
        );
    }

    /**
     * Returns a list of all employees.
     *
     * @return A list of all employees.
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * Concatenates the name and department of each employee into a single string.
     *
     * @return A list of strings where each string represents the concatenated name and department of an employee in the format "Name (Department)".
     */
    public List<String> concatenateNameAndDepartment() {
        Function<Employee, String> nameAndDeptFunction =
                employee -> employee.getName() + " (" + employee.getDepartment() + ")";

        return employees.stream()
                .map(nameAndDeptFunction)
                .collect(Collectors.toList());
    }

    /**
     * Calculates the average salary of all employees.
     *
     * @return The average salary of all employees. If there are no employees, returns 0.0.
     */
    public double calculateAverageSalary() {
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    /**
     * Filters the employees based on a given age threshold.
     *
     * @param ageThreshold The age threshold.
     * @return A list of employees whose age is greater than the given age threshold.
     */
    public List<Employee> filterEmployeesByAge(int ageThreshold) {
        return employees.stream()
                .filter(employee -> employee.getAge() > ageThreshold)
                .collect(Collectors.toList());
    }
}