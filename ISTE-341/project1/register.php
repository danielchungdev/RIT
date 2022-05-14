<!DOCTYPE html>
<?php
    include './classes/Attendee.class.php';
    include './classes/Event.class.php';
    session_start();

    require_once("./components/navbar.php");
    require_once("./components/registereventcard.php");
    require_once("./components/registersessioncard.php");
    require_once("./database/PDO.DB.class.php");
    require_once("./functions/functions.php");

    $db = new DB();

    if ($_SESSION["user"] == null){
        header("Location: http://solace.ist.rit.edu/~dec8768/341/project1/login.php");
    }
?>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Evxnts | Register</title>
    <link rel="stylesheet" href="./assets/css/navStyles.css">
    <link rel="stylesheet" href="./assets/css/registerStyles.css">
</head>
<body>
    <div class="container">
        <?php
            navbar($_SESSION["user"]->getRole(), "events");
        ?>
        <div class="content">
            <h2>Your registered Events</h2>
            <?php
                foreach ($db->getRegisteredEvents($_SESSION["user"]->getID()) as $event){
                    $description = [];
                    $description[] = "From: ".formatDateTime($event->getDatestart());
                    $description[] = "To: ".formatDateTime($event->getDateend());
                    $description[] = "Location: ".$db->getLocation($event->getVenue());
                    $paid = True; 
                    registerEventCard($event->getName(),$description, $event->getID(), $_SESSION["user"]->getID(), $db->paidEvent($event->getID(), $_SESSION["user"]->getID()));
                }
                if(count($db->getRegisteredEvents($_SESSION["user"]->getID())) < 1){
                    echo 'You are not registered to any events!';
                }
            ?>
            <h2>Your registered Sessions</h2>
            <?php
                foreach ($db->getRegisteredSession($_SESSION["user"]->getID()) as $session){
                    $description = [];
                    $description[] = "From: ".formatDateTime($session->getStartDate());
                    $description[] = "To: ".formatDateTime($session->getEndDate());
                    registersessionCard($session->getName(), $description, $session->getID(), $_SESSION["user"]->getID());
                }
                if(count($db->getRegisteredSession($_SESSION["user"]->getID())) < 1){
                    echo 'You are not registered to any Sessions!';
                }
            ?>
        </div>
    </div>
</body>
</html>