$(function() {
	startup();

	$(".ui-tabs-panel:visible").ajaxSuccess(function() {
		init();
	});
	$("#ui-tabs-4").ajaxError(function(event, xhr, settings, thrownError) {
		$("#ui-tabs-4").html(xhr.responseText);
	});
	// login
	$("#login").live("submit", function() {
		// create an AJAX call
		$.ajax({
			// validate form before ajax call;
			beforeSend : function() {
				// if this returns false, the ajax call will not be made
				return validateLogin();
			},
			data : $(this).serialize(),
			type : $(this).attr("method"),
			url : $(this).attr("action"),
			success : function(response) {
				$("#loginbox").html(response);
			},
			error : function(xhr, status, error) {
				showError($("#loginError"), xhr.responseText);
			}
		});

		// prevent default submit
		return false;
	});
	// logout
	$("#logout").live("submit", function() {
		// create an AJAX call
		$.ajax({
			type : $(this).attr("method"),
			url : $(this).attr("action"),
			success : function(response) {
				$("#loginbox").html(response);
				$("#signin").button("option", "label", "Sign in");
			}
		});

		// prevent default submit
		return false;
	});
	// new user registration
	$("#newuser").live("click", function() {
		$("#registerDialog").load("/newuser");
		$("#registerDialog").dialog("open");
		return false;
	});

	// searchrestaurant.jsp
	$("#srchRestForm").live("submit", function() {
		// create an AJAX call
		$.ajax({
			// validate form before ajax call;
			beforeSend : function() {
				// if this returns false, the ajax call will not be made
				return validateSrchRest($("#srchRestError"));
			},
			data : $(this).serialize(),
			type : $(this).attr("method"),
			url : $(this).attr("action"),
			success : function(response) {
				// update the tab content
				$(".ui-tabs-panel:visible").html(response);
			}
		});

		// prevent default submit
		return false;
	});
	
	// register a restaurant
	$("#newRstBtn").live("click", function() {
		$("#newRestaurantDialog").load("/owner/restaurant/new");
		$("#newRestaurantDialog").dialog("open");
		return false;
	});
	// edit a restaurant
	$(".editRestBtn").live("click", function() {
		var restId = $(this).attr("id").substring(11);
		$.ajax({
			data : {
				restId : restId,
			},
			type : $("#myRestaurantForm").attr("method"),
			url : $("#myRestaurantForm").attr("action"),
			success : function(response) {
				// update the tab content
				$(".ui-tabs-panel:visible").html(response);
			}	
		});

		// prevent default submit
		return false;
	});
	// submit update restaurant
	$("#updateRstBtn").live("click", function() {
		$.ajax({
			beforeSend : function() {
				// if this returns false, the ajax call will not be made
				return validateRestInputs($("#rstEditError"));
			},				
			data : $('#viewRstForm').serialize(),
			type : $('#viewRstForm').attr("method"),
			url : $('#viewRstForm').attr("action"),
			success : function(response) {
				// update the tab content
				$(".restaurantInfo").html(response);
				$(".restaurantInfo").dialog("option", "title", "HPE - Update Restaurant");
				$(".restaurantInfo").dialog("open");
				$("#menu").tabs('load', 2);
			}
		});
		
		// prevent default submit
		return false;
	});
	// back to view all my restaurants
	$("#backRstBtn").live("click", function() {
		$("#menu").tabs('load', 2);
		
		// prevent default submit
		return false;
	});

	// searchreview.jsp
	$("#srchReviewForm").live("submit", function() {
		// create an AJAX call
		$.ajax({
			// validate form before ajax call;
			beforeSend : function() {
				// if this returns false, the ajax call will not be made
				return validateReviewRest($("#srchReviewError"));
			},
			data : $(this).serialize(),
			type : $(this).attr("method"),
			url : $(this).attr("action"),
			success : function(response) {
				// update the tab content
				$(".ui-tabs-panel:visible").html(response);
				$("#sortradio").buttonset();
				$("#filterradio").buttonset();
			}
		});

		// prevent default submit
		return false;
	});
	// sort/filter reviews
	$(".sortradio, .filterradio").live("click", function() {
		// create an AJAX call
		$.ajax({
			data : $("#srchReviewForm").serialize(),
			type : $("#srchReviewForm").attr("method"),
			url : '/review/filtersort',
			success : function(response) {
				// update the tab content
				$(".ui-tabs-panel:visible").html(response);
				$("#sortradio").buttonset();
				$("#filterradio").buttonset();

			}
		});

		// prevent default submit
		return false;
	});
	// show all reviews for selected restaurant
	$(".reviewBtn").live("click", function() {
		var restID = $(this).attr("id").substring(9);
		$.ajax({
			data : {
				rest : restID,
			},
			type : 'GET',
			url : '/review/viewall',
			success : function(response) {
				// update the tab content
				$(".ui-tabs-panel:visible").html(response);
			}	
		});

		// prevent default submit
		return false;
	});
	// write new review
	$("#viewReviewForm").live("submit", function() {
		var restID = $("#viewReviewForm #restaurantId").val();
		$.ajax({
			data : {
				rest : restID,
			},
			type : $(this).attr("method"),
			url : $(this).attr("action"),
			success : function(response) {
				// new review dialog
				$("#newReviewDialog").html(response);								
				$("#newReviewDialog").dialog("open");											
			},
			error : function(xhr, status, error) {
				$(".restaurantInfo").html(xhr.responseText);
				$(".restaurantInfo").dialog("option", "title", "HPE - Write a Review");
				$(".restaurantInfo").dialog("open");
			}			
		});

		// prevent default submit
		return false;
	});	
	// searchrestmenu.jsp
	$("#srchMenuForm").live("submit", function() {
		// create an AJAX call
		$.ajax({
			// validate form before ajax call;
			beforeSend : function() {
				// if this returns false, the ajax call will not be made
				return validateSrchMenu($("#srchMenuError"));
			},
			data : $(this).serialize(),
			type : $(this).attr("method"),
			url : $(this).attr("action"),
			success : function(response) {
				// update the tab content
				$(".ui-tabs-panel:visible").html(response);
			}
		});

		// prevent default submit
		return false;
	});
	// view menu for selected restaurant
	$(".viewMenuBtn").live("click", function() {
		var restID = $(this).attr("id").substring(11);
		$.ajax({
			data : {
				rest : restID,
			},
			type : 'POST',
			url : '/menu/view',
			success : function(response) {
				// update the tab content
				$(".ui-tabs-panel:visible").html(response);
			},
			error : function(xhr, status, error) {
				$(".restaurantInfo").html(xhr.responseText);
				$(".restaurantInfo").dialog("option", "title", "HPE - View Restaurant Menu");
				$(".restaurantInfo").dialog("open");
			}
		});

		// prevent default submit
		return false;
	});
	// edit menu item
	$("#viewmenuForm").live("submit", function() {
		// create an AJAX call
		$.ajax({
			// validate form before ajax call;
			beforeSend : function() {
			},
			data : $(this).serialize(),
			type : $(this).attr("method"),
			url : $(this).attr("action"),
			success : function(response) {
				// update the tab content
				$(".ui-tabs-panel:visible").html(response);
				$("#orderError").hide();
			}
		});

		// prevent default submit
		return false;
	});
	
});

// things to do on document load
function startup() {
	// create tabs
	$("#menu").tabs();

	// load sign in page
	$("#signin").button({
		icons : {
			secondary : "ui-icon-triangle-1-s"
		}
	});
	// show signin dialog on startup
	$("#loginbox").load("/showlogin", function() {
		$("#loginButton, #newuser").button();
	});
	// submit signin
	$("#signin").click(function() {
		$("#loginbox").slideToggle("medium");
		$("#loginError").hide();
		$("#loginbox :input").removeClass("ui-state-error");
	});
	// information dialog used across app
	$(".restaurantInfo").dialog({
		autoOpen : false,
		modal : true,
		height : 175,
		width : 500,
		buttons : {
			Ok : function() {
				$(this).dialog("close");
			}
		}
	});
	// member registration dialog
	$("#registerDialog").dialog({
		autoOpen : false,
		height : 500,
		width : 1000,
		modal : true,
		buttons : {
			"Create account" : function() {
				// create an AJAX call
				$.ajax({
					// validate form before ajax call;
					beforeSend : function() {
						// if this returns false, the ajax call will not be made
						return validateRegistration($("#registerError"));
					},
					data : $("#registerForm").serialize(),
					type : $("#registerForm").attr("method"),
					url : $("#registerForm").attr("action"),
					success : function(response) {
						$("#registerDialog").dialog("close");
						$(".restaurantInfo").html(response);
						$(".restaurantInfo").dialog("option", "title", "HPE - Registration");
						$(".restaurantInfo").dialog("open");
					},
					error : function(xhr, status, error) {
						showError($("#registerError"), xhr.responseText);
					}
				});
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	});
	// new review dialog
	$("#newReviewDialog").dialog({
		autoOpen : false,
		height : 350,
		width : 600,
		modal : true,
		buttons : {
			"Post" : function() {
				// create an AJAX call
				$.ajax({
					// validate form before ajax call;
					beforeSend : function() {
						// if this returns false, the ajax call will not be made
						return validateNewReview();
					},
					data : $("#newReviewForm").serialize(),
					type : $("#newReviewForm").attr("method"),
					url : $("#newReviewForm").attr("action"),
					success : function(response) {
						// close popup and refresh parent tab
						$("#newReviewDialog").dialog("close");
						$(".ui-tabs-panel:visible").html(response);
					},
					error : function(xhr, status, error) {
						showError($("#newReviewError"), xhr.responseText);
					}
				});
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	});
	// restaurant registration dialog
	$("#newRestaurantDialog").dialog({
		autoOpen : false,
		height : 500,
		width : 1000,
		modal : true,
		buttons : {
			"Save" : function() {
				// create an AJAX call
				$.ajax({
					// validate form before ajax call;
					beforeSend : function() {
						// if this returns false, the ajax call will not be made
						return validateRestInputs($("#restError"));
					},
					data : $("#restForm").serialize(),
					type : $("#restForm").attr("method"),
					url : $("#restForm").attr("action"),
					success : function(response) {
						$("#newRestaurantDialog").dialog("close");
						$(".restaurantInfo").dialog("option", "title", "HPE - Register Restaurant");
						$(".restaurantInfo").dialog("open");
						$("#menu").tabs('load', 2);
					},
					error : function(xhr, status, error) {
						showError($("#restError"), xhr.responseText);
					}
				});
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	});	
}

// initialize all elements on the visible form using jquery ui theme
function init() {
	$("input:submit, input:button, button").button();
	$("#loginbox").hide();
	$("#ui-tabs-4 form > div").accordion({
		collapsible : true,
		autoHeight : false
	});
}

// validate signin
function validateLogin() {
	// if this returns false, the ajax call will not be made
	if (isEmpty($("#userName"))) {
		showError($("#loginError"), "Username is required");
		return false;
	} else if (isEmpty($("#userPwd"))) {
		showError($("#loginError"), "Password is required");
		return false;
	}
}

// validate fields in registration form
function validateRegistration(errorObj) {
	// if this returns false, the ajax call will not be made
	if (isEmpty($("#firstName"))) {
		showError(errorObj, "First Name is required");
		return false;
	}
	if (isEmpty($("#lastName"))) {
		showError(errorObj, "Last Name is required");
		return false;
	}
	if (isEmpty($("#streetAddress"))) {
		showError(errorObj, "Street Address is required");
		return false;
	}
	if (isEmpty($("#city"))) {
		showError(errorObj, "City is required");
		return false;
	}
	if (isEmpty($("#postal"))) {
		showError(errorObj, "Postal code is required");
		return false;
	}
	if (isEmpty($("#email"))) {
		showError(errorObj, "Email address is required");
		return false;
	}
	if (isEmpty($("#registerForm #userId"))) {
		showError(errorObj, "Please provide a username for your account");
		return false;
	}
	if (isEmpty($("#registerForm #userPwd"))) {
		showError(errorObj, "Please provide a password for your account");
		return false;
	}
	if (!checkPostalCode($("#postal"))) {
		showError(errorObj, "Postal code must contain only 6 digits");
		return false;
	}
	if (!checkEmail($("#email"))) {
		showError(errorObj, "Email address is invalid");
		return false;
	}
	if (($("#phone").val() != "") && (!checkPhone($("#phone")))) {
		showError(errorObj, "Phone number is invalid. Please use the format specified");
		return false;
	}
	if (!checkSpecialChars($("#userId"))) {
		showError(errorObj, "User Name must start with an alphabet and can be followed by only alphabets, numbers or underscore");
		return false;
	}
	if ($("#ccNumber").val() != "") {
		if (!checkCardNumber($("#ccNumber"))) {
			showError(errorObj, "Card Number is invalid. Please use the format specified");
			return false;
		} else if (isEmpty($("#ccHolderName"))) {
			showError(errorObj, "Card Holder's full name is required");
			return false;
		}
	}
}

// validate restaurant search
function validateSrchRest(errorObj) {
	if (isEmpty($("#srchRestForm #restName")) && checkValue($("#srchRestForm #cuisine"), "0") && isEmpty($("#srchRestForm #near"))) {
		showError(errorObj, "Please enter a search criteria to find restaurants");
		return false;
	}
}

// validate restaurant add/update
function validateRestInputs(errorObj) {
	if (isEmpty($("#restaurantName"))) {
		showError(errorObj, "Restaurant Name is required");
		return false;
	}
	if (isEmpty($("#streetAddress"))) {
		showError(errorObj, "Street Address is required");
		return false;
	}
	if (isEmpty($("#city"))) {
		showError(errorObj, "City is required");
		return false;
	}
	if (isEmpty($("#postal"))) {
		showError(errorObj, "Postal code is required");
		return false;
	}
	if (!checkPostalCode($("#postal"))) {
		showError(errorObj, "Postal code must contain only 6 digits");
		return false;
	}
	if (($("#phone").val() != "") && (!checkPhone($("#phone")))) {
		showError(errorObj, "Phone number is invalid");
		return false;
	}
}

// validate restaurant search for reviews
function validateReviewRest(errorObj) {
	if (isEmpty($("#srchReviewForm #restName")) && checkValue($("#srchReviewForm #cuisine"), "0") && isEmpty($("#srchReviewForm #near"))) {
		showError(errorObj, "Please enter a search criteria to find restaurants");
		return false;
	}
}

// validate new review form
function validateNewReview() {
	var checked = $('#newReviewForm input[type="radio"]').is(':checked');
	if (!checked) {
		showError($("#newReviewError"), "Please choose a rating");
		return false;
	}
}

// validate restaurant search for menu
function validateSrchMenu(errorObj) {
	if (isEmpty($("#srchMenuForm #restName")) && checkValue($("#srchMenuForm #cuisine"), "0") && isEmpty($("#srchMenuForm #near"))) {
		showError(errorObj, "Please enter a search criteria to find restaurants");
		return false;
	}
}


