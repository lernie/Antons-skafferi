package se.miun.antonsskafferi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kalle on 2017-10-18.
 */

public class Employee {
    public int empId;
    public String firstName;
    public String lastName;

    public Employee(int id, String firstName, String lastName){
        this.empId = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
