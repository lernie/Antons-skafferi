<html>
    <head>
        <script   src="https://code.jquery.com/jquery-3.2.1.min.js"   integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="   crossorigin="anonymous"></script>
    </head>
    <body>
        <script>
            /*
            var data = {"firstName": "Erik", "lastName": "Fahlén", "positionId": 0, "userName": "erfa", "password": "pass", "email": "test@t.se"};
            $.ajax({
                url: "http://37.139.13.250:8080/api/employee",
                dataType: "json",
                type : "POST",
                data : data,
                crossDomain:true,
                success : function(r){
                console.log(r);
                }
            });
            */
           
           var test = null;
            
        $.ajax({
            url: "http://37.139.13.250:8080/api/measurement/getall",
            dataType: "json",
            type : "GET",
            crossDomain : true,
            success : function(r) {
                for(var i = 0; i<r.length; i++){
                    console.log(r[i].name);
                }
                test = r;
                /*console.log(r);*/
            }
        });
        </script> 
        
        
        
    </body>
</html>