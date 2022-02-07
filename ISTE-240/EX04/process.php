<?php
    $to = $_POST["email"];
    $people = $_POST["total"];
    $text = "Thank you for visiting Panama. We hope you enjoyed your stay!";
    $subject = "Thanks for visiting!";
    $headers = "dec8768@g.rit.edu";
    mail($to,$subject,$text,$headers);
    echo "Data Sent!";
?>