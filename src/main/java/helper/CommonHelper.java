package helper;
import ajaxHandler.AjaxHandler;
import beans.input.CarInputBean;
import beans.input.RentalCarInputBean;
import beans.input.SoldRentCar;
import beans.input.UserInputBean;
import dao.AllDao;
import impl.Implementation;

public class CommonHelper {
	 boolean status;
	 Implementation implementation = new Implementation();
	 
	public  int addUser(UserInputBean uc) {
		
		if (uc.getFirstname().equals("")) {
						
			return 0;
		}
		 if (!isValidMobileNumber(uc.getMobileNo())) {
	            return 0;
	        }
		 if (!isValidPassword(uc.getPassword())) {
	            return 0;
	        }
		return implementation.saveUser(uc);
		
	}
	
	public boolean addCar(CarInputBean cBean){
		System.out.println("Add car");
	
		if (cBean.getCarBrand().equals("")) {
			return false;
		}
		if (cBean.getFuelType()==0) {
			return false;
		}
		
		System.out.println("Add car end");
		return implementation.saveCar(cBean);
		}
	
//	public boolean login(String Username,String Password){
//		return implementation.login(Username,Password);
//		
//	}
	
	
	
	 private boolean isValidMobileNumber(long mobileNo) {
	       
	        return mobileNo > 0;
	    }
	 private boolean isValidPassword(String password) {
	       
	        return password != null && !password.isEmpty();
	    }

	public static String likedCar(int cId, int uId) {
		AjaxHandler handler=new AjaxHandler();
		if (cId<=0) {
			System.out.println("Inside helper If");
			return null;
		} else {
			System.out.println("Inside helper else");
           return handler.likedCar(cId,uId);
		}
		
	}

	public boolean updateUser(UserInputBean us) {
		
		if (us.getFirstname().equals("")) {
			
			return false;
		}
		 if (!isValidMobileNumber(us.getMobileNo())) {
	            return false;
	        }
		 if (!isValidPassword(us.getPassword())) {
	            return false;
	        }
		return implementation.UpadteUser(us);
	}

	public int userInactive(int userId) {
		return implementation.userInactive(userId);
		
	}
	public int userActive(int userId) {
		// TODO Auto-generated method stub
		return implementation.userActive(userId);
	}



	public boolean addRentalCar(RentalCarInputBean carInputBean) {
		// TODO Auto-generated method stub
		System.out.println("Add car");
		
		if (carInputBean.getCarBrand().equals("")) {
			return false;
		}
		if (carInputBean.getFuelType()==0) {
			return false;
		}
		
		System.out.println("Add car end");
		return implementation.saveRentalCar(carInputBean);
	}

	public CarInputBean getCarDetailsbyId(int carId) {
		// TODO Auto-generated method stub
		System.out.println("getCar details");

		if (carId!=0) {
			return implementation.getCarDetailsbyId(carId);	}
		return null;
		}

	public boolean updateSingleCar(CarInputBean carInputBean) {
		// TODO Auto-generated method stub
System.out.println("Update.... car");
		
		if (carInputBean.getCarBrand().equals("")) {
			return false;
		}
		if (carInputBean.getFuelType()==0) {
			return false;
		}
		
		System.out.println("Add car end");
		return implementation.updateSingleCar(carInputBean);	
		}

	public RentalCarInputBean getRentalCarDetailsbyId(int carId) {
		// TODO Auto-generated method stub
		System.out.println("getCar details");

		if (carId!=0) {
			return implementation.getRentalCarDetailsbyId(carId);	}
		return null;
	}

	public boolean updateRentalCar(RentalCarInputBean carInputBean) {
		// TODO Auto-generated method stub
System.out.println("Update.... car");
		
		if (carInputBean.getCarBrand().equals("")) {
			return false;
		}
		if (carInputBean.getFuelType()==0) {
			return false;
		}
		
		System.out.println("Add car end");
		return implementation.updateRentalCar(carInputBean);
		}

	public boolean addRentalBooking(int rcarId, String fromDate, String toDate, int userId) {
		// TODO Auto-generated method stub
		return implementation.addRentalBooking(rcarId,fromDate,toDate,userId);
	}

	public boolean isactiveChangeRental(int carId) {
		// TODO Auto-generated method stub
		return implementation.isactiveChangeRental(carId);
	}
	public boolean carSell(int carSelledId, int carBuyUserId) throws ClassNotFoundException {
		System.out.println("inside in car sell in Common helper ");
		
		
		SoldRentCar soldRentCar = new SoldRentCar() ;
		soldRentCar.setCarId(carSelledId);
		soldRentCar.setUserId(carBuyUserId);
		
		Boolean setStatus= AllDao.carActiveStatus(carSelledId,"N");
		if(setStatus)
		{
		return implementation.carSell(soldRentCar);
		}
		return false;
	}

	public static String rentallikedCar(int cId, int uId) {
		// TODO Auto-generated method stub
		AjaxHandler handler=new AjaxHandler();
		if (cId<=0) {
			System.out.println("Inside helper If");
			return null;
		} else {
			System.out.println("Inside helper else");
           return handler.rentalLikedCar(cId,uId);
		}	}

		
	
}
