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

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

import src.passwordmanager.utils.Utils;
import src.passwordmanager.crypt.Decrypt;

public class SecretKey extends JFrame {
    private static Utils utils = new Utils();
    private static Decrypt decrypt = new Decrypt();

    public SecretKey(String field) {
        this.setSize(500, 170);
        this.setTitle("Enter secret key");
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

        JButton button = new JButton("Decrypt");
        button.setBounds(350, 100, 300, 30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String secretKey = new String(secretKeyText.getPassword());
                try {
                    String hash = utils.readHash(field);
                    String password = decrypt.decrypt(hash, secretKey);
                    System.out.println(password); // testing

                    StringSelection stringSelection = new StringSelection(password);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(button, "An error occured. Could not decrypt password");
                } finally {
                    dispose();
                }
            }
        });

        panel.add(button);
        this.add(panel);
        this.setVisible(true);
    }
}
