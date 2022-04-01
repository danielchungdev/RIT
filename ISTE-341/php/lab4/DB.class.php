<?php
    class DB{

        private $db_conn;

        function __construct(){
            $this->db_conn = new mysqli("localhost", "dec8768", "Actable9#acception", "dec8768");
            if($this->db_conn->connect_error){
                echo "Database Connection Error";
                die();
            }
        }
        
        function getPeople(){
            $res = array();
            if($statement = $this->db_conn->prepare("SELECT * FROM people")){
                $statement->execute();
                $statement->store_result();
                $statement->bind_result($PersonID, $LastName, $FirstName, $NickName);
                if($statement->num_rows > 0){
                    while($statement->fetch()){
                        $res[] = array('id'=>$PersonID, 'firstName'=>$FirstName, 'lastName'=>$LastName,'nickName'=>$NickName);
                    }
                }
            }
            return $res;
        }

        function getPeopleAsTable(){
            $res = $this->getPeople();
            $returnHTML = "";
            if(count($res) > 0){
                $returnHTML = "
                    <table border = '1'>
                        <tr>
                            <th>PersonID</th><th>First Name</th><th>Last Name</th><th>Nickname</th>
                        </tr>
                ";
                for ($i = 0; $i < count($res); $i++){
                    $returnHTML .= "
                        <tr>
                            <td><a href = 'lab4_2.php?id={$res[$i]['id']}'>{$res[$i]['id']}</a></td>
                            <td>{$res[$i]['firstName']}</td>
                            <td>{$res[$i]['lastName']}</td>
                            <td>{$res[$i]['nickName']}</td>
                        </tr>
                    ";
                    
                }

                $returnHTML .= "</table>\n";
            }
            else{
                $returnHTML = "<h2>No one in table</h2>";
            }
            return $returnHTML;
        }

        function getPhoneNumbers(){
            $res = array();
            if($statement = $this->db_conn->prepare("SELECT * FROM phonenumbers")){
                $statement->execute();
                $statement->store_result();
                $statement->bind_result($PersonID, $PhoneType, $PhoneNum, $AreaCode);
                if($statement->num_rows > 0){
                    while($statement->fetch()){
                        $res[] = array('id'=>$PersonID,'type'=>$PhoneType,'num'=>$PhoneNum,'areaCode'=>$AreaCode);
                    }
                }
            }
            return $res;
        }

        function getPhoneNumbersAsTable($id){
            $query = "SELECT * FROM phonenumbers WHERE PersonId = ?";
            if($statement = $this->db_conn->prepare($query)){
                $statement->bind_param("i", intval($id));
                $statement->execute();
                $statement->store_result();
                $numRows = $statement->num_rows;
                $statement->bind_result($id, $type, $num, $areaCode);
            }
            $returnHTML = "";
            if($numRows > 0){
                while($statement->fetch()){
                    $res[] = array('id'=>$id,'type'=>$type,'num'=>$num,'areaCode'=>$areaCode);
                }
                $returnHTML .= "
                    <table border = '1'>
                        <tr>
                            <th>PersonID</th><th>Phone Type</th><th>Phone Number</th><th>Area Code</th>
                        </tr>
                ";
                for($i = 0; $i < count($res); $i++){
                    $returnHTML .= "
                        <tr>
                            <td>{$res[$i]['id']}</td>
                            <td>{$res[$i]['type']}</td>
                            <td>{$res[$i]['num']}</td>
                            <td>{$res[$i]['areaCode']}</td>
                        </tr>
                    ";
                }
                $returnHTML .= "</table>\n";
            }
            else{
                $returnHTML = "<h2>No numbers on {$id}</h2>";
            }
            return $returnHTML;
        }
    }