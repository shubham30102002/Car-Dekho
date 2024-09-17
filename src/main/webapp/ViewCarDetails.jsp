<%@page import="java.util.Iterator"%>
<%@page import="beans.output.CarOutputBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Car Details</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/Footer.css">
<link rel="stylesheet" href="./css/ViewCarDetails.css">
<link rel="stylesheet" href="./css/index.css">
<script src="./js/jQuery.js"></script>
<script src="./js/jqueryScript.js"></script>
<style type="text/css">
#alertBox {
    font-family: Arial, sans-serif;
    font-size: 14px;
    width: 250px;
    text-align: center;
}

</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>

<body>
	<div id="viewParent">
	
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
%>		<%
		int id = Integer.parseInt(request.getParameter("id"));
		HttpSession session2 = request.getSession();
		ArrayList cars = (ArrayList) session.getAttribute("Car");
		CarOutputBean selectedCar = null;

		Iterator iterator = cars.iterator();
		while (iterator.hasNext()) {
			CarOutputBean car = (CarOutputBean) iterator.next();
			if (car.getId() == id) {
				selectedCar = car;
				break;
			}
			
		}
		String carType="";
		  if(Integer.parseInt(selectedCar.getCarType())==1){
			  carType="SUV";
		  }
		  else if(Integer.parseInt(selectedCar.getCarType())==2){
			  carType="Sedan";
		  }
		%>
<div id="alertBox" style="display: none; position: fixed; top: 10px; right: 10px; padding: 15px; border-radius: 5px; color: #fff; z-index: 1000;">
</div>

		<div id="viewCarDiv">
			<span id="carImage"> <img src=<%=selectedCar.getCarImg()%>
				width="450px" height="300px" alt="image not found" id="carImageSrc" />
			</span> <span id="deatailsDiv">
				<h1 id="brandName">
					Car Brand :
					<%=selectedCar != null ? selectedCar.getCarBrand() : "No car selected"%></h1>
				<br>
				<h3 id="price">
					Rs :
					<%=selectedCar != null ? selectedCar.getPrice() : "N/A"%>
					lakh*
				</h3> <br>
				<h3 id="ViewCarModal">
					Modal :
					<%=selectedCar != null ? selectedCar.getCarModal() : "N/A"%></h3> <br>
				<h5>
					Register year :
					<%=selectedCar != null ? selectedCar.getRegisterDate() : "N/A"%></h5>
				<br> <span id="ViewCarTransmission">Trnsamission Type :
					<%=selectedCar != null ? selectedCar.getTransmission() : "N/A"%></span>
				<br>
				<h5 id="ViewEngineCC">
					Engine-cc :
					<%=selectedCar.getEngine_cc()%></h5> <br>
				<h5 id="ViewCarType">
					Car Type :
					<%=carType%></h5> <br>
				<h5 id="ViewCarFuelType">
					Fuel Type :
					<%=selectedCar.getFuelType()%></h5> <br>
				<h4 id="viewDesc">
					Description:
					<%=selectedCar != null ? selectedCar.getCar_desc() : "N/A"%></h4> <br>
				<h4 id="ViewState">
					Register State:
					<%=selectedCar.getRegisterState()%></h4>
			</span> <img src="images/unliked.png" alt="img not found" srcset=""
				id="LikedIconImg" onclick="likedCar(<%=selectedCar.getId()%>)">
	</div>			
<form id="buyForm" action="CommonController" method="post">
    <input type="hidden" name="action" value="sellCar">
    <input type="hidden" name="carId" value="<%= selectedCar.getId() %>">
    <input type="hidden" name="UserId" id="userId" value="<%= session.getAttribute("userId") %>">
    <div id="buttonContainer">
        <input type="button" value="Buy Car" id="BookSellID" onclick="handleBuyCar()">
    </div>
</form>

 <div>
        <a href="./index.jsp" style="color:red">&larr; Home</a> 
  </div> 
			
			<jsp:include page="LoginForm.jsp"></jsp:include>

		

	</div>

	<jsp:include page="Footer.jsp"></jsp:include>

	<script src="./js/scriptI.js"></script>
</body>
</html>