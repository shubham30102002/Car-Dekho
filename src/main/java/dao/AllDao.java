package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.input.CarInputBean;
import beans.input.RentalCarInputBean;
import beans.input.SoldRentCar;
import beans.input.UserInputBean;
import beans.output.CarOutputBean;
import beans.output.RentalCarOutputBean;
import utility.DBconnection;

public class AllDao {
	private static String query;
	private static PreparedStatement pStatement;
	private static int resultSet;
	
	public static int addUser(UserInputBean us) {
		 Connection connection =null;
		try {	
			 connection = DBconnection.getConnection();
	        
	        
	        String checkQuery = "SELECT COUNT(*) FROM Register_User WHERE Email_id = ?";
	        PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
	        checkStatement.setString(1, us.getEmailId());
	        ResultSet checkResultSet = checkStatement.executeQuery();
	        checkResultSet.next();
	        int count = checkResultSet.getInt(1);
	        
	        if (count > 0) {
	            return -1; 
	        }
		
			query="Insert into Register_User (FirstName,LastName,MobileNo,Email_id,C_Password,Address,State,City,Pincode,isActive,createdBY,ModifiedBy,createdDate,ModifiedDate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_DATE,NULL)";
			
			pStatement=connection.prepareStatement(query);
			pStatement.setString(1, us.getFirstname());
			pStatement.setString(2, us.getLastname());
			pStatement.setLong(3, us.getMobileNo());
			pStatement.setString(4, us.getEmailId());
			pStatement.setString(5, us.getPassword());
			pStatement.setString(6, us.getAddress());
			pStatement.setString(7, us.getState());
			pStatement.setString(8, us.getCity());
			pStatement.setLong(9, us.getPincode());
			pStatement.setString(10, "Y");
			pStatement.setString(11, "user");
			pStatement.setString(12, null);
			
			
            resultSet=pStatement.executeUpdate();
			
			System.out.println("Query ok"+resultSet);
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		 finally {
		       
		        try {
		            if (pStatement != null) pStatement.close();
		            if (connection != null) connection.close();
		            
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		return resultSet;
				
	}

	public static boolean loginDao(String mobileNo, String password) {
		Connection connection=null;
		 try  {
			 
			 System.out.println("From Dao"+mobileNo+" "+password);
			  connection=DBconnection.getConnection();
	             query = "SELECT * FROM Register_User WHERE Email_id = ? AND C_password = ?";
	             pStatement=connection.prepareStatement(query);
	             pStatement.setString(1, mobileNo);
	 			pStatement.setString(2, password );
	 			try (ResultSet resultSet = pStatement.executeQuery()) {
                   
                    return resultSet.next();
                }
	           
	        } catch (Exception e) {
	            e.printStackTrace(); 
	             
	        }
		 finally {
		       
		        try {
		            
		            if (connection != null) connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		 return false;
	}
	
	public static boolean AdminLogin(String mobileNo, String password) {
		Connection connection=null;
		 try  {
			 System.out.println("AdminFrom Dao"+mobileNo+" "+password);
			  connection=DBconnection.getConnection();
	             query = "SELECT * FROM Admin WHERE Email_id = ? AND C_Password = ?";
	             pStatement=connection.prepareStatement(query);
	             pStatement.setString(1, mobileNo);
	 			pStatement.setString(2, password );
	 			try (ResultSet resultSet = pStatement.executeQuery()) {
	 				System.out.println("ResultSet "+resultSet);
                   return resultSet.next();
               }
	           
	        } catch (Exception e) {
	            e.printStackTrace(); 
	             
	        }
		 finally {
		       
		        try {
		            if (connection != null) connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		 return false;
	}
	
	public static ResultSet getUserByEmail(String UserName){
		ResultSet rs=null;
		try  {
			 Connection connection=DBconnection.getConnection();
	             query = "SELECT * FROM Register_User WHERE Email_id = ?";
	             pStatement=connection.prepareStatement(query);
	             pStatement.setString(1, UserName);
	             rs=pStatement.executeQuery();
		}
		catch (Exception e){
			 e.printStackTrace();
		}
		
			return (rs != null) ? rs : null;
		
	}
	

	public static ResultSet getAdminByEmail(String UserName){
		ResultSet rs=null;
		try  {
			System.out.println("Admin Dao");
			 Connection connection=DBconnection.getConnection();
	             query = "SELECT * FROM Admin WHERE Email_id = ?";
	             pStatement=connection.prepareStatement(query);
	             pStatement.setString(1, UserName);
	             rs=pStatement.executeQuery();
		}
		catch (Exception e){
			 e.printStackTrace();
		}
		
	
			return (rs != null) ? rs : null;
		
	}
	
	public static List<UserInputBean> getAllUsers() {
	    List<UserInputBean> allUsers = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement pStatement = null;
	    ResultSet rSet = null;
	    
	    try {
	        connection = DBconnection.getConnection();
	        String query = "SELECT id, FirstName,LastName,MobileNo,Email_id,C_Password,Address,State,City,Pincode,isActive FROM Register_User";
	        pStatement = connection.prepareStatement(query);
	        rSet = pStatement.executeQuery();
	        
	       
	        while (rSet.next()) {
	            UserInputBean user = new UserInputBean();
	            user.setId(rSet.getInt("id"));
	            user.setFirstname(rSet.getString("FirstName"));
	            user.setLastname(rSet.getString("LastName"));
	            user.setMobileNo(rSet.getLong("MobileNo"));
	            user.setEmailId(rSet.getString("Email_id"));
	            user.setPassword(rSet.getString("C_Password")); 
	            user.setAddress(rSet.getString("Address"));
	            user.setState(rSet.getString("State"));
	            user.setCity(rSet.getString("City"));
	            user.setPincode(rSet.getLong("Pincode"));
	            user.setIsActive(rSet.getString("isActive").charAt(0));
	            
	            allUsers.add(user);
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    } 
	    finally {
	       
	        try {
	            if (rSet != null) rSet.close();
	            if (pStatement != null) pStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return allUsers;
	}


	public static boolean isUserActive(String username) {
        String query = "SELECT isActive FROM Register_User WHERE Email_id = ?";
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(query)) {
            pStatement.setString(1, username);
            try (ResultSet rs = pStatement.executeQuery()) {
                if (rs.next()) {
                    String isActive = rs.getString("isActive");
                    return isActive.equals("Y");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
	
	
	
	

	public static List<CarOutputBean> getAllCars() {
	    List<CarOutputBean> allCars = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement pStatement = null;
	    ResultSet rSet = null;
	    
	    try {
	        connection = DBconnection.getConnection();
	        String query = "SELECT c.*, ft.type_name, ct.type_name As CAR_TYPE"+
                            " FROM Cars c"+
                            " INNER JOIN FuelTypes ft ON c.Fuel_type = ft.id"+
                            " INNER JOIN CarTypes ct ON c.carType = ct.id WHERE "
                            + "c.isActive = 'Y';";
	        pStatement = connection.prepareStatement(query);
	        rSet = pStatement.executeQuery();
	        
	       
	        while (rSet.next()) {
	            CarOutputBean car = new CarOutputBean();
	           car.setId(rSet.getInt("id"));
	            car.setCarBrand(rSet.getString("carBrand"));
	            car.setCarModal(rSet.getString("carModal"));
	            car.setRegisterDate(rSet.getString("Register_Date"));
	            car.setRegisterState(rSet.getString("Register_State"));
	            car.setFuelType(rSet.getString("type_name")); 
	            car.setPrice(rSet.getString("Price"));
	            car.setTransmission(rSet.getString("Transmission"));
	            car.setEngine_cc(rSet.getDouble("ENGINE_cc"));
	            car.setCar_desc(rSet.getString("car_Description"));
	            car.setCarType(rSet.getString("carType"));
	            car.setCarImg(rSet.getString("car_image"));
	            car.setIsActive(rSet.getString("isActive"));
	            car.setCreatedBy(rSet.getString("createdBy"));
	            car.setCreatedDate(rSet.getString("createdDate"));
	            car.setModifiedBY(rSet.getString("ModifiedBy"));
	            car.setModifiedDateString(rSet.getString("ModifiedDate"));
	            
	            allCars.add(car);
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    } 
	    finally {
	       
	        try {
	            if (rSet != null) rSet.close();
	            if (pStatement != null) pStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return allCars;
	}
	public static int addCar(CarInputBean cBean) {
	    System.out.println("Car Dao");
	    int resultSet = 0;
	    String query = "INSERT INTO Cars (carBrand, carModal, Register_Date, Register_State, Fuel_type, Price, Transmission, ENGINE_cc, car_Description, car_image, carType, isActive, createdBY) " +
	                   "VALUES (?,?,?,?,?,?,?,?,?,?,?,'Y','User')";

	    try (Connection connection = DBconnection.getConnection();
	         PreparedStatement pStatement = connection.prepareStatement(query)) {

	        System.out.println("Inside try");

	        // Set parameters
	        pStatement.setString(1, cBean.getCarBrand());
	        pStatement.setString(2, cBean.getCarModal());
	        pStatement.setString(3, cBean.getRegisterDate());
	        pStatement.setString(4, cBean.getRegisterState());
	        pStatement.setInt(5, cBean.getFuelType());
	        pStatement.setDouble(6, cBean.getPrice());
	        pStatement.setString(7, cBean.getTransmission());
	        pStatement.setDouble(8, cBean.getEngine_cc());
	        pStatement.setString(9, cBean.getCar_desc());
	        pStatement.setString(10, cBean.getCarImg());
	        pStatement.setInt(11, cBean.getCarType());

	        // Execute update
	        resultSet = pStatement.executeUpdate();

	        System.out.println("resultSet: " + resultSet);
	        System.out.println("Query ok");

	    } catch (Exception e) {
	        e.printStackTrace();  // Print the stack trace for debugging
	    }

	    System.out.println("Dao End");
	    return resultSet;
	}

	public static  List<CarOutputBean> getCarByLocation(String location) {
		 List<CarOutputBean> cList= new ArrayList<>();
		 Connection connection=null;
		 ResultSet rSet=null;
		 try{
		
		 connection = DBconnection.getConnection();
	        String query = "SELECT c.*, ft.type_name AS fuel_type, ct.type_name AS car_type"+
	        		" FROM Cars c"+
	        		" INNER JOIN FuelTypes ft ON c.Fuel_type = ft.id"+
	        		" INNER JOIN CarTypes ct ON c.carType = ct.id"+
	        		" WHERE c.Register_State = ?  AND c.isActive='Y' ";
	        pStatement = connection.prepareStatement(query);
	        pStatement.setString(1,location);
	       rSet = pStatement.executeQuery();
	       
	       while (rSet.next()) {
	       CarOutputBean car = new CarOutputBean();
           car.setId(rSet.getInt("id"));
            car.setCarBrand(rSet.getString("carBrand"));
            car.setCarModal(rSet.getString("carModal"));
            car.setRegisterDate(rSet.getString("Register_Date"));
            car.setRegisterState(rSet.getString("Register_State"));
            car.setFuelType(rSet.getString("fuel_type")); 
            car.setPrice(rSet.getString("Price"));
            car.setCarImg(rSet.getString("car_image"));
            car.setTransmission(rSet.getString("Transmission"));
            car.setEngine_cc(rSet.getDouble("ENGINE_cc"));
            car.setCar_desc(rSet.getString("car_Description"));
            car.setCarType(rSet.getString("car_type"));
            
            cList.add(car);
		 } 
		 }
		 catch(Exception e){
			    e.printStackTrace();
		 }
		 finally {
		       
		        try {
		            if (rSet != null) rSet.close();
		           
		            if (connection != null) connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		return cList;
	}
	
	public static String likedCar(int carId,int userId) {
		String setStatus="";
		int status=0;
		
		try{
			Connection connection=DBconnection.getConnection();
			Statement statement=connection.createStatement();
			int favId=0;
			ResultSet rsSet=statement.executeQuery("select * from Liked_Cars where Carid="+carId+" and Userid="+userId);
			if (rsSet.next()) {
				favId=rsSet.getInt(1);
			} 
			if(favId==0){
				status=statement.executeUpdate("insert into Liked_Cars(Userid,Carid,isActive) values("+userId+","+carId+",'Y')");
				setStatus="liked";
			}
			
			else {
               status=statement.executeUpdate("Update Liked_Cars set isActive='N' where id="+favId+" and isActive='Y'");
               setStatus="unliked";
               if(status==0){
            	   status=statement.executeUpdate("Update Liked_Cars set isActive='Y' where id="+favId+" and isActive='N'");
            	   setStatus="liked";
               }
              // setStatus="updated";
			}
			if(status==0){
				setStatus=null;
			}
			
		}
		catch(Exception e){
			status=0;
			System.out.println(e);
		}
		return setStatus;
	}
	
	public static String rentalLikedCar(int cId, int uId) {
		// TODO Auto-generated method stub
		String setStatus="";
		int status=0;
		
		try{
			Connection connection=DBconnection.getConnection();
			Statement statement=connection.createStatement();
			int favId=0;
			ResultSet rsSet=statement.executeQuery("select * from rental_liked_cars where Carid="+cId+" and Userid="+uId);
			if (rsSet.next()) {
				favId=rsSet.getInt(1);
			} 
			if(favId==0){
				status=statement.executeUpdate("insert into rental_liked_cars(Userid,Carid,isActive) values("+uId+","+cId+",'Y')");
				setStatus="liked";
			}
			
			else {
               status=statement.executeUpdate("Update rental_liked_cars set isActive='N' where id="+favId+" and isActive='Y'");
               setStatus="unliked";
               if(status==0){
            	   status=statement.executeUpdate("Update rental_liked_cars set isActive='Y' where id="+favId+" and isActive='N'");
            	   setStatus="liked";
               }
              // setStatus="updated";
			}
			if(status==0){
				setStatus=null;
			}
			
		}
		catch(Exception e){
			status=0;
			System.out.println(e);
		}
		return setStatus;
	
	}
	

	public static List<CarOutputBean> getLikedCars(int uId) {
		List<CarOutputBean> allLikedCars = new ArrayList<>();
		try{
			Connection connection=DBconnection.getConnection();
			Statement statement=connection.createStatement();
			ResultSet rSet=statement.executeQuery("SELECT c.* FROM Cars c INNER JOIN Liked_Cars lc ON c.id = lc.Carid WHERE lc.Userid ="+uId+" and lc.isActive ='Y' ");

			 while (rSet.next()) {
			       CarOutputBean car = new CarOutputBean();
		           car.setId(rSet.getInt("id"));
		            car.setCarBrand(rSet.getString("carBrand"));
		            car.setCarModal(rSet.getString("carModal"));
		            car.setRegisterDate(rSet.getString("Register_Date"));
		            car.setRegisterState(rSet.getString("Register_State"));
		            car.setIsActive(rSet.getString("isActive"));

		           // car.setFuelType(rSet.getString("fuel_type")); 
		            car.setPrice(rSet.getString("Price"));
		            car.setCarImg(rSet.getString("car_image"));
		            car.setTransmission(rSet.getString("Transmission"));
		            car.setEngine_cc(rSet.getDouble("ENGINE_cc"));
		            car.setCar_desc(rSet.getString("car_Description"));
		            //car.setCarType(rSet.getString("car_type"));
		            
		            allLikedCars.add(car);
				 } 

					
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return allLikedCars;
	}

	
	public static List<RentalCarOutputBean> getRentalLikedCars(int uId) {
		List<RentalCarOutputBean> allLikedCars = new ArrayList<>();
		try{
			Connection connection=DBconnection.getConnection();
			Statement statement=connection.createStatement();
			ResultSet rSet=statement.executeQuery("SELECT c.* FROM rentalcars c INNER JOIN rental_liked_cars lc ON c.id = lc.Carid WHERE lc.Userid ="+uId+" and lc.isActive ='Y'");

			 while (rSet.next()) {
			       RentalCarOutputBean car = new RentalCarOutputBean();
			       car.setId(rSet.getInt("id"));
		            car.setCarBrand(rSet.getString("carBrand"));
		            car.setCarModal(rSet.getString("carModal"));  
		            car.setRegisterLocation(rSet.getString("car_location"));
		            car.setDateFrom(rSet.getString("date_From"));
		            car.setDateTo(rSet.getString("date_To"));
		            car.setFuelType(rSet.getString("fuel_type")); 
		            car.setPrice(rSet.getString("Price"));
		            car.setCarImg(rSet.getString("car_image"));
		            car.setIsActive(rSet.getString("isActive"));
		            car.setCar_desc(rSet.getString("car_description"));
		            car.setCarType(rSet.getString("carType"));
		            
		          allLikedCars.add(car);
				 } 

					
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return allLikedCars;
	}

	
	
	public static List<CarOutputBean> searchCarBy(int price, int car_type) {
		List<CarOutputBean> searchCarBy = new ArrayList<>();
		 ResultSet rSet=null;
		try{
			Connection connection=DBconnection.getConnection();
			
			//Statement statement=connection.createStatement();
			//if(price != 0 && car_type !=0){
				
		        String query = "SELECT c.*, ft.type_name AS fuel_type, ct.type_name AS car_type"+
		        		" FROM Cars c"+
		        		" INNER JOIN FuelTypes ft ON c.Fuel_type = ft.id"+
		        		" INNER JOIN CarTypes ct ON c.carType = ct.id"+
		        		" WHERE c.Price = ? and ft.id=? AND c.isActive='Y'";
		        pStatement = connection.prepareStatement(query);
		        pStatement.setInt(1,price);
		        pStatement.setInt(2, car_type);
		        
		       rSet = pStatement.executeQuery();
		       while (rSet.next()) {
			       CarOutputBean car = new CarOutputBean();
		           car.setId(rSet.getInt("id"));
		            car.setCarBrand(rSet.getString("carBrand"));
		            car.setCarModal(rSet.getString("carModal"));
		            car.setRegisterDate(rSet.getString("Register_Date"));
		            car.setRegisterState(rSet.getString("Register_State"));
		            car.setFuelType(rSet.getString("fuel_type")); 
		            car.setPrice(rSet.getString("Price"));
		            car.setCarImg(rSet.getString("car_image"));
		            car.setTransmission(rSet.getString("Transmission"));
		            car.setEngine_cc(rSet.getDouble("ENGINE_cc"));
		            car.setCar_desc(rSet.getString("car_Description"));
		            car.setCarType(rSet.getString("car_type"));
		            
		            searchCarBy.add(car);
				 } 
		       
			//}
			
			
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return searchCarBy;
	}

	
	public static int updateUser(UserInputBean us) {
		try {
			Connection connection=DBconnection.getConnection();
			query="UPDATE Register_User SET FirstName = ?, LastName = ?,MobileNo = ?,Email_id = ?,C_Password = ?,Address = ?,State = ?, City = ?,Pincode = ?,isActive = ?,createdBY = ?,ModifiedBy = ?,ModifiedDate = CURRENT_DATE WHERE  id= ?";
			
			pStatement=connection.prepareStatement(query);
			pStatement.setString(1, us.getFirstname());
			pStatement.setString(2, us.getLastname());
			pStatement.setLong(3, us.getMobileNo());
			pStatement.setString(4, us.getEmailId());
			pStatement.setString(5, us.getPassword());
			pStatement.setString(6, us.getAddress());
			pStatement.setString(7, us.getState());
			pStatement.setString(8, us.getCity());
			pStatement.setLong(9, us.getPincode());
			pStatement.setString(10, "Y");
			pStatement.setString(11, "user");
			pStatement.setString(12,"user" );
			pStatement.setInt(13, us.getId());
			
			
			
            resultSet=pStatement.executeUpdate();
			
			System.out.println("Query ok"+resultSet);
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return resultSet;
		}

	public static int userInactive(int userId) {
		Connection connection=null;
		try{
			connection=DBconnection.getConnection();
			query="Update Register_User SET isActive = ?,ModifiedBy = ?,ModifiedDate = CURRENT_DATE WHERE  id= ? ";
			
			pStatement=connection.prepareStatement(query);
			pStatement.setString(1,"N");
			pStatement.setString(2, "Admin");
			pStatement.setInt(3, userId);
			resultSet=pStatement.executeUpdate();
			System.out.println("Updated success "+resultSet);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resultSet;
		
	}
	
	public static int userActive(int userId) {
		// TODO Auto-generated method stub
		Connection connection=null;
		try{
			connection=DBconnection.getConnection();
			query="Update Register_User SET isActive = ?,ModifiedBy = ?,ModifiedDate = CURRENT_DATE WHERE  id= ? ";
			
			pStatement=connection.prepareStatement(query);
			pStatement.setString(1,"Y");
			pStatement.setString(2, "Admin");
			pStatement.setInt(3, userId);
			resultSet=pStatement.executeUpdate();
			System.out.println("Updated success "+resultSet);
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resultSet;
		
	}


	public static int addRentalCar(RentalCarInputBean carInputBean) {
		// TODO Auto-generated method stub
		System.out.println("Car Dao");
	    int resultSet = 0;
	    String query = "INSERT INTO RentalCars (carBrand, carModal, Fuel_type, Price,  car_Description, car_location, carType,car_image) " +
	                   "VALUES (?,?,?,?,?,?,?,?)";

	    try (Connection connection = DBconnection.getConnection();
	         PreparedStatement pStatement = connection.prepareStatement(query)) {

	        System.out.println("Inside try");

	        // Set parameters
	        pStatement.setString(1, carInputBean.getCarBrand());
	        pStatement.setString(2, carInputBean.getCarModal());
	        pStatement.setInt(3, carInputBean.getFuelType());
	        pStatement.setDouble(4, carInputBean.getPrice());
	        pStatement.setString(5, carInputBean.getCar_desc());
	        pStatement.setString(6, carInputBean.getRegisterLocation());
	        pStatement.setInt(7, carInputBean.getCarType());
	        pStatement.setString(8, carInputBean.getCarImg());
	     System.out.println("----------------------------------"+carInputBean.getCarImg());
	        // Execute update
	        resultSet = pStatement.executeUpdate();

	        System.out.println("resultSet: " + resultSet);
	        System.out.println("Query ok");

	    } catch (Exception e) {
	        e.printStackTrace();  // Print the stack trace for debugging
	    }

	    System.out.println("Dao End");
	    return resultSet;
	}

	public static List<RentalCarOutputBean> getAllRentalCars() {
		// TODO Auto-generated method stub
		 List<RentalCarOutputBean> allCars = new ArrayList<>();
		    Connection connection = null;
		    PreparedStatement pStatement = null;
		    ResultSet rSet = null;
		    
		    try {
		        connection = DBconnection.getConnection();
		        String query = "SELECT c.*, ft.type_name, ct.type_name As CAR_TYPE"+
	                            " FROM RentalCars c"+
	                            " INNER JOIN FuelTypes ft ON c.Fuel_type = ft.id"+
	                            " INNER JOIN CarTypes ct ON c.carType = ct.id WHERE "
	                            + "c.isActive = 'Y';";
		        pStatement = connection.prepareStatement(query);
		        rSet = pStatement.executeQuery();
		        
		       
		        while (rSet.next()) {
		        	RentalCarOutputBean car = new RentalCarOutputBean();
		           car.setId(rSet.getInt("id"));
		            car.setCarBrand(rSet.getString("carBrand"));
		            car.setCarModal(rSet.getString("carModal"));
		            car.setDateFrom(rSet.getString("Date_from"));
		            car.setDateTo(rSet.getString("Date_to"));
		            car.setFuelType(rSet.getString("type_name")); 
		            car.setPrice(rSet.getString("Price"));
		            car.setCar_desc(rSet.getString("car_Description"));
		            car.setCarType(rSet.getString("carType"));
		            car.setRegisterLocation(rSet.getString("car_location"));
		            car.setCarImg(rSet.getString("car_image"));
		            car.setIsActive(rSet.getString("isActive"));
		            allCars.add(car);
		        }
		    } catch (SQLException | ClassNotFoundException e) {
		        e.printStackTrace();
		    } 
		    finally {
		       
		        try {
		            if (rSet != null) rSet.close();
		            if (pStatement != null) pStatement.close();
		            if (connection != null) connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    
		    return allCars;
	}

	public static CarInputBean getCarDetailsById(int carId) {
		// TODO Auto-generated method stub
		CarInputBean car = null;
	    Connection connection = null;

        try {
            // Database connection setup
	        connection = DBconnection.getConnection();
            
            // SQL query to fetch car details
            String sql = "SELECT * FROM Cars WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, carId);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Populate CarInputBean with result set data
                car = new CarInputBean();
                System.out.println("-------"+rs.getInt("id"));
                car.setId(rs.getInt("id"));
	            car.setCarBrand(rs.getString("carBrand"));
	            car.setCarModal(rs.getString("carModal"));
	            car.setRegisterDate(rs.getString("Register_Date"));
	            car.setRegisterState(rs.getString("Register_State"));
	            car.setFuelType(rs.getInt("fuel_type")); 
	            car.setPrice(rs.getInt("Price"));
	            car.setCarImg(rs.getString("car_image"));
	            car.setTransmission(rs.getString("Transmission"));
	            car.setEngine_cc(rs.getDouble("ENGINE_cc"));
	            car.setCar_desc(rs.getString("car_Description"));
	          
            }
           
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(car.toString());
        return car;
	}

	public static int updateSingleCar(CarInputBean cBean) {
	    System.out.println("Car Dao");
	    int resultSet = 0;
	    String query = "UPDATE Cars SET carBrand = ?, carModal = ?, Register_Date = ?, Register_State = ?, Fuel_type = ?, Price = ?, Transmission = ?, ENGINE_cc = ?, car_Description = ?, car_image = ?, carType = ? " +
	                   "WHERE Id = ?";

	    try (Connection connection = DBconnection.getConnection();
	         PreparedStatement pStatement = connection.prepareStatement(query)) {

	        System.out.println("Inside try");

	        // Set parameters
	        pStatement.setString(1, cBean.getCarBrand());
	        pStatement.setString(2, cBean.getCarModal());
	        pStatement.setString(3, cBean.getRegisterDate());
	        pStatement.setString(4, cBean.getRegisterState());
	        pStatement.setInt(5, cBean.getFuelType());
	        pStatement.setDouble(6, cBean.getPrice());
	        pStatement.setString(7, cBean.getTransmission());
	        pStatement.setDouble(8, cBean.getEngine_cc());
	        pStatement.setString(9, cBean.getCar_desc());
	        pStatement.setString(10, cBean.getCarImg());
	        pStatement.setInt(11, cBean.getCarType());
	        pStatement.setInt(12, cBean.getId());  // Set the car ID for the WHERE clause

	        // Execute update
	        resultSet = pStatement.executeUpdate();

	        System.out.println("resultSet: " + resultSet);
	        System.out.println("Query ok");

	    } catch (Exception e) {
	        e.printStackTrace();  // Print the stack trace for debugging
	    }

	    System.out.println("Dao End");
	    return resultSet;
	
}

	public static RentalCarInputBean getRetalCarDetailsById(int carId) {
		// TODO Auto-generated method stub
		RentalCarInputBean car = null;
	    Connection connection = null;

        try {
            // Database connection setup
	        connection = DBconnection.getConnection();
            
            // SQL query to fetch car details
            String sql = "SELECT * FROM RentalCars WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, carId);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Populate CarInputBean with result set data
                car = new RentalCarInputBean();
                System.out.println("-------"+rs.getInt("id"));
                car.setId(rs.getInt("id"));
	            car.setCarBrand(rs.getString("carBrand"));
	            car.setCarModal(rs.getString("carModal"));
	            car.setRegisterLocation(rs.getString("car_location"));
	            car.setFuelType(rs.getInt("fuel_type")); 
	            car.setPrice(rs.getInt("Price"));
	            car.setCarImg(rs.getString("car_image"));
	            car.setCar_desc(rs.getString("car_Description"));
	            car.setCarType(rs.getInt("carType"));
	            car.setIsActive(rs.getString("isActive").charAt(0));
	            
	          
            }
           
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(car.toString());
        return car;
	} 
		
	
	public static int updateRentalCar(RentalCarInputBean cBean) {
	    System.out.println("Car Dao");
	    int resultSet = 0;
        String query = "UPDATE RentalCars SET carBrand = ?, carModal = ?, Fuel_type = ?, Price = ?, car_Description = ?, car_location = ?, carType = ?, car_image = ? WHERE Id = ?";

	    try (Connection connection = DBconnection.getConnection();
	         PreparedStatement pStatement = connection.prepareStatement(query)) {

	        System.out.println("Inside try");

	        // Set parameters
	        pStatement.setString(1, cBean.getCarBrand());           
            pStatement.setString(2, cBean.getCarModal());           
            pStatement.setInt(3, cBean.getFuelType());              
            pStatement.setDouble(4, cBean.getPrice());              
            pStatement.setString(5, cBean.getCar_desc());            
            pStatement.setString(6, cBean.getRegisterLocation());   
            pStatement.setInt(7, cBean.getCarType());               
            pStatement.setString(8, cBean.getCarImg());             
            pStatement.setInt(9, cBean.getId()); 

	        // Execute update
	        resultSet = pStatement.executeUpdate();

	        System.out.println("resultSet: " + resultSet);
	        System.out.println("Query ok");

	    } catch (Exception e) {
	        e.printStackTrace();  // Print the stack trace for debugging
	    }

	    System.out.println("Dao End");
	    return resultSet;
	
}


	public static void addRentalBooking(int rcarId, String fromDate, String toDate, int userId) {
		// TODO Auto-generated method stub
		
		System.out.println("In Dao");
		Connection connection=null;
		try {
            // Database connection setup
	        connection = DBconnection.getConnection();
               connection.setAutoCommit(false);
	            
	            // Insert into rentalcar table
	            String rentalUpdateQuery = "UPDATE rentalcars SET Date_from = ?, Date_to = ?, IsActive = 'N' WHERE id = ?";
	            
	            try (PreparedStatement rentalStmt = connection.prepareStatement(rentalUpdateQuery)) {
	                rentalStmt.setString(1, fromDate);
	                rentalStmt.setString(2, toDate);
	                rentalStmt.setInt(3, rcarId);
	                int rowsAffected = rentalStmt.executeUpdate();
	                
	                if (rowsAffected == 0) {
	                    // No rows updated, which means the carId might not exist
	                    throw new SQLException("No record found for carId: " + rcarId);
	                }
	            }
	            
	            // Insert into rentedcar table
	            String rentedQuery = "INSERT INTO rentedcar (userId,rentalCarId) VALUES (?,?)";
	            try (PreparedStatement rentedStmt = connection.prepareStatement(rentedQuery)) {
	                rentedStmt.setInt(1, userId);
	                rentedStmt.setInt(2, rcarId);
	                rentedStmt.executeUpdate();
	            }
	            
	            connection.commit();
	            
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	            try {
	                if (connection != null) {
	                    connection.rollback();
	                }
	            } catch (SQLException rollbackEx) {
	                rollbackEx.printStackTrace();
	            }
	        } finally {
	            try {
	                if (connection != null) {
	                    connection.close();
	                }
	            } catch (SQLException closeEx) {
	                closeEx.printStackTrace();
	            }
	        }
		
		
	}

	public static List<RentalCarOutputBean> getAllRentalCarsFull() {
		// TODO Auto-generated method stub
		 List<RentalCarOutputBean> allCars = new ArrayList<>();
		    Connection connection = null;
		    PreparedStatement pStatement = null;
		    ResultSet rSet = null;
		    
		    try {
		        connection = DBconnection.getConnection();
		        String query = "SELECT c.*, ft.type_name, ct.type_name As CAR_TYPE"+
	                            " FROM RentalCars c"+
	                            " INNER JOIN FuelTypes ft ON c.Fuel_type = ft.id"+
	                            " INNER JOIN CarTypes ct ON c.carType = ct.id ";
		        pStatement = connection.prepareStatement(query);
		        rSet = pStatement.executeQuery();
		        
		       
		        while (rSet.next()) {
		        	RentalCarOutputBean car = new RentalCarOutputBean();
		           car.setId(rSet.getInt("id"));
		            car.setCarBrand(rSet.getString("carBrand"));
		            car.setCarModal(rSet.getString("carModal"));
		            car.setDateFrom(rSet.getString("Date_from"));
		            car.setDateTo(rSet.getString("Date_to"));
		            car.setFuelType(rSet.getString("type_name")); 
		            car.setPrice(rSet.getString("Price"));
		            car.setCar_desc(rSet.getString("car_Description"));
		            car.setCarType(rSet.getString("carType"));
		            car.setRegisterLocation(rSet.getString("car_location"));
		            car.setCarImg(rSet.getString("car_image"));
		            car.setIsActive(rSet.getString("isActive"));
		            allCars.add(car);
		        }
		    } catch (SQLException | ClassNotFoundException e) {
		        e.printStackTrace();
		    } 
		    finally {
		       
		        try {
		            if (rSet != null) rSet.close();
		            if (pStatement != null) pStatement.close();
		            if (connection != null) connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    
		    return allCars;
	}
	public static boolean carActiveStatus(int carId, String status) {
        PreparedStatement pStatement = null;
        boolean isSuccess = false;
        Connection connection = null;

        try {
            connection = DBconnection.getConnection();

            String query = "UPDATE Cars SET isActive = ? WHERE id = ?";

            pStatement = connection.prepareStatement(query);
            pStatement.setString(1, status);
            pStatement.setInt(2, carId);

            int result = pStatement.executeUpdate();

            if (result > 0) {
                isSuccess = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
                if (pStatement != null) pStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isSuccess;
    }
	
	

	public static int carSell(SoldRentCar soldRentCar) throws ClassNotFoundException {
		 PreparedStatement pStatement = null;
	        int isSuccess = 0;
        	Connection connection = DBconnection.getConnection();

        	try {
                // Get the database connection
                connection = DBconnection.getConnection();

                // SQL query to insert a new record into the SoldedCar table
                String query = "INSERT INTO SoldedCar (userId, carId, soldDate) VALUES (?, ?, CURRENT_DATE)";

                // Create a PreparedStatement to execute the query
                pStatement = connection.prepareStatement(query);
                pStatement.setInt(1, soldRentCar.getUserId());
                pStatement.setInt(2, soldRentCar.getCarId());

                // Execute the update and get the number of affected rows
                isSuccess = pStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Ensure the PreparedStatement and Connection are closed
                try {
                    if (pStatement != null) pStatement.close();
                    if (connection != null) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return isSuccess; 
        }

	

	public static List<RentalCarOutputBean> searchRentedCarBy(String location) {
		List<RentalCarOutputBean> searchRentedCar = new ArrayList<>();
		 ResultSet rSet=null;
		try{
			Connection connection=DBconnection.getConnection();
			
			//Statement statement=connection.createStatement();
			//if(price != 0 && car_type !=0){
				
		        String query = "SELECT * from RentalCars where car_location =? and isActive= 'Y'";
		        pStatement = connection.prepareStatement(query);
		        pStatement.setString(1,location);
		        
		        
		       rSet = pStatement.executeQuery();
		       while (rSet.next()) {
		    	   RentalCarOutputBean car = new RentalCarOutputBean();
		           car.setId(rSet.getInt("id"));
		            car.setCarBrand(rSet.getString("carBrand"));
		            car.setCarModal(rSet.getString("carModal"));  
		            car.setRegisterLocation(rSet.getString("car_location"));
		            car.setDateFrom(rSet.getString("date_From"));
		            car.setDateTo(rSet.getString("date_To"));
		            car.setFuelType(rSet.getString("fuel_type")); 
		            car.setPrice(rSet.getString("Price"));
		            car.setCarImg(rSet.getString("car_image"));
		            car.setIsActive(rSet.getString("isActive"));
		            car.setCar_desc(rSet.getString("car_description"));
		            car.setCarType(rSet.getString("carType"));
		            
		            searchRentedCar.add(car);
				 } 
		       
			//}
			
			
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return searchRentedCar;
	}
	public static boolean isactiveChangeRental(int carId) {
		// TODO Auto-generated method stub
		int rentedRowsAffected=0;
		int rentalRowsAffected=0;
    
		try (Connection connection = DBconnection.getConnection()) {
            // Update rentalcars table
		    String updateRentalQuery = "UPDATE rentalcars SET Date_from = NULL, Date_to = NULL, IsActive = 'Y' WHERE id = ?";
            try (PreparedStatement updateRentalStmt = connection.prepareStatement(updateRentalQuery)) {
                updateRentalStmt.setInt(1, carId);
                 rentalRowsAffected = updateRentalStmt.executeUpdate();
                if (rentalRowsAffected > 0) {
                    // Delete from rentedcar table
                    String deleteRentedQuery = "DELETE FROM rentedcar WHERE rentalCarId = ?";
                    try (PreparedStatement deleteRentedStmt = connection.prepareStatement(deleteRentedQuery)) {
                        deleteRentedStmt.setInt(1, carId);
                         rentedRowsAffected = deleteRentedStmt.executeUpdate();
                        
                    }catch (SQLException e) {
                        e.printStackTrace();
                        // Handle SQL exception
                    }
                } 
           
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
	
} catch (ClassNotFoundException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
} catch (SQLException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
		return rentedRowsAffected==rentalRowsAffected;

	}

	public static List<CarOutputBean> getSoldCars(int uId) {
	    List<CarOutputBean> soldCars = new ArrayList<>();
	    Connection connection = null;
	    Statement statement = null;
	    ResultSet resultSet = null;

	    try {
	        // Get database connection
	        connection = DBconnection.getConnection();
	        statement = connection.createStatement();

	        // SQL query to get sold cars for the given user
	        String sql = "SELECT c.* " +
	                     "FROM Cars c " +
	                     "INNER JOIN soldedcar sc ON c.id = sc.Carid " +
	                     "WHERE sc.Userid = " + uId;

	        // Execute the query
	        resultSet = statement.executeQuery(sql);

	        // Process the result set
	        while (resultSet.next()) {
	            CarOutputBean car = new CarOutputBean();
	            car.setId(resultSet.getInt("id"));
	            car.setCarBrand(resultSet.getString("carBrand"));
	            car.setCarModal(resultSet.getString("carModal"));
	            car.setRegisterDate(resultSet.getString("Register_Date"));
	            car.setRegisterState(resultSet.getString("Register_State"));
	            car.setPrice(resultSet.getString("Price"));
	            car.setCarImg(resultSet.getString("car_image"));
	            car.setTransmission(resultSet.getString("Transmission"));
	            car.setEngine_cc(resultSet.getDouble("ENGINE_cc"));
	            car.setCar_desc(resultSet.getString("car_Description"));
	            car.setFuelType(resultSet.getString("fuel_type"));
	            car.setCarType(resultSet.getString("carType"));

	            soldCars.add(car);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // Close resources
	        try {
	            if (resultSet != null) resultSet.close();
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return soldCars;
	}

	public static List<RentalCarOutputBean> getRentedCars(int uId) {
	    List<RentalCarOutputBean> rentedCars = new ArrayList<>();
	    Connection connection = null;
	    Statement statement = null;
	    ResultSet resultSet = null;

	    try {
	        // Get database connection
	        connection = DBconnection.getConnection();
	        statement = connection.createStatement();

	        // SQL query to get rented cars for the given user
	        String sql = "SELECT rc.* " +
	                     "FROM RentalCars rc " +
	                     "INNER JOIN RentedCar rc2 ON rc.id = rc2.rentalCarId " +
	                     "WHERE rc2.Userid = " + uId;

	        // Execute the query
	        resultSet = statement.executeQuery(sql);

	        // Process the result set
	        while (resultSet.next()) {
	            RentalCarOutputBean car = new RentalCarOutputBean();
	            car.setId(resultSet.getInt("id"));
	            car.setCarBrand(resultSet.getString("carBrand"));
	            car.setCarModal(resultSet.getString("carModal"));
	            car.setRegisterLocation(resultSet.getString("car_location")); // Adjust based on actual column name
	            car.setDateFrom(resultSet.getString("date_From")); // Ensure column names match
	            car.setDateTo(resultSet.getString("date_To")); // Ensure column names match
	            car.setFuelType(resultSet.getString("fuel_type"));
	            car.setPrice(resultSet.getString("Price"));
	            car.setCarImg(resultSet.getString("car_image"));
	            car.setIsActive(resultSet.getString("isActive"));
	            car.setCar_desc(resultSet.getString("car_description"));
	            car.setCarType(resultSet.getString("carType"));

	            rentedCars.add(car);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // Close resources
	        try {
	            if (resultSet != null) resultSet.close();
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return rentedCars;
	}

	public static List<CarOutputBean> searchCarBrand(String searchQuery) {
		// TODO Auto-generated method stub
		System.out.println("-----dao  --"+searchQuery);

		List<CarOutputBean> searchCarBy = new ArrayList<>();
		 ResultSet rSet=null;
		try{
			Connection connection=DBconnection.getConnection();
			
			//Statement statement=connection.createStatement();
			//if(price != 0 && car_type !=0){
				
		        String query = "SELECT c.*, ft.type_name AS fuel_type, ct.type_name AS car_type"+
		        		" FROM Cars c"+
		        		" INNER JOIN FuelTypes ft ON c.Fuel_type = ft.id"+
		        		" INNER JOIN CarTypes ct ON c.carType = ct.id"+
		        		" WHERE ct.type_name LIKE ? AND c.isActive='Y'";
		        pStatement = connection.prepareStatement(query);
		        pStatement.setString(1, "%" + searchQuery + "%");
		        
		       rSet = pStatement.executeQuery();
		       while (rSet.next()) {
			       CarOutputBean car = new CarOutputBean();
		           car.setId(rSet.getInt("id"));
		            car.setCarBrand(rSet.getString("carBrand"));
		            car.setCarModal(rSet.getString("carModal"));
		            car.setRegisterDate(rSet.getString("Register_Date"));
		            car.setRegisterState(rSet.getString("Register_State"));
		            car.setFuelType(rSet.getString("fuel_type")); 
		            car.setPrice(rSet.getString("Price"));
		            car.setCarImg(rSet.getString("car_image"));
		            car.setTransmission(rSet.getString("Transmission"));
		            car.setEngine_cc(rSet.getDouble("ENGINE_cc"));
		            car.setCar_desc(rSet.getString("car_Description"));
		            car.setCarType(rSet.getString("car_type"));
		            
		            searchCarBy.add(car);
				 } 
		       
			//}
			
			
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return searchCarBy;
	}

	public static List<RentalCarOutputBean> searchCarRentalBrand(String searchQuery) {
		// TODO Auto-generated method stub
		 List<RentalCarOutputBean> allCars = new ArrayList<>();
		    Connection connection = null;
		    PreparedStatement pStatement = null;
		    ResultSet rSet = null;
		    
		    try {
		        connection = DBconnection.getConnection();
		        String query = "SELECT c.*, ft.type_name, ct.type_name As CAR_TYPE"+
	                            " FROM RentalCars c"+
	                            " INNER JOIN FuelTypes ft ON c.Fuel_type = ft.id"+
	                            " INNER JOIN CarTypes ct ON c.carType = ct.id "+
	    		        		" WHERE ct.type_name LIKE ? AND c.isActive='Y' ";
		        pStatement = connection.prepareStatement(query);
		        pStatement.setString(1, "%" + searchQuery + "%");
		        rSet = pStatement.executeQuery();
		        
		       
		        while (rSet.next()) {
		        	RentalCarOutputBean car = new RentalCarOutputBean();
		           car.setId(rSet.getInt("id"));
		            car.setCarBrand(rSet.getString("carBrand"));
		            car.setCarModal(rSet.getString("carModal"));
		            car.setDateFrom(rSet.getString("Date_from"));
		            car.setDateTo(rSet.getString("Date_to"));
		            car.setFuelType(rSet.getString("type_name")); 
		            car.setPrice(rSet.getString("Price"));
		            car.setCar_desc(rSet.getString("car_Description"));
		            car.setCarType(rSet.getString("carType"));
		            car.setRegisterLocation(rSet.getString("car_location"));
		            car.setCarImg(rSet.getString("car_image"));
		            car.setIsActive(rSet.getString("isActive"));
		            allCars.add(car);
		        }
		    } catch (SQLException | ClassNotFoundException e) {
		        e.printStackTrace();
		    } 
		    finally {
		       
		        try {
		            if (rSet != null) rSet.close();
		            if (pStatement != null) pStatement.close();
		            if (connection != null) connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    
		    return allCars;
		}

	}

	

	

