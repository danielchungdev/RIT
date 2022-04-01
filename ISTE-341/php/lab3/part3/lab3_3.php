<?php
        require('lab3_1.php');
        class BritishPerson extends Person{
        
                public function  __construct($weight, $height, $fname="Sam", $lname="Spade"){
                        $this->fname = $fname;
                        $this->lname = $lname;
                        $this->weight = $weight * 2.22046;
                        $this->height = $height / 2.54;
                }
                public function parentCalculateBMI(){
                    parent::calculateBMI();
                }
        }
?>
