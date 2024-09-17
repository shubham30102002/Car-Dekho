<%@page import="beans.input.CarInputBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Car Details</title>
<link rel="stylesheet" href="./css/SellCarForm.css">
<link rel="stylesheet" href="./css/style.css"> 
<link rel="stylesheet" href="./css/index.css">
</head>
<style>
 .invalid {
        background-color: #ffb3b3;
    }
    .container {
            position: relative;
            padding: 20px;
        }
    
        .button-container {
            position: absolute;
            top: 50%;
            left: 0;
            transform: translateY(-50%);
            padding-left: 20px;
        }

        .button {
            text-decoration: none;
            padding: 10px 20px;
            color: white;
            background-color: #007bff;
            border-radius: 5px;
            font-weight: bold;
        }
        .button:hover {
            background-color: #0056b3;
        }
    
</style>
<body>
<% CarInputBean car = (CarInputBean) request.getAttribute("updatecar"); %>

<jsp:include page="AdminNavbar.jsp"></jsp:include>
<div class="container">

<div id="carFormDiv">
    <h2 style="text-align:center">Update Car Details</h2>
    <br>
    <form id="carForm" action="./CommonActionBean3" method="post" enctype="multipart/form-data">
    <input type="hidden" name="action" value="updateCar">
    <input type="hidden" name="carId" value="<%= car.getId() %>"> <!-- Hidden field for car ID -->

    <div class="form-group">
        <label for="carBrand" class="label">Car Brand:</label>
        <input type="text" id="carBrand" class="input-text" name="carBrand" value="<%= car.getCarBrand() %>">
    </div>
    <div class="form-group">
        <label for="carModal" class="label">Car Model:</label>
        <input type="text" id="carModal" class="input-text" name="carModal" value="<%= car.getCarModal() %>">
    </div>
    <div class="form-group">
        <label for="registerDate" class="label">Register Date:</label>
        <input type="date" id="registerDate" class="input-text" name="registerDate" value="<%= car.getRegisterDate() %>" min="1000-01-01" max="9999-12-31">
    </div>
    <div class="form-group">
        <label for="RegisterState" class="label">Register State:</label>
        <select id="RegisterState" class="select" name="RegisterState">
            <option value="Maharashtra" <%= "Maharashtra".equals(car.getRegisterState()) ? "selected" : "" %>>Maharashtra</option>
            <option value="Gujrat" <%= "Gujrat".equals(car.getRegisterState()) ? "selected" : "" %>>Gujrat</option>
            <option value="Goa" <%= "Goa".equals(car.getRegisterState()) ? "selected" : "" %>>Goa</option>
            <option value="Rajastan" <%= "Rajastan".equals(car.getRegisterState()) ? "selected" : "" %>>Rajastan</option>
        </select>
    </div>
    <div class="form-group">
        <label for="FuelType" class="label">Fuel Type:</label>
        <select id="FuelType" class="select" name="FuelType">
            <option value="1" <%= car.getFuelType() == 1 ? "selected" : "" %>>Petrol</option>
            <option value="2" <%= car.getFuelType() == 2 ? "selected" : "" %>>Diesel</option>
            <option value="3" <%= car.getFuelType() == 3 ? "selected" : "" %>>Electric</option>
        </select>
    </div>
    <div class="form-group">
        <label for="price" class="label">Price:</label>
        <input type="text" id="price" class="input-number" placeholder="Price in Lakh" name="price" maxlength="2" value="<%= car.getPrice() %>" oninput="this.value = this.value.replace(/[^0-9]/g, '').slice(0, 2);">
    </div>
    <div class="form-group">
        <label for="transmission" class="label">Transmission:</label>
        <select id="transmission" class="select" name="transmission">
            <option value="Manual" <%= "Manual".equals(car.getTransmission()) ? "selected" : "" %>>Manual</option>
            <option value="Automatic" <%= "Automatic".equals(car.getTransmission()) ? "selected" : "" %>>Automatic</option>
        </select>
    </div>
    <div class="form-group">
        <label for="Engine_cc" class="label">Engine CC:</label>
        <input type="number" id="Engine_cc" class="input-number" name="Engine_cc" value="<%= car.getEngine_cc() %>">
    </div>
    <div class="form-group">
        <label for="car_desc" class="label">Car Description:</label>
        <textarea id="car_desc" class="input-text" name="car_desc" rows="4" placeholder="upto 100 characters"><%= car.getCar_desc() %></textarea>
    </div>
    <div class="form-group">
        <label for="carType" class="label">Car Type:</label>
        <select id="carType" class="select" name="carType">
            <option value="1" <%= car.getCarType() == 1 ? "selected" : "" %>>SUV</option>
            <option value="2" <%= car.getCarType() == 2 ? "selected" : "" %>>Sedan</option>
            <option value="3" <%= car.getCarType() == 3 ? "selected" : "" %>>Hatchback</option>
            <option value="4" <%= car.getCarType() == 4 ? "selected" : "" %>>Luxury</option>
            <option value="5" <%= car.getCarType() == 5 ? "selected" : "" %>>XUV</option>
        </select>
    </div>
    <div class="form-group">
        <label for="carImage" class="label">Upload Car Image:</label>
        <input type="file" id="carImage" class="input-file" name="carImage" accept="image/*">
    </div>
    <input type="submit" id="submit-button" value="Update Details">
</form>
<div class="button-container">
            <a href="AdminPage.jsp" class="button"><<<<</a>
        </div>
</div>
</div>

<script src="../js/scriptI.js"></script>

</body>
</html>
