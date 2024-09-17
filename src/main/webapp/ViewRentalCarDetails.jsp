<%@page import="java.util.Iterator"%>
<%@page import="beans.output.RentalCarOutputBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/Footer.css">
<link rel="stylesheet" href="./css/ViewCarDetails.css">
<link rel="stylesheet" href="./css/index.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="./js/jQuery.js"></script>
<script src="./js/jqueryScript.js"></script>

<style type="text/css">

#bookingFormDiv {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }#rentalForm input, rentalForm select {
            width: 20%;
            padding: 10px;
            padding-right: 1px;
            margin-bottom: 10px;
            margin-right: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
</style>
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
%> <%int id=Integer.parseInt(request.getParameter("id")); 
  HttpSession session2 = request.getSession();
  ArrayList cars = (ArrayList) session.getAttribute("RentalCar");
  RentalCarOutputBean selectedCar = null;
  
  Iterator iterator = cars.iterator();
  while(iterator.hasNext()){
	  RentalCarOutputBean car = (RentalCarOutputBean) iterator.next();
      if(car.getId()==id){
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
        <sapn id="carImage">
         <img src=<%=selectedCar.getCarImg() %> width="450px" height="300px" alt="image not found" id="carImageSrc"/>
        </sapn>
        <span id="deatailsDiv">
     <h1 id="brandName">Car Brand : <%= selectedCar != null ? selectedCar.getCarBrand() : "No car selected" %></h1> 
    <br>
    <h3 id="price">Rs : <%= selectedCar != null ? selectedCar.getPrice() : "N/A" %> rupees*</h3>
    <br>
    <h3 id="ViewCarModal">Modal : <%= selectedCar != null ? selectedCar.getCarModal() : "N/A" %></h3>
    <br>
    <h5 id="ViewCarType">Car Type : <%= carType %></h5>
    <br>
    <h5 id="ViewCarFuelType">Fuel Type : <%= selectedCar.getFuelType() %></h5>
    <br>
    <h4 id="viewDesc">Description: <%= selectedCar != null ? selectedCar.getCar_desc() : "N/A" %></h4>
    <br>
    <h4 id="ViewState">Register City: <%= selectedCar.getRegisterLocation() %></h4>
</span>
<img src="images/unliked.png" alt="img not found" srcset=""
				id="LikedIconImg" onclick="likedCar1(<%=selectedCar.getId()%>)"> 
				       <jsp:include page="LoginForm.jsp"></jsp:include>
        </div>
        
        </div>
        <div id="bookingFormDiv">
        <h2  id="h2boocar">Book This Car</h2>
        <form action="./CommonController" method="post" id="rentalForm">
            <input type="hidden" name="rcarId" value="<%= selectedCar.getId() %>">
            <input type="hidden" name="action" value="addRentalBooking">
            <label for="fromDate" class="lableColor">From Date: <span class="starColor">*</span></label>
            <input type="date" id="fromDate" name="fromDate" required>
            
            <label for="toDate" class="lableColor">To Date: <span class="starColor">*</span></label>
            <input type="date" id="toDate" name="toDate" required>
            <input type="button" class="btn" value="Book Now" onclick="handleBuyCar1()">
        </form>
        
        
    </div>
                    <a href="./index.jsp" style="color:red">&larr; Home</a> 
    
        
  <jsp:include page="Footer.jsp"></jsp:include>

<script src="./js/scriptI.js"></script>

</body>
</html>