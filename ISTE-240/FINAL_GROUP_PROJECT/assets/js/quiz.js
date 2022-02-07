/* validate that each quiz question has been answered */
function validate() {
    "use strict";
    var valid1 = false;
    var valid2 = false;
    var valid3 = false;
    
    var q1 = document.getElementsByName("a1");
    var q2 = document.getElementsByName("a2");
    var q3 = document.getElementsByName("a3");
    
    for (var i = 0; i < q1.length; i++) {
        if (q1[i].checked) {
            valid1 = true;
        }
    }
    for (var i = 0; i < q2.length; i++) {
        if (q2[i].checked) {
            valid2 = true;
        }
    }
    for (var i = 0; i < q3.length; i++) {
        if (q3[i].checked) {
            valid3 = true;
        }
    }
    
    console.log(valid1 + " " + valid2 + " " + valid3);
    return (valid1 && valid2 && valid3);
}