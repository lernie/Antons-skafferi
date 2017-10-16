/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




function getTodaySpecials(day, date){
       
         
    switch(day) {
             
    
    case 1: //Sunday
        //code block
        break;
        
    case 2: //Monday
        
            $.ajax({
                    
                    //url: "http://simonarstam.com/antons-skafferi/api/todayslunch?startdate=2017-11-02&enddate=2017-11-05",
                    url: "http://simonarstam.com/antons-skafferi/api/todayslunch?startdate="+date+"&enddate="+date,
                    method: "GET",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success : function(r) {
                        console.log(r);
                        //<![CDATA[
                        
                        for(var i = 0; i < r.length; i++){
                           console.log(r[i]);
                           $("#section1").append("<b>Dagens lunch:</b> ",r[i].foodName,"<br>");
                        }
                        
                       
                       //]]>
                       
                    }
                })
        break;
        
    case 3: //Tuesday
            $.ajax({
                    
                    //url: "http://simonarstam.com/antons-skafferi/api/todayslunch?startdate=2017-11-02&enddate=2017-11-05",
                    url: "http://simonarstam.com/antons-skafferi/api/todayslunch?startdate="+date+"&enddate="+date,
                    method: "GET",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success : function(r) {
                        console.log(r);
                        //<![CDATA[
                        
                        for(var i = 0; i < r.length; i++){
                           console.log(r[i]);
                           $("#section1").append("<b>Dagens lunch:</b> ",r[i].foodName,"<br>");
                        }
                        
                       
                       //]]>
                       
                    }
                })
        break;
        
    case 4: //Wednesday
            $.ajax({
                    
                    //url: "http://simonarstam.com/antons-skafferi/api/todayslunch?startdate=2017-11-02&enddate=2017-11-05",
                    url: "http://simonarstam.com/antons-skafferi/api/todayslunch?startdate="+date+"&enddate="+date,
                    method: "GET",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success : function(r) {
                        console.log(r);
                        //<![CDATA[
                        
                        for(var i = 0; i < r.length; i++){
                           console.log(r[i]);
                           $("#section1").append("<b>Dagens lunch:</b> ",r[i].foodName,"<br>");
                        }
                        
                       
                       //]]>
                       
                    }
                })
        break;
        
    case 5: //Thursday
            $.ajax({
                    
                    //url: "http://simonarstam.com/antons-skafferi/api/todayslunch?startdate=2017-11-02&enddate=2017-11-05",
                    url: "http://simonarstam.com/antons-skafferi/api/todayslunch?startdate="+date+"&enddate="+date,
                    method: "GET",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success : function(r) {
                        console.log(r);
                        //<![CDATA[
                        
                        for(var i = 0; i < r.length; i++){
                           console.log(r[i]);
                           $("#section1").append("<b>Dagens lunch:</b> ",r[i].foodName,"<br>");
                        }
                        
                       
                       //]]>
                       
                    }
                })
        break;
        
    case 6: //Friday
            $.ajax({
                    
                    //url: "http://simonarstam.com/antons-skafferi/api/todayslunch?startdate=2017-11-02&enddate=2017-11-05",
                    url: "http://simonarstam.com/antons-skafferi/api/todayslunch?startdate="+date+"&enddate="+date,
                    method: "GET",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success : function(r) {
                        console.log(r);
                        //<![CDATA[
                        
                        for(var i = 0; i < r.length; i++){
                           console.log(r[i]);
                           $("#section1").append("<b>Dagens lunch:</b> ",r[i].foodName,"<br>");
                        }
                        
                       
                       //]]>
                       
                    }
                })
        break;
        
    case 7: //Saturday
        //code block
        break;
}

};

function loadDropDown(){

           $.getJSON("http://simonarstam.com/antons-skafferi/api/food", function(r){
          $("#foodDropDown").html("");
          
          //<![CDATA[
         $("#foodDropDown").append("<option selected='true' disabled='disabled'>Choose Food:</option>");
       for(var i = 0; i < r.length; i++){
            
            $("#foodDropDown").append("<option value='"+r[i].id+"'>"+r[i].name+"</option>");
            }
            
            
             //]]>
   });    
};
/*
$("#fillMe").on('change', function(){
            $("#foodTxt").val($('#fillMe').val());
            
        });
*/


function getDatePicker(){
        $("#datePicker").datepicker({ dateFormat: 'yy-mm-dd' }).datepicker( "show")
    
};

function sendTodayToServer() {
         //  $("#success").val(JSON.stringify({foodId:$('#foodDropDown').val(),todaysDate:$('#datePicker').val()}));
          
                $.ajax({
                    url: "http://simonarstam.com/antons-skafferi/api/todayslunch",
                    method: "POST",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    data : JSON.stringify({foodId:$('#foodDropDown').val(),todaysDate:$('#datePicker').val()}),
                    //data : JSON.stringify({"foodId": 47, "todaysDate": "2017-10-15"}),
                    
                    success : function(r) {
                        console.log(r);
                    }
                })
            };


   
/*
$(document).ready(function(){
            
            $("#button").click(function() {
                $.ajax({
                    url: "http://simonarstam.com/antons-skafferi/api/food",
                    method: "POST",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    data : JSON.stringify({name:$("#name").val(),foodTypeId: 1,timeToCook:$("#tid").val(),price:$("#pris").val()}),
                    success : function(r) {
                       // console.log(r);
                    }
                }).done(function(response) {
                    
                   
                });
            });
            
            $.ajax({
                    url: "http://simonarstam.com/antons-skafferi/api/food",
                    method: "GET",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success : function(r) {
                       // console.log(r);
                        //<![CDATA[
                        for(var i = 0; i < r.length; i++){
                           console.log(r[i]);
                           $("#section3").append("<b>Inventarie ID:</b> ",r[i].id," <b>Maträtt:</b> ",r[i].name," <b>FoodID:</b> ",r[i].foodTypeId," <b>Tillagningstid:</b> ",r[i].timeToCook," <b>Pris:</b> ",r[i].price,"<br>");
                        }
                       
                       //]]>
                        r.forEach(function(element) {
                        
                    });
                    }
                }).done(function(response) {
                    
                
                }); 
                
});
*/
