<!DOCTYPE html>
<?php
    include './classes/Attendee.class.php';
    include './classes/Event.class.php';
    session_start();

    require_once("./components/navbar.php");
    require_once("./database/PDO.DB.class.php");
    require_once("./functions/functions.php");

    $db = new DB();

    if(array_key_exists('editvenue', $_POST)){
        $db = new DB();
        $db->editVenue($_GET['venueid'], $_POST['name'], $_POST['capacity']);
        header("Location: http://solace.ist.rit.edu/~dec8768/341/project1/admin.php");
    }
?>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Venue | Evxnts</title>
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
                <h1>Edit venue</h1>
                <?php
                    foreach($db->getVenueById($_GET['venueid']) as $venue){
                        echo "<p>Name</p>";
                        echo "<input name='name' value='{$venue->getName()}'/>";
                        echo "<p>Capacity</p>";
                        echo "<input name='capacity' type='number' value='{$venue->getCapacity()}'/>";
                        echo "<button type='submit' name='editvenue'>Edit venue</button>";
                    }
                ?>
            </form>
        </div>
    </div>
</body>
</html>