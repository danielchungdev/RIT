<?php
    class Venue{
        private $idvenue; 
        private $name;
        private $capacity;

        public function getID(){
            return $this->idvenue;
        }

        public function getName(){
            return $this->name;
        }

        public function getCapacity(){
            return $this->capacity;
        }
    }
?>