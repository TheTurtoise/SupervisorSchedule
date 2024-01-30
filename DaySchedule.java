import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DaySchedule {

	int dayId;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	Date date;

	public List<Task> getTasks() {
		return tasks;
	}

	List<Task> tasks = new ArrayList<Task>();
	
	public int getDayId() {
		return dayId;
	}
	public void setDayId(int dayId) {
		this.dayId = dayId;
	}
	public boolean add(Task e) {
		return tasks.add(e);
	}
}
