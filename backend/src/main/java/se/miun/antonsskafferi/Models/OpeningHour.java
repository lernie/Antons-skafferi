package se.miun.antonsskafferi.Models;

public class OpeningHour {
    private int id; //PK
    private String day;
    private short openingTime;
    private short closingTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public short getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(short openingTime) {
        this.openingTime = openingTime;
    }

    public short getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(short closingTime) {
        this.closingTime = closingTime;
    }
}
