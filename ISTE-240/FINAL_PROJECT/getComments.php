<?php
    require './dbconnect.inc';
    $sql = "SELECT DISTINCT * FROM comments";

    $result= mysqli_query($conn, $sql);

    if ($result == FALSE)
        die(FormatErrors(sqlsrv_errors()));
    else{
        $data = [];
        $array = array();
        while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
            $temp = array();
            $temp['username'] = $row['username'];
            $temp['comment'] = $row['comment'];

            $array[] = $temp;
        }
    echo(json_encode($array));
    }
?>