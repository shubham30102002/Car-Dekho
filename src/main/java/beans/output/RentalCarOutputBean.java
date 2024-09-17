package beans.output;

public class RentalCarOutputBean {
	private int id;
	private String carBrand;
	private String carModal;
	private String registerLocation;
	private String dateFrom;
	private String dateTo;
	private String FuelType;
	private String price;
	private String car_desc;
	private String carType;
	private String carImg;
	private String isActive;
	public RentalCarOutputBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RentalCarOutputBean(int id, String carBrand, String carModal, String registerLocation, String dateFrom,
			String dateTo, String fuelType, String price, String car_desc, String carType, String carImg,
			String isActive) {
		super();
		this.id = id;
		this.carBrand = carBrand;
		this.carModal = carModal;
		this.registerLocation = registerLocation;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		FuelType = fuelType;
		this.price = price;
		this.car_desc = car_desc;
		this.carType = carType;
		this.carImg = carImg;
		this.isActive = isActive;
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
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public String getFuelType() {
		return FuelType;
	}
	public void setFuelType(String fuelType) {
		FuelType = fuelType;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCar_desc() {
		return car_desc;
	}
	public void setCar_desc(String car_desc) {
		this.car_desc = car_desc;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getCarImg() {
		return carImg;
	}
	public void setCarImg(String carImg) {
		this.carImg = carImg;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	

}
