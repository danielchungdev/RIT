<?php
        require('lab3_3.php');
        $britishPerson = new BritishPerson(150, 120);
        echo "<p>".$britishPerson->get_fname()." ".$britishPerson->get_lname()." BMI is: ".strval($britishPerson->parentCalculateBMI())."</p>";

