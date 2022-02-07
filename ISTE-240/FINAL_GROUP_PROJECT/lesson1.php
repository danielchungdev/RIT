<!--[Pulls in the header part of the html from a php file]-->
<?php
	$path = './';
	$page = 'Lesson 1';
    $quizDB = "l1Questions";
    $quizNum = "quiz1Score";
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
        for ($i = 1; $i < 3; $i++) {
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


                $score = 0.0;
                foreach ($_POST as $response) {
                    if ($response == "correct") {
                        $score++;
                    }
                }
                $score = ($score / 3) * 100.0;

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
                <h1 class="lesson_header">Lesson 1</h1>
                <h2 class="lesson_title">Variables and Types</h2>
                <p class="lesson_content">Let’s assume you know nothing about Java and want to learn, and for those that do know, just skip ahead a few paragraphs. So we’re going to start at the very beginning, not Big Bang beginning, but at the start of this section.</p>

<h2 class="lesson_title">What is Java?</h2>

<p class="lesson_content">Java is the most popular programming language, and more than 3 billion devices run Java. That’s slightly less than a device per two people in the entire world, I think. It’s used for mobile apps, desktop apps, web applications, games, database connections, and pretty much anything you can think of. So why us it?</p>

<p class="lesson_content">It works on different platforms, from Windows, Mac, Linux, Raspberry Pi, Android, etc. It’s easy to learn and simple to use on top of being open-source and free. On top of all of that, Java is almost exactly the same language as C++ and C# (give or take a few differences) so it makes it easy to switch between them when you do eventually go and learn them.</p>

<p class="lesson_content">Or maybe you won’t. We’re not in charge of you.</p>

<h2 class="lesson_title">How to Get Started</h2>

<p class="lesson_content">Some PCs already have Java, but in our experience, it’s always good to check first BEFORE you start playing around. Throw this command into your Command Prompt (we’re assuming Windows ‘cause if you’re in Linux then you’re ahead of us):</p>

<p class="lesson_content">C:\Users\Your Name>java -version</p>

<p class="lesson_content">If you do have it installed, you’ll get a nice looking list of stuff! If not, then go and download it from oracle.com. They should have some instructions lying about somewhere.</p>

<p class="lesson_content">Hopefully.</p>

<ol>
	<li class="content_list">Go to "System Properties" (Can be found on Control Panel > System and Security > System > Advanced System Settings)</li>
	<li class="content_list">Click on the "Environment variables" button under the "Advanced" tab</li>
	<li class="content_list">Then, select the "Path" variable in System variables and click on the "Edit" button</li>
	<li class="content_list">Click on the "New" button and add the path where Java is installed, followed by <span class=”bold”>\bin</span>. By default, Java is installed in C:\Program Files\Java\jdk-11.0.1 (If nothing else was specified when you installed it). In that case, You will have to add a new path with: <span class=”bold”>C:\Program Files\Java\jdk-11.0.1\bin</span></li>
	<li class="content_list">Then, click "OK", and save the settings</li>
	<li class="content_list">At last, open Command Prompt (cmd.exe) and type <span class=”bold”>java -version</span> to see if Java is running on your machine</li>
</ol>

<h2 class="lesson_title">Now What?</h2>

<p class="lesson_content">Now you can code!</p>

<p class="lesson_content">Every application (program, script, file) begins with a class name and that class has has to match the filename or the compiler will implode. Not literally, but it’ll yell at you and then you’ll scratch your head in confusion or yell at it so we’re trying to skip that entirely.</p>

<p class="lesson_content">Crack open your favorite text editor (personally, we recommend Atom or Visual Studio). If your favorite text editor is Notepad, then please leave as we don’t deal with it and will never touch it… ever.</p>

<p class="lesson_content">You monster.</p>

<p class="lesson_content">Copy this text, or write it out to gain some vital finger memory for later, into your new file.</p>

<p class="lesson_content">public class MyClass {<br/>
  &nbsp;public static void main(String[] args) {<br/>
    &nbsp;&nbsp;System.out.println("Hello World");<br/>
  &nbsp;}<br/>
}</p>

<p class="lesson_content">This all might look weird and odd and you’re probably trying to figure out what ‘public static void main’ is but don’t worry. We want to teach you how to run the code in the first place. Save this file as ‘MyClass.java’ and put it where you want it (preferably somewhere easy to find) and open your command prompt. Navigate to the folder/directory where this file is stored using the ‘dir’ and ‘cd’ commands.</p>

<p class="lesson_content">This command line below is your life now.</p>

<p class="lesson_content">C:\Users\Your Name>javac MyClass.java</p>

<p class="lesson_content">This will compile your code since Java requires a compiler to run. Basically, a compiler is the translator between what you write and what Java can read. If there are no errors, then you can now run the file you just created.</p>

<p class="lesson_content">C:\Users\Your Name>java MyClass</p>

<p class="lesson_content">And you’ll get a simple output</p>

<p class="lesson_content">Hello World</p>

<h2 class="lesson_title">Moving On</h2>

<p class="lesson_content">Important notes: Java is a case sensitive language. ‘MyClass’ and ‘myclass’ are not the same things. Op top of this, every line of code must be inside the class. In the above example, we put all the code inside the class MyClass and had nothing outside those brackets.</p>

<p class="lesson_content">The long name we wrote out earlier, the ‘public static void main(String[] args)’ is how the file will run. Whatever is inside these brackets will be run, and only that. It also accepts arguments directly from the command line, but we’ll get to that later.</p>

<p class="lesson_content">That ‘System.out.println("Hello World");’ uses the println() method (&lt;- also later) and you guessed it, it prints whatever is between its parentheses. Use it to test whether things are working.</p>

<h2 class="lesson_title">Comments</h2>

<p class="lesson_content">Comments are used to explain the Java and should (read must) be written while you’re coding to explain what the hell your code does to another person, or in most cases, a later version of you. You don’t want to open up a file with no comments and wonder what in the nine circles of hell you wrote a few weeks ago.</p>

<p class="lesson_content">Single comments are started with //</p>

<p class="lesson_content">Multi-line comments start with /* and end with */</p>

<h2 class="lesson_title">Variables</h2>

<p class="lesson_content">Variables are containers for storing data values. That’s the simplest definition we can make of it. In Java, there are a number of different types of variables:</p>
<ul><li class="content_list">String - stores text, enclosed in double quotes (“Hello World!”)</li>
<li class="content_list">Int - stores whole numbers (integers)</li>
<li class="content_list">Float - stores decimal numbers</li>
<li class="content_list">Char - stores a single character</li>
<li class="content_list">Boolean - stores either true or false</li></ul>

<p class="lesson_content">NOTE: one of these starts uppercase. We’ll touch on this in the next section.</p>

<p class="lesson_content">So how do you create a variable?</p>

<p class="lesson_content">Simple.</p>

<p class="lesson_content">Type variable = value;</p>

<p class="lesson_content">String name = “Jacob”;<br/>
int aNumber = 39;</p>

<p class="lesson_content">You can also create an empty variable and assign it later.</p>

<p class="lesson_content">int aNumber;<br/>
aNumber = 15;</p>

<p class="lesson_content">You can also overwrite previous values.</p>

<p class="lesson_content">int aNumber = 39;<br/>
aNumber = 41;</p>

<p class="lesson_content">Final variables are those that are read-only (they can’t be written over). They will be unchangeable and always remain the same as when created (instantiated).</p>

<p class="lesson_content">final int aNumber = 39;</p>

<p class="lesson_content">If you want to see what these variables are assigned to, use the println() method. We will use the ‘aNumber’ variable from above.</p>

<p class="lesson_content">System.out.println(“The number is: ” + aNumber);</p>

<p class="lesson_content">Note something interesting here. We combined a String with a variable by appending the two using the + symbol. This + symbol can also add variables to one another, such as combining two Strings or two ints. Try this out and see what happens.</p>

<p class="lesson_content">When naming these variables, this part should really be at the top, make sure they have unique names (identifiers). They can be short like (age, sum, weight), but make them descriptive to make it easily readable in the future. You’re not going to remember what ‘erstwhile12genie’ is because that’s a bunch of nonsense, despite it being unique.</p>

<p class="lesson_content">They should always start with a lowercase letter. Thought you should know that too.</p>

<h2 class="lesson_title">Data Types</h2>

<p class="lesson_content">Now that we’ve gone over the basics of variables, let’s five right done into the types of variables. Each variable must be a specified data value as we showed with the examples above.</p>

<p class="lesson_content">Primitive data types of those that start with a lowercase letter, such as int and char. There are only eight of the within Java: byte, short, int, long, float, double, boolean, and char. The most commonly used ones are int, double, boolean, and char, so we’ll go over those.</p>

<p class="lesson_content">To store a whole number, positive and negative, use int.<br/>
To store decimal numbers, use double.<br/>
To store true or false, use boolean.<br/>
To store a single character, use char.</p>

<p class="lesson_content">Non-primitive data types are those that start with an uppercase letter, such as String. These variables point to objects and can also be referred to as reference types. The main difference is the primitive types are defined in Java, and non-primitives are user-defined (except for String).</p>

            </div>

            <div id="quiz">
                <h2 id="quiz_title">Quiz</h2>
                <form action="lesson1.php" onsubmit="return validate();" method="post">
                <?php

                    include $path."assets/inc/quiz.php";
                ?>
                </form>
            </div>
		    <?php include("assets/inc/footer.php"); ?>
        </div>
    </body>
</html>
