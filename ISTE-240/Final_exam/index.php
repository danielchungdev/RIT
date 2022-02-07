<!-- code given to the student as a "STARTER FILE"  INDEX_STARTER_FILE  -->
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="styles.css">
<head>
	<meta charset="utf-8" />
<title>ISTE 240_01 Fall 2019 Final Exam</title>
<style>
#wrapper {
    margin-left:auto;
    margin-right:auto;
     width: 97vw;
 }
 header {
         width:97vw;
         height:6em;
         border: solid  1px;
         padding-bottom:10px;
         animation: slideInFromLeft 5s;
         }

    /***************************************  
     animation must be 3 seconds
     animation must be linear 
     animation must slideInFromLeft;
    ****************************************/
 
 @keyframes slideInFromLeft {
     0% {
         transform: translateX(-100%);
     }
 
     100% {
         transform: translateX(0);
     }
 }
 
 /*  https://www.w3schools.com/cssref/css3_pr_animation.asp  */
 /* CSS #2 */
 /* animate the header tag so it slides in from left to right on load of the page */
 /* animation should happen over 3 seconds, be linear, start at time 0 and only happen once */
 /* think CSS animation using @keyframes */
 
 #column1 {width:50vw;
           display: table-cell;
           height:46em;
           overflow: auto;
           border:solid 1px purple;
           }

#column1 p::first-letter {
    font-size: 200%;
    color: rgb(255, 0, 0);
  }


 #column2 {width:50vw;
           display: table-cell;
           height:46em;
           overflow: auto;
           border:solid 1px green;
           }
 
 .astric {color:Red;
          font-size:36px;
          position:relative;
          top:11px;}

.hide {
    display: none;
}
 
 @media only screen and (max-width: 600px) {
    body {
        background-color: green;
    }

    #column1 {
        display: block;
        width: 93vw;
    }
    #column2{
        display:block;
    }
 }
</style>
<script >
function formValidation(){
    let name = document.getElementById("name").value;
    let city = document.getElementById("city").value;
    let state = document.getElementById("state").value;
    let phone = document.getElementById("phone").value;

    if (name == ""){
        document.getElementById("name").style.borderColor = "red";
        document.getElementById("name").style.backgroundColor = "pink";
        return false;
    }
    else if (city == ""){
        document.getElementById("city").style.borderColor = "red";
        document.getElementById("city").style.backgroundColor = "pink";
        return false;
    }
    else if (state == ""){
        document.getElementById("state").style.borderColor = "red";
        document.getElementById("state").style.backgroundColor = "pink";
        return false;
    }
    else if (phone == ""){
        document.getElementById("phone").style.borderColor = "red";
        document.getElementById("phone").style.backgroundColor = "pink";
        return false;
    }
    else {
        return true;
    }
}</script>




</head>
<body>
    <div id="wrapper">

        <header>
            <h2>
                <?php require 'add_to_header.php';?>
            </h2>
        </header>

        <div id="column1">
            <?php 
                require 'display.php';
            ?>
        </div>
        <div id="column2">
        <form action="process.php" method="POST" onsubmit="return formValidation()">
                <span class="astric"> * </span> Means required fields<br />

                <p>
                    <span class="astric"> * </span> Enter College name: <input type="text" name="name" id="name">
                </p>

                <p>
                    <span class="astric"> * </span> What city is the college in: <input type="text" name="city" id="city">
                </p>

                <p>
                    <span class="astric"> * </span> What State is the college in: 
                </p>
                
                <select name="state" size="1" id="state">
                                        <option value="">Select state</option>
                                        <option value="AK">AK</option>
                                        <option value="AL">AL</option>
                                        <option value="AR">AR</option>
                                        <option value="AZ">AZ</option>
                                        <option value="CA">CA</option>
                                        <option value="CO">CO</option>
                                        <option value="CT">CT</option>
                                        <option value="DC">DC</option>
                                        <option value="DE">DE</option>
                                        <option value="FL">FL</option>
                                        <option value="GA">GA</option>
                                        <option value="HI">HI</option>
                                        <option value="IA">IA</option>
                                        <option value="ID">ID</option>
                                        <option value="IL">IL</option>
                                        <option value="IN">IN</option>
                                        <option value="KS">KS</option>
                                        <option value="KY">KY</option>
                                        <option value="LA">LA</option>
                                        <option value="MA">MA</option>
                                        <option value="MD">MD</option>
                                        <option value="ME">ME</option>
                                        <option value="MI">MI</option>
                                        <option value="MN">MN</option>
                                        <option value="MO">MO</option>
                                        <option value="MS">MS</option>
                                        <option value="MT">MT</option>
                                        <option value="NC">NC</option>
                                        <option value="ND">ND</option>
                                        <option value="NE">NE</option>
                                        <option value="NH">NH</option>
                                        <option value="NJ">NJ</option>
                                        <option value="NM">NM</option>
                                        <option value="NV">NV</option>
                                        <option value="NY">NY</option>
                                        <option value="OH">OH</option>
                                        <option value="OK">OK</option>
                                        <option value="OR">OR</option>
                                        <option value="PA">PA</option>
                                        <option value="RI">RI</option>
                                        <option value="SC">SC</option>
                                        <option value="SD">SD</option>
                                        <option value="TN">TN</option>
                                        <option value="TX">TX</option>
                                        <option value="UT">UT</option>
                                        <option value="VA">VA</option>
                                        <option value="VT">VT</option>
                                        <option value="WA">WA</option>
                                        <option value="WI">WI</option>
                                        <option value="WV">WV</option>
                                        <option value="WY">WY</option>
                                    </select><span class="astric"> * </span>
                            
                
                
                
                <p>
                    <span class="astric"> * </span> What is the phone number: <input type="text" name="phone" id="phone">
                </p>

                <input type="submit">
        </form>


        </div><!-- end of column2 -->
    </div><!-- end of wrapper -->
</body>
</html>
