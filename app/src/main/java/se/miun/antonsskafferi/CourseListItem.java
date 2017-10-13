package se.miun.antonsskafferi;

import se.miun.antonsskafferi.Course;

/**
 * Created by My on 9/30/2017.
 */

public class CourseListItem {

    private Course course;
    private int count;

    public CourseListItem(Course course){
        this.course = course;
        count = 0;
    }

    public CourseListItem(Course course, int count) {
        this.course = course;
        this.count = count;
    }

    public Course getCourse() {
        return course;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}


