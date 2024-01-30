import java.util.ArrayList;
import java.util.List;

public class Schedule {
	int weekID;
	List<DaySchedule> task = new ArrayList<DaySchedule>();
	
	public int getWeekID() {
		return weekID;
	}
	public void setWeekID(int weekID) {
		this.weekID = weekID;
	}
	public boolean add(DaySchedule e) {
		return task.add(e);
	}
	public List<List<DaySchedule>> getWeekly(){
		List<List<DaySchedule>> getWeekly = new ArrayList<List<DaySchedule>>();
		int i=0;
		List<DaySchedule> list = new ArrayList<DaySchedule>();
		for (DaySchedule daySchedule : task) {
			list.add(daySchedule);
			if (i == 4) {
				getWeekly.add(list);
				list = new ArrayList<DaySchedule>();
				i = -1;
			}
			i++;
		}
		return getWeekly;
	}
}

