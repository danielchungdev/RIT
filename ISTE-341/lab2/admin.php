<!DOCTYPE html>
<?php
        session_start();
        $redirectString = "http://solace.ist.rit.edu/~dec8768/341/lab2/login.php";
        if (isset($_COOKIE['loggedIn'])){
                $dateTime = $_COOKIE['loggedIn'];
?>
<html>
        <header>
                <title> Admin </title>
        </header>
        <body>
                <?php
                        if(!isset($_GET['redirect'])){
                                echo "<h1>This is dashboard</h1>";
                                echo "<h3>You logged in $dateTime</h3>";
                                session_destroy();
                                setcookie('loggedIn', '', time()-3600);
                        }
                        else{
                                echo "<h1>This is dashboard</h1>";
                                echo "<h3>You logged in $dateTime</h3>";
                        }
                      }
                      else{
                        header("Location: ".$redirectString."?redirect=true");
                        exit;
                      }
                ?>
        </body>
</html>
