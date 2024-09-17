<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.output.RentalCarOutputBean"%>
<%@page import="beans.action.CommonActionBean"%>
<%@page import="controller.CommonController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Car Dekho</title>
<link rel="stylesheet" href="css/index.css">
<script src="js/jQuery.js"></script>
<script src="js/jqueryScript.js"></script>
</head>
<body>
	<%-- <jsp:include page="Navbar.jsp"></jsp:include> --%>
<%-- Set a request attribute to indicate this is the rentalcarindex page --%>
<%
    request.setAttribute("currentPage", "rentalcarindex");
%>

	<div id="parent">
	<% 
if (session.getAttribute("userId") != null) {
    // User is logged in, include the logged-in user navbar
    %>
    <jsp:include page="UserAfterLogInNavbar.jsp" />
    <% 
} else {
    // User is not logged in, include the default navbar
    %>
    <jsp:include page="Navbar.jsp" />
    <% 
}
%>
		<%
		if (request.getAttribute("successMessage") != null) {
		%>
		<p class="message" style="color: green;"><%=request.getAttribute("successMessage")%></p>

		<%
		}
		%>


		<%
		if (request.getAttribute("errorMessage") != null) {
		%>
		<p class="message" style="color: red;"><%=request.getAttribute("errorMessage")%></p>
		<%
		}
		%>

		<%
		if (request.getAttribute("EmailError") != null) {
		%>
		<p class="message" style="color: red;"><%=request.getAttribute("EmailError")%></p>
		<%
		}
		%>

		<%
		CommonActionBean.getAllCar(request, response);
		CommonActionBean.getRentalAllCar(request, response);

		%>
		<h1></h1>
		<div id="midBanner">
<div id="formDiv">
                <h1 style=" margin-top: 10px;">Find Your Right Car</h1>
                <form action="CommonController">
                <input type="hidden" name="action" value="searchByRentedCar">
                <div id="radioButton">
                    <!-- <input type="radio" name="radio" id="radio" value="price"> -->
                    <label for="radio">By Location</label>
                    <span id="brandDiv" style="margin-left:25px;">
                    <!-- <input type="radio" name="radio" id="radio" value="brand"> -->
                    
                    </sapn>
                 </div>
                    
                    <select name="location" id="budget" class="selectForm" style=" margin-top: 20px;">
                        <option value="Mumbai">Mumbai</option>
                        <option value="Pune">Pune</option>
                        <option value="Bangalore">Bangalore</option>
                        <option value="Delhi">Delhi</option>
                    </select>
                    <br>
                    
                    
                    <input type="submit" value="SEARCH" id="submitBtn">

                </form>
            </div>
        </div>
		<!-- --------------------------- Car Card -------------------------------- -->


		<div id="UsedCars">
			<h1>Rental cars</h1>
			<div id="cardBodyParent" class="">
				<%
				ArrayList cars = (ArrayList) session.getAttribute("RentalCar");
				Iterator iterator = cars.iterator();
				while (iterator.hasNext()) {
					RentalCarOutputBean car = (RentalCarOutputBean) iterator.next();
				%>
				<div id="cardBody" class="">
					<div id="imgDiv">
						<!--   <img id="imgDiv" src="images/Jeep.webp" alt=""> -->
						<img id="imgDiv" src=<%=car.getCarImg()%> alt="">

					</div>
					<div id="carCardContent">
						<span id="carCardName"><%=car.getCarBrand()%></span> <br> <span
							id="carCardprice">Rs <%=car.getPrice()%> rupees
						</span> <br> <br>
						<form action=ViewRentalCarDetails.jsp?id=<%=car.getId()%>">
							<input type="hidden" value="<%=car.getId()%>" name="id" /> <input
								type="submit" value="Check car details" id="cardButton" />
						</form>
					</div>
				</div>
				<%
				}
				%>
			</div>
			
			
			
			
			  <div id="UsedCars">
             <h1>Searched Cars By location</h1>
              <div id="cardBodyParent" class="">
              <%if(session.getAttribute("searchByRentedCar")!=null) {
            	  System.out.println("Car By Location");
            ArrayList Rentedcars = (ArrayList) session.getAttribute("searchByRentedCar");
            Iterator iteratorS = Rentedcars.iterator();
            while(iteratorS.hasNext()){
            	RentalCarOutputBean rentedcarS = (RentalCarOutputBean) iteratorS.next();
        %>
                    <div id="cardBody" class="">
    <div id="imgDiv">
        <img id="imgDiv" src="<%=rentedcarS.getCarImg() %>" alt="">
    </div>
    <div id="carCardContent">
         <span id="carCardName"><%= rentedcarS.getCarBrand() %></span> <br>
       <span id="carCardprice">Rs <%= rentedcarS.getPrice() %> per day</span> <br> <br>
        <form action=ViewRentalCarDetails.jsp?id=<%=rentedcarS.getId()%>">
            <input type="hidden" value="<%= rentedcarS.getId() %>" name="id"/>
            <input type="submit" value="Check car details" id="cardButton"/>
          </form> 
        </div>
    </div>
<%
        }
            }
              else {
            	%>
                <p>No Searched cars found .</p>
            <% } %>

                </div>
                <div style="clear: both;"></div>
                </div>
                
              
<%--                 	  <div id="UsedCars">
             <h1>Searched Cars By Brand</h1>
              <div id="cardBodyParent" class="">
              <%if(session.getAttribute("searchByRentedCar")!=null) {
            	  System.out.println("Car By Location");
            ArrayList Rentedcars = (ArrayList) session.getAttribute("SearchCarBYRentalBrand");
            Iterator iteratorS = Rentedcars.iterator();
            while(iteratorS.hasNext()){
            	RentalCarOutputBean rentedcarS = (RentalCarOutputBean) iteratorS.next();
        %>
                    <div id="cardBody" class="">
    <div id="imgDiv">
        <img id="imgDiv" src="<%=rentedcarS.getCarImg() %>" alt="">
    </div>
    <div id="carCardContent">
         <span id="carCardName"><%= rentedcarS.getCarBrand() %></span> <br>
       <span id="carCardprice">Rs <%= rentedcarS.getPrice() %> per day</span> <br> <br>
        <form action=ViewRentalCarDetails.jsp?id=<%=rentedcarS.getId()%>">
            <input type="hidden" value="<%= rentedcarS.getId() %>" name="id"/>
            <input type="submit" value="Check car details" id="cardButton"/>
          </form> 
        </div>
    </div>
<%
        }
            }
              else {
            	%>
                <p>No Searched cars found .</p>
            <% } %>

                </div>
                <div style="clear: both;"></div>
                </div>
                 --%>
        </div>
        
					<!--=====================================================Login form====================================================  -->
	<%-- <jsp:include page="jsp/LoginForm.jsp"></jsp:include> --%>
	<div id="popupDiv">
		<div id="popupChildMain">

			<div id="LoginImg"></div>
			<div id="popup-child">
				<span id="close" onclick="validate.closeLoginPopup()">&times;</span>
				<h1 style="text-align: center; color: black; margin: 30px;">Login
					Form</h1>
				<Form id="LoginformDiv">
					<input type="hidden" value="Login" name="action" /> <label
						for="userType">Login As:</label><br> <input type="radio"
						id="userTypeUser" name="userType" value="user" checked /> <label
						for="userTypeUser">User</label> <input type="radio"
						id="userTypeAdmin" name="userType" value="admin"> <label
						for="userTypeAdmin">Admin</label><br>
					<!-- <label for="" id="labelUsername">Username :</label> <br> -->
					<input type="text" id="Username" name="Username"
						placeholder="Email_id"> <br>
					<div id="UsernameError" class="errorDiv" style="margin-left: 25px">&nbsp;</div>
					<br>
					<!-- <label for="" id="labelPassword">Password :</label> <br> -->
					<input type="password" id="Password" name="Password"
						placeholder="Password" maxlength=15> <br>
					<div id="PasswordError" class="errorDiv" style="margin-left: 25px">&nbsp;</div>
					<br> <span id="registerLinkDiv"><a href="#"
						onclick="validate.showRegisterPopup()" id="registerlink">Not
							Register yet ?</a></span> <span> <a href="#" style="color: red">
							Forgot Password</a>
					</span> <input type="submit" id="Loginbtn" onclick="" value="Login">

				</Form>


			</div>
		</div>

		<div class="alert" id="alertBoxLogin">
			<span class="closebtn"
				onclick="this.parentElement.style.display='none';">&times;</span> <strong
				id="msglogin"></strong>
			<div id="progressBarLogin" class=""></div>
		</div>

	</div>

	<!--============================================Registeration form===============================  -->
	<div id="registerPopUp">
		<div id="Formcontainer">
			<div id="formDiv1">

				<form action="CommonController" id="formObj" method="post">
					<input type="hidden" value="register" name="action" /> <span
						id="closeRegister" onclick="validate.closeRegisterPopup()">&times;</span>
					<!-- <div id="nameDiv" class=""> -->
					<label for="fname" class="lableColor">First Name <span
						class="starColor">*</span>:
					</label> <br> <input type="text" id="fname" name="firstname"
						placeholder="Your First name.." class="inpformat inpValid"
						onkeydown="validate.inputErrorDisplay(this)" autofocus>
					<div id="firstnameError" class="errorDiv">&nbsp;</div>
					<!-- <br> -->
					<!-- </div> -->

					<!-- <div id="lnameDiv" class=""> -->
					<label for="lname" class="lableColor">Last Name <span
						class="starColor">*</span> :
					</label> <br> <input type="text" id="lname" name="lastname"
						placeholder="Your last name.." class="inpformat inpValid"
						onkeydown="validate.inputErrorDisplay(this)">
					<div id="lastnameError" class="errorDiv">&nbsp;</div>
					<!-- </div> -->


					<!-- <label for="gender" class="lableColor">Gender <span class="starColor">*</span>: </label>
						<label class="lableColor"><input type="radio" name="gender" id="gender"
								value="male">Male</label>
						<label class="lableColor"><input type="radio" name="gender" id="gender"
								value="female">Female</label>
						<label class="lableColor"><input type="radio" name="gender" id="gender"
								value="Other">Other</label>
						<div id="errorSpan"></div> -->
					<br>
					<!-- <div id="dobDiv" class="">
							<label for="dob" class="lableColor">Date Of Birth <span class="starColor">*</span>: </label>
							<br>

							<input type="text" id="dob" name="dob" placeholder="DD/MM/YYYY"
							oninput="validate.inputErrorDisplay(this)" class="inpformat">
							<div id="dobError" class="errorDiv">&nbsp;</div>
						</div>

						<div id="ageDiv" class="">
							<label for="age" class="lableColor">Age <span class="starColor">*</span>: </label> <br>

							<input type="text" name="age" id="age" class="inpformat" onchange="validate.inputErrorDisplay(this)" readonly="readonly" placeholder="00">
							<div id="ageError" class="errorDiv">&nbsp;</div>
						</div> -->

					<label for="mobile" class="lableColor">Mobile no <span
						class="starColor">*</span>:
					</label> <br> <input type="tel" name="mobileNo" id="mobile"
						minlength=1 maxlength=10 class="inpformat"
						onkeypress="validate.inputErrorDisplay(this)"
						placeholder="9112345678">
					<div id="mobileError" class="errorDiv">&nbsp;</div>

					<label for="email" class="lableColor">Email-id <span
						class="starColor">*</span>:
					</label> <br> <input type="email" name="email" id="email"
						class="inpformat" onkeydown="validate.inputErrorDisplay(this)"
						placeholder="example@gmail.com" />
					<div id="emailError" class="errorDiv">&nbsp;</div>

					<label for="password" class="lableColor">Password <span
						class="starColor">*</span>:
					</label> <br> <input type="text" name="password" id="password"
						class="inpformat" oninput="validate.inputErrorDisplay(this)"
						placeholder="Pass@123" />
					<div id="passwordError" class="errorDiv">&nbsp;</div>

					<label for="confirmPassword" class="lableColor">Confirm
						Password <span class="starColor">*</span>:
					</label> <br> <input type="password" name="confirmPassword"
						id="confirmPassword" class="inpformat"
						oninput="validate.inputErrorDisplay(this)" placeholder="Pass@123" />
					<div id="confirmPasswordError" class="errorDiv">&nbsp;</div>



					<label for="address" class="lableColor">Address <span
						class="starColor">*</span>:
					</label><br> <input type="text" name="addressArea" class="inpformat"
						id="addressArea" cols="53" rows=""
						style="margin: 10px; width: 400px;"
						oninput="validate.inputErrorDisplay(this)">
					<div id="addressAreaError" class="errorDiv"></div>

										<div id="cityDiv" class="">
						<label for="city" class="lableColor">City <span
							class="starColor">*</span>:
						</label> <br> <select name="city" id="city" disabled>
							<option value="">Select city</option>
							<option value="Pune">Pune</option>
							<option value="Mumbai">Mumbai</option>
							<option value="Nashik">Nashik</option>
							<option value="Jalgaon">Jalgaon</option>
							<option value="Surat">Surat</option>
							<option value="Nagpur">Nagpur</option>

						</select>

					</div>

					<br> <label for="pincode" class="lableColor">pincode <span
						class="starColor">*</span>:
					</label> <br> <input type="text" name="pincode" id="pincode"
						class="inpformat" oninput="validate.inputErrorDisplay(this)"
						placeholder="411062" />
					<div id="pincodeError" class="errorDiv">&nbsp;</div>


					<br>

					<div id="btn-div" class="">
						<input type="submit" class="btn" id="submitRegister"
							value="Register">
						<!-- <button onclick="" class="btn" id="resetBtn">Reset</button> -->
					</div>
				</form>
			</div>
			<div class="alert" id="alertBox">
				<span class="closebtn"
					onclick="this.parentElement.style.display='none';">&times;</span> <strong
					id="msg"></strong>
				<div id="progressBar" class=""></div>
			</div>
		</div>
	</div>

	<div class="jsP" id="jspalertBox">
		<span class="closebtn"
			onclick="this.parentElement.style.display='none';">&times;</span> <strong
			id="msg">Login Success Redirecting...</strong>
		<div id="progressBar" class="jspprogressBar"></div>
	</div>

	<jsp:include page="Footer.jsp"></jsp:include>
	</div>
	<script src="./js/scriptI.js">
		
	</script>

</body>
</html>