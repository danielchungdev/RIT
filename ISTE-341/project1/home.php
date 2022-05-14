<!DOCTYPE html>
<?php
    include './classes/Attendee.class.php';
    session_start();

    require_once("./components/navbar.php");
    require_once("./database/PDO.DB.class.php");
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
    <title>Evxnts | Home </title>
    <link rel="stylesheet" href="./assets/css/navStyles.css">
    <link rel="stylesheet" href="./assets/css/homeStyles.css">
</head>
<body>
    <div id="container">
        <?php
            navbar($_SESSION["user"]->getRole(), "events");
        ?>
        <div class="content">
            <div id="card">
                <p>Welcome back! <?php echo $_SESSION["user"]->getName();?></p>
            </div>
        </div>
    </div>
</body>
</html>