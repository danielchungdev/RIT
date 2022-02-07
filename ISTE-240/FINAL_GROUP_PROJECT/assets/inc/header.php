<!DOCTYPE html>
<!--[start the html document]-->
<html lang="en">
<head>
	<meta charset="utf-8">
	<title><?php echo $page;?></title>
	<link rel="stylesheet" type="text/css" href="<?php echo $path;?>assets/css/styles.css"/>
	<script src="<?php echo $path;?>assets/js/quiz.js"></script>
    <?php 
        if (!isset($_SESSION)) {
            session_start();
        }
    ?>
</head>
	<body>
	  <div id="page">
			<nav id="navbar">
				<a href="index.html">
					<img id="icon" src="assets/images/white_horizontal.png" alt="Java Express-o" title="Java Express-o">
				</a>
	      <ul id="navigation">
	          <li><a href="<?php echo $path;?>lesson1.php" class="navlink">Lesson 1</a></li>
	          <li><a href="<?php echo $path;?>lesson2.php" class="navlink">Lesson 2</a></li>
	          <li><a href="<?php echo $path;?>lesson3.php" class="navlink">Lesson 3</a></li>
	          <li><a href="<?php echo $path;?>lesson4.php" class="navlink">Lesson 4</a></li>
	          <li><a href="<?php echo $path;?>lesson5.php" class="navlink">Lesson 5</a></li>
	      </ul>
        <div id="profile_cropper">
        	<a href="<?php echo $path;?>profile.php"><img id="pfp" src="assets/images/pfp.jpg" alt="Profile" title="Profile"></a>
        </div>
    	</nav>
