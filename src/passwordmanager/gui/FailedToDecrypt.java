package src.passwordmanager.gui;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

public class FailedToDecrypt extends JFrame {
    public FailedToDecrypt(String msg) {
        this.setSize(500, 170);
        this.setTitle(msg);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setResizable(false);

        JLabel errorLabel = new JLabel(msg);
        errorLabel.setBounds(10, 20, 500, 50);
        errorLabel.setForeground(new ColorUIResource(255, 0, 0));
        errorLabel.setFont(new FontUIResource(FontUIResource.SANS_SERIF, FontUIResource.PLAIN, 23));

        JButton button = new JButton("OK");
        button.setBounds(100, 100, 300, 30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                dispose();
            }
        });
        this.add(errorLabel);
        this.add(button);
        this.setVisible(true);
    }
}
