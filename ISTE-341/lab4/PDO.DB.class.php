<?php
    class DB{

        private $db_conn;

        function __construct(){
            include "Person.class.php";
            include "PhoneNumbers.class.php";

                $this->db_conn = new PDO("mysql:host=localhost;dbname=dec8768", "dec8768", "Actable9#acception");

        }

        function getPeopleParameterizedAlt($id){
            $res = [];
            $statement = $this->db_conn->prepare("SELECT * FROM people WHERE PersonID = :id");
            $statement->bindParam(":id", $id, PDO::PARAM_INT);
            $statement->execute();
            while($row = $statement->fetch()){
                $res[] = $row;
            }
            return $res;
        }
        
        function getAllObjects(){

            $res = [];
            $statement = $this->db_conn->prepare("SELECT * FROM people");
            $statement->execute();
            $statement->setfetchMode(PDO::FETCH_CLASS, "Person");
            while($person = $statement->fetch()){
                $res[] = $person;
            }
            return $res;
        }

        function getPhoneNumber($id){

            $res = [];
            $statement = $this->db_conn->prepare("SELECT * FROM phonenumbers WHERE PersonID = :id");
            $statement->execute(['id'=>$id]);
            $statement->setfetchMode(PDO::FETCH_CLASS, "PhoneNumbers");
            while($phonenumbers = $statement->fetch()){
                $res[] = $phonenumbers;
            }
            return $res;
        }

        function getAllPeopleAsTable(){
            $res = $this->getAllObjects();
            $returnHTML = "";
            if(count($res) > 0){
                $returnHTML = "
                    <table border = '1'>
                        <tr>
                            <th>PersonID</th><th>First Name</th><th>Last Name</th><th>Nickname</th>
                        </tr>
                ";
                for($i = 0; $i < count($res); $i++){
                    $returnHTML .= "
                        <tr>
                            <td><a href = 'lab4_4.php?id={$res[$i]->getID()}'>{$res[$i]->getID()}</a></td>
                            <td>{$res[$i]->getFirstName()}</td>
                            <td>{$res[$i]->getLastName()}</td>
                            <td>{$res[$i]->getNickname()}</td>
                        </tr>
                    ";
                }
                $returnHTML .= "</table>";
            }
            else{
                $returnHTML = "<h2>No people found</h2>";
            }
            return $returnHTML;
        }

        function getPhoneNumbersAsTable($id){
            $res = $this->getPhoneNumber($id);
            $numRows = count($res);
            $returnHTML = "";
            $returnHTML = "<h1>Numbers Found: {$numRows}</h1>";
            if($numRows > 0){
                $returnHTML .= "
                    <table border = '1'>
                        <tr>
                            <th>PersonID</th><th>Phone Type</th><th>Phone Number</th><th>Area Code</th>
                        </tr>
                ";
                for($i = 0; $i < count($res); $i++){
                    $returnHTML .= "
                        <tr>
                            <td>{$res[$i]->getID()}</td>
                            <td>{$res[$i]->getPhoneType()}</td>
                            <td>{$res[$i]->getNumber()}</td>
                            <td>{$res[$i]->getAreaCode()}</td>
                        </tr>
                    ";
                }
                $returnHTML .= "</table>";
            }
            else{
                $returnHTML = "<h2>No phone numbers.</h2>";
            }
            return $returnHTML;
        }
    }
?>