<!DOCTYPE html>
<html lang="en">
    <?php
        require("DB.class.php");

        $db = new DB();
    ?>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Lab 4 - 1</title>
    </head>
    <body>
        <?php
            $count = count($db->getPeople());

            echo "<h1>Records Found: {$count}</h1>";
            echo $db->getPeopleAsTable();
        ?>
    </body>
</html>