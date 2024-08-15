import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EmployeeManagementGUI().setVisible(true);
        });
    }
}
