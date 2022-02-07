
<?php
    $path ='./';
    require $path.'../../../dbConnect.inc';
    // echo 'asdfjajsdfkjasdfh';
    // echo $_GET['lesson'];
    // echo $_GET['email'];
    // echo $_GET['comments'];
    // echo $_GET['experience'];
    // echo $_GET['satisfaction'];



    if(!empty($_POST['lesson[]']) && !empty($_POST['email']) && !empty($_POST['comments']) && !empty($_POST['experience']) && !empty($_POST['satisfaction'])){
           foreach($_POST['lesson'] as $lessonList){
        $lesson .= $lessonList;
    }
        //$lesson = $_POST['lesson'];
        $email = $_POST['email'];
        $comments = $_POST['comments'];
        $experience = $_POST['experience'];
        $satisfaction = $_POST['satisfaction'];

        $header = "From: JavaExpresso@edu.com";

        $email_subject = "Java Express-O Survey Feedback";
        $email_body = "Email: $email\n";
        $email_body .= "Lessons Taken: $lesson\n";
        $email_body .= "Experience: $experience\n";
        $email_body .= "Satisfaction: $satisfaction\n";
        $email_body .= "Comments: $comments\n";

        mail($email, $email_subject, $email_body, $header);
        $res = $mysqli-> query('select email from gEmails');

        if($res){
            //echo "working";
            while($rowholder = mysqli_fetch_array($res, MYSQLI_ASSOC)){
                $records[] = $rowholder;
            }
            if(mysqli_num_rows($res) > 0){
                foreach(array($records) as $this_row){
                    for($i = 0; $i <= 5;$i++){
                        mail($this_row[$i]['email'], $email_subject,$email_body);
                    }

                }
            }
        }
        else {
            //echo "not working";
        }

    }

?>

<!DOCTYPE html>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<title>Survey</title>
<link rel="stylesheet" href="assets/css/styles.css">
 </head>

<script>
    function validate(){
       if(boxesChecked() == false || textFilled() == false){

            console.log("hi");
            return false;

        }
        else{
            return true;
        }
    }

    function boxesChecked(){
        var numBox = document.getElementsByName("lesson[]").length;
        if(numBox <= 0 ){
            return false;
        }
        else{
            return true;
        }
    }

    function textFilled(){
        var nameField = document.getElementsByName("email");
        var commentField = document.getElementsByName("comments");
        var experienceField = document.getElementsByName("experience");
        var ratingField = document.getElementsByName("satisfaction");
        if(nameField == null || commentField == null || experienceField == null || ratingField == null){
           return false;
           }
        else{
           return true;
           }
    }
</script>

 <body>
     <div class="loginAndRegistration">
         <h2>Survey</h2>
          <form name="survey" onsubmit="return validate()" action = "<?php $_SERVER['PHP_SELF']; ?>" method="POST">
                <label for="email"><b>Enter your email</b></label><br />
                <input type="text" id="name" name="email" placeholder="email"><br /><br>

                <label for="experience"><b>What is your level of experience?:</b></label><br />
                <input type="text" name="experience" placeholder="level of experience">
                <br><br><br><br>


                <fieldset>

                    <legend style="color:var(--light-coffee);">
                        <b>What Lessons Did You Take?</b>
                    </legend>
                    <br>

                    <input type="checkbox" name="lesson[]" value="Loops "> Loops </input>
                    <input type="checkbox" name="lesson[]" value="Objects "> Objects </input>
                    <input type="checkbox" name="lesson[]" value="Prints "> Print Statements </input>
                    <input type="checkbox" name="lesson[]" value="Methods "> Methods </input>
                    <input type="checkbox" name="lesson[]" value="Variables "> Variables </input>

                    <br>

                </fieldset>
                <br><br><br>

                <label for="comment"><b>Anything else you want to tell us?</b></label><br />
                <input type="text" id="comment" name="comment" placeholder="thoughts"><br />
                <br><br>

              <label for="rating"><b>Please rate your satisfaction from 1-5 :</b></label><br />
              <input type="radio" name="rating" value="1"> 1
              <input type="radio" name="rating" value="2"> 2
              <input type="radio" name="rating" value="3"> 3
              <input type="radio" name="rating" value="4"> 4
              <input type="radio" name="rating" value="5"> 5

              <br>
              <br>
              <br>

              <input type="submit" name="submit">

              <br>
              <br>
              <br>
        </form>
     </div>
     <?php include("assets/inc/footer.php"); ?>
 </body>

</html>
