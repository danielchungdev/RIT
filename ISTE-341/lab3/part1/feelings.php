<!DOCTYPE html>
<html>
        <head>
                <title>Feelings</title>
        </head>
        <style>
                label {
                        margin-left: 50px;
                }

                #error{
                        color: red;
                }
        </style>
        <body> 
                <form action='<?php echo $_SERVER['PHP_SELF']?>' method="post">
                        First Name: <input name='firstName'></input><br>
                        Last Name: <input name='lastName'></input><br>
                        Date: <input type="date" name='date'></input><br>
                        Comments: <textarea name='comment'></textarea><br>
                        Mood:<br> 
                        <label>
                                <input name='mood' type="radio" value="happy"> Happy
                        </label>
                        <br>
                        <label>
                                <input name='mood' type="radio"  value="mad"> Mad
                        </label>
                        <br>
                        <label>
                              <input name='mood' type="radio" value="indifferent"> Indifferent
                        </label>
                        <br>
                        <input type="reset"></input><input name="submit" type="submit"></input>
                </form>
                <?php
                       require('validation.php');

                        if (isset($_POST['submit'])){
                                if(isset($_POST['mood'])){
                                        if (checkEmpty($_POST['firstName'], $_POST['lastName'], $_POST['comment'], $_POST['mood'], $_POST['date'])){
                                                echo '<h3 id="error">**There are empty fields**</h3>';
                                        }
                                        elseif(checkComment($_POST['comment'])){
                                                echo '<h3 id="error">**Comment must be at least 10 letters long!**</h3>';
                                        }
                                        else{
                                                $moodSentence = dynamicSentence(sanitize($_POST['mood'])); 
                                                $firstName = sanitize($_POST['firstName']);
                                                $lastName = sanitize($_POST['lastName']);
                                                $comment = sanitize($_POST['comment']);
                                                $date = sanitize(dateToString($_POST['date'])); 
                                                echo '<hr class="rounded">';
                                                echo "<i>Today is $date</i>";
                                                echo "<p>Hello $firstName $lastName. $moodSentence</p>";
                                                echo "<p>Your comments: $comment</p>";
                                        }
                                }
                                else{
                                        echo '<h3 id="error">**There are empty fields**</h3>';
                                }
                        }
                ?>
        </body>
</html>
