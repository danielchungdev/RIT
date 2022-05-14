<?php
    require_once("./database/PDO.DB.class.php");

    if(array_key_exists('deleteSession', $_POST)){
        registerSession();
    }

    function registerSession(){
        $db = new DB();
        $db->unattendSession($_POST['idattendee'], $_POST['idsession']);
    }

    function registerSessionCard($title, $description,$idsession, $idattendee){
        $htmlString = "<div class='card'><form method='POST'>";
        $htmlString .= "<h3>{$title}</h3>";
        $htmlString .= "<input class='displaynone' name='idattendee' value='{$idattendee}'></input>";
        $htmlString .= "<input class='displaynone' name='idsession' value='{$idsession}'></input>";
        for($i = 0; $i < count($description); $i++){
            $htmlString .= "<p>";
            $htmlString .= $description[$i];
            $htmlString .= "</p>";
        }
        $htmlString .= "<button type='submit' name='deleteSession'>Unattend</button>";
        $htmlString .= "</form></div>";
        echo $htmlString;
    }
?>