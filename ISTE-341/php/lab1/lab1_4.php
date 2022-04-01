<!DOCTYPE html>
<html>
	<head>
		<title>ISTE-341 Lab 1</title>
	</head>
	<body>
		<h1>Question 4</h1>
		<h3>Question 4a</h3>
		<?php
			$data = array(
				array(1,2,3,4,5),
				array(6,7,8,9,10),
				array(11,12,13)
			);
			for ($i = 0; $i < count($data); $i++){
				for ($j = 0; $j < count($data[$i]); $j++){
					if ($data[$i][$j] == 8){
						echo "<p>The element with value of 8:". $data[$i][$j]." and it's indexes are: [$i][$j]</p>";
					}
				}
			}
			$data[2][] = 14; 
			$data[] = [15,16,17];
			$data[] =  18;
		?>
		<h3>Question 4d</h3>
		<?php
			foreach ($data as $k => $val){
				if (gettype($val) == "array"){
					foreach ($val as $key => $value){
						echo "&nbsp&nbsp&nbsp&nbsp[$k][$key]: ".$val[$key];
					}
				}
				else{
					echo "&nbsp&nbsp&nbsp&nbsp[$k]: ".$val;
				}
			} 
		?>
		<h3>Question 4e</h3>
		<?php
			for ($i = 0; $i < count($data); $i++){
				if (gettype($data[$i]) == "array"){
					for ($j = 0; $j < count($data[$i]); $j++){
						echo "&nbsp&nbsp&nbsp&nbsp[$i][$j]: ".$data[$i][$j];	
					}
				}
				else {
					echo "&nbsp&nbsp&nbsp&nbsp[$i]: ".$data[$i];	
				}
			}
		?>
		<h3>Question 4g</h3>
		<?php
			$array2 = array("name" => array("first" => "Bryan", "last" => "French"), "address" => array("street" => "123 Main St", "city" => "Rochester", "state" => "NY", "zip" => "14623"));
			foreach ($array2 as $k => $v){
				foreach($v as $key => $value){
					echo "<p>[$k][$key]: $value</p>";
				}				
			}
			$array2["name"]["middle"] = "none";
			$array2["name"][] = ["my"=>"name"];
			$array2["name"][] = 25;
			$array2[] = [1,2,3,4,5];
			$array2[][] = ["testing" => "yes"];
		?>
		<h3>Question 4i</h3>
		<?php
			foreach($array2 as $k => $v){
				foreach($v as $key => $value){
					if (gettype($value) == "array"){
						foreach($value as $key2 => $value2){
							echo "<p>[$k][$key][$key2]: $value2</p>";
						}
					}
					else{
						echo "<p>[$k][$key]: $value</p>";	
					}
				}
			}			
		?>
	</body>
</html>
