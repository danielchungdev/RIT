<html>
	<?php
		$array1 = array(33,43,25,14);
		$array2 = ["RIT" => "https://www.rit.edu", "google" => "https://www.google.com"];
		for ($i = 0; $i < count($array1); $i++){
			echo "<h1>$array1[$i]</h1>";
		}	
		foreach ($array2 as $k => $x){
			echo "<a href=$x>$k</a>";
		}
		$array3 = [
			"colors" => ['red', 'blue', 'green'],
			"shapes" => ['circle', 'square', 'triangle']
		];
		
		foreach($array3 as $key => $value){
			
			}
	?>
</html>
