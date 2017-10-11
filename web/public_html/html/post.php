<html>
    <head>
        <script   src="https://code.jquery.com/jquery-3.2.1.min.js"   integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="   crossorigin="anonymous"></script>
    </head>
    <body>
        <form>
            Rättens namn: <input type="text" name="fname" id="namn1"><br>
            Kilo: <input type="text" name="fname" id="namn2"><br>
           
            
            <select>
                <option>5</option>
                <option>10 minuter </option>
                <option>15 person </option>
            </select>
            <br>
            
            <input type="button" value="button" id="button">
            
        </form>
    <script>
        window.onload = function(){
            $("#button").click(function() {
                $.ajax({
                    url: "http://37.139.13.250:8080/api/measurement/add",
                    dataType: "json",
                    type : "POST",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify({"name": $("#namn1").val(), "prefix": $("#namn2").val()}),

                    success : function(r) {
                        console.log(r);
                    }
                });
            });
        }
        
    </script> 
    </body>
</html>
