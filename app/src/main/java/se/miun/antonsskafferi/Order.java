package se.miun.antonsskafferi;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by joel on 2017-09-30.
 */

public class Order {
    private int table;
    private ArrayList<OrderItem> courses;

    public Order(int table) {
        this.table = table;
        this.courses = new ArrayList<OrderItem>();
    }

    public Order(int table, Collection<OrderItem> courses) {
        this.table = table;
        this.courses = new ArrayList<OrderItem>(courses);
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

    public static class OrderItem {
        private String course;
        private int count = 0;
        private String text = "";

        public OrderItem(String course, int count) {
            this.course = course;
            this.count = count;
        }

        public OrderItem(String course, String text) {
            this.course = course;
            this.text = text;
        }

        public boolean isSpecial() {
            return text != null && !"".equals(text);
        }

        public String getCourse() {
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
