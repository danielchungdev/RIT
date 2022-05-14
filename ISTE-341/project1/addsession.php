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

    if(array_key_exists('addSession', $_POST)){
        addSession();
        header("Location: http://solace.ist.rit.edu/~dec8768/341/project1/admin.php");
    }

    function addSession(){
        $db = new DB();
        $db->addSession($_GET['idevent'], $_POST['name'], $_POST['numberallowed'], $_POST['startdate'], $_POST['enddate']);
    }

?>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Evxnts | Add</title>
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
                <h1>Add Session</h1>
                <p>Name</p>
                <input name='name'/>
                <p>Date Start</p>
                <input name='startdate' type="datetime-local" id="start" name="trip-start">
                <p>Date End</p>
                <input name='enddate' type="datetime-local" id="start" name="trip-start">
                <p>Number Allowed</p>
                <input name='numberallowed' type='number'/>
                <button type="submit" name='addSession'>Add session</button>
            </form>
        </div>
    </div>
</body>
</html>