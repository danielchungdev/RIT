<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>Exercise 02 Due September 8</title>
	<!-- link -->
    <link rel="stylesheet" href="assets/css/ex02.css">
    <link href="https://fonts.googleapis.com/css?family=Chilanka&display=swap" rel="stylesheet"> 
</head>
<body>

<!-- TWO DIVs  -->

<header>
    Exercise 02 Due September 8, 2019
    <p>Updated: 09/08/2019</p>
</header>
        <!-- Individual Boxes -->
    <div id="wrapper">
        <div id="rightSide">
            <div class="square">
               <div class="Asterisk">
                   *
                   <div class="Flower">Lilac</div> <!-- end of class Flower -->
               </div><!-- end of class Asterisk -->
            </div><!-- end of one flower box "square" end of class content -->
            <div class="square">
                 <div class="Asterisk">
                   *
                   <div class="Flower">Dogwood</div> <!-- end of class Flower -->
                 </div><!-- end of class Asterisk -->
            </div><!-- end of one flower box "square" end of class content -->
            <div class="square">
                 <div class="Asterisk">
                      *
                      <div class="Flower">Pansy</div> <!-- end of class Flower -->
                 </div><!-- end of class Asterisk -->
            </div><!-- end of one flower box "square" end of class content -->
            <div class="square">
                <div class="Asterisk">
                   *
                   <div class="Flower">Iris</div> <!-- end of class Flower -->
               </div><!-- end of class Asterisk -->
            </div><!-- end of one flower box "square" end of class content -->
            <footer>
<?php
$filename = 'ex02_php_programming.php';
if (file_exists($filename)) {
    echo "Updated: " . date ("F d Y h:ia", filemtime($filename));
}
?>
    
    
<?php
       $myfileName = "mycount.cnt";
       $myPointer = fopen($myfileName, "r");
       $count = fgets($myPointer, 100);
     //  echo $count;
       fclose($myPointer);
       $count = $count + 1;
       $myPointer = fopen($myfileName, "w");
       fputs($myPointer, $count);
       fclose($myPointer);
    ?>
    <p>Page viewed <?php echo $count; ?> times!</p>
	  
        <a href="https://jigsaw.w3.org/css-validator/check/referer">
        <img style="border:0;width:88px;height:31px"
            src="https://jigsaw.w3.org/css-validator/images/vcss"
            alt="Valid CSS!" />
        </a>
        <br />
       <a href="https://people.rit.edu/dec8768/iste240/index.php">
            <img src="assets/images/home_blue.png" alt="HOME" title="HOME" /></a>
      
</footer>
        </div>
    </div>

</body>
</html>