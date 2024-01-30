import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;

public class Main {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, 0); // January is 0
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        Map<String,DaySchedule>  schedule = FileIO.reading(calendar.getTime());

        SwingUtilities.invokeLater(() -> new CalendarGUI(schedule));
     //   SwingUtilities.invokeLater(() -> new ScheduleGUI());

//        algorithm();
    }


}
