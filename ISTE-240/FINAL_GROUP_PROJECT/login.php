<?php
    session_start();

    $path = './';
    require $path.'../../../dbConnect.inc';


    if(!empty($_POST['email']) && !empty($_POST['psw']) ){
        $email=$_POST['email'];

        $pass=$_POST['psw'];

        $stmt=$mysqli->prepare("SELECT password FROM accountDb WHERE userEmail=?");
        $stmt->bind_param("s",$email);
        $stmt->execute();
        $stmt->bind_result($res);
		$stmt->fetch();
        echo "triggering if...";
        if (password_verify($_POST['psw'], $res)) {
            echo "if triggered!";
            $_SESSION['login']=true;
            $_SESSION['email']=$email;
                session_name($_POST['email'].'s');
            var_dump($_SESSION['email']);
			header('Location: index.html');
        }
        else{
            session_destroy();

        }
       $stmt->close();
    }




?>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Login</title>
        <link rel="stylesheet" href="assets/css/styles.css">
        <script src="assets/js/formVal.js"></script>
    </head>
    <body>
        <div class="loginAndRegistration">
            <div id="logo_image">
              <img src="assets/images/feature_logo.png"/>
            </div>
            <div class="container">
                <form name="logForm" method="POST" action="<?php echo $_SERVER['PHP_SELF']; ?>" onsubmit="return logVal()" >
                    <label for="email"><b>Email</b></label><br />
                    <input type="text" placeholder="email" name="email" />
                    <br>
                    <br>
                    <label for="psw"><b>Password</b></label><br />
                    <input type="password" placeholder="password" name="psw"/>
                    <br>
                    <br>
                    <br>
                    <br>
                    <input type="submit" name="Login"/>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                </form>
                <p style="text-align: center;">Need an account? <a style="color:#F5EDDA;" href="registration.php">Register</a>.</p>
            </div>
        </div>
    </body>
</html>
