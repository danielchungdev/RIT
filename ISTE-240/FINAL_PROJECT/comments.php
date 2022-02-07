<?php
    include 'headerFiles/header.php';
    require './dbconnect.inc';
    if (isset($_POST['submit2'])){
        $name = $_POST["name"];
        $comment = $_POST["comment"];
        $sql = "INSERT INTO comments(username, comment) VALUES ('$name', \"$comment\")";
        
        if (mysqli_query($conn, $sql)){
            echo "";
        }
        else {
            echo "Error: ".$sql."<br>".mysqli_error($conn);
        }
    }
?>

<div class="wrapper">
    <div class="background-image">
        <img src="assets/images/panama.jpg" alt="A picture of Panama">
        <p class="background-title">COMMENTS</p>
    </div>
    <div class="section-body">
        <div class="comments">
            <h1>Add a Comment!</h1>
            <form class="comment-box" action="comments.php" method="post" name="comment" onsubmit="return commentValidation();">
                <p>Name:</p><input type="text" name="name" autocomplete="off">
                <p>Comments:</p><textarea name="comment" cols="30" rows="10"></textarea>
                <br>
                <input type="submit" class="button2" name="submit2">
            </form>
        </div>
        <div id="comment-section" class="comment-section">
            <!-- input all comments here  -->
        </div>
    </div>
</div>

<?php
    include 'headerFiles/footer.php';
?>