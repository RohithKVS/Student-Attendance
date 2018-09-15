	function Validate()
	{
	var oldpass = document.forms["donreg2"]["oldpass"];
	var newpass = document.forms["donreg2"]["newpass"];
	var confnewpass = document.forms["donreg2"]["confnewpass"];
		
		
	var oldpass_required = document.getElementById("oldpass_required");
	var newpass_required = document.getElementById("newpass_required");
	var confnewpass_required = document.getElementById("confnewpass_required");
	
	
	oldpass.addEventListener("blur", oldpassVerify, true);
	newpass.addEventListener("blur", newpassVerify, true);
	confnewpass.addEventListener("blur", confnewpassVerify, true);
	
	
			// PASSWORD REQUIRED
		if (oldpass.value == "") 
		{
			oldpass_required.textContent = "Old Password is required";
			oldpass.style.border = "1px solid red";
			oldpass.focus();
			return false;
		}
		if (newpass.value == "") 
		{
			newpass_required.textContent = "New Password is required";
			newpass.style.border = "1px solid red";
			newpass.focus();
			return false;
		}
		if(oldpass.value==newpass.value)
		{
			newpass_required.textContent = "New Password must be different from old password";
			oldpass.style.border = "1px solid red";
			newpass.style.border = "1px solid red";
			newpass.focus();
			return false;
		}
		// VALIDATE PASSWORD
		if (newpass.value != confnewpass.value) {
			confnewpass_required.textContent = "The two passwords do not match";
			newpass.style.border = "1px solid red";
			confnewpass.style.border = "1px solid red";
			newpass.focus();
			return false;
		}
	}
	
	function oldpassVerify(){
		var oldpass = document.forms["donreg2"]["oldpass"];
		if (oldpass.value != "") {
			oldpass_required.innerHTML = "";
			oldpass.style.border = "1px solid #DCDCDC";
			return true;
		
		}
	}	
	function newpassVerify(){
		var newpass = document.forms["donreg2"]["newpass"];
		if (newpass.value != "") {
			newpass_required.innerHTML = "";
			newpass.style.border = "1px solid #DCDCDC";
			return true;
		
		}
	}	
	function confnewpassVerify(){
		var confnewpass = document.forms["donreg2"]["confnewpass"];
		if (confnewpass.value != "") {
			confnewpass_required.innerHTML = "";
			confnewpass.style.border = "1px solid #DCDCDC";
			return true;
		}
	}	