import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarGUI extends JFrame {
    private JLabel monthLabel;
    private JButton prevMonthButton, nextMonthButton;
    private JTable calendarTable;
    private DefaultTableModel calendarTableModel;
    private JScrollPane calendarScrollPane;
    private Calendar calendar;

    public CalendarGUI() {
        setTitle("Java Calendar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        calendar = Calendar.getInstance();

        Actions();
        update();

        setVisible(true);
    }

    private void Actions() {
        setLayout(new GridBagLayout());

        monthLabel = new JLabel("", JLabel.LEFT);
        monthLabel.setFont(new Font(null,Font.BOLD, 30));
        prevMonthButton = new JButton("<<");
        prevMonthButton.setFocusable(false);
        nextMonthButton = new JButton(">>");
        nextMonthButton.setFocusable(false);


        prevMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendar.add(Calendar.MONTH, -1);
                update();
            }
        });

        nextMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendar.add(Calendar.MONTH, 1);
                update();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.ipadx = 20;
        gbc.ipady = 20;
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(monthLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        add(prevMonthButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        add(nextMonthButton, gbc);

        calendarTableModel = createNonEditableTableModel();
        calendarTable = new JTable(calendarTableModel);
        calendarTable.setAutoCreateRowSorter(true);
        calendarScrollPane = new JScrollPane(calendarTable);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        add(calendarScrollPane, gbc);
    }

    private DefaultTableModel createNonEditableTableModel() {
        return new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
    }

    private void update() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
        monthLabel.setText(sdf.format(calendar.getTime()));

        //Make a list of headers
        String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        calendarTableModel.setColumnIdentifiers(headers);
        calendarTableModel.setRowCount(0); //thjis clears the rows of any data
        calendarTable.setRowHeight(50);

        //get the current year and month from the CAlendar class
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);


        //get information about current month and week
        Calendar cal = Calendar.getInstance(); //this thing represents the current data dn time i think?!?!?!
        cal.set(year, month, 1);

        int max = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int start = cal.get(Calendar.DAY_OF_WEEK);

        int row = 0;
        int col = start - 1;

        calendarTableModel.setRowCount(6);  //this jst sets the nymber of rows

        for (int day = 1; day <= max; day++) {
            calendarTableModel.setValueAt(day, row, col);
            col++;

            if (col == 7) { //if u reach end of the week
                col = 0;
                row++;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalendarGUI());
    }
}
