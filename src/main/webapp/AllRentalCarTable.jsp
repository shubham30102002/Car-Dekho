
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="beans.output.RentalCarOutputBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.action.CommonActionBean"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/style.css">
<style>
#AllCarsParent {
	width: 1200px;
	margin: auto;
}

table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #fff9f9;
}

th, td {
	text-align: center;
	padding: 12px;
	border: 1px solid #ddd;
}

th {
	background-image: linear-gradient(-60deg, #ff5858 0%, #f09819 100%);
}

tr:nth-child(even) {
	background-color: #d6dadd;
}

.btn {
	border: 2px solid black;
	background-color: white;
	color: black;
	padding: 10px 16px;
	font-size: 10px;
	cursor: pointer;
}

tr:hover {
	background-color: #cfd8dc;
}

.success {
padding:10px 8px;
border:none;
  border-color: #ff9800;
  color: white;
   background: #ff9800;
   cursor:pointer;
}

.success:hover {
   background: white;
  color: #ff9800;
}

.disabled {
   background: #ccc;
   cursor: not-allowed;
}
</style>
</head>
<body>

<%
	CommonActionBean.getAllCar(request, response);
	CommonActionBean.getRentalAllCarFull(request, response);
%>

<div id="AllCarsParent">
	<jsp:include page="AdminNavbar.jsp"></jsp:include>
	<h1 style="text-align: center;">All Rental Car table</h1>
	<div id="tableParentDiv" style="overflow-x: auto;">
		<table border='1' cellpadding='4' width='80%' align="center">
			<tr>
				<th>ID</th>
				<th>Car Brand</th>
				<th>Car Modal</th>
				<th>Date From</th>
				<th>Date To</th>
				<th>Fuel Type</th>
				<th>Price</th>
				<th>Car Location</th>
				<th>Car Type</th>
				<th>Status</th>
				<th>Update?Edit</th>
				<th>Status Change</th>
			</tr>
			<%
			ArrayList<RentalCarOutputBean> cars = (ArrayList<RentalCarOutputBean>) session.getAttribute("RentalCarFull");
			Iterator<RentalCarOutputBean> iterator = cars.iterator();
			while (iterator.hasNext()) {
				RentalCarOutputBean car = iterator.next();
				String isActive = car.getIsActive();
				boolean isActiveFlag = "Y".equals(isActive);
			%>
			<tr>
				<td><%= car.getId() %></td>
				<td><%= car.getCarBrand() %></td>
				<td><%= car.getCarModal() %></td>
				<td><%= car.getDateFrom() %></td>
				<td><%= car.getDateTo() %></td>
				<td><%= car.getFuelType() %></td>
				<td><%= car.getPrice() %></td>
				<td><%= car.getRegisterLocation() %></td>
				<td><%= car.getCarType() %></td>
				<td><%= isActive %></td>
				<td>
					<a href="./CommonController?action=updateRentalCar&carId=<%= car.getId() %>">
						<button 				class="success <%= isActiveFlag ? "" : "disabled" %>" <%= isActiveFlag ? "" : "disabled"
						 %>>Update</button>
					</a>
				</td>
				<td>
					<a href="./CommonController?action=isactiveChangeRental&carId=<%= car.getId() %>">
						<button class="success <%= isActiveFlag ? "disabled" : "" %>" <%= isActiveFlag ? "disabled" : "" %>>Active</button>
					</a>
				</td>
			</tr>
			<%
			}
			%>
		</table>
	</div>
	<br>
	<div>
		<a href="./AdminPage.jsp" style="color: red">&larr; Home</a>
	</div>
</div>
</body>
</html>
