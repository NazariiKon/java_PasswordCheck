import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // display the main form
        SwingUtilities.invokeLater(() -> {
            MainForm app = new MainForm();
            app.setVisible(true);
        });
    }
}