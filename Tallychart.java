// TallyChart.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TallyChart extends JFrame {
    public TallyChart(String[] teacherNames) {
        setTitle("Staff Chart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // these are the columns

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Staff Name");
        model.addColumn("Number of Shifts");
        model.addColumn("Availability");

        //amount of teachers, can add more or less here
        
        for (String teacherName : teacherNames) {
            for (int i = 1; i <= 120; i++) {
                model.addRow(new Object[]{" ", i, "Available/Unavailable"});
            }
        }

        JTable staffChartTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(staffChartTable);

        add(scrollPane, BorderLayout.CENTER);
        setSize(600, 400);
        setVisible(true);
    }
}

