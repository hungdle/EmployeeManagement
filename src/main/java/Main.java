public class Main {
    public static void main(String[] args) {
        EmployeeManagementSystem employeeManagementSystem = new EmployeeManagementSystem();
        EmployeeManagementGUI gui = new EmployeeManagementGUI(employeeManagementSystem);
        gui.setVisible(true);
    }
}
