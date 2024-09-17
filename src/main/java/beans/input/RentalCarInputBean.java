package beans.input;

public class RentalCarInputBean {
	private int id;
	private String carBrand;
	private String carModal;
	private String registerLocation;
	private int FuelType;
	private double price;
	private String car_desc;
	private int carType;
	private String carImg;
	private char isActive;
	public char getIsActive() {
		return isActive;
	}
	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}
	public RentalCarInputBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RentalCarInputBean(int id, String carBrand, String carModal, String registerLocation, int fuelType,
			double price, String car_desc, int carType, String carImg) {
		super();
		this.id = id;
		this.carBrand = carBrand;
		this.carModal = carModal;
		this.registerLocation = registerLocation;
		FuelType = fuelType;
		this.price = price;
		this.car_desc = car_desc;
		this.carType = carType;
		this.carImg = carImg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getCarModal() {
		return carModal;
	}
	public void setCarModal(String carModal) {
		this.carModal = carModal;
	}
	public String getRegisterLocation() {
		return registerLocation;
	}
	public void setRegisterLocation(String registerLocation) {
		this.registerLocation = registerLocation;
	}
	public int getFuelType() {
		return FuelType;
	}
	public void setFuelType(int fuelType) {
		FuelType = fuelType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCar_desc() {
		return car_desc;
	}
	public void setCar_desc(String car_desc) {
		this.car_desc = car_desc;
	}
	public int getCarType() {
		return carType;
	}
	public void setCarType(int carType) {
		this.carType = carType;
	}
	public String getCarImg() {
		return carImg;
	}
	public void setCarImg(String carImg) {
		this.carImg = carImg;
	}
	@Override
	public String toString() {
		return "RentalCarInputBean [id=" + id + ", carBrand=" + carBrand + ", carModal=" + carModal
				+ ", registerLocation=" + registerLocation + ", FuelType=" + FuelType + ", price=" + price
				+ ", car_desc=" + car_desc + ", carType=" + carType + ", carImg=" + carImg + "]";
	}
	
}
