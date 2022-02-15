<?php
    class DB{

        private $db_conn;

        function __construct(){
            include "Person.class.php";
            include "PhoneNumbers.class.php";
            try{
                $this->db_conn = new PDO("mysql:host=localhost;dbname=dec8768", "dec8768", "Actable9#acception");
            }
            catch(PDOException $e){
                die("Error connecting to database");
            }
        }

        function getPeopleParameterizedAlt($id){
            try{
                $res = [];
                $statement = $this->db_conn->prepare("SELECT * FROM people WHERE PersonID = :id");
                $statement->bindParam(":id", $id, PDO::PARAM_INT);
                $statement->execute();
                while($row = $statement->fetch()){
                    $res[] = $row;
                }
                return $res;
            }
            catch(PDOException $e){
                echo "There was an error: ".$e->getMessage();

                die();
            }
        }
        
        function getAllObjects(){
            try{
                $res = [];
                $statement = $this->db_conn->prepare("SELECT * FROM people");
                $statement->execute();
                $statement->setfetchMode(PDO::FETCH_CLASS, "Person");
                while($person = $statement->fetch()){
                    $res[] = $person;
                }
                return $res;
            }
            catch(PDOException $e){
                echo "There was an error: ".$e->getMessage();

                die();
            }
        }

        function getPhoneNumber($id){
            try{
                $res = [];
                $statement = $this->db_conn->prepare("SELECT * FROM phonenumbers WHERE PersonID = :id");
                $statement->execute(['id'=>$id]);
                $statement->setfetchMode(PDO::FETCH_CLASS, "PhoneNumbers");
                while($phonenumbers = $statement->fetch()){
                    $res[] = $phonenumbers;
                }
                return $res;
            }
            catch(PDOException $e){
                echo "There was an error: ".$e->getMessage();
                die();
            }
        }

        function getAllPeopleAsTable(){
            $res = $this->getAllObjects();
            if(count($res) > 0){
                $returnHTML = "
                    <table border = '1'>
                        <tr>
                            <th>ID</th><th>First</th><th>Last</th><th>Nick</th>
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
            $returnHTML = "<h1>Numbers Found: {$numRows}</h1>";
            if($numRows > 0){
                $returnHTML .= "
                    <table border = '1'>
                        <tr>
                            <th>ID</th><th>Phone Type</th><th>Phone Number</th><th>Area Code</th>
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