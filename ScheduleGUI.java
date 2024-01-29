import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class ScheduleGUI {
    public static int getCurrentDayNumber() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getDayOfMonth();
    }
    public ScheduleGUI() {
        // Create JFrame for the table
        JFrame frame = new JFrame("Schedule ");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LocalDate currentDate = LocalDate.now();
        String currentMonth = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
        int weekNumber = currentDate.get(java.time.temporal.IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        JLabel dateLabel = new JLabel("Month: " + currentMonth + " | Week Number: " + weekNumber, SwingConstants.CENTER);
        frame.add(dateLabel, BorderLayout.NORTH);
        frame.getContentPane().setBackground(Color.red);

        // Create a model for the table
        DefaultTableModel ScheudleTableModel = new DefaultTableModel();

        // Add columns` to the model need to add them each manually so that we can change
        int currentDayNumber = getCurrentDayNumber();
        for (int i = 1; i <= 7; i++) {
            if (i == 1) {
                ScheudleTableModel.addColumn("Time");
            }
            else if (i == 2) {
                ScheudleTableModel.addColumn("Duty");
            }
            //monday
            else if (i == 3 && currentDayNumber%2 == 0) {
                ScheudleTableModel.addColumn("Mon (Day 2)");
            }
            else if (i == 3 && currentDayNumber%2 != 0) {
                ScheudleTableModel.addColumn("Mon (Day 1)");
            }
            //Tuesday
            else if (i == 4 && currentDayNumber%2 == 0) {
                ScheudleTableModel.addColumn("Tue (Day 1)");
            }
            else if (i == 4 && currentDayNumber%2 != 0) {
                ScheudleTableModel.addColumn("Tue (Day 2)");
            }
            //Wednesday
            else if (i == 5 && currentDayNumber%2 == 0) {
                ScheudleTableModel.addColumn("Wed (Day 2)");
            }
            else if (i == 5 && currentDayNumber%2 != 0) {
                ScheudleTableModel.addColumn("Wed (Day 1)");
            }
            //Thursday
            else if (i == 6 && currentDayNumber%2 == 0) {
                ScheudleTableModel.addColumn("Thu (Day 1)");
            }
            else if (i == 6 && currentDayNumber%2 != 0) {
                ScheudleTableModel.addColumn("Thu (Day 2)");
            }
            //Firday
            else if (i == 7 && currentDayNumber%2 == 0) {
                ScheudleTableModel.addColumn("Fri (Day 2)");
            }
            else if (i == 7 && currentDayNumber%2 != 0) {
                ScheudleTableModel.addColumn("Fri (Day 1)");
            }


        }
        /*
        List<String> header = new ArrayList<>()
        header.add("Duty");
        header.add("Time");
        header.add("Mon");
        header.add("Tue");
        header.add("Wed");
        header.add("Thu");
        header.add("Fri");
        list heading[] = {"Time", "Duty","Mon","Tue","Wed","Thu","Fri",}
        ScheudleTableModel.setColumnIdentifiers(header);
        */

        // Add rows to the model
        for (int i = 0; i < 15; i++) {
            Object[] row = new Object[7]; // 7 columns
            for (int j = 0; j < 7; j++) {
                if (i==0) {
                    row[0] = "11:25 am - 12:02 pm";
                    row[1] = "Cafeteria";
                }
                else if (i == 1) {
                    row[1] = "Library";
                }
                else if (i == 2) {
                    row[0] = "11:35 am - 12:12 pm";
                    row[1] = "Back Foyer & Art/ASD hallways";
                }
                else if (i ==3 ) {
                    row[1] = "Front Foyer & gym/Tech hallways";
                }
                else if (i==4) {
                    row[1] = "Library";
                }
                else if (i == 5) {
                    row[0] = "11:38 am - 12:15 pm";
                    row[1] = "Gym / Weight Room";
                }
                else if (i == 6) {
                    row[1] = "Student Services";
                }
                else if (i == 7) {
                    row[1] = " Front & Back Foyer";
                }
                else if (i == 8) {
                    row[1] = "Floor 2 & 3";
                }
                else if (i == 9) {
                    row[1] = "Room 314";
                }
                else if (i == 10) {
                    row[1] = "ASD";
                }
                else if (i == 11) {
                    row[0] = "11:48 am - 12:25 pm";
                    row[1] = "Back Foyer & Art/ASD hallways";
                }
                else if (i == 12) {
                    row[1] = "Front Foyer & Gym/Tech hallways";
                }
                else if (i==13) {
                    row[1] = "Cafeteria";
                }
                else if (i==14) {
                    row[1] = "Library";
                }


//                if (j == 0 && i < 5) {
//                    row[0] = "Special " + (i + 1); // Special data for the first 5 rows in the first column
//                }
                else {
                    //row[j] = "Data " + (i + 1) + "," + (j + 1);
                }
            }

            ScheudleTableModel.addRow(row);
        }

        // Create the table with the model
        JTable ScheduleTable = new JTable(ScheudleTableModel);
        ScheduleTable.setRowHeight(25);
        ScheduleTable.getTableHeader().setReorderingAllowed(false);
        ScheduleTable.getColumnModel().getColumn(0).setPreferredWidth(85);
        ScheduleTable.getColumnModel().getColumn(1).setPreferredWidth(90);
        ScheduleTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(ScheduleTable);

        // Add the scroll pane to the frame
        frame.add(scrollPane,BorderLayout.CENTER);

        // Set the size of the frame and make it visible
        frame.setSize(1300, 455);
        frame.setVisible(true);
    }
}
