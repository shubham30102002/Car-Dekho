package beans.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ajaxHandler.AjaxHandler;
import beans.input.CarInputBean;
import beans.input.RentalCarInputBean;
import beans.input.UserInputBean;
import beans.output.CarOutputBean;
import beans.output.OutputBean;
import beans.output.RentalCarOutputBean;
import helper.CommonHelper;
import impl.Implementation;

/**
 * Servlet implementation class CommonActionBean
 */
public class CommonActionBean extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CommonActionBean() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
      
	public static HttpServletResponse registeruser(HttpServletRequest request, HttpServletResponse response){
		UserInputBean us= new UserInputBean();
		
		us.setFirstname(request.getParameter("firstname"));
		us.setLastname(request.getParameter("lastname"));
		us.setMobileNo(Long.parseLong(request.getParameter("mobileNo")));
		us.setEmailId(request.getParameter("email"));
		us.setPassword(request.getParameter("password"));
		us.setAddress(request.getParameter("addressArea"));
		us.setState(request.getParameter("state"));
		us.setCity(request.getParameter("city"));
		us.setPincode(Long.parseLong(request.getParameter("pincode")));
		
		CommonHelper helper=new CommonHelper();
		int result=helper.addUser(us);
		if (result==1) {
			response.setStatus(200);
			 request.setAttribute("successMessage", "User successfully registered!");
		} 
		if(result== -1){
			request.setAttribute("EmailError", "Error: Unable to register user. Email id already exists;");
			
		}else {
			response.setStatus(400);
			request.setAttribute("errorMessage", "Error: Unable to register user. Please try again later.");

		}
		try {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return response;
		
		
	}
	
	public static void loginUser(String email,String Password, HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException {
//	    String mobileNo = request.getParameter("Username");
//	    String password = request.getParameter("Password");
		
	    OutputBean op=new OutputBean();
	    op.setUsername(email);
	    op.setPassword(Password);
	    System.out.println("Action Bean "+email+" "+Password);
	   // Implementation.loginUser(op, response);
	    AjaxHandler.loginUser(op, response,request);
  
	}
	
	public static void loginAdmin(String email,String Password, HttpServletResponse response,HttpServletRequest request) throws ServletException, IOException {
//	    String mobileNo = request.getParameter("Username");
//	    String password = request.getParameter("Password");
		
	    OutputBean op=new OutputBean();
	    op.setUsername(email);
	    op.setPassword(Password);
	    System.out.println("Admin Action Bean "+email+" "+Password);
	   // Implementation.loginUser(op, response);
	    AjaxHandler.loginAdmin(op, response,request);
  
	}
	
	public static void getAllUser(HttpServletRequest request, HttpServletResponse response) {
	    List<UserInputBean> users = Implementation.getAllUserImpl();

	    if (users != null) {
	        HttpSession session = request.getSession();
	        session.setAttribute("user", users);

	        try {
	        	System.out.println("User list"+users);
	           // RequestDispatcher rDispatcher = request.getRequestDispatcher("../jsp/GetUserTable.jsp");
	            //rDispatcher.forward(request, response);
	            response.sendRedirect("GetUserTable.jsp");
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        
	       System.out.println("Else block");
	    }
	}
	
	public static void getAllCar(HttpServletRequest request, HttpServletResponse response) {
	    List<CarOutputBean> cars = Implementation.getAllCar();

	    if (cars != null) {
	        HttpSession session = request.getSession();
	        session.setAttribute("Car", cars);

	        try {
	        	System.out.println("Car list"+cars);
	           // RequestDispatcher rDispatcher = request.getRequestDispatcher("../jsp/GetUserTable.jsp");
	            //rDispatcher.forward(request, response);
	          //  response.sendRedirect("index.jsp");
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	        }
	    } else {
	        
	       System.out.println("Else block");
	    }
	}

	public static void getRentalAllCar(HttpServletRequest request, HttpServletResponse response) {
	    List<RentalCarOutputBean> cars = Implementation.getAllRentalCar();

	    if (cars != null) {
	        HttpSession session = request.getSession();
	        session.setAttribute("RentalCar", cars);

	        try {
	        	System.out.println("RentalCar list"+cars);
	           // RequestDispatcher rDispatcher = request.getRequestDispatcher("../jsp/GetUserTable.jsp");
	            //rDispatcher.forward(request, response);
	          //  response.sendRedirect("index.jsp");
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	        }
	    } else {
	        
	       System.out.println("Else block");
	    }
	}

	public static void getRentalAllCarFull(HttpServletRequest request, HttpServletResponse response) {
	    List<RentalCarOutputBean> cars = Implementation.getAllRentalCarFull();

	    if (cars != null) {
	        HttpSession session = request.getSession();
	        session.setAttribute("RentalCarFull", cars);

	        try {
	        	System.out.println("RentalCar list"+cars);
	           // RequestDispatcher rDispatcher = request.getRequestDispatcher("../jsp/GetUserTable.jsp");
	            //rDispatcher.forward(request, response);
	          //  response.sendRedirect("index.jsp");
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	        }
	    } else {
	        
	       System.out.println("Else block");
	    }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String url=request.getParameter("action");  
		if (url.equals("register")) {
			
			UserInputBean us= new UserInputBean();
			
			us.setFirstname(request.getParameter("firstname"));
			us.setLastname(request.getParameter("lastname"));
			us.setMobileNo(Long.parseLong(request.getParameter("mobileNo")));
			us.setEmailId(request.getParameter("email"));
			us.setPassword(request.getParameter("password"));
			us.setAddress(request.getParameter("addressArea"));
			us.setState(request.getParameter("state"));
			us.setCity(request.getParameter("city"));
			us.setPincode(Long.parseLong(request.getParameter("pincode")));
			
			CommonHelper helper=new CommonHelper();
			if (helper.addUser(us)) {
				response.setStatus(200);
				 request.setAttribute("successMessage", "User successfully registered!");
			} else {
				response.setStatus(400);
				request.setAttribute("errorMessage", "Error: Unable to register user. Please try again later.");

			}
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		} 
		else if(url.equals("Login"))
		{
			String mobileNo=request.getParameter("Username");
			String password=request.getParameter("Password");
			
		
			
		}
		else {
            System.out.println("Null request");
		}
	}
*/
	
	    System.out.println("addCar Action bean start");
	    // Prepare to handle file uploads
        if (ServletFileUpload.isMultipartContent(request)) {

        
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    CarInputBean carInputBean = new CarInputBean();
	    String filePath = null;

	    try {
	        // Parse the request
	        List<FileItem> items = upload.parseRequest(request);
	        for (FileItem item : items) {
	            if (item.isFormField()) {
	                // Handle form fields
	                String fieldName = item.getFieldName();
	                String fieldValue = item.getString();
	                
	                switch (fieldName) {
	                    case "carBrand":
	                        carInputBean.setCarBrand(fieldValue);
	                        break;
	                    case "carModal":
	                        carInputBean.setCarModal(fieldValue);
	                        break;
	                    case "registerDate":
	                        carInputBean.setRegisterDate(fieldValue);
	                        break;
	                    case "RegisterState":
	                        carInputBean.setRegisterState(fieldValue);
	                        break;
	                    case "FuelType":
	                        carInputBean.setFuelType(Integer.parseInt(fieldValue));
	                        break;
	                    case "price":
	                        carInputBean.setPrice(Double.parseDouble(fieldValue));
	                        break;
	                    case "transmission":
	                        carInputBean.setTransmission(fieldValue);
	                        break;
	                    case "Engine_cc":
	                        carInputBean.setEngine_cc(Integer.parseInt(fieldValue));
	                        break;
	                    case "car_desc":
	                        carInputBean.setCar_desc(fieldValue);
	                        break;
	                    case "carType":
	                        carInputBean.setCarType(Integer.parseInt(fieldValue));
	                        break;
	                }
	            } else {
	                // Handle file uploads
	                String fileName = item.getName();
	                if (fileName != null && !fileName.isEmpty()) {
	                    // Define the path to save the file
	                	//String absolutePath = "C:/AshuTraining/Cardekho/src/main/webapp/images/allcarimages/Jeep.webp";

	                    String uploadPath = "C:/Users/Shubham Gupta/eclipse-workspace/Training/Cardekho/src/main/webapp/images/allcarimages";
	                    File uploadDir = new File(uploadPath);
	                    if (!uploadDir.exists()) uploadDir.mkdir();

	                    filePath = uploadPath + File.separator + fileName;
	                    File file = new File(filePath);
	                    item.write(file);
	                    
	                    
                         String myFilePath="images/allcarimages"+File.separator + fileName;
	                    // Save the file path in the bean
	                    carInputBean.setCarImg(myFilePath);
	                }
	            }
	        }

	        // Use CommonHelper to add car details
	        CommonHelper helper = new CommonHelper();
	        HttpSession session = request.getSession();

	        if (session.getAttribute("username") != null) {
	            if (helper.addCar(carInputBean)) {
	                response.setStatus(HttpServletResponse.SC_OK);
	                request.setAttribute("successMessage", "Car successfully sent for verification!");
	            } else {
	                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	                request.setAttribute("errorMessage", "Error: Something went wrong. Please try again later.");
	            }
	        } else {
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            request.setAttribute("errorMessage", "Error: Please login first.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        request.setAttribute("errorMessage", "Error: File upload failed. Please try again.");
	    }

	    try {
	        request.getRequestDispatcher("AdminPage.jsp").forward(request, response);
	    } catch (ServletException | IOException e) {
	        e.printStackTrace();
	    }

	    System.out.println("addCar Action bean end");
	}
        	
        	
        	
     	}

	public static void logoutuser(HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session = request.getSession(false); 
		if (session != null) {
		    session.invalidate(); 
		}
		try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
	}


	public static void getCarByLocation(HttpServletRequest request,
			HttpServletResponse response) {
		 AjaxHandler.getCarByLocation(response,request);
		
	}


	public static void likedCar(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("Action Start");
		HttpSession session =request.getSession();
		int cId=Integer.parseInt(request.getParameter("CarId"));
		int uId=Integer.parseInt(session.getAttribute("userId").toString());
		String status="";
		try{
			status=CommonHelper.likedCar(cId,uId);
			System.out.println("Action try");
			if(status!=null){
				System.out.println("Action if");
				response.setStatus(200);
				session.setAttribute("likedStatus", status);
				response.getWriter().write(status);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static void rentalLikedCar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("Action Start");
		HttpSession session =request.getSession();
		
		 Object userIdObj = session.getAttribute("userId");
	        if (userIdObj == null) {
	            // If userId is not found in the session, respond with an error
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 status code
	            try {
					response.getWriter().write("User not logged in");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            return;
	        }
		int cId=Integer.parseInt(request.getParameter("CarId"));
		int uId=Integer.parseInt(session.getAttribute("userId").toString());
		String status="";
		try{
			status=CommonHelper.rentallikedCar(cId,uId);
			System.out.println("Action try");
			if(status!=null){
				System.out.println("Action if");
				response.setStatus(200);
				session.setAttribute("likedStatus", status);
				response.getWriter().write(status);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}


	public static void getLikedCars(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session =request.getSession();
		int uId=Integer.parseInt(session.getAttribute("userId").toString());
		List<CarOutputBean> cars = Implementation.getLikedCars(uId);
		 if (cars != null) {
		        
		        session.setAttribute("GetLikedCar", cars);

		     
		    } else {
		        
		       System.out.println("Else block");
		    }
		
	}

	public static void getRetalLikedCars(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session =request.getSession();
		int uId=Integer.parseInt(session.getAttribute("userId").toString());
		List<RentalCarOutputBean> cars = Implementation.getRentalLikedCars(uId);
		 if (cars != null) {
		        
		        session.setAttribute("GetRentalLikedCar", cars);

		        
		    } else {
		        
		       System.out.println("Else block");
		    }
		
	}


	public static void searchCarBy(HttpServletRequest request,
			HttpServletResponse response) {
		int price=Integer.parseInt(request.getParameter("Budget"));
		int car_type=Integer.parseInt(request.getParameter("CarType"));
		List<CarOutputBean> cars = Implementation.searchCarBy(price,car_type);
		
		if (cars != null) {
	        HttpSession session = request.getSession();
	        session.setAttribute("SearchCarBYPrice", cars);

	        try {
	        	System.out.println("Price List"+cars);
	            RequestDispatcher rDispatcher = request.getRequestDispatcher("index.jsp");
	            rDispatcher.forward(request, response);
	           // response.sendRedirect("index.jsp");
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	        }
	    } else {
	        
	       System.out.println("Else block");
	    }
		
	}

	public static void searchCarBrand(HttpServletRequest request, HttpServletResponse response) {
        String searchQuery = request.getParameter("searchQuery");
System.out.println("------"+searchQuery);
	List<CarOutputBean> cars = Implementation.searchCarBrand(searchQuery);
		
		if (cars != null) {
			System.out.println("-,-,-,-,");
	        HttpSession session = request.getSession();
	        session.setAttribute("SearchCarBYBrand", cars);

	        try {
	        	System.out.println("Brand List"+cars);
	        //    RequestDispatcher rDispatcher = request.getRequestDispatcher("index.jsp");
	          //  rDispatcher.forward(request, response);
	            response.sendRedirect("index.jsp");
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	        }
	    } else {
	        
	       System.out.println("Else block");
	    }
		
		
	}

	public static void searchCarRentalBrand(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		 String searchQuery = request.getParameter("searchQuery");
		 System.out.println("------"+searchQuery);
		 	List<RentalCarOutputBean> cars = Implementation.searchCarRentalBrand(searchQuery);
		 		
		 		if (cars != null) {
		 			System.out.println("-,-,-,-,");
		 	        HttpSession session = request.getSession();
		 	        session.setAttribute("SearchCarBYRentalBrand", cars);

		 	        try {
		 	        	System.out.println("Brand List"+cars);
		 	     //       RequestDispatcher rDispatcher = request.getRequestDispatcher("RentalCarIndex.jsp");
		 	       //     rDispatcher.forward(request, response);
		 	             response.sendRedirect("RentalCarIndex.jsp");
			 	           
		 	            
		 	        } catch (Exception e) {
		 	            e.printStackTrace();
		 	            
		 	        }
		 	    } else {
		 	        
		 	       System.out.println("Else block");
		 	    }
		 		
		 		

	}



	public static HttpServletResponse updateUser(HttpServletRequest request,
			HttpServletResponse response) {
         UserInputBean us= new UserInputBean();
		us.setId(Integer.parseInt(request.getParameter("userId")));
		us.setFirstname(request.getParameter("firstname"));
		us.setLastname(request.getParameter("lastname"));
		us.setMobileNo(Long.parseLong(request.getParameter("mobileNo")));
		us.setEmailId(request.getParameter("email"));
		us.setPassword(request.getParameter("password"));
		us.setAddress(request.getParameter("addressArea"));
		us.setState(request.getParameter("state"));
		us.setCity(request.getParameter("city"));
		us.setPincode(Long.parseLong(request.getParameter("pincode")));
		
		
		CommonHelper helper=new CommonHelper();
		if (helper.updateUser(us)) {
			response.setStatus(200);
			 request.setAttribute("UpdatesuccessMessage", "Request Send for updation ");
			 
		} else {
			response.setStatus(400);
			request.setAttribute("UpdateerrorMessage", "Error: Unable to update user. Please try again later.");

		}
		RequestDispatcher rDispatcher = request.getRequestDispatcher("ViewUser.jsp");
        try {
			rDispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		}
		return response;
	}


	public static void userInactive(HttpServletRequest request,
			HttpServletResponse response) {
		int userId=Integer.parseInt(request.getParameter("userId"));
		CommonHelper helper=new CommonHelper();
		int status=helper.userInactive(userId);
		if(status == 1){
			RequestDispatcher rDispatcher = request.getRequestDispatcher("AdminPage.jsp");
			try {
				rDispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				
				e.printStackTrace();
			}
		}
		
	}


	public static void userActive(HttpServletRequest request, HttpServletResponse response) {
		int userId=Integer.parseInt(request.getParameter("userId"));
		CommonHelper helper=new CommonHelper();
		int status=helper.userActive(userId);
		if(status == 1){
			RequestDispatcher rDispatcher = request.getRequestDispatcher("AdminPage.jsp");
			try {
				rDispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				
				e.printStackTrace();
			}
		}
				
	}

	public static void updateSingleCar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String carIdParam = request.getParameter("carId");
        if (carIdParam == null || carIdParam.isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        System.out.println(" " +carIdParam);
        int carId = Integer.parseInt(carIdParam);
		CommonHelper helper=new CommonHelper();

        // Fetch car details from database
        CarInputBean car = helper.getCarDetailsbyId(carId);
        System.out.println(car.toString());
        if (car != null) {
            // Set car details as a request attribute
            request.setAttribute("updatecar", car);
            // Forward to update form page
            RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateCar.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }

	}


	public static void updateRentalCar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		 String carIdParam = request.getParameter("carId");
	        if (carIdParam == null || carIdParam.isEmpty()) {
	            response.sendRedirect("error.jsp");
	            return;
	        }

	        System.out.println(" " +carIdParam);
	        int carId = Integer.parseInt(carIdParam);
			CommonHelper helper=new CommonHelper();

	        // Fetch car details from database
	        RentalCarInputBean car = helper.getRentalCarDetailsbyId(carId);
	        System.out.println(car.toString());
	        if (car != null) {
	            // Set car details as a request attribute
	            request.setAttribute("updateRentalcar", car);
	            // Forward to update form page
	            RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateRentalCar.jsp");
	            dispatcher.forward(request, response);
	        } else {
	            response.sendRedirect("error.jsp");
	        }

		
	}


	public static void addRentalBooking(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    HttpSession session = request.getSession(false);
System.out.println("-----------------");
	    // Check if session is null or userId is not present
	    if (session == null || session.getAttribute("userId") == null) {
	        response.setContentType("text/plain");
	        response.getWriter().write("not_logged_in");
	        return;
	    }

	    try {
	        int rcarId = Integer.parseInt(request.getParameter("rcarId"));
	        String fromDate = request.getParameter("fromDate");
	        String toDate = request.getParameter("toDate");
	        // Ensure userId is retrieved correctly
	        int userId = (int) session.getAttribute("userId"); // Assuming userId is an integer in session

	        // Log the values for debugging
	        System.out.println("Car ID: " + rcarId);
	        System.out.println("From Date: " + fromDate);
	        System.out.println("To Date: " + toDate);
	        System.out.println("User ID: " + userId);

	        CommonHelper helper = new CommonHelper();
	        boolean status = helper.addRentalBooking(rcarId, fromDate, toDate, userId);

	        if (status) {
	            response.setContentType("text/plain");
	            response.getWriter().write("success");
	        } else {
	            response.setContentType("text/plain");
	            response.getWriter().write("failure");
	        }
	    } catch (NumberFormatException e) {
	        // Handle number format exceptions
	        response.setContentType("text/plain");
	        response.getWriter().write("error");
	        e.printStackTrace();
	    } catch (Exception e) {
	        // Handle any other exceptions
	        response.setContentType("text/plain");
	        response.getWriter().write("error");
	        e.printStackTrace();
	    }
	}
	public static void isactiveChangeRental(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
        int carId = Integer.parseInt(request.getParameter("carId"));

		CommonHelper helper=new CommonHelper();
		
		if(helper.isactiveChangeRental(carId)) {
            try {
				response.sendRedirect("ViewRentalCarDetails.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else {
            try {
				response.sendRedirect("error.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	
	
	

	public static void searchrentedCarByLocation(HttpServletRequest request,
				HttpServletResponse response) {
			String location=(String)(request.getParameter("location"));
			
			List<RentalCarOutputBean> Rentedcars = Implementation.searchRentedCarBy(location);
			
			if (Rentedcars != null) {
		        HttpSession session = request.getSession();
		        session.setAttribute("searchByRentedCar", Rentedcars);

		        try {
		        	System.out.println("Location List"+Rentedcars);
		            RequestDispatcher rDispatcher = request.getRequestDispatcher("RentalCarIndex.jsp");
		            rDispatcher.forward(request, response);
		           // response.sendRedirect("index.jsp");
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		            
		        }
		    } else {
		        
		       System.out.println("Else block");
		    }
			
		}

	public static void SellCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        String carIdParam = request.getParameter("carId");
	        String userIdParam = request.getParameter("UserId");
	        
	        if (carIdParam == null || userIdParam == null) {
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            response.getWriter().write("Required parameters are missing.");
	            return;
	        }
	        
	        int carSelledId = Integer.parseInt(carIdParam);
	        int carBuyUserId = Integer.parseInt(userIdParam);
	        
	        // Log the parameters for debugging
	        System.out.println("Car ID: " + carSelledId);
	        System.out.println("User ID: " + carBuyUserId);
	        
	        CommonHelper helper = new CommonHelper();
	        boolean carSellStatus = helper.carSell(carSelledId, carBuyUserId);
	        
	        if (carSellStatus) {
	            response.setContentType("text/plain");
	            response.getWriter().write("success");
	        } else {
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            response.getWriter().write("Failed to sell the car. Please try again.");
	        }
	    } catch (NumberFormatException e) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        response.getWriter().write("Invalid input format.");
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        response.getWriter().write("An error occurred. Please try again.");
	        e.printStackTrace();
	    }
	}

	public static void getUserCars(HttpServletRequest request, HttpServletResponse response) {
	    HttpSession session = request.getSession();
	    int uId = Integer.parseInt(session.getAttribute("userId").toString());

	    List<CarOutputBean> soldCars = Implementation.getSoldCars(uId);
	    List<RentalCarOutputBean> rentedCars = Implementation.getRentedCars(uId);

	    session.setAttribute("SoldCars", soldCars);
	    session.setAttribute("RentedCars", rentedCars);
	}




	
}
