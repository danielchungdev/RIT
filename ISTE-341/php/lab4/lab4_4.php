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
        <title>Lab 4 - 4</title>
    </head>
    <body>
        <?php
            if(isset($_GET['id'])){
                echo $db->getPhoneNumbersAsTable($_GET['id']); 
                echo "<a href='lab4_3.php'>(Go back to lab4_3.php)</a>";
            }
            else{
                header("Location: lab4_3.php");
                exit;
            }
        ?>
    </body>
</html>