function checkDate(){
    var dateValid=true; 
    var vdate=document.getElementById('visitDate').value;
    var today=new Date();
    console.log("User Date = " + vdate);
    var dd = today.getDate();
    var mm = today.getMonth() + 1;
    var yyyy = today.getFullYear();
    
    if(dd < 10){
        dd='0'+dd; 
    }
    if (mm<10){
        mm='0'+mm;
    }
    
    var td = ""+yyyy+"-"+mm+"-"+dd;
    console.log("System date = "+td);
    
    if (td >= vdate){
        document.getElementById("errorMessage").innerHTML=" ** Warning you cannot select today or the days after. ** ";
        document.getElementById("visitDate").style.borderColor="red";
        document.getElementById("errorMessage").style.backgroundColor='pink';
        dateValid=false;
    }
    else {
        alert("THIS SUCKSSSSS");
        document.getElementById("errorMessage").innerHTML="";
        document.getElementById("visitDate").style.border = null;
        document.getElementById("errorMessage").style = null;
        dateValid = true;
    }
    
    return(dateValid);
}

function validateForm() {
        var isvalid = true;

        if (document.getElementById("visitDate").value == "") {
            document.getElementById("visitDate").style.borderColor = "red";
            document.getElementById("visitDate").style.backgroundColor = 'pink';
            isvalid = false;
        } else {
            document.getElementById("name").style = null;
            isvalid = true;
        }

		var dateValid = false;

		dateValid = checkDate();
		//alert("Please hold screen");
        return (isvalid && dateValid);

	}