<?php

    require_once("./database/PDO.DB.class.php");

    if(array_key_exists('registerSession', $_POST)){
        registerSession();
    }

    function registerSession(){
        $db = new DB();
        $db->insertAttendeeSession($_POST['idattendee'], $_POST['idsession']);
    }

    function sessionCard($title, $description, $available, $idsession, $idattendee){
        $htmlString = "<div class='card'><form method='POST'>";
        $htmlString .= "<h3>{$title}</h3>";
        $htmlString .= "<input class='displaynone' name='idattendee' value='{$idattendee}'></input>";
        $htmlString .= "<input class='displaynone' name='idsession' value='{$idsession}'></input>";
        for($i = 0; $i < count($description); $i++){
            $htmlString .= "<p>";
            $htmlString .= $description[$i];
            $htmlString .= "</p>";
        }
        if ($available == 0){
            $htmlString .= "<button type='submit' name='registerSession'>Register</button>";
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