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
			header('Location: gomd.php');
		}else{
			echo '<script language="javascript">';
			echo 'alert("Felaktigt användarnamn eller lösenord")';
			echo '</script>';
		}
	
	}	
?>

<html>
    <body>
        <div id="logindiv">
            <h1>Min PHP sida</h1>
            <!-- form for login -->
            <form method="POST">
                Användarnamn: <br>
		<input type="text" name="username" value="admin"><br><br>
		Lösenord: <br>
		<input type="password" name="password" value="12345"><br><br>
		<input type="submit" value="Logga in">
            </form>
	</div>
    </body>
</html>

