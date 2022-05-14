<?php
    class Event{
        private $idevent;
        private $name;
        private $datestart;
        private $dateend;
        private $numberallowed;
        private $venue;

        public function getID(){
            return $this->idevent;
        }
    
        public function getName(){
            return $this->name;
        }
    
        public function getDateStart(){
            return $this->datestart;
        }

        public function getDateEnd(){
            return $this->dateend;
        }

        public function getNumberallowed(){
            return $this->numberallowed;
        }

        public function getVenue(){
            return $this->venue;
        }

    }
?>