<?php
        class Person{
                protected $fname;
                protected $lname;
                protected $height;
                protected $weight;
        
        public function __construct($height, $weight, $fname="Sam", $lname="Spade"){
                $this->fname = $fname;
                $this->lname = $lname;
                $this->height = $height;
                $this->weight = $weight;
        }


        public function set_fname($fname){
                $this->fname = $fname;
        }

        public function get_fname() {
                return $this->fname;
        }

        public function set_lname($lname){
                $this->lname = $lname;
        }

        public function get_lname(){
                return $this->lname;
        }

        public function set_height($height){
                $this->height = $height;
        }

        public function get_height(){
                return $this->height;
        }

        public function set_weight($weight){
                $this->weight = $weight;
        }

        public function get_weight(){
                return $this->weight;
        }
        
        public function calculateBMI(){
                $bmi = 705 * $this->weight / ($this->height * $this->height);
                return $bmi;
        }
        }
?>
