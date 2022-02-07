function jsStyle() {
    // function to change style
    // Change the color and the size of the font
    // in the paragraph with id='text'
    document.getElementById("text").style.color = "green";
    document.getElementById("text").style.fontSize = "50px";
}

function init(){
    text = document.getElementById("text");
    text.style.position = "relative";
    text.style.left = "0px";
}

function jsMove(){
    text  = document.getElementById("text");
    text.style.left = parseInt(text.style.left) + 10 + "px";
}

function getFormValues() {
    // function to send first and last names
    // to an 'alert' message.
    var firstname = document.getElementById("fname").value;
    var secondname = document.getElementById("lname").value;
    var theString = firstname.concat(" ", secondname);
    console.log(theString);
    alert(theString);
}

function howmany(){
    var count = 0;
    var x = document.getElementById("regForm");
    var length = document.getElementsByTagName("input").length;
    for (var i = 0; i < length; i++){
        if (x[i].type == "text" && x[i].innerHTML != ""){
            count++;
        }
    }
    alert(count);
}

function colorchanger(){
    var color = document.getElementById("mySelect").value;
    document.getElementById("sixc").style.backgroundColor = color;
}

function getOptions() {
	// function to display the number of options in an alert()
	var numberOfOptions = document.getElementById("mySelect").length;
	alert(numberOfOptions);

}

function multiply(){
    var header = document.getElementById("result");
    if (header.hasChildNodes()){
        header.removeChild(header.firstChild);
    }
    var firstoperand = document.getElementById("firstoperand").value;
    var secondoperand = document.getElementById("secondoperand").value;
    var value = parseInt(firstoperand) * parseInt(secondoperand);
    var textnode = document.createTextNode(value.toString());
    header.appendChild(textnode);
}

function divide(){
    var header = document.getElementById("result");
    if (header.hasChildNodes()){
        header.removeChild(header.firstChild);
    }
    var firstoperand = document.getElementById("firstoperand").value;
    var secondoperand = document.getElementById("secondoperand").value;
    var value = parseInt(firstoperand) / parseInt(secondoperand);
    var textnode = document.createTextNode(value.toString());
    header.appendChild(textnode);
}

window.onload = init;
