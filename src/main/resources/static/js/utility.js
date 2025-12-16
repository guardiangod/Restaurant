// check if element value is empty
function isEmpty(obj) {
	if(obj.val() == "") {
		obj.addClass("ui-state-error");
		obj.focus();
		return true;
	} else {
		obj.removeClass("ui-state-error");
		return false;
	}
}

function checkValue(obj, value) {
	if(obj.val() == value) {
		return true;
	} else {
		return false;
	}
}


// check min/max length of an element
function checkLength(obj, min, max) {
	if(obj.val().length > max || obj.val().length < min) {
		obj.addClass("ui-state-error");
		obj.focus();
		return false;
	} else {
		obj.removeClass("ui-state-error");
		return true;
	}
}

// check for special chars in the element value; first char must be alphabet
function checkSpecialChars(obj) {
	var regexp = /^[a-z]([0-9a-z_])+$/i;
	if(!(regexp.test(obj.val()) )) {
		obj.addClass("ui-state-error");
		obj.focus();
		return false;
	} else {
		obj.removeClass("ui-state-error");
		return true;
	}
}

// check if element value contains only digits
function isDigits(obj) {
	var regexp = /^(\d)+$/;
	if(!(regexp.test(obj.val()) )) {
		obj.addClass("ui-state-error");
		obj.focus();
		return false;
	} else {
		obj.removeClass("ui-state-error");
		return true;
	}
}

// check for valid email
function checkEmail(obj) {
	var regexp = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(!(regexp.test(obj.val()) )) {
		obj.addClass("ui-state-error");
		obj.focus();
		return false;
	} else {
		obj.removeClass("ui-state-error");
		return true;
	}
}

// check for valid postalcode xxxxxx
function checkPostalCode(obj) {
	var regexp = /^(\d){6}$/;
	if(!(regexp.test(obj.val()) )) {
		obj.addClass("ui-state-error");
		obj.focus();
		return false;
	} else {
		obj.removeClass("ui-state-error");
		return true;
	}
}

function showError(obj, message) {
	obj.find("span:last-child").html(message);	
	obj.show();
}

// check for valid phone xxx xxxx xxxx
function checkPhone(obj) {
	var regexp = /^\+?([0-9]{2})\)?[-. ]?([0-9]{4})[-. ]?([0-9]{4})$/;
	if(!(regexp.test(obj.val()) )) {
		obj.addClass("ui-state-error");
		obj.focus();
		return false;
	} else {
		obj.removeClass("ui-state-error");
		return true;
	}
}

function checkCardNumber(obj) {
	var regexp = /^(\d){4}-(\d){4}-(\d){4}-(\d){4}$/;
	if(!(regexp.test(obj.val()) )) {
		obj.addClass("ui-state-error");
		obj.focus();
		return false;
	} else {
		obj.removeClass("ui-state-error");
		return true;
	}	
}


