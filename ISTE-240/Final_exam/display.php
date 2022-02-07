<?php
	$page = '';
	require  'dbconnect.inc';
?>	


<?php
	/*
		Connecting and getting data from a database in php!
			1. Connect to the db
			2. build the query
			3. retrieve/store the query
			4. close the connection
			5. do something with the results
	*/

	//step 2. build the query

	//select from tableName where name=val and other=otherVal
	$sql = "select * from modularsite2";

	//step 3 - execute the query

	
	//make a multi-dimensional ARRAY
	if($results = $mysqli->query($sql)){
		//loop through results
		while($rowHolder = mysqli_fetch_array($results,MYSQLI_ASSOC)){
			echo $rowHolder["content"];
		}
	}
    else
    {
        echo "Issue with select statement";
    }
	mysqli_close($mysqli);
?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>ISTE 240_01 List of Califonia Colleges</title>
</head>
<body>

</body>
</html>
