<!DOCTYPE html>
<?php
    require_once("PDO.DB.class.php");
    $db = new DB();
?>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
    </head>
    <body>
        <?php
            $count = count($db->getAllObjects());

            echo "<h2>Records Found: {$count}</h2>";
            echo $db->getAllPeopleAsTable();
        ?>
    </body>
</html>