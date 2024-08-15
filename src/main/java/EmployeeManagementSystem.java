import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmployeeManagementSystem {
    // Sample dataset of employees
    private List<Employee> employees;

    public EmployeeManagementSystem() {
        employees = Arrays.asList(
                new Employee("Alice", 28, "Engineering", 70000),
                new Employee("Bob", 35, "Marketing", 65000),
                new Employee("Charlie", 40, "Engineering", 85000),
                new Employee("Diana", 32, "Sales", 56000),
                new Employee("Edward", 29, "Marketing", 48000)
        );
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<String> concatenateNameAndDepartment() {
        Function<Employee, String> nameAndDeptFunction =
                employee -> employee.getName() + " (" + employee.getDepartment() + ")";

        return employees.stream()
                .map(nameAndDeptFunction)
                .collect(Collectors.toList());
    }

    public double calculateAverageSalary() {
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    public List<Employee> filterEmployeesByAge(int ageThreshold) {
        return employees.stream()
                .filter(employee -> employee.getAge() > ageThreshold)
                .collect(Collectors.toList());
    }
}
