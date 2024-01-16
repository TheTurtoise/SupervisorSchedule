

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CalendarGUI {
    private JLabel monthLabel;
    private JButton prevMonthButton, nextMonthButton;
    private JTable calendarTable;
    private DefaultTableModel calendarTableModel;
    private java.util.Calendar myCalendar;
    private JScrollPane calendarScrollPane;
    private JFrame f = new JFrame("Calendar!");
    private JPanel mainPanel = new JPanel();
    private JPanel labelPanel = new JPanel();
    int year;
    int month;
    CalendarGUI() {
        UIManager.put("Panel.background", Color.WHITE);
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(Color.WHITE);
        f.setLocationRelativeTo(null);


        myCalendar = Calendar.getInstance();
        Actions();
        update();


        // Create a panel to hold the table
        JPanel tablePanel = new JPanel(new GridLayout(2, 1));
        tablePanel.add(calendarTable);


        // Add some padding around the tablePanel
        tablePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Adjust the values as needed


        // Add the tablePanel to the center of the frame
        f.add(tablePanel, BorderLayout.CENTER);


        // Add the buttonPanel to the south (bottom) of the frame
        f.add(mainPanel, BorderLayout.NORTH);


        f.setVisible(true);
    }


    public void Actions() {
        //Button text
        monthLabel = new JLabel("", JLabel.CENTER);
        prevMonthButton = new JButton("<");
        prevMonthButton.setFocusable(false);
        nextMonthButton = new JButton(">");
        nextMonthButton.setFocusable(false);

        // make buttons change month
        prevMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myCalendar.add(Calendar.MONTH, -1);
                update();
            }
        });


        nextMonthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myCalendar.add(Calendar.MONTH, 1);
                update();
            }
        });


        //add stuff to panel
        mainPanel.setBackground(Color.red);
        mainPanel.setPreferredSize(new Dimension(500, 40));
        mainPanel.add(nextMonthButton);
        mainPanel.add(prevMonthButton);
        mainPanel.add(monthLabel);


        calendarTableModel = createNonEditableTableModel();


        calendarTable = new JTable(calendarTableModel);
        calendarTable.setShowGrid(true);
        calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // I may wwant to change this from selectring one cell to selecting the whole row
        calendarTable.getTableHeader().setReorderingAllowed(false);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.ipadx = 20;
        gbc.ipady = 20;
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(monthLabel, gbc);


        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 10;
        gbc.weightx = 0;
        mainPanel.add(prevMonthButton, gbc);


        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        mainPanel.add(nextMonthButton, gbc);
    }


    //THIS IS A METHOD TO OVERRIDE A BUILT IN METHOD OF A DEFAULT TABLE MODEL
    public DefaultTableModel createNonEditableTableModel() {
        return new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
    }


    public void update() {
        SimpleDateFormat monthYear = new SimpleDateFormat("MMMM yyyy");
        monthLabel.setText(monthYear.format(myCalendar.getTime()));


        String headings[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        calendarTableModel.setColumnIdentifiers(headings);
        calendarTable.setRowHeight(30);

        year = myCalendar.get(Calendar.YEAR);
        month = myCalendar.get(Calendar.MONTH);
        //get information about current month and week
        Calendar cal = Calendar.getInstance(); //this thing represents the current data dn time i think?!?!?!
        cal.set(year, month, 1);


        int max = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int start = cal.get(Calendar.DAY_OF_WEEK);


        int row = 1;
        int col = start - 1;

        calendarTableModel.setRowCount(0);  //This clears the pre-existing values on the table to prevent them from bleeding onto new months
        calendarTableModel.setRowCount(7);  //this just sets the number of rows

        int dayNumber = 0;
        for (String day : headings) {
            calendarTableModel.setValueAt(day,0,dayNumber);
            dayNumber++;
        }

        for (int day = 1; day <= max; day++) {
            calendarTableModel.setValueAt(day, row, col);
            col++;


            if (col == 7) { //if u reach end of the week
                col = 0;
                row++;
            }
        }
    }


}
