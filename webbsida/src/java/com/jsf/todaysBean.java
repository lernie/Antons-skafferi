/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped; 

@ManagedBean
@SessionScoped
public class todaysBean implements Serializable{
   
    private String foodName = null; 
    private String todaysDate = null;
    



public todaysBean() {
    
    /**
     * @param foodName
     * @param todaysDate
     */
}

public todaysBean(String foodName, String todaysDate) {

    this.foodName = foodName;
    this.todaysDate = todaysDate;
}

public String getFoodName(){
return foodName;
}

public void setFoodName(String foodName){
this.foodName = foodName;
}

public String getTodaysDate(){
    Date date = new Date();
    todaysDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
    return todaysDate;
}

public void setTodaysDate(String todaysDate){
    this.todaysDate = todaysDate;
}

}
