<!DOCTYPE html>
<?php
    include './classes/Attendee.class.php';
    include './classes/Event.class.php';
    session_start();

    require_once("./components/navbar.php");
    require_once("./database/PDO.DB.class.php");
    require_once("./components/eventcard.php");
    require_once("./components/sessioncard.php");
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
    <title>Evxnts | Events</title>
    <link rel="stylesheet" href="eventStyles.css">
    <link rel="stylesheet" href="./assets/css/navStyles.css">
    <link rel="stylesheet" href="./assets/css/eventStyles.css">
    <link rel="stylesheet" href="./assets/css/eventCard.css">
</head>
<body>
    <div id='container'>
        <?php
            navbar($_SESSION["user"]->getRole(), "events");
        ?>
        <div class="content">
            <h1>Events</h1>
            <div class="content--cards">
            <?php
                foreach($db->getEvents() as $event){
                    $available = 0; 
                    $availableSpots = $db->getAvailableSpotsEvent($event->getID(), $event->getNumberallowed());
                    $description = [];
                    $description[] = "From: ".formatDateTime($event->getDatestart());
                    $description[] = "To: ".formatDateTime($event->getDateend());
                    $description[] = "Location: ".$db->getLocation($event->getVenue());
                    $description[] = "Available: ".$availableSpots;
                    if ($availableSpots < 1){
                        //full
                        $available = 1;
                    }
                    if ($db->isRegisteredToEvent($_SESSION["user"]->getID(), $event->getID())){
                        $available = 2;
                    }
                    eventCard($event->getName(),$description, $available, $event->getID(), $_SESSION["user"]->getID());
                }
            ?>
            </div>
            <h1>Sessions</h1>
            <div class="content--cards">
                <?php
                    foreach($db->getSessions() as $session){
                        $available = 0;
                        $availableSpots  = $db->getAvailableSpotsSession($session->getID(), $session->getNumberallowed());
                        $description = [];
                        $description[] = "From: ".formatDateTime($session->getStartDate());
                        $description[] = "To: ".formatDateTime($session->getEndDate());
                        $description[] = "Available: ".$availableSpots;
                        if ($availableSpots < 1){
                            //full
                            $available = 1;
                        }
                        if ($db->isRegisteredToSession($_SESSION["user"]->getID(), $session->getID())){
                            $available = 2;
                        }
                        sessionCard($session->getName(), $description, $available, $session->getID(), $_SESSION["user"]->getID());
                    }
                ?>
            </div>
        </div>
    </div>
</body>
</html>