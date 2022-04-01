<?php
    class Person{
        
        private $PersonID;
        private $LastName;
        private $FirstName;
        private $NickName;

        public function getID(){
            return $this->PersonID;
        }

        public function getFirstName(){
            return $this->FirstName;
        }

        public function getLastName(){
            return $this->LastName;
        }

        public function getNickname(){
            return $this->NickName;
        }
    }
?>