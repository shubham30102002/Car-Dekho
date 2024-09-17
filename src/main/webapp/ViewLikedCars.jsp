
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@page import="beans.action.CommonActionBean"%>
<%@page import="beans.output.RentalCarOutputBean"%>
<%@page import="beans.output.CarOutputBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>

<style>
#cardBody {
    display: inline-block;
    width:250px;
    height: 330px;
    border: 1px solid grey;
    margin: 14px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    color: black;
    vertical-align: top; 
    font-size: 16px; 
}
#carCardContent {
    padding:0px 3px 3px 15px;
}
#carCardprice {
    font-size: 20px;
    font-weight: bold;
}
#carCardName {
    line-height: 40px;
    font-size: 20px;
    font-weight: bold;
}
#cardButtonContainer {
    display: flex;
    gap: 10px;
    margin-top: 10px;
}
#cardButton, #cardButtonForREnt {
    padding: 10px 8px;
    background-color: white;
    border: 1px solid #f75d34;
    color: #f75d34;
    border-radius: 5px;
    cursor: pointer;
    text-align: center;
    flex: 1;
}
#cardButton:hover, #cardButtonForREnt:hover {
    background-color: #f75d34;
    border-color: white;
    color: white;
}
#cardButton:disabled, #cardButtonForREnt:disabled {
    background-color: grey;
    border-color: grey;
    color: white;
    cursor: not-allowed;
}
#imgDiv {
    height: 200px;
    width: 250px;
    border-radius: 1px;
}
</style>
</head>
<body>

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
%><div id="alertBoxLogin" style="display:none;">
    <span id="msglogin"></span>
    <span class="closebtn">&times;</span>
    <div id="progressBarLogin"></div>
</div>



        <h3>Buy Liked Cars :</h3>

<div>
<% 
CommonActionBean.getLikedCars(request, response);
CommonActionBean.getRetalLikedCars(request, response);    

ArrayList searchCars = (ArrayList)session.getAttribute("GetLikedCar");
if (searchCars != null) {
    Iterator iterator1 = searchCars.iterator();
    while(iterator1.hasNext()) {
        CarOutputBean cars = (CarOutputBean) iterator1.next();
%>
    <div id="cardBody" class="">
        <div id="imgDiv">
            <img id="imgDiv" src="<%=cars.getCarImg() %>" alt="image not found">
        </div>  
        <div id="carCardContent">
            <span id="carCardName"><%= cars !=null ? cars.getCarBrand() : "No Car Searched" %></span> <br>
            <span id="carCardprice">Rs <%= cars.getPrice() %> lakh</span> <br>
            <div id="cardButtonContainer">
                <form action="./ViewCarDetails.jsp?id=<%= cars.getId() %>">
                    <input type="hidden" value="<%= cars.getId() %>" name="id"/>
                    <input type="submit" value="<%= cars.getIsActive().equals("N") ? "SOLD OUT" : "BUY CAR" %>" id="cardButton" <%= cars.getIsActive().equals("N") ? "disabled" : "" %> />
                </form>
              </div>
        </div>
    </div>
<% 
    }
} else {
%>
    <p>No liked cars found yet.</p>
<% } %>
</div>
<div>

        <h3>Rental Liked Cars :</h3>

        <% 
        // Assuming session is already available

        ArrayList<RentalCarOutputBean> rentalCars = (ArrayList<RentalCarOutputBean>) session.getAttribute("GetRentalLikedCar");
        if (rentalCars != null && !rentalCars.isEmpty()) {
            for (RentalCarOutputBean car : rentalCars) {
        %>
        <div id="cardBody">
            <div id="imgDiv">
                <img id="imgDiv" src="<%= car.getCarImg() %>" alt="image not found">
            </div>
            <div id="carCardContent">
                <span id="carCardName"><%= car.getCarBrand() %></span> <br>
                <span id="carCardPrice">Rs <%= car.getPrice() %> per days</span> <br>
                <div id="cardButtonContainer">
                    
                    <form action="./ViewRentalCarDetails.jsp" method="get">
                        <input type="hidden" name="id" value="<%= car.getId() %>">
                    <input type="submit" value="<%= car.getIsActive().equals("N") ? "RENTED OUT" : "TAKE ON RENT" %>" id="cardButton" <%= car.getIsActive().equals("N") ? "disabled" : "" %> />
                    </form>
                </div>
            </div>
        </div>
        <% 
            }
        } else {
        %>
        <p>No rental liked cars found yet.</p>
        <% } %>
    </div>

<div>
    <a href="./index.jsp" style="color:red">&larr; Home</a> 
</div>
<jsp:include page="Footer.jsp"></jsp:include>     
</body>
</html>
