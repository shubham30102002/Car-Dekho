<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
<style type="text/css">
.contact{
}
.contact {
    position: relative;
    display: inline-block;
}


.dropdown-menu {
    display: none; /* Hide by default */
    position: absolute; /* Position relative to the profile container */
    top: 100%; /* Position directly below the profile container */
    right: 0; /* Align the dropdown menu to the right side */
    background-color: #ffffff; /* Set the background color of the dropdown */
    padding: 10px; /* Adjust padding for size */
    border: 1px solid #ddd; /* Light border for definition */
    border-radius: 5px; /* Rounded corners */
    z-index: 10; /* Ensure it appears above other content */
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2); /* Add shadow for better visibility */
}

.contact:hover .dropdown-menu {
    display: block;
}

.dropdown-menu ul {
    list-style-type: none;
    padding: 0;
    margin: 0;
}

.dropdown-menu li {
    padding: 12px 16px; /* Adjust padding for item size */
}

.dropdown-menu a {
    text-decoration: none;
    color: #333; /* Text color for items */
    font-size: 16px; /* Adjust font size for readability */
    display: block; /* Ensure each item takes up full width */
}

.dropdown-menu a:hover {
    background-color: #007bff; /* Background color on hover */
    color: white; /* Text color on hover */
    border-radius: 4px; /* Rounded corners on hover */
}
</style>
</head>
<script src="/js/jQuery.js"></script>
<script src="/js/jqueryScript.js"></script> 
<body>

<%! public String userName(HttpServletRequest req){
	HttpSession session=req.getSession();
	
	if(session.getAttribute("username")!=null){
		return session.getAttribute("username").toString();
	}
	return "Login/Register";
 } 

 public String AdminName(HttpServletRequest req){
	 HttpSession session=req.getSession();
	 
	 if(session.getAttribute("createdBY")!=null){
		     System.out.println(session.getAttribute("createdBY"));
			return "View All Users";
		}
		return "";
 }
 %>
 
 <div id="NavContainer" class="">
	<div class="header">
			<header>
				<div class="logo">
					<a href="#"><img title="jsp" src="https://stimg.cardekho.com/images/cms/carnewsimages/editorimages/63b3f752b6f86.jpg?impolicy=resize&imwidth=420" width="150" height="100"
							alt="logo"></a>
				</div>
				<div class="srch-con">
					<div class="search-container">
						<div class="search">
							<div>
								<input id="search" title="start typing product name"
									placeholder="Search Car Brand" type="text">
							</div>
							<div id="serch-btn"><button><img src="images/search.png" title="search"
										alt="search" /></button></div>
						</div>
					</div>
					
					<div class="contact">
						<!-- <div id="LikeIconDiv">
							<a href=""><img src="https://iconape.com/wp-content/png_logo_vector/like.png" id="like-icon" alt="" srcset=""></a>
						</div> -->
						<a href="#" id="login-Btn" onclick="validate.showLoginPoup()">
							<img src="https://t4.ftcdn.net/jpg/00/65/77/27/360_F_65772719_A1UV5kLi5nCEWI0BNLLiFaBPEkUbv5Fv.jpg" alt="" srcset="" id="profile-img"> <sup id="loginProfile"><%=userName(request)%></sup>
							</a>
							
				<div class="dropdown-menu">
        <ul>
            <li><a href="./CommonController?action=logOut" class="logout-link" > Logout</a></li>
        </ul>
    </div>				
					
					
						<div id="clear-both"></div>
					</div>
					<div id="clear-both"></div>

				</div>
				<div id="clear-both"></div>
			</header>


			<hr>


			<nav>
				<div>

					
					<div class="nav-bar">
						<div class="nav-bar-content">
							<ul>
									  <div class="dropdown">
									<button class="dropbtn">SELL CAR
									</button>
									<div class="dropdown-content">
									  <a href="SellCarForm.jsp">Sell Your Car</a>
									</div>
								  </div> 
								  <div class="dropdown">
									<button class="dropbtn">RENT CAR
									</button>
									<div class="dropdown-content">
									  <a href="RentCarForm.jsp">Add rental cars</a>
									  
									</div>
								  </div> 
								   <div class="dropdown">
									<a href="AdminPage.jsp" id="adminPageBtn" ><button class="dropbtn" ><%=AdminName(request)%>
									</button></a>
									<!-- <div class="dropdown-content">
									  <a href="#">Explore new cars</a>
									  <a href="#">Electric cars</a>
									  <a href="#">Sell cars</a>
									</div> -->
								  </div> 
								  
								  
								<!-- <li><a href="#">Pages</a></li>
								<li><a href="#">Shop</a></li>
								<li><a href="#">Blog</a></li>
								<li><a href="#">Contact</a></li>
								<li><a href="#" onclick="formPopups.openRegister()" title="register"
										id="register-btn">Register</a></li>
								<li><a href="#">GET&nbsp;PRO</a></li> -->
								<div id="clear-both"></div>
							</ul>
						</div>

					</div>
					<!-- <div class="category">
						<div class="cars">
							<select title="state" name="category">
								<option value="Search By State" selected>State</option>
								<option value="Maharashtra">Maharashtra</option>
								<option value="Gujrat">Gujrat</option>
								<option value="Rajastan">Rajastan</option>
							</select>
						</div>
						<div id="clear-both"></div>
					</div> -->
					<div id="clear-both"></div>

				</div>
				<div id="clear-both"></div>
			</nav>
			<div id="clear-both"></div>
 </div>
 
 <script src="../js/scriptI.js"></script>

		
 </body>
</html>