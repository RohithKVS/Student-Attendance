	function Validate()
	{
	var fname = document.forms["donreg2"]["fname"];
	var lname = document.forms["donreg2"]["lname"];
	var hno = document.forms["donreg2"]["hno"];
	var strname = document.forms["donreg2"]["strname"];
	var are = document.forms["donreg2"]["are"];
	var district = document.forms["donreg2"]["district"];
	var pincode = document.forms["donreg2"]["pincode"];
	var mob = document.forms["donreg2"]["mob"];
	
	
	var fname_required = document.getElementById("fname_required");
	var lname_required = document.getElementById("lname_required");
	var hno_required = document.getElementById("hno_required");
	var strname_required = document.getElementById("strname_required");
	var are_required = document.getElementById("are_required");
	var district_required = document.getElementById("district_required");
	var pincode_required = document.getElementById("pincode_required");
	var mob_required = document.getElementById("mob_required");
	
	fname.addEventListener("blur", fnameVerify, true);
	lname.addEventListener("blur", lnameVerify, true);
	hno.addEventListener("blur", hnoVerify, true);
	strname.addEventListener("blur", strnameVerify, true);
	are.addEventListener("blur", areVerify, true);
	district.addEventListener("blur", districtVerify, true);
	pincode.addEventListener("blur", pincodeVerify, true);
	mob.addEventListener("blur", mobVerify, true);
	
	// VALIDATE USERNAME
		if(fname.value == ""){
			fname_required.textContent = "First Name is required";
			fname.style.border = "1px solid red";
			fname.focus();
			return false;
		}	
		if(lname.value == ""){
			lname_required.textContent = "Last Name is required";
			lname.style.border = "1px solid red";
			lname.focus();
			return false;
		}					
		if(hno.value == ""){
			hno_required.textContent = "House No. is required";
			hno.style.border = "1px solid red";
			hno.focus();
			return false;
		}	
		if(strname.value == ""){
			strname_required.textContent = "Street Name is required";
			strname.style.border = "1px solid red";
			strname.focus();
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
		if(pincode.value.length < 6)
		{
			pincode_required.textContent = "Pincode should be 10 digits";
			pincode.style.border = "1px solid red";
			pincode.focus();
			return false;
		}
		if(mob.value == ""){
			mob_required.textContent = "Mobile No. is required";
			mob.style.border = "1px solid red";
			mob.focus();
			return false;
		}	
		if(mob.value.length < 10)
		{
			mob_required.textContent = "Mobile No. should be 10 digits";
			mob.style.border = "1px solid red";
			mob.focus();
			return false;
		}
	}
	
	function fnameVerify(){
		var fname = document.forms["donreg2"]["fname"];
		if (fname.value != "") {
			fname_required.innerHTML = "";
			fname.style.border = "1px solid #DCDCDC";
			return true;
		}
	}
	function lnameVerify(){
		var lname = document.forms["donreg2"]["lname"];
		if (lname.value != "") {
			lname_required.innerHTML = "";
			lname.style.border = "1px solid #DCDCDC";
			return true;
		}
	}	
	function hnoVerify(){
		var hno = document.forms["donreg2"]["hno"];
		if (hno.value != "") {
			hno_required.innerHTML = "";
			hno.style.border = "1px solid #DCDCDC";
			return true;
		}
	}	
	function strnameVerify(){
		var strname = document.forms["donreg2"]["strname"];
		if (strname.value != "") {
			strname_required.innerHTML = "";
			strname.style.border = "1px solid #DCDCDC";
			return true;
		}
	}	
	function areVerify(){
		var are = document.forms["donreg2"]["are"];
		if (are.value != "") {
			are_required.innerHTML = "";
			are.style.border = "1px solid #DCDCDC";
			return true;
		}
	}	
	function districtVerify(){
		var district = document.forms["donreg2"]["district"];
		if (district.value != "Select") {
			district_required.innerHTML = "";
			district.style.border = "1px solid #DCDCDC";
			return true;
		}
	}
	function pincodeVerify(){
		var pincode = document.forms["donreg2"]["pincode"];
		if (pincode.value != "") {
			pincode_required.innerHTML = "";
			pincode.style.border = "1px solid #DCDCDC";
			return true;
		}
	}	
	function mobVerify(){
		var mob = document.forms["donreg2"]["mob"];
		if (mob.value != "") {
			mob_required.innerHTML = "";
			mob.style.border = "1px solid #DCDCDC";
			return true;
		}
	}