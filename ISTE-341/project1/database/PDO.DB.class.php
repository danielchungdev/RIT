<?php
    class DB{
        private $db_conn;

        function __construct(){
            include_once("classes/Attendee.class.php");
            include_once("classes/Event.class.php");
            include_once("classes/Session.class.php");
            include_once("classes/Venue.class.php");
            $this->db_conn = new PDO("mysql:host = localhost; dbname=dec8768", "dec8768", "Actable9#acception");
        }

        function login($username, $password){
            $res = [];
            $password = hash("sha256", $password);
            $statement = $this->db_conn->prepare("SELECT idattendee, name, role FROM attendee WHERE name = :name AND password = :password");
            $statement->bindParam(":name", $username, PDO::PARAM_STR);
            $statement->bindParam(":password", $password, PDO::PARAM_STR);
            $statement->execute();
            $statement->setfetchMode(PDO::FETCH_CLASS, "Attendee");
            while($Attendee = $statement->fetch()){
                $res[] = $Attendee;
            }
            return $res;
        }

        function signup($username, $password, $role){
            if (!$this->userExists($username)){
                $password = hash("sha256", $password);
                $statement = $this->db_conn->prepare("INSERT INTO attendee (name, password, role) VALUES (:name, :password, :role)");
                $statement->bindParam(":name", $username, PDO::PARAM_STR);
                $statement->bindParam(":password", $password, PDO::PARAM_STR);
                $statement->bindParam(":role", $role, PDO::PARAM_INT);
                if(!$statement->execute()){
                    //Failure
                    return -1;
                }
                else{
                    //Success
                    return 0;
                }
            }
            else{
                //Username exists
                return -2;
            }
        }

        function userExists($username){
            $res = [];
            $statement = $this->db_conn->prepare("SELECT * FROM attendee WHERE name = :name");
            $statement->bindParam(":name", $username, PDO::PARAM_STR);
            $statement->execute();
            $statement->setfetchMode(PDO::FETCH_CLASS, "Attendee");
            while($Attendee = $statement->fetch()){
                $res[] = $Attendee;
            }
            if (count($res) > 0){
                return true;
            }
            else{
                return false;
            }
        }

        function getEvents(){
            $res = [];
            $statement = $this->db_conn->prepare("SELECT * FROM event");
            $statement->execute();
            $statement->setfetchmode(PDO::FETCH_CLASS, "Event");
            while($Event = $statement->fetch()){
                $res[] = $Event;
            }
            return $res;
        }

        function getSessions(){
            $res = [];
            $statement = $this->db_conn->prepare("SELECT * FROM session");
            $statement->execute();
            $statement->setfetchmode(PDO::FETCH_CLASS, "Session");
            while($Session = $statement->fetch()){
                $res[] = $Session;
            }
            return $res;
        }

        function getLocation($idlocation){
            $res = [];
            $statement = $this->db_conn->prepare("SELECT name FROM venue WHERE idvenue = :idlocation");
            $statement->bindParam(":idlocation", $idlocation, PDO::PARAM_INT);
            $statement->execute();
            return $statement->fetch()["name"];
        }

        function getAvailableSpotsEvent($idevent, $total){
            $secondStmt = $this->db_conn->prepare("SELECT count(attendee) FROM attendee_event WHERE event = :idevent");
            $secondStmt->bindParam(":idevent", $idevent, PDO::PARAM_INT);
            $secondStmt->execute();
            $attending = $secondStmt->fetch()["count(attendee)"];
            return $total - $attending;
        }

        function getAvailableSpotsSession($idsession, $total){
            $secondStmt = $this->db_conn->prepare("SELECT count(attendee) FROM attendee_session WHERE session = :idsession");
            $secondStmt->bindParam(":idsession", $idsession, PDO::PARAM_INT);
            $secondStmt->execute();
            $attending = $secondStmt->fetch()["count(attendee)"];
            return $total - $attending;
        }

        function isRegisteredToEvent($idattendee, $idevent){
            $statement = $this->db_conn->prepare("SELECT count(*) FROM attendee_event WHERE attendee = :idattendee AND event = :idevent");
            $statement->bindParam(":idattendee", $idattendee, PDO::PARAM_INT);
            $statement->bindParam(":idevent", $idevent, PDO::PARAM_INT);
            $statement->execute();
            $amount = $statement->fetch()["count(*)"];

            if ($amount > 0){
                //he is registered
                return true;
            }
            else{
                //not registered
                return false;
            }
        }

        function isRegisteredToSession($idattendee, $idsession){
            $statement = $this->db_conn->prepare("SELECT count(*) FROM attendee_session WHERE attendee = :idattendee AND session = :idsession");
            $statement->bindParam(":idattendee", $idattendee, PDO::PARAM_INT);
            $statement->bindParam(":idsession", $idsession, PDO::PARAM_INT);
            $statement->execute();
            $amount = $statement->fetch()["count(*)"];

            if ($amount > 0){
                //he is registered
                return true;
            }
            else{
                //not registered
                return false;
            }
        }

        function insertAttendeeEvent($idattendee, $idevent){
            $statement = $this->db_conn->prepare("INSERT INTO attendee_event (event, attendee) VALUES (:idevent, :idattendee)");
            $statement->bindParam(":idevent", $idevent, PDO::PARAM_INT);
            $statement->bindParam(":idattendee", $idattendee, PDO::PARAM_INT);
            $statement->execute();
        }

        function insertAttendeeSession($idattendee, $idsession){
            $statement = $this->db_conn->prepare("INSERT INTO attendee_session (session, attendee) VALUES (:idsession, :idattendee)");
            $statement->bindParam(":idsession", $idsession, PDO::PARAM_INT);
            $statement->bindParam(":idattendee", $idattendee, PDO::PARAM_INT);
            $statement->execute();
        }

        function getRegisteredEvents($idattendee){
            $res = [];
            $statement = $this->db_conn->prepare("SELECT idevent, name, datestart, dateend, numberallowed, venue FROM attendee_event RIGHT JOIN event on attendee_event.event = event.idevent WHERE attendee_event.attendee = :idattendee");
            $statement->bindParam(":idattendee", $idattendee, PDO::PARAM_INT);
            $statement->execute();
            $statement->setfetchmode(PDO::FETCH_CLASS, "Event");
            while($Event = $statement->fetch()){
                $res[] = $Event;
            }
            return $res;
        }

        function getRegisteredSession($idattendee){
            $res = [];
            $statement = $this->db_conn->prepare("SELECT idsession, name, numberallowed, event, startdate, enddate FROM attendee_session RIGHT JOIN session ON attendee_session.session = session.idsession WHERE attendee_session.attendee = :idattendee");
            $statement->bindParam(":idattendee", $idattendee, PDO::PARAM_INT);
            $statement->execute();
            $statement->setfetchmode(PDO::FETCH_CLASS, "Session");
            while ($Session = $statement->fetch()){
                $res[] = $Session;
            }
            return $res;
        }

        function unattendEvent($idattendee, $idevent){
            $statement = $this->db_conn->prepare("DELETE FROM attendee_event WHERE event = :idevent AND attendee = :idattendee");
            $statement->bindParam(":idattendee", $idattendee, PDO::PARAM_INT);
            $statement->bindParam(":idevent", $idevent, PDO::PARAM_INT);
            $statement->execute();
        }

        function unattendSession($idattendee, $idsession){
            $statement = $this->db_conn->prepare("DELETE FROM attendee_session WHERE session = :idsession AND attendee = :idattendee");
            $statement->bindParam(":idattendee", $idattendee, PDO::PARAM_INT);
            $statement->bindParam(":idsession", $idsession, PDO::PARAM_INT);
            $statement->execute();
        }

        function paidEvent($idevent, $idattendee){
            $statement = $this->db_conn->prepare("SELECT paid FROM attendee_event WHERE event = :idevent AND attendee = :idattendee");
            $statement->bindParam(":idattendee", $idattendee, PDO::PARAM_INT);
            $statement->bindParam(":idevent", $idevent, PDO::PARAM_INT);
            $statement->execute();
            return $statement->fetch()['paid'];
        }
        
        function payEvent($idevent, $idattendee){
            $statement = $this->db_conn->prepare("UPDATE attendee_event SET paid = 1 WHERE event = :idevent AND attendee = :idattendee");
            $statement->bindParam(":idattendee", $idattendee, PDO::PARAM_INT);
            $statement->bindParam(":idevent", $idevent, PDO::PARAM_INT);
            $statement->execute();
        }

        function getManagerEvent($idattendee){
            $res = [];
            $statement = $this->db_conn->prepare("SELECT idevent, name, datestart, dateend, numberallowed, venue FROM manager_event RIGHT JOIN event on manager_event.event = event.idevent WHERE manager = :idattendee");
            $statement->bindParam(":idattendee", $idattendee, PDO::PARAM_INT);
            $statement->setfetchmode(PDO::FETCH_CLASS, "Event");
            $statement->execute();
            while ($Event = $statement->fetch()){
                $res[] = $Event;
            }
            return $res;
        }

        function getEventSessions($idevent){
            $res = [];
            $statement = $this->db_conn->prepare("SELECT * FROM session WHERE event = :idevent");
            $statement->bindParam(":idevent", $idevent, PDO::PARAM_INT);
            $statement->setfetchmode(PDO::FETCH_CLASS, "Session");
            $statement->execute();
            while($Session = $statement->fetch()){
                $res[] =  $Session;
            }
            return $res;
        }

        function getEventAttendees($idevent){
            $res = [];
            $statement = $this->db_conn->prepare("SELECT name, paid, idattendee FROM attendee_event RIGHT JOIN attendee ON attendee_event.attendee = attendee.idattendee WHERE event = :idevent");
            $statement->bindParam(":idevent", $idevent, PDO::PARAM_INT);
            $statement->execute(); 
            while( $x = $statement->fetch()){
                $res[] = $x;
            }
            return $res;
        }
        
        function getVenues(){
            $res = [];
            $statement = $this->db_conn->prepare("SELECT * FROM venue");
            $statement->execute();
            $statement->setfetchmode(PDO::FETCH_CLASS, "Venue");
            while( $venue = $statement->fetch()){
                $res[] = $venue;
            }
            return $res;
        }

        function addEvent($idattendee, $name, $datestart, $dateend, $numberallowed, $venue){
            $statement = $this->db_conn->prepare("INSERT INTO event (name, datestart, dateend, numberallowed, venue) VALUES (:name, :datestart, :dateend, :numberallowed, :venue)");
            $statement->bindParam(":name", $name, PDO::PARAM_STR);
            $statement->bindParam(":datestart", $datestart, PDO::PARAM_STR);
            $statement->bindParam(":dateend", $dateend, PDO::PARAM_STR);
            $statement->bindParam(":numberallowed", $numberallowed, PDO::PARAM_INT);
            $statement->bindParam(":venue", $venue, PDO::PARAM_INT);
            $statement->execute();
            
            $id = $this->db_conn->prepare("SELECT idevent FROM event ORDER BY idevent DESC LIMIT 1");
            $id->execute();
            $insertedid = $id->fetch()['idevent'];

            $stmt = $this->db_conn->prepare("INSERT INTO manager_event(event, manager) VALUES (:insertedid, :idattendee)");
            $stmt->bindParam(":insertedid", $insertedid, PDO::PARAM_INT);
            $stmt->bindParam(":idattendee", $idattendee, PDO::PARAM_INT);
            $stmt->execute();
        }

        function deleteEvent($idevent){
            $statement = $this->db_conn->prepare("DELETE FROM event WHERE idevent = :idevent");
            $statement->bindParam(":idevent", $idevent, PDO::PARAM_INT);
            $statement->execute();

            $stmt = $this->db_conn->prepare("DELETE FROM manager_event WHERE event = :idevent");
            $stmt->bindParam(":idevent", $idevent, PDO::PARAM_INT);
            $stmt->execute();

            $st = $this->db_conn->prepare("DELETE FROM attendee_event WHERE event = :idevent");
            $st->bindParam(":idevent", $idevent, PDO::PARAM_INT);
            $st->execute();

            $s = $this->db_conn->prepare("DELETE FROM session WHERE event = :idevent");
            $s->bindParam(":idevent", $idevent, PDO::PARAM_INT);
            $s->execute();
        }

        function deleteSession($idsession){
            $stmt = $this->db_conn->prepare("DELETE FROM session WHERE idsession = :idsession");
            $stmt->bindParam(":idsession", $idsession, PDO::PARAM_INT);
            $stmt->execute();
        }

        function addSession($idevent, $name, $numberallowed, $startdate, $enddate){
            $query  = "INSERT INTO session (name, numberallowed, event, startdate, enddate) VALUES ('{$name}', {$numberallowed}, {$idevent}, '{$startdate}', '{$enddate}')";
            $stmt = $this->db_conn->prepare($query);
            $stmt->execute();
        }

        function getEventById($idevent){
            $res = [];
            $stmt = $this->db_conn->prepare("SELECT * FROM event WHERE idevent = :idevent");
            $stmt->bindParam(":idevent", $idevent, PDO::PARAM_INT);
            $stmt->setfetchmode(PDO::FETCH_CLASS, "Event");
            $stmt->execute();
            while( $event = $stmt->fetch()){
                $res[] = $event;
            }
            return $res;
        }

        function editEvent($idevent, $name, $datestart, $dateend, $numberallowed, $venue){
            $stmt = $this->db_conn->prepare("UPDATE event SET name=:name, datestart=:datestart, dateend=:dateend, numberallowed=:numberallowed, venue=:venue WHERE idevent = :idevent");
            $stmt->bindParam(":idevent", $idevent, PDO::PARAM_INT);
            $stmt->bindParam(":name", $name, PDO::PARAM_STR);
            $stmt->bindParam(":datestart", $datestart, PDO::PARAM_STR);
            $stmt->bindParam(":dateend", $dateend, PDO::PARAM_STR);
            $stmt->bindParam(":numberallowed", $numberallowed, PDO::PARAM_INT);
            $stmt->bindParam(":venue", $venue, PDO::PARAM_INT);
            $stmt->execute();
        }

        function getSessionById($idsession){
            $res = [];
            $stmt = $this->db_conn->prepare("SELECT * FROM session WHERE idsession = :idsession");
            $stmt->bindParam(":idsession", $idsession, PDO::PARAM_INT);
            $stmt->setfetchmode(PDO::FETCH_CLASS, "session");
            $stmt->execute();
            while( $session = $stmt->fetch()){
                $res[] = $session;
            }
            return $res;
        }

        function editSession($idsession, $name, $datestart, $enddate, $numberallowed){
            $stmt = $this->db_conn->prepare("UPDATE session SET name=:name, startdate=:datestart, enddate=:enddate, numberallowed=:numberallowed WHERE idsession = :idsession");
            $stmt->bindParam(":idsession", $idsession, PDO::PARAM_INT);
            $stmt->bindParam(":name", $name, PDO::PARAM_STR);
            $stmt->bindParam(":datestart", $datestart, PDO::PARAM_STR);
            $stmt->bindParam(":enddate", $enddate, PDO::PARAM_STR);
            $stmt->bindParam(":numberallowed", $numberallowed, PDO::PARAM_INT);
            $stmt->execute();
        }

        function removeUserFromEvent($idevent, $idattendee){
            $stmt = $this->db_conn->prepare("DELETE FROM attendee_event WHERE event = :idevent AND attendee = :idattendee");
            $stmt->bindParam(":idevent", $idevent, PDO::PARAM_INT);
            $stmt->bindParam(":idattendee", $idattendee, PDO::PARAM_INT);
            $stmt->execute();
        }


        function getSessionAttendees($idsession){
            $res = [];
            $statement = $this->db_conn->prepare("SELECT name, idattendee FROM attendee_session RIGHT JOIN attendee ON attendee_session.attendee = attendee.idattendee WHERE session = :idsession");
            $statement->bindParam(":idsession", $idsession, PDO::PARAM_INT);
            $statement->execute(); 
            while( $x = $statement->fetch()){
                $res[] = $x;
            }
            return $res;
        }

        function removeUserFromSession($idsession, $idattendee){
            $stmt = $this->db_conn->prepare("DELETE FROM attendee_session WHERE session = :idsession AND attendee = :idattendee");
            $stmt->bindParam(":idsession", $idsession, PDO::PARAM_INT);
            $stmt->bindParam(":idattendee", $idattendee, PDO::PARAM_INT);
            $stmt->execute();
        }

        function getAllAttendees(){
            $res = [];
            $stmt = $this->db_conn->prepare("SELECT * FROM attendee");
            $stmt->execute();
            $stmt->setfetchmode(PDO::FETCH_CLASS, "Attendee");
            while( $attendee = $stmt->fetch()){
                $res[] = $attendee;
            }
            return $res;
        }

        function getAllVenues(){
            $res = [];
            $stmt = $this->db_conn->prepare("SELECT * FROM venue");
            $stmt->execute();
            $stmt->setfetchmode(PDO::FETCH_CLASS, "Venue");
            while( $venue = $stmt->fetch()){
                $res[] = $venue;
            }
            return $res;
        }

        function addVenue($name, $capacity){
            $stmt = $this->db_conn->prepare("INSERT INTO venue (name, capacity) VALUES ('{$name}', '{$capacity}')");
            $stmt->execute();
        }

        function deleteVenue($venueid){
            $stmt = $this->db_conn->prepare("DELETE FROM venue WHERE idvenue = :venueid");
            $stmt->bindParam(":venueid", $venueid, PDO::PARAM_INT);
            $stmt->execute();
        }

        function getVenueById($venueid){
            $res = [];
            $stmt = $this->db_conn->prepare("SELECT * FROM venue WHERE idvenue = :venueid");
            $stmt->bindParam(":venueid", $venueid, PDO::PARAM_INT);
            $stmt->execute();
            $stmt->setfetchmode(PDO::FETCH_CLASS, "Venue");
            while( $venue = $stmt->fetch()){
                $res[] = $venue;
            }
            return $res;
        }

        function editVenue($idvenue, $name, $capacity){
            $stmt = $this->db_conn->prepare("UPDATE venue SET name='{$name}', capacity={$capacity} WHERE idvenue = {$idvenue}");
            $stmt->execute();
        }
    }
?>