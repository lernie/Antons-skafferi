/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped; 

@ManagedBean
@SessionScoped
public class TodaySpecial implements Serializable{

    private String todaysDate = null;
    private int dayOfTheWeek = 0;
    private String currentMon = null;
    private String currentTue = null;
    private String currentWed = null;
    private String currentThu = null;
    private String currentFri = null;

    



public TodaySpecial() {
    
    /**
     * @param todaysDate
     * @param dayOfTheWeek
     * @param currentMon
     * @param currentTue
     * @param currentWed
     * @param currentThu
     * @param currentFri
     */
}

public TodaySpecial(String todaysDate, int dayOfTheWeek, String currentMon, String currentTue, String currentWed, String currentThu, String currentFri) {


    this.todaysDate = todaysDate;
    this.dayOfTheWeek = dayOfTheWeek;
    this.currentMon = currentMon;
    this.currentTue = currentTue;
    this.currentWed = currentWed;
    this.currentThu = currentThu;
    this.currentFri = currentFri;

    
}



public String getTodaysDate(){
    Date date = new Date();
    todaysDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
    return todaysDate;
}

public void setTodaysDate(String todaysDate){
    this.todaysDate = todaysDate;
}

public int getDayOfTheWeek(){
    
    Calendar calendar = Calendar.getInstance();
    dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);
    
    return dayOfTheWeek;
}

public void setDayOfTheWeek(int dayOfTheWeek){
    this.dayOfTheWeek = dayOfTheWeek;
}

public String getCurrentMon(){
    Calendar c = Calendar.getInstance();
    c.setFirstDayOfWeek(Calendar.MONDAY);
    c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    currentMon = df.format(c.getTime());

    return currentMon;
}

public void setCurrentMon(String currentMon){
    this.currentMon = currentMon;
}

public String getCurrentTue(){
    Calendar c = Calendar.getInstance();
    c.setFirstDayOfWeek(Calendar.MONDAY);
    c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    c.add(Calendar.DATE, 1);
    currentTue = df.format(c.getTime());
    return currentTue;
}

public void setCurrentTue(String currentTue){
    this.currentTue = currentTue;
}

public String getCurrentWed(){
    Calendar c = Calendar.getInstance();
    c.setFirstDayOfWeek(Calendar.MONDAY);
    c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    c.add(Calendar.DATE, 2);
    currentWed = df.format(c.getTime());
    return currentWed;
}

public void setCurrentWed(String currentWed){
    this.currentWed = currentWed;
}

public String getCurrentThu(){
    Calendar c = Calendar.getInstance();
    c.setFirstDayOfWeek(Calendar.MONDAY);
    c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    c.add(Calendar.DATE, 3);
    currentThu = df.format(c.getTime());
    return currentThu;
}

public void setCurrentThu(String currentThu){
    this.currentThu = currentThu;
}

public String getCurrentFri(){
    Calendar c = Calendar.getInstance();
    c.setFirstDayOfWeek(Calendar.MONDAY);
    c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    c.add(Calendar.DATE, 4);
    currentFri = df.format(c.getTime());
    return currentFri;
}

public void setCurrentFri(String currentFri){
    this.currentFri = currentFri;
}
}