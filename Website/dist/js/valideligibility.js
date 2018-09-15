	function validate()
	{
	var age = document.forms["donreg1"]["age"];
	var weight = document.forms["donreg1"]["weight"];

	var age_required = document.getElementById("age_required");
	var weight_required = document.getElementById("weight_required");
		
	age.addEventListener("blur", ageVerify, true);
	weight.addEventListener("blur", weightVerify, true);
	
		if(age.value == ""){
			age_required.textContent = "Age is required";
			age.style.border = "1px solid red";
			age.focus();
			return false;
		}	
		if(weight.value == ""){
			weight_required.textContent = "Weight is required";
			weight.style.border = "1px solid red";
			weight.focus();
			return false;
		}
	}
	
	function ageVerify(){
		var age = document.forms["donreg1"]["age"];
		if (age.value != "") {
			age_required.innerHTML = "";
			age.style.border = "1px solid #DCDCDC";
			return true;
		}
	}
	function weightVerify(){
		var weight = document.forms["donreg1"]["weight"];
		if (weight.value != "") {
			weight_required.innerHTML = "";
			weight.style.border = "1px solid #DCDCDC";
			return true;
		}
	}