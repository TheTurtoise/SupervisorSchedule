package certExamPractice;

import java.util.List;

public class Teacher {
    String course;
    String description;
    String name;
    String code;
    String schedule;
    String num;
    String room;
    int total;
    int max;
    boolean blended;
    String platoon;
    String teacher;
    double credit;

    Teacher() {

    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public boolean isBlended() {
        return blended;
    }

    public void setBlended(boolean blended) {
        this.blended = blended;
    }

    public String getPlatoon() {
        return platoon;
    }

    public void setPlatoon(String platoon) {
        this.platoon = platoon;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setAll(List<String> data) {
        this.setCourse(data.get(1));
        this.setDescription(data.get(2));
        this.setName(data.get(3));
        this.setCode(data.get(4));
        this.setSchedule(data.get(5));
        this.setNum(data.get(6));
        this.setRoom(data.get(7));
        this.setTotal(Integer.parseInt(data.get(8)));
        this.setMax(Integer.parseInt(data.get(9)));
        this.setBlended(Boolean.parseBoolean(data.get(10)));
        this.setPlatoon(data.get(12));
        this.setTeacher(data.get(13));
        this.setCredit(Double.parseDouble(data.get(14)));
    }
}
