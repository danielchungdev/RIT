<?php
		$dbHost = "localhost";
		$dbUser = "dec8768";
		$dbPassword = "coldsoldier";
		$db = "dec8768";		

		$conn = mysqli_connect($dbHost, $dbUser, $dbPassword, $db);
		if (mysqli_connect_errno()){
			printf("CONNECT FAILED %s\n",mysqli_connect_error());
			exit(); 
		}
?>