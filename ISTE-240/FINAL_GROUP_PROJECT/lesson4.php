<!--[Pulls in the header part of the html from a php file]-->
<?php
	$path = './';
	$page = 'Lesson 4';
    $quizDB = "l4Questions";
    $quizNum = "quiz4Score";
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
                <h1 class="lesson_header">Lesson 4</h1>
                <h2 class="lesson_title">Loops</h2>
                <p class="lesson_content">In Java there are three basic types of loops: for-loop, while loop, and do-while loop. The way these work are simple; they have a condition that stops them and code that is implemented until that condition is met or until the condition stops being true.</p>


<h2 class="lesson_title">For-loop:</h2>
<p class="lesson_content">For-loop conditions are based around a number that either increments or decrements every time the code inside the for-loop is run.</p>

<p class="lesson_content">Code:<br/>
&nbsp;for(int i = 0; i &lt; 4; i++){<br/>
	&nbsp;&nbsp;System.out.println(“i is equal to “ + i);<br/>
&nbsp;}</p>

<p class="lesson_content">Output:<br/>
&nbsp;i is equal to 0<br/>
&nbsp;i is equal to 1<br/>
&nbsp;i is equal to 2<br/>
&nbsp;i is equal to 3</p>

<h2 class="lesson_title">While-loop:</h2>
<p class="lesson_content">While loops work by running the code inside of it until the condition set stops being true.</p>

<p class="lesson_content">Code:<br/>
&nbsp;String example = “”;<br/><br/>

&nbsp;while(example.length() &lt; 4){<br/>
	&nbsp;&nbsp;example += “A”;<br/>
&nbsp;&nbsp;System.out.println(example);<br/>
&nbsp;}</p>

<p class="lesson_content">Output:<br/>
&nbsp;A<br/>
&nbsp;AA<br/>
&nbsp;AAA</p>

<h2 class="lesson_title">Do-while:</h2>
<p class="lesson_content">Do-while loops work by running the code inside of it at least once, and then stopping once the conditions in the while are no longer true.</p>

<p class="lesson_content">Code:<br/>
&nbsp;int i = 0;<br/>
&nbsp;do{<br/>
	&nbsp;&nbsp;System.out.println(“i is equal to “ + i);<br/>
&nbsp;}while(i > 1);</p>

<p class="lesson_content">Output:<br/>
&nbsp;i is equal to 0</p>

            </div>

            <div id="quiz">
                <h2 id="quiz_title">Quiz</h2>
                <form action="lesson4.php" onsubmit="return validate();" method="post">
                <?php

                    include $path."assets/inc/quiz.php";
                ?>
                </form>
            </div>
			<?php include("assets/inc/footer.php"); ?>
        </div>
    </body>
</html>
