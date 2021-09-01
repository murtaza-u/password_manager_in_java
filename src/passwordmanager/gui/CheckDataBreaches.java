package src.passwordmanager.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import java.util.Map;
import java.util.HashMap;

import src.passwordmanager.utils.DataBreaches;

class PopulateLabels extends Thread {
    private static JLabel[] dataLabels = new JLabel[5];
    private static String breachName;
    private static JButton searchBtn;

    private static HashMap<String, String> getBreachData(String searchTerm) {
        DataBreaches dataBreaches = new DataBreaches();
        HashMap<String, String> breachData = dataBreaches.getData(searchTerm);
        return breachData;
    }

    @Override
    public void run() {
        HashMap<String, String> breachData = getBreachData(breachName);
        if(breachData != null) {
            int i = 0;
            for (Map.Entry<String, String> row : breachData.entrySet()) {
                dataLabels[i].setText(row.getKey() + ":     " + row.getValue());
                i ++;
            }
        } else {
            JOptionPane.showMessageDialog(searchBtn, "Try using a different search term or check your internet connection and try again");
            for (JLabel dataLabel : dataLabels) {
                dataLabel.setText("");
            }
        }
    }

    PopulateLabels(String name, JLabel label[], JButton btn) {
        breachName = name;
        dataLabels = label;
        searchBtn = btn;
    }
}

public class CheckDataBreaches {
    public JPanel getCheckBreachData() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1000, 1000);
        panel.setLayout(null);

        JLabel searchLabel = new JLabel("Search Term");
        searchLabel.setFont(new FontUIResource(FontUIResource.SANS_SERIF, FontUIResource.PLAIN, 18));
        searchLabel.setBounds(50, 10, 130, 50);

        JTextField searchText = new JTextField(20);
        searchText.setBounds(240, 10, 500, 50);
        searchText.setFont(new FontUIResource(FontUIResource.SANS_SERIF, FontUIResource.PLAIN, 18));

        JLabel[] dataLabels = new JLabel[5];
        int x = 150;
        int y = 200;
        int width = 500;
        int height = 50;
        for(int i = 0; i < 5; i ++) {
            dataLabels[i] = new JLabel("");
            dataLabels[i].setBounds(x, y + i * height + 10, width, height);
            dataLabels[i].setFont(new FontUIResource(FontUIResource.SANS_SERIF, FontUIResource.PLAIN, 22));
            panel.add(dataLabels[i]);
        }


        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(425, 110, 150, 50);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String breachName = searchText.getText();
                PopulateLabels populateLabels = new PopulateLabels(breachName, dataLabels, searchBtn);
                populateLabels.start();
            }
        });

        panel.add(searchLabel);
        panel.add(searchText);
        panel.add(searchBtn);

        return panel;
    }
}
