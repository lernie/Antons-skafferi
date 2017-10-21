package se.miun.antonsskafferi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by joel on 2017-09-30.
 */

public class Order {
    private int table;
    private ArrayList<OrderItem> courses;
    private long created;

    public Order(int table) {
        this.table = table;
        this.courses = new ArrayList<OrderItem>();
        this.created = 0;
    }

    public Order(int table, Collection<OrderItem> courses, long created) {
        this.table = table;
        this.courses = new ArrayList<OrderItem>(courses);
        this.created = created;
    }

    public long getCreated() {
        return created;
    }

    public int getTable() {
        return table;
    }

    public int getCourseCount() {
        return courses.size();
    }

    public OrderItem getItem(int index) {
        return courses.get(index);
    }

    public static class OrderItem implements Serializable {
        private Course course;
        private int count = 0;
        private String text = "";

        public OrderItem(Course course, int count) {
            this.course = course;
            this.count = count;
        }

        public OrderItem(Course course, String text) {
            this.course = course;
            this.text = text;
        }

        public boolean isSpecial() {
            return text != null && !"".equals(text);
        }

        public Course getCourse() {
            return course;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
