<?php

    function sanitizeString($str){
        $sanitized = filter_var($str, FILTER_SANITIZE_STRING, FILTER_FLAG_STRIP_HIGH);
        return $sanitized;
    }

    function isEmpty($username, $password){
        if ($username == "" or $password == ""){
            return true;
        }
        else{
            return false;
        }
    }
    
    function formatDateTime($text){
        $newDateTime = new DateTime($text);
        return $newDateTime->format('M j Y, g:i A');
    }
?>