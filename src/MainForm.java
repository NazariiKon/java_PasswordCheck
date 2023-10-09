import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    private JPasswordField passwordField;
    private JLabel resultLabel;

    public MainForm() {
        setTitle("Password Check");
        setSize(400, 260);
        // close the program by clicking on the cross button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // label
        JLabel enterPasswordLabel = new JLabel("Enter your password:");
        enterPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        createResultLabel();
        
        // text box for password
        passwordField = new JPasswordField(20);
        passwordField.setEchoChar('*'); // to hide password
        Font textFieldFont = new Font("Arial", Font.PLAIN, 17);
        passwordField.setFont(textFieldFont);

        // button to hide/show password
        JButton showHideButton = new JButton("Show");
        showHideButton.setContentAreaFilled(false);

        // button click handler
        showHideButton.addActionListener(new ActionListener() {
            private boolean passwordVisible = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                passwordVisible = !passwordVisible;
                if (passwordVisible) {
                    passwordField.setEchoChar((char) 0); // show password
                    showHideButton.setText("Hide");
                } else {
                    passwordField.setEchoChar('*'); // hide password
                    showHideButton.setText("Show");
                }
            }
        });

        JButton checkButton = submitButton();

        // Panel
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Выравнивание по центру с отступами
        add(enterPasswordLabel);
        add(passwordField);
        add(showHideButton);
        add(checkButton);
        add(resultLabel);
    }

    private JButton submitButton() {
        JButton checkButton = new JButton("Check");
        checkButton.setFont(new Font("Arial", Font.BOLD, 16));
        checkButton.setPreferredSize(new Dimension(200, 40));

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                PasswordChecker.PasswordStrength strength = PasswordChecker.checkPassword(password);

                switch (strength) {
                    case STRONG:
                        resultLabel.setText("<html>Good password!<br>Your password is tamper-resistant<html>");
                        resultLabel.setForeground(Color.GREEN);
                        break;
                    case WEAK:
                        resultLabel.setText("<html>It's time to change the password.<br>Your password is easily hacked<html>");
                        resultLabel.setForeground(Color.ORANGE);
                        break;
                    case TOO_SHORT:
                        resultLabel.setText("<html>It's time to change the password!<br>Password too short<html>");
                        resultLabel.setForeground(Color.RED);
                        break;
                    case TOO_LONG:
                        resultLabel.setText("<html>Your password is too long<html>");
                        resultLabel.setForeground(Color.BLUE);
                        break;
                }
            }
        });
        return checkButton;
    }

    private void createResultLabel() {
        resultLabel = new JLabel("");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        resultLabel.setPreferredSize(new Dimension(380, 380));
        resultLabel.setMaximumSize(new Dimension(380, 380));
        resultLabel.setOpaque(true);
        resultLabel.setVerticalAlignment(SwingConstants.TOP);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    }
}
