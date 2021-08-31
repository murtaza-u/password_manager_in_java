package src.passwordmanager.gui;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.plaf.FontUIResource;

import src.passwordmanager.utils.Utils;

public class ChangePassword extends JFrame {
    private static Utils utils = new Utils();

    ChangePassword(String field) {
        this.setSize(500, 260);
        this.setTitle("Change Password");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel secretKeyLabel = new JLabel("Secret Key");
        secretKeyLabel.setBounds(10, 20, 250, 30);
        panel.add(secretKeyLabel);

        JPasswordField secretKeyText = new JPasswordField(100);
        secretKeyText.setBounds(160, 20, 300, 30);
        secretKeyText.setFont(new FontUIResource(FontUIResource.SANS_SERIF, FontUIResource.PLAIN, 23));
        panel.add(secretKeyText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 80, 250, 30);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(100);
        passwordText.setBounds(160, 80, 300, 30);
        passwordText.setFont(new FontUIResource(FontUIResource.SANS_SERIF, FontUIResource.PLAIN, 23));
        panel.add(passwordText);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setBounds(10, 140, 250, 30);
        panel.add(confirmPasswordLabel);

        JPasswordField confirmPasswordText = new JPasswordField(100);
        confirmPasswordText.setBounds(160, 140, 300, 30);
        confirmPasswordText.setFont(new FontUIResource(FontUIResource.SANS_SERIF, FontUIResource.PLAIN, 23));
        panel.add(confirmPasswordText);

        JButton button = new JButton("OK");
        button.setBounds(210, 210, 80, 30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String password = new String(passwordText.getPassword());
                String confirmPassword = new String(confirmPasswordText.getPassword());
                if (password.equals(confirmPassword)) {
                    String secretKey = new String(secretKeyText.getPassword());
                    String hash = utils.getHash(password, secretKey);
                    try {
                        utils.insert(field, hash);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(button, "An error occured. Could not encrypt password");
                    } finally {
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(button, "Passwords donot match");
                }
            }
        });

        panel.add(button);
        this.add(panel);
        this.setVisible(true);
    }
}
