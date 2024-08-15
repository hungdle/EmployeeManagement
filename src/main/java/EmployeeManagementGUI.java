import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmployeeManagementGUI extends JFrame {
    private JTextArea displayArea;
    private JButton loadButton, concatenateButton, averageSalaryButton, filterButton;
    private JTextField ageThresholdField;

    public EmployeeManagementGUI() {
        setTitle("Employee Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        loadButton = new JButton("Load Employees");
        concatenateButton = new JButton("Concatenate Name & Department");
        averageSalaryButton = new JButton("Calculate Average Salary");
        filterButton = new JButton("Filter by Age (> 30)");
        ageThresholdField = new JTextField(10);

        // Set up layout
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loadButton);
        buttonPanel.add(concatenateButton);
        buttonPanel.add(averageSalaryButton);
        buttonPanel.add(new JLabel("Age Threshold:"));
        buttonPanel.add(ageThresholdField);
        buttonPanel.add(filterButton);

        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        loadButton.addActionListener(new LoadButtonListener());
        concatenateButton.addActionListener(new ConcatenateButtonListener());
        averageSalaryButton.addActionListener(new AverageSalaryButtonListener());
        filterButton.addActionListener(new FilterButtonListener());
    }

    private class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Load the dataset and display in the text area
            List<Employee> employees = loadEmployees();
            displayArea.setText("Employees loaded.\n" + employees.toString());
        }
    }

    private class ConcatenateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Employee> employees = loadEmployees();
            Function<Employee, String> concatFunction = emp -> emp.getName() + " - " + emp.getDepartment();
            List<String> concatenatedList = employees.stream()
                    .map(concatFunction)
                    .collect(Collectors.toList());
            displayArea.setText("Concatenated Names & Departments:\n" + concatenatedList.toString());
        }
    }

    private class AverageSalaryButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Employee> employees = loadEmployees();
            double averageSalary = employees.stream()
                    .mapToDouble(Employee::getSalary)
                    .average()
                    .orElse(0.0);
            displayArea.setText("Average Salary: " + averageSalary);
        }
    }

    private class FilterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int ageThreshold = Integer.parseInt(ageThresholdField.getText());
            List<Employee> employees = loadEmployees();
            List<Employee> filteredEmployees = employees.stream()
                    .filter(emp -> emp.getAge() > ageThreshold)
                    .collect(Collectors.toList());
            displayArea.setText("Filtered Employees (Age > " + ageThreshold + "):\n" + filteredEmployees.toString());
        }
    }

    // Mock method to load employees (replace with actual data loading)
    private List<Employee> loadEmployees() {
        return List.of(
                new Employee("Alice", 30, "Engineering", 70000),
                new Employee("Bob", 35, "Marketing", 60000),
                new Employee("Charlie", 40, "HR", 50000)
        );
    }
}