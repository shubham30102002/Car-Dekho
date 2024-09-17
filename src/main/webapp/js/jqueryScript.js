$(function() {
	$("#Email").on("input", function(event) {
		loginValidation(event.target);
	});

	$(".message").each(function() {
		var message = $(this);
		setTimeout(function() {
			message.hide();
		}, 2000);
	});

	function loginValidation(e) {
		let err = $("#" + e.name + "Error");
		let pattern = /^[a-zA-Z0-9_@.]{8,20}$/;

		if (e.id == "Password") {
			if (e.value.length > 14) {
				err.html("Please enter up to 15 characters ");
			} else if (e.value == "") {
				err.html("Please enter password");
			} else {
				err.html("&nbsp;");
			}
		}
	}

	$("#Username").on("input", function(event) {
		loginValidation(event.target);
	});

	function loginValidation(e) {
		let err = $("#" + e.name + "Error");
		let pattern = /^[a-zA-Z0-9_@.]{8,20}$/;

		if (e.id == "Username") {
			if (/[#$%&*()!^]/.test(e.value)) {
				e.value = e.value.replace(/[#$%&*()!^]/g, '');
				err.html("Error: Username contain underscores, '@', or periods only. ");
				return;
			}
			if (!/^[a-zA-Z0-9_@.]{8,20}$/.test(e.value)) {
				err.html("Please enter alphanumeric characters, underscores, '@', or periods only.");

				// e.preventDefault(); 
				//  return false; 
			} else {
				err.html("&nbsp;");
			}
		}
		else if (e.id == "Password") {

			if (e.value.length > 14) {
				err.html("Please enter upto 15 characters ");
			}
			else if (e.value == "") {
				err.html("Please enter password");
			}

			else {
				err.html("&nbsp;");
			}

		}

	}


	/* $("#login-Btn").click(function (event){
		 let loginProfile = $("#loginProfile").html();
 
		 if (loginProfile == "Login/Register") {
			 $("#LoginpopupDiv").css("display", "block");
		 } else {
			 fetchUserInfo();
			 window.location.href = "jsp/ViewUser.jsp";
		 }
	 });*/

	$("#Loginbtn").click(function(event) {
		event.preventDefault();
		var email = $("#Username").val();
		var userType = $('input[name="userType"]:checked').val();
		console.log("User" + userType);
		var password = $("#Password").val();
		var action = $('input[name="action"]').val();

		if (email === "" || password === "" || !userType) {
			alertDisplayLogin("Please fill all the fields", "red");
		} else {
			$.ajax({
				url: "CommonController",
				method: "POST",
				data: {
					"action": "Login",
					"email": email,
					"password": password,
					"userType": userType
				},
				success: function(response) {
					if (userType === "user") {
						console.log("User selected");
						alertDisplayLogin("Logging in ...", "green");
						setTimeout(function() {
							window.location.assign("AfterUserLoginIndex.jsp");  // Navigate without refreshing
						}, 2000);
					} else if (userType === "admin") {
						console.log("Admin selected");
						alertDisplayLogin("Logging in ...", "green");
						setTimeout(function() {
							window.location.assign("AdminPage.jsp");  // Navigate without refreshing
						}, 2000);
					}
				}, error: function(xhr, status, error) {
					console.error("AJAX Error:", error);
					console.error("Response Text:", xhr.responseText);
					console.error("Status:", status);					// alert("User not found error");
					let errorMessage = "An error occurred. Please try again.";
					if (xhr.status === 401) {
						errorMessage = "Invalid username or password.";
					} else if (xhr.status === 403) {
						errorMessage = "Your account is inactive. Please contact the admin.";
					}
					alertDisplayLogin(errorMessage, "red");

				}
			});
		}
		console.log(email, password, action);
	});

	/* $('#LikedIconImg').on('click', function() {
	    
		 likedCar(selectedCar.getId());
	 });*/

	function alertDisplayLogin(msg, color) {
		$("#alertBoxLogin").fadeIn();
		$("#alertBoxLogin").css("background", color);
		$("#msglogin").text(msg);
		$("#progressBarLogin").css("animation", 'progress 3s 1 ease-in-out');
		$(".closebtn").click(function() { $("#alertBoxLogin").fadeOut(); })
		setTimeout(function() {
			$("#alertBoxLogin").fadeOut();
		}, 2000);
	}





});

function likedCar(carId) {
	console.log(carId + " jdj");
	$.ajax({
		url: "./CommonController",
		method: "POST",
		data: {
			"action": "likedCar",
			"CarId": carId
		},
		success: function(data) {
			if (data === "liked") {
				$("#LikedIconImg").attr('src', './images/liked.jpg');
				showNotification("Car liked successfully!", "green");
			} else if (data === "unliked") {
				$("#LikedIconImg").attr('src', './images/unliked.png');
				showNotification("Car unliked successfully!", "red");
			} else {
				showNotification("An unexpected response was received.", "orange");
			}
		},
		error: function(xhr, status, error) {
			console.error("AJAX Error:", status, error);
			showNotification("Please log in first.", "red");
			// Optionally, you can redirect to login page
			// window.location.href = "./index.jsp";
		}
	});
}




function likedCar1(carId) {
	console.log(carId + " jdj");
	$.ajax({
		url: "./CommonController",
		method: "POST",
		data: {
			"action": "rentallikedCar",
			"CarId": carId
		},
		success: function(data) {
			console.log("Response Data:", data); // Log the response data for debugging

			if (data === "liked") {
				$("#LikedIconImg").attr('src', './images/liked.jpg');
				showNotification("Car liked successfully!", "green");
			} else if (data === "unliked") {
				$("#LikedIconImg").attr('src', './images/unliked.png');
				showNotification("Car unliked successfully!", "red");
			} else {
				showNotification("An unexpected response was received.", "orange");
			}
		},
		error: function(xhr, status, error) {
			console.error("AJAX Error:", status, error);
			console.log("Response Text:", xhr.responseText); // Log the response text for debugging

			if (xhr.status === 401) {
				showNotification("Please log in first.", "red");
				// Optionally, you can redirect to the login page
				// window.location.href = "./login.jsp";
			} else {
				showNotification("An error occurred. Please try again.", "red");
			}
		}
	});
}

function handleBuyCar() {
	// Retrieve the user ID from the DOM
	var userId = document.getElementById('userId').value;
	console.log('User ID from DOM:', userId);
	if (!userId || userId === null) {
		showNotification('You need to log in to perform this action.', 'red');
		return false; // Prevent form submission
	}

	$.ajax({
		url: './CommonController',
		method: 'POST',
		data: {
			action: 'sellCar',
			carId: $('input[name="carId"]').val(),
			UserId: userId
		},
		success: function(response) {
			if (response.trim() === 'success') {
				showNotification('Car purchased successfully!', 'green');
				window.location.href = 'AfterUserLoginIndex.jsp'; // Redirect after purchase
			} else {
				showNotification('Failed to sell the car. Please try again.', 'red');
			}
		},
		error: function(xhr, status, error) {
			console.error('AJAX Error:', status, error);
			showNotification('You need to log in to perform this action.', 'red');
		}
	});
}

function handleBuyCar1() {
	// Retrieve the user ID from the DOM
	// Get the form values
	var rcarId = document.querySelector('input[name="rcarId"]').value;
	var fromDate = document.getElementById('fromDate').value;
	var toDate = document.getElementById('toDate').value;

	// Construct the data object
	var data = {
		action: 'addRentalBooking',
		rcarId: rcarId,
		fromDate: fromDate,
		toDate: toDate,
	};

	$.ajax({
		url: './CommonController',
		method: 'POST',
		data: data, // Send data as a plain object
		success: function(response) {
			// Handle the response based on the server's message
			switch (response.trim()) {
				case 'success':
					showNotification('Booking successful!', 'green');
					// Optionally, redirect or refresh
					window.location.href = 'ViewUser.jsp	'; // Adjust as needed
					break;
				case 'not_logged_in':
					showNotification('You need to log in to perform this action.', 'red');
					break;
				case 'error':
					showNotification('An error occurred. Please try again.', 'red');
					break;
				case 'failure':
					showNotification('Failed to book the car. Please try again.', 'red');
					break;
				default:
					showNotification('Unexpected response. Please try again.', 'orange');
					break;
			}
		},
		error: function(xhr, status, error) {
			console.error('AJAX Error:', status, error);
			showNotification('An error occurred. Please try again.', 'red');
		}
	});
}

function showNotification(message, color) {
	$("#alertBox").fadeIn();
	$("#alertBox").css("background", color);
	$("#alertBox").text(message);
	setTimeout(function() {
		$("#alertBox").fadeOut();
	}, 3000); // Adjust timing as needed
}






function alertDisplayLogin(msg, color) {
	$("#alertBoxLogin").fadeIn();
	$("#alertBoxLogin").css("background", color);
	$("#msglogin").text(msg);
	$("#progressBarLogin").css("animation", 'progress 3s 1 ease-in-out');
	$(".closebtn").click(function() { $("#alertBoxLogin").fadeOut(); });
	setTimeout(function() {
		$("#alertBoxLogin").fadeOut();
	}, 2000);
}



document.addEventListener('DOMContentLoaded', function() {
	// Get today's date
	let today = new Date().toISOString().split('T')[0];

	// Set the min attribute for both date inputs
	document.getElementById('fromDate').setAttribute('min', today);
	document.getElementById('toDate').setAttribute('min', today);

	// Add event listener to ensure "To Date" is not before "From Date"
	document.getElementById('fromDate').addEventListener('change', function() {
		let fromDate = this.value;
		document.getElementById('toDate').setAttribute('min', fromDate);
	});
});

