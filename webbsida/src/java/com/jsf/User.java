package com.jsf;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
@ManagedBean
@SessionScoped

public class User {
    private String name;
    public String getName()
    {
        return name;
    }
    public void setName(String name1)
    {
        this.name = name1; 
    }
    
}
