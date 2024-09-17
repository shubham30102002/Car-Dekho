<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="beans.action.CommonActionBean"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Page</title>
<link rel="stylesheet" href="../css/style.css">
<style>
.ViewRegisterUserBtn{
 padding:15px 10px ;
 background-color: #f75d34;
 border:none;
 Cursor:pointer;
 color:white; 
 box-shadow:7px 3px 3px 3px #cfd9df;
 margin-left:100px;
}
.ViewRegisterUserBtn:hover{
background-color: white;
 border:1px solid black;
 color: #f75d34; 
}
</style>
</head>
<body>
<%
		CommonActionBean.getAllCar(request, response);
		CommonActionBean.getRentalAllCar(request, response);

		%>


<div id="parent">
  <jsp:include page="AdminNavbar.jsp"></jsp:include>
  <h1 style="color:#f75d34; text-align: center;">Welcome Admin</h1>
  <br>
  <div style="margin-top: 100px;">  <a href="./CommonController?action=getAllUsers" style="margin-left: 200px; "> <button class="ViewRegisterUserBtn">View Register users</button> </a>
  <a href="AllCarTable.jsp"><button class="ViewRegisterUserBtn">View All Cars</button></a>
    <a href="AllRentalCarTable.jsp"><button class="ViewRegisterUserBtn">View All Rental Cars</button></a>
  <!--  <a href="./CommonController?action=logOut"><button
						type="button" class="ViewRegisterUserBtn">Logout</button></a>
 -->
 </div>
 
 </div>
</body>
</html>