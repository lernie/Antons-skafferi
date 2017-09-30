package se.miun.antonsskafferi.Courses;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by joel on 2017-09-30.
 */

public class Order {
    private int table;
    private ArrayList<String> courses;

    public Order(int table) {
        this.table = table;
        this.courses = new ArrayList<String>();
    }

    public Order(int table, Collection<String> courses) {
        this.table = table;
        this.courses = new ArrayList<String>(courses);
    }

    public int getTable() {
        return table;
    }

    public int getCourseCount() {
        return courses.size();
    }

    public String getCourse(int index) {
        return courses.get(index);
    }
}
