<?php
    
    // Start the session
	ob_start();
    session_start();
    //Defines username and password. Retrieve however you like,
    //$username = "user";
    //$password = "12345";
	if (isset($_POST['username']) and isset($_POST['password'])){
		$username = $_POST['username'];
		$password = $_POST['password'];
		if ($username == "admin" and $password == "12345"){
			$_SESSION['username'] = $username;
			header('Location: admin.php');
		}else{
                    echo '<script language="javascript">';
                    echo 'alert("Felaktigt användarnamn eller lösenord")';
                    echo '</script>';
		}
	
	}
?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>TODO supply a title</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
       

    </head>
    <body>
        <div id="logindiv">
            <h1>Min PHP sida</h1>
            <!-- form for login -->
            <form method="POST">
                Användarnamn: 
                    
		<input type="text" name="username" value="admin"/>
                            
                Lösenord: 
                
		<input type="password" name="password" value="12345"/>
                    
		<input type="submit" value="Logga in"/>
            </form>
	</div>
    </body>
</html>