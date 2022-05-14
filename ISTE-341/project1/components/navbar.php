<?php

    function logout(){
        session_destroy();
    }

    if (isset($_GET["logout"])){
        logout();
        header("Location: http://solace.ist.rit.edu/~dec8768/341/project1/login.php");
    }

    function navbar($role, $here){
        echo "
            <div class='navMenu'>
                <p class='logo'><a href='http://solace.ist.rit.edu/~dec8768/341/project1/home.php'>Evxnts</a></p>
                <div><a href='http://solace.ist.rit.edu/~dec8768/341/project1/events.php'>Events</a></div>
                <div><a href='http://solace.ist.rit.edu/~dec8768/341/project1/register.php'>Register</a></div>
                <div id='s{$role}'><a href='http://solace.ist.rit.edu/~dec8768/341/project1/admin.php'>Admin</a></div>
                <div><a href=".$_SERVER['REQUEST_URI'].'?logout=true'.">Logout</a></div>
                <div id='holder'></div>
            </div>
        ";
    }
?>