<?php
    $path = './';
    require $path.'../../../dbConnect.inc';

    if(!empty($_POST['r_email']) && !empty($_POST['r_psw']) && !empty($_POST['r_pswR']) && passCheck() && eCheck()){

        $email=$_POST['r_email'];

        $pass=$_POST['r_psw'];
      try{

        $stmt=$mysqli->prepare("INSERT INTO accountDb (userEmail, password) VALUES(?,?)");
        $stmt->bind_param("ss",$email, password_hash($_POST['r_psw'], PASSWORD_DEFAULT));
		$stmt->execute();
		$stmt->close();
        header('Location: login.php');
      }catch(Exception $e){
          if($mysqli->errno === 1062) echo 'Duplicate entry';
      }

    }
    function passCheck(){
		if(strcmp($_POST['r_psw'],$_POST['r_pswR'])==0){
			return true;
		}else{
			return false;
		}
	}
    function eCheck(){

        if (!filter_var($_POST['r_email'], FILTER_VALIDATE_EMAIL)) {
            return false;
        }
         return true;




    }

?>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Registration</title>
        <link rel="stylesheet" href="assets/css/styles.css">
        <script src="assets/js/formVal.js"></script>
    </head>
    <body>
      <div class="loginAndRegistration">
          <div id="logo_image">
            <img src="assets/images/feature_logo.png"/>
          </div>
          <div class="container">
              <form name="regForm" method="POST" action="<?php echo $_SERVER['PHP_SELF']; ?>" onsubmit="return regVal()" >

                  <label for="email"><b>Email</b></label><br />
                  <input type="text" placeholder="email" name="r_email" />
                  <br>
                  <br>
                  <label for="psw"><b>Password</b></label><br />
                  <input type="password" placeholder="password" name="r_psw"/>

                  <br>
                  <br>
                  <label for="psw-repeat"><b>Repeat Password</b></label><br />
                  <input type="password" placeholder="repeat password" name="r_pswR" />


                  <br>
                  <br>
                  <input type="submit" value="Submit"/>

                  <br>
                  <br>
                  <p style="text-align: center;">Already have an account? <a style="color:#F5EDDA; " href="login.php">Sign in</a>.</p>

               </form>
            </div>
          </div>

</body>

</html>
