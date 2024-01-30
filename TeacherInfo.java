public class TeacherInfo {
	private String name;
	private DayOccupied dayOneOccupied;
	private DayOccupied dayTwoOccupied;
	
	private int assignedCounter =0;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DayOccupied getDayOneOccupied() {
		return dayOneOccupied;
	}
	public void setDayOneOccupied(DayOccupied dayOneOccupied) {
		this.dayOneOccupied = dayOneOccupied;
	}
	public DayOccupied getDayTwoOccupied() {
		return dayTwoOccupied;
	}
	public void setDayTwoOccupied(DayOccupied twoOneOccupied) {
		this.dayTwoOccupied = twoOneOccupied;
	}
	
	@Override
	public String toString() {
		return "TeacherInfo [name=" + name + ", dayOneOccupied=" + dayOneOccupied + ", dayTwoOccupied=" + dayTwoOccupied
				+ "]";
	}
	public int getAssignedCounter() {
		return assignedCounter;
	}
	public void incAssignedCounter() {
		this.assignedCounter++;
	}

}
