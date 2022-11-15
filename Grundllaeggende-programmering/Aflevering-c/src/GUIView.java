package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIView {
    
    private MockDB db;
    private CustomerTracker cTracker;
    private JFrame frame;
    private JTextField textField = new JTextField();
    private JTextField inputField = new JTextField("Indsæt Tekst her");

    public GUIView(MockDB db, CustomerTracker cTracker) {
        this.db = db;
        this.cTracker = cTracker;
        makeFrame();
    }

    private void makeFrame()
    {
        frame = new JFrame("Customer Tracker");

        frame.add(textField);
        frame.add(inputField);

        Container contentPane = frame.getContentPane();

        contentPane.setLayout(new GridLayout(2, 2));

        JPanel secondContainer = new JPanel();

        contentPane.add(secondContainer);

        secondContainer.setLayout(new GridLayout(1, 1));
        secondContainer.add(makeButtonPanel());

        // building is done - arrange the components and show        
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel makeButtonPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));

        JButton today = new JButton("TODAY");
        today.addActionListener(e -> {
            textField.setText("Kunder i dag: " + cTracker.today());
        });
        panel.add(today);

        JButton avgThisWeek = new JButton("AVG. THIS WEEK");
        avgThisWeek.addActionListener(e -> {
            textField.setText("Gennemsnitligt antal kunder denne uge: " + cTracker.avgThisWeek());
        });
        panel.add(avgThisWeek);

        JButton compareToWeek = new JButton("COMPARE TO CURRENT");
        compareToWeek.addActionListener(e -> {
            textField.setText("" + cTracker.comparedToWeek(Integer.parseInt(inputField.getText())));
        });
        panel.add(compareToWeek);


        return panel;
    }
}
