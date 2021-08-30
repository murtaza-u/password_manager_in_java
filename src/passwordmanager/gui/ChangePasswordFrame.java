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

import src.passwordmanager.crypt.Encrypt;
import src.passwordmanager.utils.Utils;

class ChangePassword {
    Utils utils = new Utils();
    Encrypt encrypt = new Encrypt();

    ChangePassword(String field, String newPassword, String secretKey) {
        String hash = encrypt.encrypt(newPassword, secretKey);
        try {
            utils.insert(field, hash);
        } catch (Exception e) {
            System.out.println("A error occured: " + e);
        }
    }
}

public class ChangePasswordFrame extends JFrame {
    ChangePasswordFrame(String field) {
        this.setSize(500, 260);
        this.setTitle("Change Password");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel masterPasswordLabel = new JLabel("Master Password");
        masterPasswordLabel.setBounds(10, 20, 250, 30);
        panel.add(masterPasswordLabel);

        JPasswordField masterPasswordText = new JPasswordField(100);
        masterPasswordText.setBounds(160, 20, 300, 30);
        masterPasswordText.setFont(new FontUIResource(FontUIResource.SANS_SERIF, FontUIResource.PLAIN, 23));
        panel.add(masterPasswordText);

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
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordText.getPassword());
                String confirmPassword = new String(confirmPasswordText.getPassword());
                if (password.equals(confirmPassword)) {
                    String inputKey = new String(masterPasswordText.getPassword());
                    new ChangePassword(field, password, inputKey);
                    dispose();
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
