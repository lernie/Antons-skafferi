        $("#sendToServer").click(function() {
          // $("#success").val(JSON.stringify({foodId:$('#fillMe').val(),todaysDate:$('#datePicker').val()}));
          
                $.ajax({
                    url: "http://simonarstam.com/antons-skafferi/api/todayslunch",
                    method: "POST",
                    dataType: "json",
                    contentType: "application/json; chaset=utf-8",
                    data : JSON.stringify({foodId:$('#fillMe').val(),todaysDate:$('#datePicker').val()}),
                    //data : JSON.stringify({"foodId": 47, "todaysDate": "2017-10-15"}),
                    
                    success : function(r) {
                        console.log(r);
                    }
                })
            });
            
  $( function() {
    $( "#datePicker" ).datepicker({ dateFormat: 'yy-mm-dd' });
    
  } );
  
  
          $("#fillMe").on('change', function(){
            $("#foodTxt").val($('#fillMe').val());
            
        });
        
                 $(document).ready(function(){
           $.getJSON("http://simonarstam.com/antons-skafferi/api/food", function(r){
          $("#fillMe").html("");
          //<![CDATA[
       for(var i = 0; i < r.length; i++){
            
            $("#fillMe").append("<option value='"+r[i].id+"'>"+r[i].name+"</option>");
            }
            
             //]]>
   });    
 });
 
 
 
             $(document).ready(function(){
            $(document).ready(function(){
                console.log("hej");
                $("#button").click(function() {
                    $.ajax({
                        url: "http://simonarstam.com/antons-skafferi/api/food",
                        method: "POST",
                        dataType: "json",
                        contentType: "application/json; chaset=utf-8",
                        data : JSON.stringify({name:$("#name").val(),foodTypeId: 1,timeToCook:$("#tid").val(),price:$("#pris").val()}),
                        success : function(r) {
                            console.log(r);
                        }
                    }).done(function(response) {
                    
                    console.log("hej", JSON.parse(response));
                    });
                });
            });
            });

