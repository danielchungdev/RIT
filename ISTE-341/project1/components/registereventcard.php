<?php
    require_once("./database/PDO.DB.class.php");

    if(array_key_exists('deleteEvent', $_POST)){
        registerEvent();
    }

    if(array_key_exists('payEvent', $_POST)){
        payEvent();
    }

    function payEvent(){
        $db = new DB();
        $db->payEvent($_POST['idevent'], $_POST['idattendee']);
    }

    function registerEvent(){
        $db = new DB();
        $db->unattendEvent($_POST['idattendee'], $_POST['idevent']);
    }

    function registerEventCard($title, $description,$idevent, $idattendee, $paid){
        $htmlString = "<div class='card'><form method='POST'>";
        $htmlString .= "<h3>{$title}</h3>";
        $htmlString .= "<input class='displaynone' name='idattendee' value='{$idattendee}'></input>";
        $htmlString .= "<input class='displaynone' name='idevent' value='{$idevent}'></input>";
        for($i = 0; $i < count($description); $i++){
            $htmlString .= "<p>";
            $htmlString .= $description[$i];
            $htmlString .= "</p>";
        }
        $htmlString .= "<button type='submit' name='deleteEvent'>Unattend</button>";
        if($paid == 0){
            $htmlString .= "<button type='submit' name='payEvent'>Pay</button>";
        }
        else{
            $htmlString .= "<button disabled>Already paid</button>";
        }
        $htmlString .= "</form></div>";
        echo $htmlString;
    }
?>