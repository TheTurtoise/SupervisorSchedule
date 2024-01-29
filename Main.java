import certExamPractice.FileIO;

import java.util.HashMap;

public class Main {
    static HashMap<String, Integer> sizeList = new HashMap<>();

    public static void main(String[] args) {
        // stored as (row #, column #) (y, x)
        FileIO.reading();

        // File IO for staff name list
        // staff names would replace "i" (key)
        // 0 represents tally of total supervisions (value)
        for (int i = 0; i < FileIO.getDataTable().size(); i++) {
            sizeList.put(FileIO.getDataTable().get(i).get(2), 0);
        }

//        System.out.println(sizeList);

        SwingUtilities.invokeLater(() -> new CalendarGUI());
        SwingUtilities.invokeLater(() -> new ScheduleGUI());



//        FileIO.writing();
    }
}
