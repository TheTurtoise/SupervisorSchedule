import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CalendarGUI extends JFrame {
    private JLabel monthLabel;
    private JButton prevMonthButton, nextMonthButton, openChartButton;
    private JTable calendarTable;
    private DefaultTableModel calendarTableModel;
    private JScrollPane calendarScrollPane;
    private Calendar calendar;

    private Map<String,DaySchedule> daySchedule;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public CalendarGUI(Map<String,DaySchedule> daySchedule) {
        setTitle("Java Calendar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        calendar = Calendar.getInstance();
        this.daySchedule = daySchedule;

        Actions();
        update();

        setVisible(true);
    }

    private void Actions() {
        setLayout(new GridBagLayout());

        monthLabel = new JLabel("", JLabel.LEFT);
        monthLabel.setFont(new Font(null, Font.BOLD, 30));
        prevMonthButton = new JButton("<");
        prevMonthButton.setFocusable(false);
        nextMonthButton = new JButton(">");
        nextMonthButton.setFocusable(false);
        openChartButton = new JButton("Open Staff Chart");
        openChartButton.setFocusable(false);

        prevMonthButton.addActionListener(e -> {
            calendar.add(Calendar.MONTH, -1);
            update();
        });

        nextMonthButton.addActionListener(e -> {
            calendar.add(Calendar.MONTH, 1);
            update();
        });

        openChartButton.addActionListener(e -> {
            // Code to open the TallyChart
            new TallyChart();
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
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

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        add(openChartButton, gbc);

        calendarTableModel = createNonEditableTableModel();
        calendarTable = new JTable(calendarTableModel);
        calendarTable.setAutoCreateRowSorter(true);
        calendarScrollPane = new JScrollPane(calendarTable);

        calendarTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = calendarTable.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    // Check if any column in the clicked row has a non-null value
                    boolean hasNonNullValue = false;
                    for (int col = 0; col < calendarTable.getColumnCount(); col++) {
                        Object value = calendarTable.getValueAt(row, col);
                        if (value != null) {
                            hasNonNullValue = true;
                            break;
                        }
                    }

                    if (hasNonNullValue) {
                        // Handle the row click action here
                        calendar.get(Calendar.MONTH);
                        printDaysOfWeek(row);
                        List<String>  days = getDate(row);

                        SwingUtilities.invokeLater(() -> new ScheduleGUI(daySchedule,days));
                    }
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        add(calendarScrollPane, gbc);
    }
    private void printDaysOfWeek(int clickedRow) {
        System.out.println("Click on week " + (clickedRow + 1) + ":");

        for (int i = 0; i < 7; i++) {
            Object cellValue = calendarTable.getValueAt(clickedRow, i);
            if (cellValue != null) {
                // Get the header of the corresponding column
                String columnHeader = (String) calendarTable.getColumnModel().getColumn(i).getHeaderValue();
                System.out.println(columnHeader + ": " + cellValue);
            }
        }
    }
    private List<String> getDate(int clickedRow) {
        System.out.println("Click on week " + (clickedRow + 1) + ":");
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Object cellValue = calendarTable.getValueAt(clickedRow, i);
            if (cellValue != null) {
                // Get the header of the corresponding column
                String columnHeader = (String) calendarTable.getColumnModel().getColumn(i).getHeaderValue();
                System.out.println(columnHeader + ": " + cellValue);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
                calendar2.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
                calendar2.set(Calendar.DAY_OF_MONTH, Integer.parseInt(cellValue.toString()));
                // Set hour, minute, second and millisecond to 0
                calendar2.set(Calendar.HOUR_OF_DAY, 0);
                calendar2.set(Calendar.MINUTE, 0);
                calendar2.set(Calendar.SECOND, 0);
                calendar2.set(Calendar.MILLISECOND, 0);

                int dayOfWeek = calendar2.get(Calendar.DAY_OF_WEEK);
                if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                 //   dates.add(null);
                    continue;
                }
                String formattedDate = dateFormat.format(calendar2.getTime());
                dates.add(formattedDate);

            }
        }
        return dates;
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

        String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        calendarTableModel.setColumnIdentifiers(headers);
        calendarTableModel.setRowCount(0);
        calendarTable.setRowHeight(50);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);

        int max = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int start = cal.get(Calendar.DAY_OF_WEEK);

        int row = 0;
        int col = start - 1;

        calendarTableModel.setRowCount(6);

        for (int day = 1; day <= max; day++) {
            calendarTableModel.setValueAt(day, row, col);
            col++;

            if (col == 7) {
                col = 0;
                row++;
            }
        }
    }
}