package beans.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import beans.input.RentalCarInputBean;
import helper.CommonHelper;

/**
 * Servlet implementation class CommonActionBean2
 */
@WebServlet("/CommonActionBean2")
public class CommonActionBean2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommonActionBean2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  System.out.println("addCar Action bean start");
		    // Prepare to handle file uploads
	        if (ServletFileUpload.isMultipartContent(request)) {
	    	  	
		  	    DiskFileItemFactory factory = new DiskFileItemFactory();
		  	    ServletFileUpload upload = new ServletFileUpload(factory);
		  	    RentalCarInputBean carInputBean = new RentalCarInputBean();
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
		  	                    case "RegisterLocation":
		  	                        carInputBean.setRegisterLocation(fieldValue);
		  	                        break;
		  	                    case "FuelType":
		  	                        carInputBean.setFuelType(Integer.parseInt(fieldValue));
		  	                        break;
		  	                    case "price":
		  	                        carInputBean.setPrice(Double.parseDouble(fieldValue));
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
		  	            if (helper.addRentalCar(carInputBean)) {
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

	
	}


