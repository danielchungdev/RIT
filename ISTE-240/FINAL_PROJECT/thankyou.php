<?php
    include './headerFiles/header.php';
    require 'dbconnect.inc';
    if(isset($_POST["email"])){
        $email = $_POST['email'];
        $date = $_POST['date'];
        $place = $_POST['where'];
        $experience = $_POST['experience'];

        $sql = "INSERT INTO survey(email, date, place, experience) VALUES ('$email', $date, \"$place\", $experience)";

        if (mysqli_query($conn, $sql)){
            echo "";
        }
        else {
            echo "Error: ".$sql."<br>".mysqli_error($conn);
        }

        $subject = "Thank you!";
        $message = "<h1>THANK YOU!</h1><p>Thank you for filling out the survey. We hope you are more interested in Panama.</p>";
        $headers = "From: dec8768@g.rit.edu\r\n";
        $headers .= "Reply-To: dec8768@g.rit.edu\r\n";
        $headers .= "Content-type: text/html\r\n";
        mail($email, $subject, $message, $headers);
    }  

?>

<div class="wrapper">
    <div class="background-image">
        <img src="assets/images/panamacanal.jpg" alt="A pciture of the panama canal">
        <p class="background-title">THANK YOU!</p>
    </div>

    <div class="section-body">
        <div class="introduction-paragraph">
            <p>Thank you for completing the survey. We hope that you enjoyed the website as much as we enjoyed making it.</p>
        </div>
        <a href="index" class="button3">Link Button</a>

    </div>
</div>

<?php
    include './headerFiles/footer.php'
?>