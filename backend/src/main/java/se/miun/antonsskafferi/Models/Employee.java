package se.miun.antonsskafferi.Models;

import jdk.nashorn.internal.runtime.regexp.RegExp;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.sql.Date;

import java.lang.annotation.Target;
import java.util.regex.Pattern;


public class Employee {
    // ID,FIRSTNAME,LASTNAME,POSITIONID,USERNAME,PASSWORD,EMAIL,STARTDATE
    private int id; //PK
    private String firstName;
    private String lastName;
    private int positionId; //FK
    private String userName;
    private String password;
    private String email;
    @JsonbDateFormat("yyyy-MM-dd")
    private Date startDate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    private boolean isValidEmail() {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    private boolean isOnlyChars(String value) {
        Pattern pChars = Pattern.compile("[a-zA-Z]");
        return pChars.matcher(value).matches();
    }

    public boolean checkValidity() {
        return isOnlyChars(firstName);
    }
}
