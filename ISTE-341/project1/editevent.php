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
        updateEvent();
        header("Location: http://solace.ist.rit.edu/~dec8768/341/project1/admin.php");
    }

    function updateEvent(){
        $db = new DB();
        $db->editEvent($_GET['idevent'], $_POST['name'], $_POST['datestart'], $_POST['dateend'], $_POST['numberallowed'], $_POST['venue']);

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
                <h1>Edit Event</h1>
                <?php
                    foreach($db->getEventById($_GET['idevent']) as $event){
                        echo "<p>Name</p>";
                        echo "<input name='name' value='{$event->getName()}'>";
                        echo "<p>Date Start</p>";
                        echo "<input name='datestart' type='datetime-local' value='{$event->getDateStart()}'>";
                        echo "<p>Date End</p>";
                        echo "<input name='dateend'  type='datetime-local' value='{$event->getDateEnd()}'>";
                        echo "<p>Number Allowed</p>";
                        echo "<input name='numberallowed' type='number' value='{$event->getNumberAllowed()}'>";
                        echo "<select name='venue'  value='{$event->getVenue()}'>";
                        foreach($db->getVenues() as $venues){
                            echo "<option value={$venues->getID()}>{$venues->getName()}</option>";
                        }
                        echo "</select>";
                        echo "<p><button type='submit' name='updatevent'>Update event</button></p>";
                    }
                ?>
            </form>
        </div>
    </div>
</body>
</html>