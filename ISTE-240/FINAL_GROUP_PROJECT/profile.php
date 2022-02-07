<!--[Pulls in the header part of the html from a php file]-->
<?php
    $path = './';
    $page = 'Profile';
    include $path.'assets/inc/header.php';
	require $path.'../../../dbConnect.inc';
    
    if ($_SESSION['login'] != true) {
            header("Location: login.php");
            exit();
        }
    echo "<div id='profile_wrapper'>";

    if ($mysqli) {
        $email = $_SESSION['email'];
        $sql = "SELECT quiz1Score, quiz2Score, quiz3Score, quiz4Score, quiz5Score FROM accountDb where userEmail = '$email'";
        $res = $mysqli -> query($sql);
        
        if ($res) {
            while ($rowHolder = mysqli_fetch_array($res, MYSQLI_ASSOC)) {
                $scores[] = $rowHolder;
            }
            
            echo '<table id="score_table">
                    <tr>
                        <th>Quiz Number</th>
                        <th>Quiz Score</th>
                    </tr>';
            for ($i = 1; $i <= 5; $i++) {
                echo '<tr>
                        <td>'.$i.'</td>
                        <td>'.$scores[0]["quiz".$i."Score"].'</td>
                        </tr>';
            }
            echo '</table>';
            
        }
        
    }
    echo "</div>";
?>
</body>
</html>