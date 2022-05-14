<!DOCTYPE html>
<html lang="en">
    <?php
        require("./database/PDO.DB.class.php");
        require("./functions/functions.php");
        $db = new DB();

        //StateManager
        $emptyField = false;
        $userExist = false;
        $somethingWentWrong = false;

        if ($_SERVER["REQUEST_METHOD"] == "POST"){
            $emptyField = false;
            $userExist = false;
            $somethingWentWrong = false;
            if (!isEmpty($_POST["username"], $_POST["password"])){
                if ($_POST["password"] == $_POST["confirmPassword"]){
                    $cleanUsername = sanitizeString($_POST["username"]);
                    $cleanPassword = sanitizeString($_POST["password"]);
                    $attemptedSignup = $db->signup($_POST["username"], $_POST["password"], $_POST["role"]);
                    if ($attemptedSignup == -2){
                        $userExist = true;
                    }
                    if($attemptedSignup == -1){
                        $somethingWentWrong = true;
                    }
                    if ($attemptedSignup == 0){
                        header("Location: http://ist-solace.main.ad.rit.edu/~dec8768/341/project1/login.php");
                    }
                }
            }
            else{
                $emptyField = true;
            }
        }
    ?>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign up | Evxnts</title>
    <link rel="stylesheet" href="./assets/css/signupStyles.css">
</head>
<body>
<div id="container">
        <div id="card">
            <div id="content">
                <h1>Signup</h1>
                <form action="<?php echo $_SERVER['PHP_SELF']?>" method="POST">
                    <div id="card--field">
                        <p>Username:</p>
                        <input name="username"/>
                    </div>
                    <div id="card--field">
                        <p>Password:</p>
                        <input type="password" name="password"/>
                    </div>
                    <div id="card--field">
                        <p>Confirm Password:</p>
                        <input type="password" name="confirmPassword"/>
                    </div>
                    <div id="card--field">
                        <p>Role: </p>
                        <select name="role" id="role--select">
                            <option value="1"">Admin</option>
                            <option value="2">Event Manager</option>
                            <option value="3">Attendee</option>
                        </select>
                    </div>
                    <a href="./login.php">Already have an account?</a>
                    <?php
                        if ($emptyField){
                            echo "<p id='error'>There are empty fields!</p>";
                        }
                        if ($userExist){
                            echo "<p id='error'>Username already exists.</p>";
                        }
                    ?>
                    <button id="login--button" type="submit">Login</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>