<?php
    class Session{
        private $idsession;
        private $name;
        private $numberallowed;
        private $event;
        private $startdate;
        private $enddate;

        public function getID(){
            return $this->idsession;
        }

        public function getName(){
            return $this->name;
        }

        public function getNumberallowed(){
            return $this->numberallowed;
        }

        public function getEventID(){
            return $this->event;
        }

        public function getStartDate(){
            return $this->startdate;
        }

        public function getEndDate(){
            return $this->enddate;
        }
    }
?>