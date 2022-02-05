<?php
        
        function dynamicSentence( $mood ) {
                switch($mood){
                        case 'happy':
                               return "I'm glad you're $mood!";
                        case 'mad':
                               return "I'm sorry you're $mood :(";
                        case 'indifferent':
                                return "It's ok to be $mood :)";
                }
        }

        function dateToString( $date ){
                $tempDate = new DateTime($date);
                return $tempDate->format('F j, Y');
        }
        
        function checkComment( $comment ){
                if (strlen($comment) < 10){
                        return true;
                }
                else {
                        return false;
                }
        }

        function checkEmpty($fname, $lname, $comment, $mood, $date){
                if ($fname == '' || $lname == '' || $comment == '' || !isset($mood) || $date == ''){
                        return true;
                } 
                else{
                        return false;
                }
        }

        function sanitize($var){
                $var = trim($var);
                $var = stripslashes($var);
                $var = htmlentities($var);
                $var = strip_tags($var);
                return $var;
        }
?>
