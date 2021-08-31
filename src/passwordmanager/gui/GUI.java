package src.passwordmanager.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.passwordmanager.crypt.Encrypt;
import src.passwordmanager.utils.Utils;

public class GUI extends JFrame {
    Utils utils = new Utils();
    Encrypt encrypt = new Encrypt();

    public GUI() {
        this.setTitle("Password Manager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel fieldLabel = new JLabel("Field");
        fieldLabel.setBounds(30, 30, 200, 50);

        JTextField fieldText = new JTextField(20);
        fieldText.setBounds(200, 30, 500, 50);
        fieldText.setFont(new FontUIResource(FontUIResource.SANS_SERIF, FontUIResource.PLAIN, 18));

        JLabel secretKeyLabel = new JLabel("Secret Key");
        secretKeyLabel.setBounds(30, 100, 200, 50);

        JPasswordField secretKeyText = new JPasswordField(20);
        secretKeyText.setBounds(200, 100, 500, 50);
        secretKeyText.setFont(new FontUIResource(FontUIResource.SANS_SERIF, FontUIResource.PLAIN, 23));

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(30, 170, 200, 50);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(200, 170, 500, 50);
        passwordText.setFont(new FontUIResource(FontUIResource.SANS_SERIF, FontUIResource.PLAIN, 23));

        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setBounds(30, 240, 200, 50);

        JPasswordField confirmPasswordText = new JPasswordField(20);
        confirmPasswordText.setBounds(200, 240, 500, 50);
        confirmPasswordText.setFont(new FontUIResource(FontUIResource.SANS_SERIF, FontUIResource.PLAIN, 23));

        JLabel feedBack = new JLabel("");
        feedBack.setFont(new FontUIResource(FontUIResource.SANS_SERIF, FontUIResource.BOLD, 22));
        feedBack.setBounds(30, 600, 300, 200);

        // Border borderError = BorderFactory.createLineBorder(new ColorUIResource(220, 53, 69), 5);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(400, 400, 50, 50);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String field = fieldText.getText();
                String password = new String(passwordText.getPassword());
                String confirmPassword = new String(confirmPasswordText.getPassword());
                String secretKey = new String(secretKeyText.getPassword());

                if (field.isBlank() || password.isBlank() || confirmPassword.isBlank() || secretKey.isBlank()) {
                    JOptionPane.showMessageDialog(addBtn, "One or more field has been left blank");
                    return;
                }

                if (password.equals(confirmPassword)) {
                    if(utils.fieldExists(field)) {
                        int dialogResult = JOptionPane.showConfirmDialog(addBtn, "Field exists. Overwrite?", "Field exists", JOptionPane.YES_NO_OPTION);
                        if(dialogResult == JOptionPane.NO_OPTION){
                            return;
                        }
                    }

                    String hash = encrypt.encrypt(password, secretKey);
                    try {
                        utils.insert(field, hash);
                        feedBack.setForeground(new ColorUIResource(25, 135, 84));
                        feedBack.setText("Successfully added");
                        fieldText.setText(null);
                        secretKeyText.setText(null);
                        passwordText.setText(null);
                        confirmPasswordText.setText(null);
                    } catch (Exception e) {
                        feedBack.setForeground(new ColorUIResource(220, 53, 69));
                        feedBack.setText("An error occurred. Could not encrypt password");
                        // fieldText.setBorder(borderError);
                    }
                } else {
                    JOptionPane.showMessageDialog(addBtn, "Passwords donot match");
                }
            }
        });

        JPanel p1 = new JPanel();
        p1.setBounds(0, 0, 500, 500);
        p1.setLayout(null);
        p1.add(fieldLabel);
        p1.add(fieldText);
        p1.add(secretKeyLabel);
        p1.add(secretKeyText);
        p1.add(passwordLabel);
        p1.add(passwordText);
        p1.add(confirmPasswordLabel);
        p1.add(confirmPasswordText);
        p1.add(addBtn);
        p1.add(feedBack);

        JPanel p2 = new JPanel();

        JPanel p3 = new JPanel();

        String padding = "     ";

        JTabbedPane tabs = new JTabbedPane();
        tabs.setBounds(0, 0, 1000, 1000);
        tabs.add(padding + "Add new" + padding, p1);
        tabs.add(padding + "List" + padding, p2);
        tabs.add(padding + "HaveIBeenPwned" + padding, p3);

        this.add(tabs);
        this.setVisible(true);
    }
}
