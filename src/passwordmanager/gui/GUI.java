package src.passwordmanager.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
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

        JLabel masterPasswordLabel = new JLabel("Master Password");
        masterPasswordLabel.setBounds(30, 100, 200, 50);

        JPasswordField masterPasswordText = new JPasswordField(20);
        masterPasswordText.setBounds(200, 100, 500, 50);
        masterPasswordText.setFont(new FontUIResource(FontUIResource.SANS_SERIF, FontUIResource.PLAIN, 23));

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

        JButton addBtn = new JButton("OK");
        addBtn.setBounds(400, 400, 50, 50);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String password = new String(passwordText.getPassword());
                String confirmPassword = new String(confirmPasswordText.getPassword());
                if (password.equals(confirmPassword)) {
                    String field = fieldText.getText();
                    String inputKey = new String(masterPasswordText.getPassword());
                    String hash = encrypt.encrypt(password, inputKey);
                    try {
                        utils.insert(field, hash);
                    } catch (Exception e) {
                        System.out.println("An error occured: " + e);
                    } finally {
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(addBtn, "Passwords donot match");
                }
            }
        });

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.add(fieldLabel);
        p1.add(fieldText);
        p1.add(masterPasswordLabel);
        p1.add(masterPasswordText);
        p1.add(passwordLabel);
        p1.add(passwordText);
        p1.add(confirmPasswordLabel);
        p1.add(confirmPasswordText);
        p1.add(addBtn);

        JPanel p2 = new JPanel();

        JPanel p3 = new JPanel();

        JTabbedPane tabs = new JTabbedPane();
        tabs.setBounds(0, 0, 1000, 1000);
        tabs.add("Add new", p1);
        tabs.add("List", p2);
        tabs.add("HaveIBeenPwned", p3);

        this.add(tabs);
        this.setVisible(true);
    }
}
