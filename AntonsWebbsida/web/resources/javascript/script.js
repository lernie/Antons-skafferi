/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function getLunch(d1,d2,d3,d4,d5){
    
    var date1 = d1; //Today or Monday
    var date2 = d2; //Today or Friday
    var date3 = d3; //Tuesday
    var date4 = d4; //Wedenesday
    var date5 = d5; //Thursday
    
    if(date1 === date2){
        $.ajax({
        //url: "http://simonarstam.com/antons-skafferi/api/todayslunch?startdate=2017-11-02&enddate=2017-11-05",
        url: "http://simonarstam.com/antons-skafferi/api/todayslunch?startdate="+d1+"&enddate="+d2,
        method: "GET",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success : function(r) {
            
            
                for(var i = 0; i < r.length; i++){
                   
                    $("#weekMenuLeft").append(r[i].foodName,",  ",r[i].price,":-<br>");
                }
            
        }
    });
    }
    else{
    
    
    $.ajax({
        //url: "http://simonarstam.com/antons-skafferi/api/todayslunch?startdate=2017-11-02&enddate=2017-11-05",
        url: "http://simonarstam.com/antons-skafferi/api/todayslunch?startdate="+date1+"&enddate="+date2,
        method: "GET",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success : function(r) {
            
                for(var i = 0; i < r.length; i++){
                    
                    var date = r[i].todaysDate;
                    
                    //MONDAY
                    if (date1 === date){ 
                        $("#weekMenuMonday").append(r[i].foodName,", ",r[i].price,":-<br>");

                    }
                    
                    //TUESDAY
                    else if(date3 === date){ 
                        $("#weekMenuTuesday").append(r[i].foodName,", ",r[i].price,":-<br>");
                    }
                    
                    //WEDNESDAY
                    else if(date4 === date){
                        $("#weekMenuWednesday").append(r[i].foodName,", ",r[i].price,":-<br>");
                    }
                    
                    //THURSDAY
                    else if(date5 === date){
                        $("#weekMenuThursday").append(r[i].foodName,", ",r[i].price,":-<br>");
                    }
                    
                    // FRIDAY
                    else if(date2 === date){ 
                        $("#weekMenuFriday").append(r[i].foodName,", ",r[i].price,":-<br>");
                    }
                    else {
                        
                    }
                    console.log(date);

                   
                    
                    
                   
                };
        
        }
    });
    }

}
function getTodaySpecials(day, date){
         
    switch(day) {
             
    case 1: //Sunday
        $("#weekMenuLeft").append("Idag söndag är det stängt");
        break;
        
    case 2: //Monday
        getLunch(date, date);
           
        break;
        
    case 3: //Tuesday
        getLunch(date, date);
        
        break;
        
    case 4: //Wednesday
        getLunch(date, date);  
        
        break;
        
    case 5: //Thursday
        getLunch(date, date);
        
        break;
        
    case 6: //Friday
        getLunch(date, date);
        
        break;
        
    case 7: //Saturday
        $("#weekMenuLeft").append("Lördag är lunchstängt");
        break;
}

};

function loadDropDown(){

           $.getJSON("http://simonarstam.com/antons-skafferi/api/food", function(r){
          $("#foodDropDown").html("");
          
         
         $("#foodDropDown").append("<option selected='true' disabled='disabled'>Choose Food:</option>");
       for(var i = 0; i < r.length; i++){
            
            $("#foodDropDown").append("<option value='"+r[i].id+"'>"+r[i].name+"</option>");
            }
            
            
        
   });    
};

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
                    data : JSON.stringify({foodId:$('#foodDropDown').val(),todaysDate:$('#datePicker').val(),price:$('#foodPrice').val()}),
                    //data : JSON.stringify({"foodId": 47, "todaysDate": "2017-10-15"}),
                    
                    success : function(r) {
                        console.log(r);
                    }
                });
                $('#foodDropDown').val(function() {
                    return this.defaultValue;
                });
                $('#datePicker').val(function() {
                    return this.defaultValue;
                });
                $('#foodPrice').val(function() {
                    return this.defaultValue;
                });
};

function deleteLunch(){
    var url = "http://simonarstam.com/antons-skafferi/api/todayslunch/"+$("#datePicker").val()+"/"+$("#foodDropDown").val();
    
        $.ajax({
              
            url: url,
            method : "DELETE",
            success : function(r) {
                console.log(r);
          }
      });
}
   
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