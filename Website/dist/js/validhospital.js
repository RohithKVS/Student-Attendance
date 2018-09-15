	function validate()
	{
	var hospname = document.forms["hospreg"]["hospname"];
	var regno = document.forms["hospreg"]["regno"];
	var pass = document.forms["hospreg"]["pass"];
	var confpass = document.forms["hospreg"]["confpass"];
	var are = document.forms["hospreg"]["are"];
	var district = document.forms["hospreg"]["district"];
	var pincode = document.forms["hospreg"]["pincode"];
	var tele = document.forms["hospreg"]["tele"];
	var email = document.forms["hospreg"]["email"];
	
	var hospname_required = document.getElementById("hospname_required");
	var regno_required = document.getElementById("regno_required");
	var pass_required = document.getElementById("pass_required");
	var confpass_required = document.getElementById("confpass_required");
	var are_required = document.getElementById("are_required");
	var district_required = document.getElementById("district_required");
	var pincode_required = document.getElementById("pincode_required");
	var tele_required = document.getElementById("tele_required");
	var email_required = document.getElementById("email_required");
	
	hospname.addEventListener("blur", hospnameVerify, true);
	regno.addEventListener("blur", regnoVerify, true);
	pass.addEventListener("blur", passVerify, true);
	confpass.addEventListener("blur", confpassVerify, true);
	are.addEventListener("blur", areVerify, true);
	district.addEventListener("blur", districtVerify, true);
	pincode.addEventListener("blur", pincodeVerify, true);
	tele.addEventListener("blur", teleVerify, true);
	email.addEventListener("blur", emailVerify, true);
	
	// VALIDATE USERNAME
		if(hospname.value == ""){
			hospname_required.textContent = "Hospital Name is required";
			hospname.style.border = "1px solid red";
			hospname.focus();
			return false;
		}
		if(regno.value == ""){
			regno_required.textContent = "Registration No. is required";
			regno.style.border = "1px solid red";
			regno.focus();
			return false;
		}		
		// PASSWORD REQUIRED
		if (pass.value == "" || confpass.value == "") {
			pass_required.textContent = "Password is required";
			pass.style.border = "1px solid red";
			confpass.style.border = "1px solid red";
			pass.focus();
			return false;
		}
		// VALIDATE PASSWORD
		if (pass.value != confpass.value) {
			confpass_required.textContent = "The two passwords do not match";
			pass.style.border = "1px solid red";
			confpass.style.border = "1px solid red";
			pass.focus();
			return false;
		}	
		if(are.value == ""){
			are_required.textContent = "Area is required";
			are.style.border = "1px solid red";
			are.focus();
			return false;
		}	 
		if(district.value == "Select"){
			district_required.textContent = "District is required";
			district.style.border = "1px solid red";
			district.focus();
			return false;
		}
		if(pincode.value == ""){
			pincode_required.textContent = "Pincode is required";
			pincode.style.border = "1px solid red";
			pincode.focus();
			return false;
		}	
		if(tele.value == ""){
			tele_required.textContent = "Telephone No. is required";
			tele.style.border = "1px solid red";
			tele.focus();
			return false;
		}
		if(email.value == ""){
			email_required.textContent = "Email Id is required";
			email.style.border = "1px solid red";
			email.focus();
			return false;
		}
		
	}
	function hospnameVerify(){
		var hospname = document.forms["hospreg"]["hospname"];
		if (hospname.value != "") {
			hospname_required.innerHTML = "";
			hospname.style.border = "1px solid #DCDCDC";
			return true;
		}
	}
	function regnoVerify(){
		var regno = document.forms["hospreg"]["regno"];
		if (regno.value != "") {
			regno_required.innerHTML = "";
			regno.style.border = "1px solid #DCDCDC";
			return true;
		}
	}
	function passVerify(){
		var pass = document.forms["hospreg"]["pass"];
		if (pass.value != "") {
			pass_required.innerHTML = "";
			pass.style.border = "1px solid #DCDCDC";
			return true;
		
		}
	}	
	function confpassVerify(){
		var confpass = document.forms["hospreg"]["confpass"];
		if (confpass.value != "") {
			confpass_required.innerHTML = "";
			confpass.style.border = "1px solid #DCDCDC";
			return true;
		}
	}	
	function areVerify(){
		var are = document.forms["hospreg"]["are"];
		if (are.value != "") {
			are_required.innerHTML = "";
			are.style.border = "1px solid #DCDCDC";
			return true;
		}
	}	
	function districtVerify(){
		var district = document.forms["hospreg"]["district"];
		if (district.value != "Select") {
			district_required.innerHTML = "";
			district.style.border = "1px solid #DCDCDC";
			return true;
		}
	}		
	function pincodeVerify(){
		var pincode = document.forms["hospreg"]["pincode"];
		if (pincode.value != "") {
			pincode_required.innerHTML = "";
			pincode.style.border = "1px solid #DCDCDC";
			return true;
		}
	}	
	function teleVerify(){
		var tele = document.forms["hospreg"]["tele"];
		if (tele.value != "") {
			tele_required.innerHTML = "";
			tele.style.border = "1px solid #DCDCDC";
			return true;
		}
	}
	function emailVerify(){
		var email = document.forms["hospreg"]["email"];
		if (email.value != "") {
			email_required.innerHTML = "";
			email.style.border = "1px solid #DCDCDC";
			return true;
		}
	}
	
function sync() {
    var n1 = document.getElementById('regno');
    var n2 = document.getElementById('uname');
    n2.value = n1.value;
}

function valu() {
    var n1 = document.getElementById('regno');
    return n1;
}
