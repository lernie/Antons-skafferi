<?php
	$page_title = "Startsida";
	include("includes/header.php");
?>


	
	<p>
		Spara ditt bokningsnummer för att kontrollera vilket bord det är du har bokat och om du vill avboka senare. 
	</p> 
	
	</br> 



<?php
	include("includes/tid.php");
?>
<?php
	$page_title = "Del A";
	
	// Report all errors
	error_reporting( E_ALL );

	include 'bokning/registrera.class.php';
	include 'bokning/cyklar.class.php';
	
	// Create a global variable to the instansiated object that can be used all over
	global $bookings;
	$bookings = new Bookings("bookingsList");

	// If add "booking" button pressed?
	if(isset($_REQUEST["bokaKnapp"])){
		if(strlen($_REQUEST["name"]) > 0){
			$bokningsnummer = rand(1111, 9999); //should be randomly generated
			$bookings->plusBokning($_REQUEST["pass"], strtoupper($_REQUEST["cykel"]), 
				strtoupper($_REQUEST["name"]), Date('c'), strtoupper($bokningsnummer));
		}
		unset($_REQUEST["plusBokning"]); // Disable button press
		header("Location: bokning.php?bokningsnummer=$bokningsnummer");
		exit();
	}

	// If delete "booking" anchor pressed?
	if(isset($_REQUEST["delBooking"])){
		$bookings->removeBooking($_REQUEST["delBooking"]);
		unset($_REQUEST["delBooking"]); // Disable button press
		header("Location: bokning.php");
		exit();
	}

	// if bokningsnummer = $bookings->getBooking(id)->bokningsnummer
	if(isset($_REQUEST["updateBooking"])){
		$bookings->updateBooking($_REQUEST["id_booking"], $_REQUEST["new_event"], $_REQUEST["new_bike"],
			$_REQUEST['new_name'], Date('c'), $_REQUEST['booking_nr']);
		unset($_REQUEST["updateBooking"]); // Disable button press
		header("Location: bokning.php");
		exit();
	}

?>
	<div id="FormDB">
		<form method="POST">
			<p>
			<?php
				if(isset($_REQUEST["updateView"])){
					$id = $_REQUEST["updateView"];
					$obj = $bookings->getBooking($id);
			?>
			<p>
				<input class="hidden_input" type="text" name="id_booking" value="<?php echo $id?>" readonly />
				<br/>

				Pass<: <input type="text" name="new_event" value="<?php echo $obj->getPass()?>" />
				Cykel: <input type="text" name="new_bike" value="<?php echo $obj->getCykel()?>" />
				Namn: <input type="text" name="new_name" value="<?php echo $obj->getName()?>" />
				<input class="hidden_input" type="text" name="booking_nr" value="<?php echo $obj->getBokningNummer()?>" />
				<input type="submit" name="updateBooking" value="Uppdatera Information" />
				<input type="button" id="exit" value="Avbryt" onClick="history.back()" />
			</p>

			<?php
				}
				//Varför blir det stora bokstäver?
				//Skriver ut bokningen.
				// Write list of the booking registered with the booking nr
				if(isset($_REQUEST["display_booking_nr"])){
					$displayBookingNr = $_REQUEST["display_booking_nr"];

					foreach($bookings->hamtaBokning() as $key => $obj){
						if($obj->getBokningNummer() == $displayBookingNr){
						  	echo "<div class=\"display_bookings\"><h2>Information om din bokning</h2><br/>" . $obj->getPass() . ", " .  $obj->getCykel() . ", " . $obj->getName() .
						  	", " . $obj->getDatumOchTid() .
							" <a href='bokning.php?delBooking=$key'>Avboka</a></div>";
						}
					}
				}
			?>
			<?php if(date("w") != 7){ //not sunday
					if(!isset($_GET['bokningsnummer'])){ //if not just booked

						$current_event = 0;
						if(isset($_GET['selected'])){
							$current_event = $_GET['selected'];
						}
			?>
			</p>
			
	<div id="content2">
			
		<h2>
			Välj ditt pass här!
		</h2>
				
			
		<select class="event_list" name="pass" id="pass">
			<option selected="selected" disabled="disabled">Välj bord</option>
			<option <?php if($current_event == 1) echo 'selected="selected"' ?> value="Pass 1">Bord 1</option>
			<option <?php if($current_event == 2) echo 'selected="selected"' ?> value="Pass 2">Bord 2</option>
		</select>
				
		</br>
		<h2> 
			Välj ditt bord här:
		</h2>
				
		<select name="cykel" id="cykel">
			<option selected="selected" disabled="disabled">Välj antalet personer</option>
			<option <?php if($current_event == 2) echo 'selected="selected"' ?> value="Cykel 1">1 person </option>
			<option <?php if($current_event == 2) echo 'selected="selected"' ?> value="Cykel 2">2 personer</option>
			<option <?php if($current_event == 2) echo 'selected="selected"' ?> value="Cykel 3">3 personer</option>
			<option <?php if($current_event == 2) echo 'selected="selected"' ?> value="Cykel 4">4 personer</option>
			<option <?php if($current_event == 2) echo 'selected="selected"' ?> value="Cykel 5">5 personer</option>
			<option <?php if($current_event == 2) echo 'selected="selected"' ?> value="Cykel 6">6 personer</option>
			<option <?php if($current_event == 2) echo 'selected="selected"' ?> value="Cykel 7">7 personer</option>
			<option <?php if($current_event == 2) echo 'selected="selected"' ?> value="Cykel 8">8 personer</option>
			<option <?php if($current_event == 2) echo 'selected="selected"' ?> value="Cykel 9">9 personer</option>
			<option <?php if($current_event == 2) echo 'selected="selected"' ?> value="Cykel 10">10 personer</option>
		</select>
			
		</br> 
		<p>
			Ditt namn:
		</p> 
		
		<input type="text" name="name" /><br/>
				
		</br> 
				
		<input type="submit" name="bokaKnapp" value="Lägg Till bokning" onsubmit="update.style.display='none'" />
								
	</div id="content2">
			
	</form>
	
	<div id="content1"> 
		<h1>Visa bokning</h1>
		<form method="POST">
			Ange bokningsnummer: <input type="text" name="display_booking_nr" /><br/>
			<input type="submit" name="changeBooking" value="Visa bokning" onsubmit="update.style.display='none'" />
		</form>
	</div> 		
		
	</div>
	<?php
		}else{
			echo 'Ditt bokningsnummer är: '.$_GET['bokningsnummer'].'<br>';
			echo '<a href="bokning.php">Gör en ny bokning</a>';
		}
		}else{
			echo 'Det är stängt på söndagar!';
		}
	?>
		
	
	<?php
		include("includes/sidebar.php");
		include("includes/footer.php");

