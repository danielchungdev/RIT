<?php
        session_start();
        $userVar = isset($_GET['user']);
        $passVar = isset($_GET['password']);
        $redirectString = "http://solace.ist.rit.edu/~dec8768/341/lab2/admin.php?redirect=true";

        if (isset($_SESSION["loggedIn"])){
                header('Location: '.$redirectString);
                exit;
        }

        if ($userVar && $passVar && !isset($_GET['redirect'])){
                
                $username = "admin";
                $password = "password";
                $_SESSION["loggedIn"] = true;
                $inputUser = $_GET['user'];
                $inputPassword = $_GET['password'];
        
                $date = date("F, d Y g:i a");
        
                if ($inputUser == $username && $password == $inputPassword){
                        setcookie("loggedIn", $date, time() + 600);
                        header('Location: '.$redirectString);
                        exit;
                }
                else{
                        echo "<h1>Incorret credentials</h1>";
                }
        }
        else{
                if(isset($_GET['redirect'])){
                        echo "<h1>You need to log in</h1>";
                        $modifiedString = substr($redirectString, 0, 43);
                        echo "<h3>Usage: $modifiedString/login?username=&lt;YOUR USERNAME&gt;&password=&lt;YOUR PASSWORD&gt;</h3>";
                }
                else{
                        echo "<h1>Invalid Login</h1>";
                }
        }

?>
