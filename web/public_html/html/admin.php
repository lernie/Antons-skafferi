<?php
	$page_title = "Startsida";

?>
<?php
    // Start the session
    ob_start();
	session_start();
	if(!isset($_SESSION['username'])){ //if login in session is not set
    	header("Location: loggain.php");
	}
?>

<html>
    
    <head>
        <link rel="stylesheet" type="text/css" href="../css/design.css">
    </head>
    
    <body>
        <div id="wrapper"> 
            <h1> Välkommen till Admin-sidan </h1>
            <p> Här kommer du kunna lägga till meny.... </p>
            <p> work in progress </p>
        </div>
    </body>
    
</html> 



