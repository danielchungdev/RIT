<!--[Pulls in the header part of the html from a php file]-->
<?php
	$path = './';
	$page = 'Lesson 2';
    $quizDB = "l2Questions";
    $quizNum = "quiz2Score";
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
                <h1 class="lesson_header">Lesson 2</h1>
                <h2 class="lesson_title">Print Statements</h2>
                <p class="lesson_content">In Java, a print statement can be an incredibly valuable tool. First and foremost, print statements allow for the Java program to communicate with the user. A print statement can inform the user about the program’s purpose and processes, prompt the user to input data, and output data for the user to see or interact with. But print statements are not solely for the benefit of the user. Internally, print statements make a great asset for debugging. When incorporated into a program at frequent intervals, print statements can be used to report information about the program to the developer. For example, the developer can double check that the value of a variable is in fact the correct value. When used often, these print statements can be used to catch bugs quickly and pinpoint the exact moment when something has gone wrong.</p>

<p class="lesson_content">Print statements are written in the following formats:</p>

	<p class="lesson_content">&nbsp;&nbsp;System.out.print(argument);<br/>
	&nbsp;&nbsp;System.out.println(argument);</p>

<p class="lesson_content">Where the argument is the String to be printed. When printing an existing variable, the name of the variable must be used as the argument. When printing static text, the text must be enclosed in either double or single quotation marks. The “println” statement concatenates a carriage return to the end of the argument, while the standard print statement outputs the String as is. For example:</p>

	<p class="lesson_content">&nbsp;&nbsp;System.out.print(“one”);<br/>
&nbsp;&nbsp;System.out.print(“two”);<br/>
&nbsp;&nbsp;System.out.print(“three”);<br/><br/>

	&nbsp;&nbsp;-> onetwothree<br/><br/>

	&nbsp;&nbsp;System.out.println(‘one’);<br/>
	&nbsp;&nbsp;System.out.println(‘two’);<br/>
	&nbsp;&nbsp;System.out.println(‘three’);<br/><br/>

	&nbsp;&nbsp;-> one<br/>
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;two<br/>
	     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;three</p>

<p class="lesson_content">Print statements can also print variables directly by automatically converting them into Strings, as well as perform concatenation within the argument itself:</p>

	<p class="lesson_content">&nbsp;&nbsp;int x = 10;<br/>
	&nbsp;&nbsp;String s = “hello world!”;<br/>
	&nbsp;&nbsp;boolean b = true;<br/>
	&nbsp;&nbsp;System.out.print(x + “ “ + s + “ “ + b);<br/><br/>

	&nbsp;&nbsp;-> 10 hello world! true</p>

            </div>

            <div id="quiz">
                <h2 id="quiz_title">Quiz</h2>
                <form action="lesson2.php" onsubmit="return validate();" method="post">
                <?php

                    include $path."assets/inc/quiz.php";
                ?>
                </form>
            </div>
						<?php include("assets/inc/footer.php"); ?>
        </div>
    </body>
</html>
