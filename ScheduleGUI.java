import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class ScheduleGUI {
    public static int getCurrentDayNumber() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getDayOfWeek().getValue();
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
        for (int i = 0; i < 16; i++) {
            Object[] row = new Object[7]; // 7 columns
            for (int j = 0; j < 7; j++) {
                if (j == 0 && i < 5) {
                    //row[j] = "Special " + (i + 1); // Special data for the first 5 rows in the first column
                } else {
                    //row[j] = "Data " + (i + 1) + "," + (j + 1);
                }
            }

            ScheudleTableModel.addRow(row);
        }

        // Create the table with the model
        JTable ScheduleTable = new JTable(ScheudleTableModel);
        ScheduleTable.setRowHeight(20);
        ScheduleTable.getTableHeader().setReorderingAllowed(false);
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(ScheduleTable);

        // Add the scroll pane to the frame
        frame.add(scrollPane,BorderLayout.CENTER);

        // Set the size of the frame and make it visible
        frame.setSize(800, 400);
        frame.setVisible(true);
    }
}import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class ScheduleGUI {
    public static int getCurrentDayNumber() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getDayOfWeek().getValue();
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
        for (int i = 0; i < 16; i++) {
            Object[] row = new Object[7]; // 7 columns
            for (int j = 0; j < 7; j++) {
                if (j == 0 && i < 5) {
                    //row[j] = "Special " + (i + 1); // Special data for the first 5 rows in the first column
                } else {
                    //row[j] = "Data " + (i + 1) + "," + (j + 1);
                }
            }

            ScheudleTableModel.addRow(row);
        }

        // Create the table with the model
        JTable ScheduleTable = new JTable(ScheudleTableModel);
        ScheduleTable.setRowHeight(20);
        ScheduleTable.getTableHeader().setReorderingAllowed(false);
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(ScheduleTable);

        // Add the scroll pane to the frame
        frame.add(scrollPane,BorderLayout.CENTER);

        // Set the size of the frame and make it visible
        frame.setSize(800, 400);
        frame.setVisible(true);
    }
}import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class ScheduleGUI {
    public static int getCurrentDayNumber() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getDayOfWeek().getValue();
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
        for (int i = 0; i < 16; i++) {
            Object[] row = new Object[7]; // 7 columns
            for (int j = 0; j < 7; j++) {
                if (j == 0 && i < 5) {
                    //row[j] = "Special " + (i + 1); // Special data for the first 5 rows in the first column
                } else {
                    //row[j] = "Data " + (i + 1) + "," + (j + 1);
                }
            }

            ScheudleTableModel.addRow(row);
        }

        // Create the table with the model
        JTable ScheduleTable = new JTable(ScheudleTableModel);
        ScheduleTable.setRowHeight(20);
        ScheduleTable.getTableHeader().setReorderingAllowed(false);
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(ScheduleTable);

        // Add the scroll pane to the frame
        frame.add(scrollPane,BorderLayout.CENTER);

        // Set the size of the frame and make it visible
        frame.setSize(800, 400);
        frame.setVisible(true);
    }
}import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class ScheduleGUI {
    public static int getCurrentDayNumber() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getDayOfWeek().getValue();
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
        for (int i = 0; i < 16; i++) {
            Object[] row = new Object[7]; // 7 columns
            for (int j = 0; j < 7; j++) {
                if (j == 0 && i < 5) {
                    //row[j] = "Special " + (i + 1); // Special data for the first 5 rows in the first column
                } else {
                    //row[j] = "Data " + (i + 1) + "," + (j + 1);
                }
            }

            ScheudleTableModel.addRow(row);
        }

        // Create the table with the model
        JTable ScheduleTable = new JTable(ScheudleTableModel);
        ScheduleTable.setRowHeight(20);
        ScheduleTable.getTableHeader().setReorderingAllowed(false);
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(ScheduleTable);

        // Add the scroll pane to the frame
        frame.add(scrollPane,BorderLayout.CENTER);

        // Set the size of the frame and make it visible
        frame.setSize(800, 400);
        frame.setVisible(true);
    }
}import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class ScheduleGUI {
    public static int getCurrentDayNumber() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getDayOfWeek().getValue();
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
        for (int i = 0; i < 16; i++) {
            Object[] row = new Object[7]; // 7 columns
            for (int j = 0; j < 7; j++) {
                if (j == 0 && i < 5) {
                    //row[j] = "Special " + (i + 1); // Special data for the first 5 rows in the first column
                } else {
                    //row[j] = "Data " + (i + 1) + "," + (j + 1);
                }
            }

            ScheudleTableModel.addRow(row);
        }

        // Create the table with the model
        JTable ScheduleTable = new JTable(ScheudleTableModel);
        ScheduleTable.setRowHeight(20);
        ScheduleTable.getTableHeader().setReorderingAllowed(false);
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(ScheduleTable);

        // Add the scroll pane to the frame
        frame.add(scrollPane,BorderLayout.CENTER);

        // Set the size of the frame and make it visible
        frame.setSize(800, 400);
        frame.setVisible(true);
    }
}
