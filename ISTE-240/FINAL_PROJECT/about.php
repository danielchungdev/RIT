<?php
    include 'headerFiles/header.php';
?>

<div class="wrapper">
    <div class="background-image">
        <img src="assets/images/me.JPG" alt="A picture of me">
        <p class="background-title">ABOUT</p>
    </div>

    <div class="section-body">
        <div class="introduction-paragraph">
            <p>I'm Daniel Chung and I developed this website. I'm currently a second year Web and Mobile Computing Major with a minor in Computer Science. I decided to go with Panama, because this is the place where I was born so I'm really familiar to it. In Panama I've made the most important memories and friends in my life and as soon as I heared about this project the first thing that came into my mind was "I have to make it about Panama" there was no other choice. While working on this project, I've remembered a lot of my memories from back home. I hope that everyone enjoys this website as much as I did making it.</p>
            <p>I can be contacted through email (dec8768@g.rit.edu) or phone (5857667536)</p>
            <p>Please feel free to fill out the following survey:</p>
        </div>
        <div class="form-wrapper">
            <form class="form-body" action="thankyou.php" onsubmit="return validateForm()" method="post" name="myForm">
                <h1 class="form-title">Please tell us your experience</h1>
                <ul class="form-paragraph">
                    <li><span >Enter your email: </span><input type="text" id="email" name="email" autocomplete="off"></li>
                    <li><span>When do you plan to visit? </span><input type="date" name="date" id="date" value="2018-07-22"></li>
                    <li><span>What place would you visit? </span><input type="text" id="where" name="where" autocomplete="off"></li>
                    <li>Please rate your experience:</li>
                    <li><input type="range" name="experience" min="0" max="100"></li>
                    <li><input type="submit" class="button2"></li> 
                </ul>
            </form>
        </div>
    </div>
</div>
<?php
include 'headerFiles/footer.php';
?>