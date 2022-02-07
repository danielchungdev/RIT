<?php

	$page = 'Survey Form';
    $path = './';
  	require $path.'../../../dbConnect.inc';

//      if(!empty($_POST['name']) && !empty($_POST['name']) ){
    if ($mysqli) {
        if(!empty($_POST['name']) && !empty($_POST['name']) ){
	  if (isset($_POST['name']) && isset($_POST['comment'])) {

        $name=$_POST['name'];

        $comment=$_POST['comment'];
		$stmt=$mysqli->prepare("INSERT INTO gComments (name, comment) VALUES(?,?)");

		$stmt->bind_param("ss",$name, $comment);
		$stmt->execute();
		$stmt->close();


      }// End of if isset
        }
      $res=$mysqli->query('select name, comment from gComments');
	  if($res){
		while($rowHolder = mysqli_fetch_array($res,MYSQLI_ASSOC)){
			$records[] = $rowHolder;
		}//end the while loo
      }
    }// end of the connect to databbase
      //}
          ?>
<!DOCTYPE html>
<html lang="en">
<head>
	<title><?php echo $page;?></title>

    <link rel="stylesheet" type="text/css" href="assets/css/survey.css"/>
    <script src="asset/js/survey.js"></script>
</head>
<body>
<div class="loginAndRegistration">
   <div class="container" style="text-align: left; width: 70%; margin: 0 auto;">
      <h1>Please Give Us Your Feedback</h1>
	   <form id="cForm" name="commentForm" action="<?php echo $_SERVER['PHP_SELF']; ?>" method="POST" onsubmit="return post();">
        <h2>Please Enter Your Name</h2>
            <input id="nameIn" type="text" name="name"/>

            <br>
            <h2>We would Love To Know What You Thought</h2>
            <!--            <input id="commemt"  type="text" name="comment"/>-->

            <input id="commentTa" type="text" name="comment" />
            <!--            <textarea rows="5"  cols="50" form="commentForm" id="commentTa" value="com" name="comment"></textarea>-->
            <br>
            <br>
            <!--            <input id="sub" type="button" value="Submit"/>-->
             <input id="sub" type="submit" value="Submit"/>
            <h2>Comments</h2>
            <div class="commentsDiv">
            <?php
           			if (mysqli_num_rows($res) > 0) {
			     //echo "<ul>";
		         foreach(array_reverse($records) as $this_row){
			  echo "<li style='font-size:16px; color:#F5EDDA; list-style-type: none;'>".$this_row['name'] . ": " . $this_row['comment']."</li><br>";
			}
                    //echo '</ul>';
                    }

            ?>
           </div>
        </form>
        <?php include("assets/inc/footer.php"); ?>
      </div>
     </div>

    </body>
</html>
