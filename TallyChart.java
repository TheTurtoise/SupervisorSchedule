import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TallyChart {

    private JFrame frame;

    public TallyChart() {
        frame = new JFrame("Staff Chart");

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Staff Name");
        model.addColumn("Number of Shifts");
        model.addColumn("Availability");

        for (int i = 1; i <= 120; i++) {
            model.addRow(new Object[]{" ", i, "Available/Unavailable"});
        }

        JTable staffChartTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(staffChartTable);
        staffChartTable.setRowHeight(20);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
}
