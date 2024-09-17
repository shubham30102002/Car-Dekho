<%@page import="beans.input.RentalCarInputBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Update Rental Car Details</title>
    <link rel="stylesheet" href="./css/SellCarForm.css">
    <link rel="stylesheet" href="./css/style.css"> 
    <link rel="stylesheet" href="./css/index.css">
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
</head>
<body>
<% RentalCarInputBean car = (RentalCarInputBean) request.getAttribute("updateRentalcar"); %>

    <jsp:include page="AdminNavbar.jsp"></jsp:include>
    <div class="container">

        <div id="carFormDiv">
            <h2 style="text-align:center">Update Rental Car Details Form</h2>
            <br>
            <form id="carForm" action="./CommonActionBean4" method="post" enctype="multipart/form-data">
                <input type="hidden" name="action" value="updateRentalCar">
                <input type="hidden" name="carId" value="<%= car.getId() %>"> <!-- Hidden field for car ID -->

                <div class="form-group">
                    <label for="carBrand" class="label">Car Brand:</label>
                    <input type="text" id="carBrand" class="input-text" name="carBrand" value="<%= car.getCarBrand() %>" required>
                </div>
                <div class="form-group">
                    <label for="carModal" class="label">Car Model:</label>
                    <input type="text" id="carModal" class="input-text" name="carModal" value="<%= car.getCarModal() %>" required>
                </div>
                <div class="form-group">
                    <label for="FuelType" class="label">Fuel Type:</label>
                    <select id="FuelType" class="select" name="FuelType" required>
                        <option value="1" <%= car.getFuelType() == 1 ? "selected" : "" %>>Petrol</option>
                        <option value="2" <%= car.getFuelType() == 2 ? "selected" : "" %>>Diesel</option>
                        <option value="3" <%= car.getFuelType() == 3 ? "selected" : "" %>>Electric</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="price" class="label">Price:</label>
                    <input type="text" id="price" class="input-number" name="price" placeholder="Price per day" maxlength="5" oninput="this.value = this.value.replace(/[^0-9]/g, '').slice(0, 5);" value="<%= car.getPrice() %>" required>
                </div>
                <div class="form-group">
                    <label for="car_desc" class="label">Car Description:</label>
                    <textarea id="car_desc" class="input-text" name="car_desc" rows="4" placeholder="Up to 100 characters" maxlength="100" required><%= car.getCar_desc() %></textarea>
                </div>
                <div class="form-group">
                    <label for="RegisterLocation" class="label">Register Location:</label>
                    <select id="RegisterLocation" class="select" name="RegisterLocation" required>
                        <option value="Mumbai" <%= "Mumbai".equals(car.getRegisterLocation()) ? "selected" : "" %>>Mumbai</option>
                        <option value="Pune" <%= "Pune".equals(car.getRegisterLocation()) ? "selected" : "" %>>Pune</option>
                        <option value="Nashik" <%= "Nashik".equals(car.getRegisterLocation()) ? "selected" : "" %>>Nashik</option>
                        <option value="Delhi" <%= "Delhi".equals(car.getRegisterLocation()) ? "selected" : "" %>>Delhi</option>
                        <option value="Banglore" <%= "Banglore".equals(car.getRegisterLocation()) ? "selected" : "" %>>Banglore</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="carType" class="label">Car Type:</label>
                    <select id="carType" class="select" name="carType" required>
                        <option value="1" <%= car.getCarType() == 1 ? "selected" : "" %>>SUV</option>
                        <option value="2" <%= car.getCarType() == 2 ? "selected" : "" %>>Sedan</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="carImage" class="label">Upload New Car Image:</label>
                    <input type="file" id="carImage" class="input-file" name="carImage" accept="image/*">
                </div> 
                <input type="submit" id="submit-button" value="Update Details">
            </form>
        </div>
<div class="button-container">
            <a href="AdminPage.jsp" class="button"><<<<</a>
        </div>
    </div>
</body>
</html>
