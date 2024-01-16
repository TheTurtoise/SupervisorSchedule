import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScheduleGUI {
    private JFrame f = new JFrame("Schedule!");
    private JTable scheduleTable;
    private DefaultTableModel scheduleTableModel;
    private java.util.Calendar mySchedule;
    private JPanel mainPanel = new JPanel();

    private JLabel datePanel = new JLabel("Date");

    public ScheduleGUI() {
        f.setSize(900,550);

        JPanel header = new JPanel(new GridLayout(2, 1));

        //heading label thing
        JLabel dateLabel = new JLabel("Date", JLabel.LEFT);
        dateLabel.setSize(850,50);
        header.setSize(900,100);
        header.setBackground(Color.RED);
        header.add(dateLabel);
        f.add(header,BorderLayout.NORTH);

        createTable();
        f.add(mainPanel);
        f.setLocationRelativeTo(null);

        f.pack();
        f.setVisible(true);
    }

    public void createTable() {
        //scheduleTableModel = createNonEditableTableModel();
        mainPanel.setBackground(Color.blue);
        mainPanel.setPreferredSize(new Dimension(850, 550));
        scheduleTableModel = new DefaultTableModel();
        scheduleTable = new JTable(scheduleTableModel);
        String headings[] = {"Time", "Duty", "Monday", "Tue", "Wed", "Thu", "Fri"};
        scheduleTableModel.setColumnIdentifiers(headings);
        scheduleTable.getRow
        //scheduleTableModel.setRowCount(5);
        scheduleTableModel.setColumnCount(7);
        scheduleTable.setRowHeight(110);
        scheduleTable.getColumnModel().getColumn(0).setPreferredWidth(130);
        scheduleTable.getColumnModel().getColumn(1).setPreferredWidth(130);
        scheduleTable.getColumnModel().getColumn(2).setPreferredWidth(130);
        scheduleTable.getColumnModel().getColumn(3).setPreferredWidth(130);
        scheduleTable.getColumnModel().getColumn(4).setPreferredWidth(130);
        scheduleTable.getColumnModel().getColumn(5).setPreferredWidth(130);
        scheduleTable.getColumnModel().getColumn(6).setPreferredWidth(130);

        scheduleTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        scheduleTable.setShowGrid(true);
        scheduleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // I may wwant to change this from selectring one cell to selecting the whole row
        scheduleTable.getTableHeader().setReorderingAllowed(false);

        mainPanel.add(scheduleTable);
    }
}
