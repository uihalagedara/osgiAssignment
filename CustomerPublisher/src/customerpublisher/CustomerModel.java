package customerpublisher;

public class CustomerModel {
	
	private int id;
	private String customerName;
	private int customerTele;
	private String rentedVehicleNumber;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getCustomerTele() {
		return customerTele;
	}
	public void setCustomerTele(int customerTele) {
		this.customerTele = customerTele;
	}
	public String getRentedVehicleNumber() {
		return rentedVehicleNumber;
	}
	public void setRentedVehicleNumber(String rentedVehicleNumber) {
		this.rentedVehicleNumber = rentedVehicleNumber;
	}
	
}
