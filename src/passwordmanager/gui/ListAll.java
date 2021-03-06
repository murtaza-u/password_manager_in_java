package src.passwordmanager.gui;

// import java.nio.file.Paths;
// import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;

import src.passwordmanager.utils.Utils;

public class ListAll {
    private static Utils utils = new Utils();

    private static JButton getButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new DimensionUIResource(100, 20));
        button.setFont(new FontUIResource(FontUIResource.SANS_SERIF, FontUIResource.TRUETYPE_FONT, 15));
        return button;
    }

    private static JLabel getLabel(String fieldName) {
        JLabel label = new JLabel(fieldName);
        label.setFont(new FontUIResource(FontUIResource.SANS_SERIF, FontUIResource.TRUETYPE_FONT, 18));
        return label;
    }

    private static void build(JPanel panel, JButton refreshBtn) {
        panel.removeAll();
        panel.add(refreshBtn);

        String[] fields = utils.listFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].replace(".enc", "");
            SpringLayout layout = new SpringLayout();

            JPanel collection = new JPanel();

            JLabel label = getLabel(fields[i]);

            JButton copyBtn = getButton("copy");
            copyBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JLabel lbl = (JLabel)collection.getComponent(0);
                    new SecretKey(lbl.getText());
                }
            });

            JButton editBtn = getButton("edit");
            editBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JLabel lbl = (JLabel)collection.getComponent(0);
                    new ChangePassword(lbl.getText());
                }
            });

            collection.add(label);
            collection.add(copyBtn);
            collection.add(editBtn);
            collection.setLayout(layout);

            layout.putConstraint(SpringLayout.WEST, label, 15, SpringLayout.WEST, collection);
            layout.putConstraint(SpringLayout.EAST, copyBtn, -150, SpringLayout.EAST, collection);
            layout.putConstraint(SpringLayout.EAST, editBtn, -10, SpringLayout.EAST, collection);

            layout.putConstraint(SpringLayout.NORTH, label, 16, SpringLayout.NORTH, collection);
            layout.putConstraint(SpringLayout.NORTH, copyBtn, 16, SpringLayout.NORTH, collection);
            layout.putConstraint(SpringLayout.NORTH, editBtn, 16, SpringLayout.NORTH, collection);

            collection.setBounds(0, 40 + i * 50, 1000, 50);
            panel.add(collection);
        }
        panel.revalidate();
    }

    public JPanel getList() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 500, 500);
        panel.setLayout(null);

        JButton refreshBtn = getButton("refresh");
        refreshBtn.setBounds(1000-110, 5, 100, 20);
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                build(panel, refreshBtn);
            }
        });

        build(panel, refreshBtn);
        return panel;
    }
}
