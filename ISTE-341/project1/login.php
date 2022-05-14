<!DOCTYPE html>
<?php
    require("./database/PDO.DB.class.php");
    require("./functions/functions.php");
    $db = new DB();

    //State
    $passwordError = false;
    $emptyField = false;

    if ($_SERVER["REQUEST_METHOD"] == "POST"){
        if (!isEmpty($_POST["username"], $_POST["password"])){
            $attemptedLogging = $db->login(sanitizeString($_POST["username"]), sanitizeString($_POST["password"])); 
            if (count($attemptedLogging) > 0){
                session_start();
                $_SESSION['user'] = $attemptedLogging[0];
                header("Location: http://solace.ist.rit.edu/~dec8768/341/project1/home.php");
            }
            else{
                $passwordError= true;
            }
        }
        else{
            $emptyField = true;
        }
    }

?>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Evxnts</title>
    <link rel="stylesheet" href="./assets/css/loginStyles.css">
</head>
<body>
    <div id="container">
        <div id="card">
            <div id="content">
                <h1>Login</h1>
                <form action="<?php echo $_SERVER['PHP_SELF']?>" method="POST">
                    <div id="card--field">
                        <p>Username:</p>
                        <input name="username" type="text"/>
                    </div>
                    <div id="card--field">
                        <p>Password:</p>
                        <input name="password" type="password"/>
                    </div>
                    <a href="./signup.php">Create an account!</a>
                    <?php
                        if ($passwordError){
                            echo "<p id='error'>Invalid login! Try again!</p>";
                        }
                        if($emptyField){
                            echo "<p id='error'>There are empty fields</p>";
                        }
                    ?>
                    <button id="login--button" type="submit">Login</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>