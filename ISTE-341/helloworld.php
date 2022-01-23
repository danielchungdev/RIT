<html>
	<body>
		<?php
			$title = "first php program!";
		?>
		<h1>
			<?php echo "<p>hi world - $title</p>";  
				echo "<br/> Name is".$_GET['name'];	
			?>
		</h1>
		<?php
			$version = phpversion();
			echo "<h2>The version is $version</h2>";
			var_dump($_REQUEST);
		?>
	</body>
</html> 
