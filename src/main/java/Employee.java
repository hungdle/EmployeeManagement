/**
 * Represents an employee with a name, age, department, and salary.
 */
public class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;

    /**
     * Constructs an Employee object with the given name, age, department, and salary.
     *
     * @param name       The name of the employee.
     * @param age        The age of the employee.
     * @param department The department the employee belongs to.
     * @param salary     The salary of the employee.
     */
    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    /**
     * Returns the name of the employee.
     *
     * @return The name of the employee.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the age of the employee.
     *
     * @return The age of the employee.
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the department the employee belongs to.
     *
     * @return The department the employee belongs to.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Returns the salary of the employee.
     *
     * @return The salary of the employee.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Returns a string representation of the employee in the format "name (age), department, $salary".
     *
     * @return A string representation of the employee.
     */
    @Override
    public String toString() {
        return name + " (" + age + "), " + department + ", $" + salary;
    }
}