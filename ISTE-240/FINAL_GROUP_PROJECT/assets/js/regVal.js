function formval(){
          var sEmail= document.forms["regForm"]["email"].value; 
          var sPass = document.forms["regForm"]["psw"].value; 
          var sPass2 = document.forms["regForm"]["pswR"].value; 
        var check=0;
        if(sEmail == ""){
            console.log("no email");
            check=1;
        }
        if(sPass==""){
            check=1;
            console.log("no password")
        }
    
        if(check==1){
            
            return false;
        }
        return true;
}