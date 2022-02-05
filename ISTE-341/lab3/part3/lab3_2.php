<?php
        require('lab3_1.php');

        //Default test
        $testPerson = new Person(150, 67);
        $bmi  = strval($testPerson->calculateBMI());

        echo "<p>".$testPerson->get_fname()." ".$testPerson->get_lname()." BMI is: ".$bmi."</p>";
?>
