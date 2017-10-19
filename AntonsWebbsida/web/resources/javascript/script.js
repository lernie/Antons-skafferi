/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$( window ).load(function() {
  loadDropDown();
});

function getLunch(date1,date2,date3,date4,date5){

    if(date1 === date2){
        $.ajax({
        //url: "http://simonarstam.com/antons-skafferi/api/todayslunch?startdate=2017-11-02&enddate=2017-11-05",
        url: "http://simonarstam.com/antons-skafferi/api/todayslunch?startdate="+date1+"&enddate="+date2,
        method: "GET",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success : function(r) {
            
            
                for(var i = 0; i < r.length; i++){
                   
                    $("#menuLeftTodaysLunch").append(r[i].foodName,", ",r[i].price,":-<br>");
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
                    
                    var jsonDate = r[i].todaysDate;
                    
                    //MONDAY
                    if (date1 === jsonDate){ 
                        $("#weekMenuMonday").append(r[i].foodName,", ",r[i].price,":-<br>");

                    }
                    
                    //TUESDAY
                    else if(date3 === jsonDate){ 
                        $("#weekMenuTuesday").append(r[i].foodName,", ",r[i].price,":-<br>");
                    }
                    
                    //WEDNESDAY
                    else if(date4 === jsonDate){
                        $("#weekMenuWednesday").append(r[i].foodName,", ",r[i].price,":-<br>");
                    }
                    
                    //THURSDAY
                    else if(date5 === jsonDate){
                        $("#weekMenuThursday").append(r[i].foodName,", ",r[i].price,":-<br>");
                    }
                    
                    // FRIDAY
                    else if(date2 === jsonDate){ 
                        $("#weekMenuFriday").append(r[i].foodName,", ",r[i].price,":-<br>");
                    }
                    else {
                        break;
                    }
                    console.log(jsonDate);
 
                };
        
        }
    });
      
    }

}
function getTodaySpecials(day, date){
         
    switch(day) {
             
        case 1: //Sunday
            $("#weekMenuLeft").val("Söndag = Stängt")
            break;
        
        case 7: //Saturday
            $("#weekMenuLeft").val("Lördag är lunchstängt!");
            break;

        default: //Monday - Friday
            getLunch(date, date);

            break;

}

};

function loadDropDown(){

           $.getJSON("http://simonarstam.com/antons-skafferi/api/food", function(r){
                $("#foodDropDown").html("");
                $("#databaseDropDown").html("");
                $("#foodDropDown").append("<option selected='true' disabled='disabled'>Choose Food:</option>");
                $("#databaseDropDown").append("<option selected='true' disabled='disabled'>Choose Food:</option>");
                r.sort(function(a,b){return a.price-b.price;});
                
                    for(var i = 0; i < r.length; i++){
                        
                        $("#foodDropDown").append("<option value='"+r[i].id+"'>"+r[i].name+' Food Type= '+r[i].price+"</option>")
                        $("#databaseDropDown").append("<option value='"+r[i].id+"'>"+r[i].name+' Food Type= '+r[i].price+"</option>")
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
                $("#foodDropDown").val($("#foodDropDown option:first").val());

                $("#datePicker").val("");

                $('#foodPrice').val("");
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
                $("#foodDropDown").val($("#foodDropDown option:first").val());

                $("#datePicker").val("");

                $('#foodPrice').val("");
}
function deleteFood(){
    var url = "http://simonarstam.com/antons-skafferi/api/food/"+$("#databaseDropDown").val();
    $('#name').val();
    console.log(url);
        $.ajax({
              
            url: url,
            method : "DELETE",
            success : function(r) {
                console.log(r);
          }
      });
}

function sendFoodToServer(){
    
                $.ajax({
                    url: "http://simonarstam.com/antons-skafferi/api/food",
                    method: "POST",
                    dataType: "json",
                    contentType: "application/json; chaset=utf-8",
                    data : JSON.stringify({name:$("#name").val(),foodTypeId:$("#foodTypeId").val(),timeToCook:$("#timeToCook").val(),price:$("#price").val()}),
                    success : function(r) {
                        console.log(r); 
                    }
                });
                
                

                $("#name").val("");

                $("#foodTypeId").val("");

                $('#timeToCook').val("");
                
                $('#price').val("");
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