<!DOCTYPE html>
<html>
	<head>
		<title>ISTE-341 Lab 1</title>
	</head>
	<body>
		<h1>Question 2</h1>
		<?php
			$grades = array(87, 75, 93, 95);
			$totalSum = 0;
			$average = NULL;
			foreach ($grades as $grade){
				$totalSum += $grade;
			}
			$average = $totalSum / count($grades);
			echo "<p>The average test score is $average</p>";
		?>
	</body>
</html>