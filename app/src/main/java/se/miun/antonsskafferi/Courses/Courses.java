package se.miun.antonsskafferi.Courses;

/**
 * Created by My on 9/28/2017.
 */

public class Courses {
    String Name;
    int counter;


    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Courses(String Name){
        super();
        this.Name = Name;
    }
}