function validateForm(){
    var email = document.forms['myForm']['email'].value;
    if (email == ""){
        alert("Email must be filled out.");
        return false;
    }
    else {
        return true;
    }
}

function commentValidation(){
    var name = document.forms['comment']['name'].value;
    var comment = document.forms['comment']['comment'].value;
    if(name == "" && comment == ""){
        alert("Name and comment must be filled out.");
        return false;
    }
    else if (name == ""){
        alert("Name must be filled out.");
        return false;
    }
    else if (comment == ""){
        alert("Comment must be filled out.");
        return false;
    }
    else {
        alert("Your comment was submitted. Thank you!");
        return true;
    }
}

function testing(){
    if (document.getElementById("comment-section")){
        document.getElementById("comment-section").innerHTML = "THIS IS WORKING?";
    }
}

function getComments(){
    if (document.getElementById("comment-section")){
        if (window.XMLHttpRequest) {
            xmlhttp=new XMLHttpRequest();
        } 
        else { 
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange=function() {
            // document.getElementById("comment-section").innerHTML = this.responseText;
            if (this.readyState==4 && this.status==200) {
                if(this.responseText != null){
                    var comment_big = document.getElementById("comment-section");
                    var r = JSON.parse(this.responseText);
                    for (var i = 0; i < r.length; i++){
                        if (r.length > 0){
                            var starting_div = document.createElement("div");
                            var name = document.createElement("h2");
                            var comment = document.createElement("p");

                            name.innerHTML = r[i].username;
                            comment.innerHTML = r[i].comment;
                            
                            starting_div.appendChild(name);
                            starting_div.appendChild(comment);
                            
                            comment_big.appendChild(starting_div);
                        }
                    }
                }
                else{
                    document.getElementById("comment-section").innerHTML = "Error";
                }
            }
        }
    xmlhttp.open("POST", "getComments.php", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send();
    }
}

function navSlide() {
    const burger = document.querySelector(".burger");
    const nav = document.querySelector(".nav-links");
    const navLinks = document.querySelectorAll(".nav-links li");
    
    burger.addEventListener("click", () => {
        //Toggle Nav
        nav.classList.toggle("nav-active");
        
        //Animate Links
        navLinks.forEach((link, index) => {
            if (link.style.animation) {
                link.style.animation = ""
            } else {
                link.style.animation = `navLinkFade 0.5s ease forwards ${index / 7 + 0.5}s`;
            }
        });
        //Burger Animation
        burger.classList.toggle("toggle");
    });
    
}

navSlide();