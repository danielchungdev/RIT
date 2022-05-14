<?php
    class Attendee{
        private $idattendee;
        private $name;
        private $role;

        public function getID(){
            return $this->idattendee;
        }
    
        public function getName(){
            return $this->name;
        }
    
        public function getRole(){
            return $this->role;
        }
    }
?>