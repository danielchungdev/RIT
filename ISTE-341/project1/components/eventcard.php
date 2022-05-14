<?php

    require_once("./database/PDO.DB.class.php");

    if(array_key_exists('registerEvent', $_POST)){
        registerEvent();
    }

    function registerEvent(){
        $db = new DB();
        $db->insertAttendeeEvent($_POST['idattendee'], $_POST['idevent']);
    }

    function eventCard($title, $description, $available, $idevent, $idattendee){
        $htmlString = "<div class='card'><form method='POST'>";
        $htmlString .= "<h3>{$title}</h3>";
        $htmlString .= "<input class='displaynone' name='idattendee' value='{$idattendee}'></input>";
        $htmlString .= "<input class='displaynone' name='idevent' value='{$idevent}'></input>";
        for($i = 0; $i < count($description); $i++){
            $htmlString .= "<p>";
            $htmlString .= $description[$i];
            $htmlString .= "</p>";
        }
        if ($available == 0){
            $htmlString .= "<button type='submit' name='registerEvent'>Register</button>";
        }
        else if ($available == 1) {
            $htmlString .= "<button disabled>Sold out</button>";
        }
        else if ($available == 2){
            $htmlString .= "<button disabled>Already Registered</button>";
        }
        $htmlString .= "</form></div>";
        echo $htmlString;
    }

?>