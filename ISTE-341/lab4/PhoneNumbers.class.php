<?php
    class PhoneNumbers{

        private $PersonID;
        private $PhoneType;
        private $PhoneNum;
        private $AreaCode;

        public function getID(){
            return $this->PersonID;
        }

        public function getPhoneType(){
            return $this->PhoneType;
        }

        public function getNumber(){
            return $this->PhoneNum;
        }

        public function getAreaCode(){
            return $this->AreaCode;
        }
    }
?>