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

    if(array_key_exists('deleteevent', $_POST)){
        $db = new DB();
        $db->deleteEvent($_POST['id']);
    }

    if(array_key_exists('removesession', $_POST)){
        $db = new DB();
        $db->deleteSession($_POST['idsession']);
    }

    if(array_key_exists('addsession', $_POST)){
        header("Location: http://solace.ist.rit.edu/~dec8768/341/project1/addsession.php?idevent={$_POST['id']}");
    }

    if(array_key_exists('editevent', $_POST)){
        header("Location: http://solace.ist.rit.edu/~dec8768/341/project1/editevent.php?idevent={$_POST['id']}");
    }

    if(array_key_exists('editsession', $_POST)){
        header("Location: http://solace.ist.rit.edu/~dec8768/341/project1/editsession.php?idsession={$_POST['idsession']}");
    }

    if(array_key_exists('deleteAttendee', $_POST)){
        $db->removeUserFromEvent($_POST['idevent'], $_POST['idattendee']);
    }

    if(array_key_exists('deleteAttendeeSession', $_POST)){
        $db->removeUserFromSession($_POST['idsession'], $_POST['idattendee']);
    }

    if(array_key_exists('addAttendee', $_POST)){
        $db->insertAttendeeEvent($_POST['addid'], $_POST['id']);
    }

    if(array_key_exists('addAttendeeSession', $_POST)){
        $db->insertAttendeeSession($_POST['addid'], $_POST['id']);
    }

    if(array_key_exists('addvenue', $_POST)){
        header("Location: http://solace.ist.rit.edu/~dec8768/341/project1/addvenue.php");
    }

    if(array_key_exists('deletevenue', $_POST)){
        $db->deleteVenue($_POST['venueid']);
    }

    if(array_key_exists('editvenue', $_POST)){
        header("Location: http://solace.ist.rit.edu/~dec8768/341/project1/editvenue.php?venueid={$_POST['venueid']}");
    }

?>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Evxnts | Admin</title>
    <link rel="stylesheet" href="./assets/css/navStyles.css">
    <link rel="stylesheet" href="./assets/css/adminStyles.css">
</head>
<body>
    <div class="container">
        <?php
            navbar($_SESSION["user"]->getRole(), "events");
        ?>
        <div class="content">
            <?php
                if ($_SESSION["user"]->getRole() == 2){
                    echo "<h1>Your events: <button onclick=\"location.href='addevent.php?idattendee={$_SESSION['user']->getID()}'\">Add</button></h1>";
                    foreach($db->getManagerEvent($_SESSION["user"]->getID()) as $event){
                        echo "<div class='event'>";
                        echo "<form method='POST'>";

                        echo "<input class='displaynone' name='id' value={$event->getID()}>";

                        echo "<h2>{$event->getName()}</h2>";
                        echo "<p>Start date: ".formatDateTime($event->getDateStart())."</p>";
                        echo "<p>End date: ".formatDateTime($event->getDateEnd())."</p>";
                        echo "<p>Total space: ".$event->getNumberAllowed()."</p>";
                        echo "<p>Available space: ".$db->getAvailableSpotsEvent($event->getID(), $event->getNumberAllowed())."</p>";
                        echo "<button type='submit' name='editevent'>Edit</button>";
                        echo "<button type='submit' name='deleteevent'>Delete</button>";
                        echo "<h3>Attendees: </h3>";
                        echo "<table>";
                        echo "<tr><th>Name</th><th>Paid</th><th><form method='POST'><input class='displaynone' name='id' value={$event->getID()}><input placeholder='user id' name='addid'><button type='submit' name='addAttendee'>Add</button></form></th></tr>";
                        foreach($db->getEventAttendees($event->getID()) as $attendee){
                            if ($attendee['paid'] == '0'){
                                echo "<tr> <form method='POST'> <td>".$attendee['name']." <input class='displaynone' name='idattendee' value={$attendee['idattendee']}><input class='displaynone' name='idevent' value={$event->getID()}></td><td>No</td><td><button type='submit' name='deleteAttendee'>Delete</button></td></form></tr>";
                            }
                            else{
                                echo "<tr> <form method='POST'> <td>".$attendee['name']." <input class='displaynone' name='idattendee' value={$attendee['idattendee']}><input class='displaynone' name='idevent' value={$event->getID()}></td><td>Yes</td><td><button type='submit' name='deleteAttendee'>Delete</button></td></form></tr>";
                            }
                        }
                        echo "</table>";
                        echo "<h3>Sessions: <button type='submit' name='addsession'>Add</button></h3>";
                        echo "<form method='POST'>";
                        foreach($db->getEventSessions($event->getID()) as $session){
                            echo "<input class='displaynone' name='idsession' value={$session->getID()}>";
                            echo "<div class='session'>";
                            echo "<h2>".$session->getName()."</h2>";
                            echo "<p>Start date: ".formatDateTime($session->getStartDate())."</p>";
                            echo "<p>End date: ".formatDateTime($session->getEndDate())."</p>";
                            echo "<p>Total space: ".$session->getNumberAllowed()."</p>";
                            echo "<h3>Attendees: </h3>";
                            echo "<table>";
                            echo "<tr><th>Name</th><th><form method='POST'><input class='displaynone' name='id' value={$session->getID()}><input placeholder='user id' name='addid'><button type='submit' name='addAttendeeSession'>Add</button></form></th></tr>";
                            foreach($db->getSessionAttendees($session->getID()) as $attendee){
                                echo "<tr> <form method='POST'> <td>".$attendee['name']." <input class='displaynone' name='idattendee' value={$attendee['idattendee']}><input class='displaynone' name='idsession' value={$session->getID()}></td><td><button type='submit' name='deleteAttendeeSession'>Delete</button></td></form></tr>";
                            }
                            echo "</table>";
                            echo "<p><button type='submit' name='editsession'>Edit</button><button type='submit' name='removesession'>Delete</button></p>";
                            echo "</div>";
                        }
                        echo "</form>";
                        echo "</form>";
                        echo "</div>";
                    }
                }
                else{
                    echo "<h1>Users</h1>";
                    echo "<table>";
                    echo "<tr><th>User ID</th><th>Name</th><th></th></tr>";
                    foreach($db->getAllAttendees() as $attendee){
                        echo "<tr><td>{$attendee->getID()}</td><td>{$attendee->getName()}</td><td><button>Delete</button><button>Edit</button></td></tr>";
                    }
                    echo "</table>";

                    echo "<h1>Venues</h1>";
                    echo "<table>";
                    echo "<tr><th>Venue ID</th><th>Name</th><th>capacity</th><th><form method='POST'><button name='addvenue' type='submit'>Add venue</button></form></th></tr>";
                    foreach($db->getAllVenues() as $venue){
                        echo "<tr><td>{$venue->getID()}</td><td>{$venue->getName()}</td><td>{$venue->getCapacity()}</td><td><form method='POST'><input class='displaynone' name='venueid' value={$venue->getID()}><button type='submit' name='deletevenue'>Delete</button><button type='submit' name='editvenue'>Edit</button></td></form></tr>";
                    }
                    echo "</table>";
                    

                    echo "<h1>Your events: <button onclick=\"location.href='addevent.php?idattendee={$_SESSION['user']->getID()}'\">Add</button></h1>";
                    foreach($db->getEvents() as $event){
                        echo "<div class='event'>";
                        echo "<form method='POST'>";

                        echo "<input class='displaynone' name='id' value={$event->getID()}>";

                        echo "<h2>{$event->getName()}</h2>";
                        echo "<p>Start date: ".formatDateTime($event->getDateStart())."</p>";
                        echo "<p>End date: ".formatDateTime($event->getDateEnd())."</p>";
                        echo "<p>Total space: ".$event->getNumberAllowed()."</p>";
                        echo "<p>Available space: ".$db->getAvailableSpotsEvent($event->getID(), $event->getNumberAllowed())."</p>";
                        echo "<button type='submit' name='editevent'>Edit</button>";
                        echo "<button type='submit' name='deleteevent'>Delete</button>";
                        echo "<h3>Attendees: </h3>";
                        echo "<table>";
                        echo "<tr><th>Name</th><th>Paid</th><th><form method='POST'><input class='displaynone' name='id' value={$event->getID()}><input placeholder='user id' name='addid'><button type='submit' name='addAttendee'>Add</button></form></th></tr>";
                        foreach($db->getEventAttendees($event->getID()) as $attendee){
                            if ($attendee['paid'] == '0'){
                                echo "<tr> <form method='POST'> <td>".$attendee['name']." <input class='displaynone' name='idattendee' value={$attendee['idattendee']}><input class='displaynone' name='idevent' value={$event->getID()}></td><td>No</td><td><button type='submit' name='deleteAttendee'>Delete</button></td></form></tr>";
                            }
                            else{
                                echo "<tr> <form method='POST'> <td>".$attendee['name']." <input class='displaynone' name='idattendee' value={$attendee['idattendee']}><input class='displaynone' name='idevent' value={$event->getID()}></td><td>Yes</td><td><button type='submit' name='deleteAttendee'>Delete</button></td></form></tr>";
                            }
                        }
                        echo "</table>";
                        echo "<h3>Sessions: <button type='submit' name='addsession'>Add</button></h3>";
                        echo "<form method='POST'>";
                        foreach($db->getEventSessions($event->getID()) as $session){
                            echo "<input class='displaynone' name='idsession' value={$session->getID()}>";
                            echo "<div class='session'>";
                            echo "<h2>".$session->getName()."</h2>";
                            echo "<p>Start date: ".formatDateTime($session->getStartDate())."</p>";
                            echo "<p>End date: ".formatDateTime($session->getEndDate())."</p>";
                            echo "<p>Total space: ".$session->getNumberAllowed()."</p>";
                            echo "<h3>Attendees: </h3>";
                            echo "<table>";
                            echo "<tr><th>Name</th><th><form method='POST'><input class='displaynone' name='id' value={$session->getID()}><input placeholder='user id' name='addid'><button type='submit' name='addAttendeeSession'>Add</button></form></th></tr>";
                            foreach($db->getSessionAttendees($session->getID()) as $attendee){
                                echo "<tr> <form method='POST'> <td>".$attendee['name']." <input class='displaynone' name='idattendee' value={$attendee['idattendee']}><input class='displaynone' name='idsession' value={$session->getID()}></td><td><button type='submit' name='deleteAttendeeSession'>Delete</button></td></form></tr>";
                            }
                            echo "</table>";
                            echo "<p><button type='submit' name='editsession'>Edit</button><button type='submit' name='removesession'>Delete</button></p>";
                            echo "</div>";
                        }
                        echo "</form>";
                        echo "</form>";
                        echo "</div>";
                    }
                }
            ?>
        </div>
    </div>
</body>
</html>