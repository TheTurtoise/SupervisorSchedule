package certExamPractice;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class FileIO {
    static List<List<String>> dataTable = new ArrayList<List<String>>();

    static int rowIteration = 0;
    static int columnIteration = 0;

    public static void instantiate() {
        for(int i = 0; i < 17; i++)  {
            dataTable.add(new ArrayList<String>());
        }
        System.out.println("2d arraylist instantiated");
    }
    public static void reading() {
        // Read
        try {
            FileInputStream file = new FileInputStream(new File("teacher-input-template.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                columnIteration = 0;
                while (columnIteration <= 17)
                {
                    Cell cell = row.getCell(columnIteration);
                    //Check the cell type and format accordingly
                    if (cell == null) {
                        System.out.print("Empty ");
                        System.out.println(rowIteration + ", " + columnIteration);
                    } else {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:
                                dataTable.get(rowIteration).add(String.valueOf(cell.getNumericCellValue()));
                                System.out.println(rowIteration + ", " + columnIteration);
                                break;
                            case Cell.CELL_TYPE_STRING:
                                dataTable.get(rowIteration).add(cell.getStringCellValue());
                                System.out.println(rowIteration + ", " + columnIteration);
                                break;
                        }
                    }
                    columnIteration++;
                }
                rowIteration++;
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nsuccess");
        System.out.println(dataTable);
    }

    public static void writing() {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Employee Data");

        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        // ---- Note ----
        // String data is row number, object data is object stored in consecutive cells
//        data.put("1", new Object[]{"ID", "NAME", "LASTNAME"});
//        data.put("2", new Object[]{1, "Amit", "Shukla"});
//        data.put("3", new Object[]{2, "Lokesh", "Gupta"});
//        data.put("4", new Object[]{3, "John", "Adwards"});
//        data.put("5", new Object[]{4, "Brian", "Schultz"});

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();

        int rownum = 0;
        for (String key : keyset)
        {
            //create a row of excelsheet
            Row row = sheet.createRow(rownum++);

            //get object array of prerticuler key
            Object[] objArr = data.get(key);

            int cellnum = 0;

            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                {
                    cell.setCellValue((String) obj);
                }
                else if (obj instanceof Integer)
                {
                    cell.setCellValue((Integer) obj);
                }
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("teacher-input-template.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("\nsuccess");

    }
}
