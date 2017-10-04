package se.miun.antonsskafferi;

/**
 * Created by My on 9/28/2017.
 */

public class Course {
    String Name;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Course(String Name){
        this.Name = Name;
    }
}