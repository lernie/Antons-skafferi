/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped; 

@ManagedBean
@SessionScoped
public class TodaySpecial implements Serializable{

    private String todaysDate = null;
    private int dayOfTheWeek = 0;
    



public TodaySpecial() {
    
    /**
     * @param todaysDate
     * @param dayOfTheWeek
     */
}

public TodaySpecial(String todaysDate, int dayOfTheWeek) {


    this.todaysDate = todaysDate;
    this.dayOfTheWeek = dayOfTheWeek;
    
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
}