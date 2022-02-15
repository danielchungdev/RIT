<!DOCTYPE html>
<?php
        require("DB.class.php");

        $db = new DB();
?>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Lab 4 - 2</title>
    </head>
    <body>
        <?php
            if(isset($_GET['id'])){
                $count = count($db->getPhoneNumbers());
                echo "<h1>Numbers Found: {$count}</h1>";
                echo $db->getPhoneNumbersAsTable($_GET['id']); 
                echo "<a href='lab4_1.php'>(Go back to lab4_1.php)</a>";
            }
            else{
                header("Location: lab4_1.php");
                exit;
            }
        ?>
    </body>
</html>