<?php
     //Remember, all data collected from the user, MUST use
	 //PHP prepaired statements to insert the data into a table.
	 require 'dbconnect.inc';

	 if ($_SERVER["REQUEST_METHOD"] == "POST") {
		 $name = $_POST["name"];
		 $city = $_POST["city"];
		 $state = $_POST["state"];
		 $phone = $_POST["phone"];
		if (isset($name) && isset($city) && isset($state) && isset($phone)){
			if ($mysqli) {
			
			
			$sql= "INSERT INTO collegeinfo(college, state, city, Phone) VALUES('".$name."','".$city."','".$state."','".$phone."')";
			$results=$mysqli->query($sql);
			
			if($results->num_rows>0){
				echo $results->num_rows;
				header('index.php');
			}  		  
			else  {
					echo '<h3>Something is wrong with the $sql</h3>';
					echo "<p>$sql</p>";
				die("Unable to perform operation");
				}//end of false path
			}//end of if connected to the database
		}
	}
?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>ISTE 240_01 Record Count 2019</title>
</head>
<body>
<!--  insert a hyperlink (ideally use home rollover button provided) to return to your index.php page -->
</body>
</html>




