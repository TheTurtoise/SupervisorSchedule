import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileIO {

	private static final int TotalNumberDays = 75; //(Number of week * 5 day a week (75))
	public static final int numberOfTaskPerDay = 15;
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


	public static Map<String,DaySchedule>  reading(Date startDate) {
		// Read
		try {
			FileInputStream file = new FileInputStream(new File("teacher-input-template.xlsx"));
			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			//Iterate through each rows one by one
			Queue<TeacherInfo> teacherList = buildTeacherList(sheet);
			file.close();

			Map<String,DaySchedule>  data =  buildSchedule(teacherList,startDate);

			writeResultTofile(data);

			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void writeResultTofile(Map<String,DaySchedule> data) throws Exception {
		List<Date> dates = new ArrayList<>();
		for (String date:data.keySet()) {
			dates.add(dateFormat.parse(date));
		}
		Collections.sort(dates);

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Result Data Sheet");

		List<DaySchedule>  daySchedules = new ArrayList<>(data.values());
		Collections.sort(daySchedules, new Comparator<DaySchedule>() {
			@Override
			public int compare(DaySchedule p1, DaySchedule p2) {
				return p1.getDate().compareTo(p2.getDate());
			}
		});


		for (int i = 0; i <= 14; i++) {
			XSSFRow row = sheet.createRow(i);
			for (int j=0; j<data.size();j++) {
				XSSFCell cell = row.createCell(j);
				DaySchedule daySchedule = daySchedules.get(j);
				if (i == 0) {
					cell.setCellValue(dateFormat.format(daySchedule.getDate()));
				} else {
					cell.setCellValue(daySchedule.getTasks().get(i).getTeacherInfo().getName());
				}
			}
		}

		String filePath = "StoredFile.xlsx";
		FileOutputStream fileOut = new FileOutputStream(filePath);
		// Write the workbook to the file
		workbook.write(fileOut);


		System.out.println("Excel file created and saved successfully.");
	}

	private static Queue<TeacherInfo> buildTeacherList(XSSFSheet sheet) {
		Iterator<Row> rowIterator = sheet.iterator();
		Queue<TeacherInfo> teacherList = new LinkedList<>();
		boolean firstRow = true; // 
		while (rowIterator.hasNext()) {
			if (firstRow) {
				firstRow = false;
				Row row = rowIterator.next();
				continue; // skip Column Name
			}

			Row row = rowIterator.next();
			String name = row.getCell(2).getStringCellValue(); //Name
			String schedule = row.getCell(4).getStringCellValue(); //Schedule

			TeacherInfo teacherInfo = new TeacherInfo();
			teacherInfo.setName(name);

			String[] days = schedule.split(" ");//Day1(A) Day2(B)
			for (String token : days) {
				if (token.contains("(") && token.contains(")")) {
					String day = token.substring(0, token.indexOf("("));
					DayOccupied dayOccupied = new DayOccupied();
					if ("Day1".equals(day)) {
						teacherInfo.setDayOneOccupied(dayOccupied);
					} else if ("Day2".equals(day)) {
						teacherInfo.setDayTwoOccupied(dayOccupied);
					}
					String value = token.substring(token.indexOf("(") + 1, token.indexOf(")"));
					dayOccupied.process(value);
				}
			}
			if (teacherInfo.getDayOneOccupied() != null && teacherInfo.getDayTwoOccupied() != null) {
				teacherList.offer(teacherInfo);
			}

//			System.out.println("Name : " + name + "    Schedule : " + schedule);
		}
		return teacherList;
	}

//	public static List<Schedule> buildSchedule(Queue<TeacherInfo> teacherList) {
     public static Map<String,DaySchedule> buildSchedule(Queue<TeacherInfo> teacherList, Date startDate) {

		 boolean start =true;
		 int day = 1;
		Map<String,DaySchedule> weekSchedules = new HashMap<>();
		Schedule weekSchedule = new Schedule();
		 Date date = startDate;
		 while (day <= TotalNumberDays) {
			date = getNextDate(date,start);
			start = false;
			//Schedule weekSchedule = new Schedule();
			weekSchedule.setWeekID(day);
			int odd = day % 2; //  Day1 -> odd -- Day2 -> even
			int taskCounter = 0;
			DaySchedule taskOfDay = new DaySchedule();
			weekSchedule.add(taskOfDay);
			while (taskCounter < numberOfTaskPerDay) {
				TeacherInfo teacherInfo = teacherList.poll();
				teacherList.offer(teacherInfo); // adding to end of the queue;
				DayOccupied occupied;
				if (odd == 1) {
					occupied = teacherInfo.getDayOneOccupied();
				} else {
					occupied = teacherInfo.getDayTwoOccupied();
				}
				if (teacherInfo.getAssignedCounter() > 14) {
					continue;
				}
				if (!occupied.isBlockL()) {
					teacherInfo.incAssignedCounter();
					Task task = new Task();
					task.setTeacherInfo(teacherInfo);
					taskOfDay.add(task);
					taskCounter++;
				}
			}
			 taskOfDay.setDate(date);
			 String formattedDate = dateFormat.format(date);
			weekSchedules.put(formattedDate,taskOfDay);
			day++;
		}
		return weekSchedules;
	}

	private static Date getNextDate(Date date, boolean startDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if(!startDate){
			calendar.add(Calendar.DAY_OF_MONTH,1);
		}
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		// If it's Saturday or Sunday, adjust to next Monday
		if (dayOfWeek == Calendar.SATURDAY) {
			calendar.add(Calendar.DAY_OF_MONTH, 2); // Add 2 days if it's Saturday
		} else if (dayOfWeek == Calendar.SUNDAY) {
			calendar.add(Calendar.DAY_OF_MONTH, 1); // Add 1 day if it's Sunday
		}
		// Set hour, minute, second and millisecond to 0
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

}