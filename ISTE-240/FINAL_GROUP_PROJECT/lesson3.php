<!--[Pulls in the header part of the html from a php file]-->
<?php
	$path = './';
	$page = 'Lesson 3';
    $quizDB = "l3Questions";
    $quizNum = "quiz3Score";
	include $path.'assets/inc/header.php';
	require $path.'../../../dbConnect.inc';

    if ($mysqli) {


        $sql = "SELECT question, c1, c2, c3, answer FROM $quizDB";
        $res = $mysqli -> query($sql);
        if ($res) {
            while ($rowHolder = mysqli_fetch_array($res, MYSQLI_NUM)) {
                $quiz[] = $rowHolder;
            }
        }

        $nums[0] = rand(0,5);
        for($i = 1; $i < 3; $i++) {
            $n = rand(0,5);
            while (in_array($n, $nums)) {
                $n = rand(0,5);
            }
            $nums[$i] = $n;
        }

        foreach ($nums as $n) {
            $questions[] = $quiz[$n];
        }

        if (isset($_POST["a1"]) && isset($_POST["a2"]) && isset($_POST["a3"])) {

            $score = 0;
            foreach ($_POST as $response) {
                if ($response == "correct") {
                    $score++;
                }
            }
            $score = ($score / 3) * 100;

            if(isset($_SESSION['email'])) {
            $email = $_SESSION['email'];
                    $sql = "UPDATE accountDb SET $quizNum = $score where userEmail = '$email'";
                    $mysqli -> query($sql);
            }



        }

    }

?>
<!--[body tag is already open]-->

            <div id="content">
                <h1 class="lesson_header">Lesson 3</h1>
                <h2 class="lesson_title">Methods</h2>
                <p class="lesson_content">Methods are  functions if you have ever used another coding language. Methods are a block of code which is meant to run and provide a certain output. The output can vary depending on the return type.</p>


<p class="lesson_content">Example:<br/><br/>
public static void main(String[] args) {<br/>
	System.out.println(count()+””);<br/>
}<br/>
public int count(){<br/>
	int index =1;<br/>
	return index;<br/>
}<br/><br/>
Output: 1</p>

<p class="lesson_content">Methods can also take in arguments and then execute some action whether that be checking for a value or modifying the argument. Methods can be executed more than once so if needed you can use methods to validate or modify the data repeatedly.</p>

<p class="lesson_content">Example:<br/><br/>
public static void main(String[] args) {<br/>
	int index=1;<br/>
	index=count(index);<br/>
	System.out.println(index);<br/>
}<br/>
public int count(int i){<br/>
	i=i++;<br/>
}<br/><br/>
Output:2</p>

<p class="lesson_content">When creating a java file you are required to make a main method. This method can take in arguments from the command line. The main method is denoted like this:<br/><br/>

public static void main(String[] args) { }</p>

            </div>

            <div id="quiz">
                <h2 id="quiz_title">Quiz</h2>
                <form action="lesson3.php" onsubmit="return validate();" method="post">
                <?php

                    include $path."assets/inc/quiz.php";
                ?>
                </form>
            </div>
						<?php include("assets/inc/footer.php"); ?>
        </div>
    </body>
</html>
