package src.passwordmanager.gui;

import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.JPanel;

import src.passwordmanager.crypt.Encrypt;
import src.passwordmanager.utils.Utils;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;

public class GUI extends JFrame {
    Utils utils = new Utils();
    Encrypt encrypt = new Encrypt();
    AddNewEntry addNewEntry = new AddNewEntry();
    ListAll listAll = new ListAll();
    CheckDataBreaches checkDataBreaches = new CheckDataBreaches();
    private static String padding = "     ";

    public GUI() {
        try {
            UIManager.setLookAndFeel(new FlatOneDarkIJTheme());
            SwingUtilities.updateComponentTreeUI(this);
            this.pack();
        } catch (Exception err) {
            System.out.println(err);
        }

        ImageIcon logo = new ImageIcon(Paths.get("src", "passwordmanager", "assets", "logo.png").toString());

        this.setIconImage(logo.getImage());
        this.setTitle("Password Manager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p1 = addNewEntry.getAddNewEntry();
        JPanel p2 = listAll.getList();
        JPanel p3 = checkDataBreaches.getCheckBreachData();

        JTabbedPane tabs = new JTabbedPane();
        tabs.setBounds(0, 0, 1000, 1000);
        tabs.add(padding + "Add new" + padding, p1);
        tabs.add(padding + "List" + padding, p2);
        tabs.add(padding + "Data Breaches" + padding, p3);

        this.add(tabs);
        this.setVisible(true);
    }
}
