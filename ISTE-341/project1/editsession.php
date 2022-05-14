<!DOCTYPE html>
<?php
    include './classes/Attendee.class.php';
    include './classes/Event.class.php';
    session_start();

    require_once("./components/navbar.php");
    require_once("./database/PDO.DB.class.php");
    require_once("./functions/functions.php");

    $db = new DB();


    if ($_SESSION["user"] == null){
        header("Location: http://solace.ist.rit.edu/~dec8768/341/project1/login.php");
    }

    if(array_key_exists('updatevent', $_POST)){
        updatevent();
        header("Location: http://solace.ist.rit.edu/~dec8768/341/project1/admin.php");
    }
    
    function updatevent(){
        $db = new DB();
        $db->editSession($_GET['idsession'], $_POST['name'], $_POST['datestart'], $_POST['dateend'], $_POST['numberallowed']);
    }


?>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Evxnts | Edit Session</title>
    <link rel="stylesheet" href="./assets/css/navStyles.css">
    <link rel="stylesheet" href="./assets/css/addeventStyles.css">
</head>
<body>
<div class="container">
        <?php
            navbar($_SESSION["user"]->getRole(), "events");
        ?>
        <div class="content">
            <form method="POST">
                <h1>Edit Session</h1>
                <?php
                    foreach($db->getSessionById($_GET['idsession']) as $event){
                        echo "<p>Name</p>";
                        echo "<input name='name' value='{$event->getName()}'>";
                        echo "<p>Date Start</p>";
                        echo "<input name='datestart' type='datetime-local' value='{$event->getStartDate()}'>";
                        echo "<p>Date End</p>";
                        echo "<input name='dateend'  type='datetime-local' value='{$event->getEndDate()}'>";
                        echo "<p>Number Allowed</p>";
                        echo "<input name='numberallowed' type='number' value='{$event->getNumberAllowed()}'>";
                        echo "<p><button type='submit' name='updatevent'>Update Session</button></p>";
                    }
                ?>
            </form>
        </div>
    </div>
</body>
</html>