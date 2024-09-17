package impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import beans.input.CarInputBean;
import beans.input.RentalCarInputBean;
import beans.input.SoldRentCar;
import beans.input.UserInputBean;
import beans.output.CarOutputBean;
import beans.output.OutputBean;
import beans.output.RentalCarOutputBean;
import beans.output.UserOutputBean;
import dao.AllDao;

public class Implementation {
	
	private static int status;
	boolean result;
	public  int saveUser(UserInputBean bean) {
		
		status=AllDao.addUser(bean);
		if (status==1) {
			return 1;
		} 
		if (status== -1) {
			
			return -1;
		}else {
			return 0;
		}
		
	}
//	public boolean login(String username, String password) {
//		result=AllDao.loginDao(username,password);
//		return false;
//	}
	public boolean saveCar(CarInputBean cBean) {
		System.out.println("Impl car ");
		if (AllDao.addCar(cBean)==1) {
			return true;
		}
		return false;
	}
	
	public static void loginUser(OutputBean op, HttpServletResponse response) throws IOException {
        String username = op.getUsername();
        String password = op.getPassword();

       
        boolean loginSuccess = AllDao.loginDao(username, password);
        System.out.println("Ajax enter");
        if(loginSuccess){
        	ResultSet rs=AllDao.getUserByEmail(username);
        	UserOutputBean uOutputBean = new UserOutputBean();
              System.out.println("Ajax Success");
            try {
                if (rs != null && rs.next()) {
                    uOutputBean.setId(rs.getInt("id"));
                    uOutputBean.setFirstname(rs.getString("FirstName"));
                    uOutputBean.setLastname(rs.getString("LastName"));
                    uOutputBean.setMobileNo(rs.getLong("MobileNo"));
                    uOutputBean.setEmailId(rs.getString("Email_id"));
                    uOutputBean.setPassword(rs.getString("C_Password"));
                    uOutputBean.setAddress(rs.getString("Address"));
                    uOutputBean.setState(rs.getString("State"));
                    response.sendRedirect("ViewCarDetails.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                
            }
        	
        }
	}
	
	public static List<UserInputBean> getAllUserImpl(){
		List<UserInputBean> userInputBean=AllDao.getAllUsers();
		System.out.println("Impl"+userInputBean);
		return userInputBean;
	}
	public static List<CarOutputBean> getAllCar() {
		List<CarOutputBean> carInputBean=AllDao.getAllCars();
		System.out.println("Impl"+carInputBean);
		return carInputBean;
	}
	/*public static String likedcar(int carId,int userId){
		
	}*/
	public static List<CarOutputBean> getLikedCars(int uId) {
		List<CarOutputBean> carOutputBeans=AllDao.getLikedCars(uId);
		return carOutputBeans;
	}
	
	public static List<CarOutputBean> searchCarBy(int price, int car_type) {
		List<CarOutputBean> carOutputBeans=AllDao.searchCarBy(price,car_type);
		return carOutputBeans;
	}
	public boolean UpadteUser(UserInputBean us) {
		
		status=AllDao.updateUser(us);
		if (status==1) {
			return true;
		} else {
			return false;
		}
	}
	public int userInactive(int userId) {
		status=AllDao.userInactive(userId);
		return status;
	}

public int userActive(int userId) {
	// TODO Auto-generated method stub
	status=AllDao.userActive(userId);
	return status;
}

	public boolean saveRentalCar(RentalCarInputBean carInputBean) {
		// TODO Auto-generated method stub
		System.out.println("Impl car ");
		if (AllDao.addRentalCar(carInputBean)==1) {
			return true;
		}
		return false;
		}
	public static List<RentalCarOutputBean> getAllRentalCar() {
		List<RentalCarOutputBean> carOutputBean=AllDao.getAllRentalCars();
		System.out.println("Impl"+carOutputBean);
		return carOutputBean;
		}
	public CarInputBean getCarDetailsbyId(int carId) {
		// TODO Auto-generated method stub
		System.out.println("car Dao");
		return AllDao.getCarDetailsById(carId);
	}
	public boolean updateSingleCar(CarInputBean carInputBean) {
		// TODO Auto-generated method stub
		System.out.println("Impl car ");
		if (AllDao.updateSingleCar(carInputBean)==1) {
			return true;
		}
		return false;	
		}
	public RentalCarInputBean getRentalCarDetailsbyId(int carId) {
		// TODO Auto-generated method stub
		System.out.println("car Dao");
		return AllDao.getRetalCarDetailsById(carId);
	}
	public boolean updateRentalCar(RentalCarInputBean carInputBean) {
		// TODO Auto-generated method stub
		System.out.println("Impl car ");
		if (AllDao.updateRentalCar(carInputBean)==1) {
			return true;
		}
		return false;		
		}
	public boolean addRentalBooking(int rcarId, String fromDate, String toDate, int userId) {
		// TODO Auto-generated method stub
		System.out.println(rcarId);
		
		if(rcarId==0 || fromDate=="" || toDate==""|| userId==0)
		{
			return false;

		}
		else {
		AllDao.addRentalBooking(rcarId,fromDate,toDate,userId);
		}
		return true;

	}
	public static List<RentalCarOutputBean> getAllRentalCarFull() {
		// TODO Auto-generated method stub
		List<RentalCarOutputBean> carOutputBean=AllDao.getAllRentalCarsFull();
		System.out.println("Impl"+carOutputBean);
		return carOutputBean;
			}
	public static List<RentalCarOutputBean> searchRentedCarBy(String location) {
		List<RentalCarOutputBean> carOutputBeans=AllDao.searchRentedCarBy(location);
		return carOutputBeans;
	}
	
	public boolean carSell(SoldRentCar soldRentCar) throws ClassNotFoundException {
        System.out.println("Impl car sell");

        if (AllDao.carSell(soldRentCar)==1) 
        {
            return true; 
        }
        return false;
    }
	
public boolean isactiveChangeRental(int carId) {
		// TODO Auto-generated method stub
  if(carId==0) {
	  return false;
  }
  else {
	  return AllDao.isactiveChangeRental(carId);
  }
	}
public static List<RentalCarOutputBean> getRentalLikedCars(int uId) {
	// TODO Auto-generated method stub
	List<RentalCarOutputBean> carOutputBeans=AllDao.getRentalLikedCars(uId);
	return carOutputBeans;
}
public static List<CarOutputBean> getSoldCars(int uId) {
	// TODO Auto-generated method stub
	List<CarOutputBean> carOutputBeans=AllDao.getSoldCars(uId);
	return carOutputBeans;}
public static List<RentalCarOutputBean> getRentedCars(int uId) {
	// TODO Auto-generated method stub
	List<RentalCarOutputBean> carOutputBeans=AllDao.getRentedCars(uId);
	return carOutputBeans;
}
public static List<CarOutputBean> searchCarBrand(String searchQuery) {
	// TODO Auto-generated method stub
	System.out.println("------impl--"+searchQuery);

	List<CarOutputBean> carOutputBeans=AllDao.searchCarBrand(searchQuery);
	return carOutputBeans;
}
public static List<RentalCarOutputBean> searchCarRentalBrand(String searchQuery) {
	// TODO Auto-generated method stub
	System.out.println("------impl--"+searchQuery);

	List<RentalCarOutputBean> carOutputBeans=AllDao.searchCarRentalBrand(searchQuery);
	return carOutputBeans;
}	

}
