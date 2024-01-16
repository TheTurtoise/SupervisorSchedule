import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScheduleGUI {
    private JFrame f = new JFrame("Schedule!");
    private JTable scheudleTable;
    private DefaultTableModel scheduleTableModel;
    private java.util.Calendar mySchedule;
    private JPanel mainPanel = new JPanel();

    private JLabel datePanel = new JLabel("Date");
    public ScheduleGUI() {
        f.setSize(900,550);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel header = new JPanel(new GridLayout(2, 1));

        //heading label thing
        JLabel dateLabel = new JLabel("Date", JLabel.LEFT);
        dateLabel.setSize(850,50);
        header.setBackground(Color.RED);
        header.add(dateLabel);
        f.add(header,BorderLayout.NORTH);

        createTable();
        f.add(mainPanel,BorderLayout.CENTER);

        f.setVisible(true);
    }

    public void createTable() {
        //scheduleTableModel = createNonEditableTableModel();
        mainPanel.setBackground(Color.white);
        mainPanel.setPreferredSize(new Dimension(900, 550));
        scheudleTable = new JTable(scheduleTableModel);
        String headings[] = {"Time", "Duty", "Monday", "Tue", "Wed", "Thu", "Fri"};
        scheduleTableModel.setColumnIdentifiers(headings);
        scheduleTableModel.setRowCount(7);
        scheduleTableModel.setColumnCount(5);
        scheudleTable.setShowGrid(true);
        scheudleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // I may wwant to change this from selectring one cell to selecting the whole row
        scheudleTable.getTableHeader().setReorderingAllowed(false);
        mainPanel.add(scheudleTable);
    }
}
