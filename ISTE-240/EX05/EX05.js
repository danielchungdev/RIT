function jsStyle() {
    // function to change style
    // Change the color and the size of the font
    // in the paragraph with id='text'
    document.getElementById("text").style.color = "green";
    document.getElementById("text").style.fontSize = "50px";
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

function getOptions() {
	// function to display the number of options in an alert()
	var numberOfOptions = document.getElementById("mySelect").length;
	alert(numberOfOptions);

}

function multiply(){
    var firstoperand = document.getElementById("firstoperand").value;
    var secondoperand = document.getElementById("secondoperand").value;
    document.getElementById("result").innerHTML = parseInt(firstoperand) * parseInt(secondoperand);
}

function divide(){
    var firstoperand = document.getElementById("firstoperand").value;
    var secondoperand = document.getElementById("secondoperand").value;
    document.getElementById("result").innerHTML = parseInt(firstoperand) / parseInt(secondoperand);
}
