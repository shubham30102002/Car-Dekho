<%@page import="beans.action.CommonActionBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href=".css/index.css">
</head>
<body>
<div id="popupDiv">
 <div id="popupChildMain">

				<div id="LoginImg"></div>
				<div id="popup-child">
					<span id="close" onclick="validate.closeLoginPopup()">&times;</span>
					<h1 style="text-align: center;color: black;margin: 30px;">Login Form</h1>
					<Form id="LoginformDiv" >
                         <input type="hidden" value="Login" name="action">
                         <label for="userType">Login As:</label><br>
                               <input type="radio" id="userTypeUser" name="userType" value="user" checked >
                               <label for="userTypeUser">User</label>
                              <input type="radio" id="userTypeAdmin" name="userType" value="admin" >
                              <label for="userTypeAdmin">Admin</label><br>
						<!-- <label for="" id="labelUsername">Username :</label> <br> -->
						<input type="text" id="Username"  name="Username" placeholder="Email_id" > <br> 
						<div id="UsernameError" class="errorDiv" style="margin-left:25px">&nbsp;</div>
						<br> 
						<!-- <label for="" id="labelPassword">Password :</label> <br> -->
						<input type="password" id="Password" name="Password" placeholder="Password"  maxlength=15 >
						<br>
						<div id="PasswordError" class="errorDiv" style="margin-left:25px">&nbsp;</div><br>
                              
						<span id="registerLinkDiv" ><a href="#" onclick="validate.showRegisterPopup()" id="registerlink">Not
								Register yet ?</a></span>  <span> <a href="#" style="color:red"> Forgot Password</a> </span>
						<input type="submit" id="Loginbtn" onclick="" value="Login">

					</Form>


				</div>
				</div>
		<script src="./js/scriptI.js"></script>
</body>
</html>