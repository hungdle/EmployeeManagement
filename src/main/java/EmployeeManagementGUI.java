import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmployeeManagementGUI extends JFrame {
    private JTable employeeTable;
    private DefaultTableModel fullTableModel, concatenatedTableModel;
    private JButton loadButton, concatenateButton, averageSalaryButton, filterButton;
    private EmployeeManagementSystem employeeManagementSystem;
    private JLabel welcomeLabel;
    private JPanel centerPanel;

    public EmployeeManagementGUI(EmployeeManagementSystem employeeManagementSystem) {
        this.employeeManagementSystem = employeeManagementSystem;

        setTitle("Employee Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize the full table model with column names
        fullTableModel = new DefaultTableModel();
        fullTableModel.setColumnIdentifiers(new String[]{"Name", "Age", "Department", "Salary"});

        // Initialize the concatenated table model with column names
        concatenatedTableModel = new DefaultTableModel();
        concatenatedTableModel.setColumnIdentifiers(new String[]{"Name & Department", "Age", "Salary"});

        // Create the JTable with the full table model initially
        employeeTable = new JTable(fullTableModel);

        // Create components
        loadButton = new JButton("Load Employees");
        concatenateButton = new JButton("Concatenate Name & Department");
        averageSalaryButton = new JButton("Calculate Average Salary");
        filterButton = new JButton("Filter by Age");

        // Set up layout
        setLayout(new BorderLayout());

        // Create an action panel for buttons with GridLayout
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(4, 1, 10, 10)); // 4 rows, 1 column, with gaps
        actionPanel.add(loadButton);
        actionPanel.add(concatenateButton);
        actionPanel.add(averageSalaryButton);
        actionPanel.add(filterButton);

        // Create center panel with welcome message
        centerPanel = new JPanel(new BorderLayout());
        welcomeLabel = new JLabel("Welcome to the Employee Management System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        centerPanel.add(welcomeLabel, BorderLayout.CENTER);

        // Add components to the frame
        add(actionPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);

        // Add action listeners
        loadButton.addActionListener(new LoadButtonListener());
        concatenateButton.addActionListener(new ConcatenateButtonListener());
        averageSalaryButton.addActionListener(new AverageSalaryButtonListener());
        filterButton.addActionListener(new FilterButtonListener());
    }

    private class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Load employees and update table with fullTableModel
            List<Employee> employees = employeeManagementSystem.getEmployees();
            updateTable(fullTableModel, employees);
            switchToTableView(fullTableModel);
        }
    }

    private class ConcatenateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Use the concatenateNameAndDepartment method from EmployeeManagementSystem
            List<String> concatenatedList = employeeManagementSystem.concatenateNameAndDepartment();
            List<Employee> employees = employeeManagementSystem.getEmployees();
            concatenatedTableModel.setRowCount(0); // Clear existing data
            for (int i = 0; i < employees.size(); i++) {
                Employee emp = employees.get(i);
                concatenatedTableModel.addRow(new Object[]{
                        concatenatedList.get(i),
                        emp.getAge(),
                        emp.getSalary()
                });
            }
            switchToTableView(concatenatedTableModel);
        }
    }

    private class AverageSalaryButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Calculate average salary using EmployeeManagementSystem
            double averageSalary = employeeManagementSystem.calculateAverageSalary();
            JOptionPane.showMessageDialog(null, "Average Salary: " + averageSalary);
        }
    }

    private class FilterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Show input dialog to enter age threshold and filter employees
            String input = JOptionPane.showInputDialog(null, "Enter Age Threshold:");
            if (input != null && !input.isEmpty()) {
                int ageThreshold = Integer.parseInt(input);
                List<Employee> filteredEmployees = employeeManagementSystem.filterEmployeesByAge(ageThreshold);
                updateTable(fullTableModel, filteredEmployees);
                switchToTableView(fullTableModel);
            }
        }
    }

    // Method to update table data
    private void updateTable(DefaultTableModel tableModel, List<Employee> employees) {
        tableModel.setRowCount(0); // Clear existing data
        for (Employee emp : employees) {
            tableModel.addRow(new Object[]{
                    emp.getName(),
                    emp.getAge(),
                    emp.getDepartment(),
                    emp.getSalary()
            });
        }
    }

    // Method to switch to table view in center panel
    private void switchToTableView(DefaultTableModel tableModel) {
        employeeTable.setModel(tableModel);
        centerPanel.removeAll();
        centerPanel.add(new JScrollPane(employeeTable), BorderLayout.CENTER);
        centerPanel.revalidate();
        centerPanel.repaint();
    }
}
