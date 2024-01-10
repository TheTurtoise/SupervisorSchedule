import certExamPractice.FileIO;

import java.util.HashMap;

public class Main {
    static HashMap<Integer, Integer> sizeList = new HashMap<>();

    public static void main(String[] args) {
        // File IO for staff name list
        // staff names would replace "i" (key)
        // 0 represents tally of total supervisions (value)
        for (int i = 0; i < 120; i++) {
            sizeList.put(i, 0);
        }

        FileIO.reading();

//        FileIO.writing();

        System.out.println(sizeList);

        new CalendarGUI();
    }
}