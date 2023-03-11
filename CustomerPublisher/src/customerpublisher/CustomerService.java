package customerpublisher;
import java.sql.*;
import java.util.*;
//import java.util.ArrayList;
//import java.util.Scanner;

import vehiclerentaldatabase.IVehicleRentalDb;
import vehiclerentaldatabase.VehicleRentalDb;


public class CustomerService implements ICustomerService{
	
	private Connection connection = null;
	private Statement statement = null;
	private  IVehicleRentalDb vehicleRentalDatabase;
	private ResultSet resultSet; 
	
	
	Scanner scan = new Scanner(System.in);
	
	public CustomerService() {
		super();
		vehicleRentalDatabase = (IVehicleRentalDb) new VehicleRentalDb();
		connection = vehicleRentalDatabase.connection();
	}

	@Override
	public void addCustomer() {
		CustomerModel customerModel = new CustomerModel();
		
	
		
		System.out.println("Enter Customer Name : ");
		customerModel.setCustomerName(scan.nextLine().trim());
		

		System.out.println("Enter Customor Rented Vehicle Number : ");
		customerModel.setRentedVehicleNumber(scan.nextLine().trim());
		
		
		System.out.println("Enter Customer Telephone : ");
		customerModel.setCustomerTele(scan.nextInt());
		
		String insertCustomer = "INSERT INTO customerdetails(customerName,customerTele,rentedVehicle) " 
		+ "VALUES('"+ customerModel.getCustomerName() +"', '"+ customerModel.getCustomerTele() +"','"+ customerModel.getRentedVehicleNumber() +"')";
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(insertCustomer);
			System.out.println("Customer Inserted");
		}catch (SQLException exc) {
			System.out.println("Error with Interted customer");
			System.out.println(exc.getMessage());
			
		}
	}

	@Override
	public void getAllCus() {

		ArrayList<CustomerModel>customerList=new ArrayList<CustomerModel>();
		try {
			statement=connection.createStatement();
			String SelectAll = "SELECT * FROM customerdetails";
			resultSet = statement.executeQuery(SelectAll);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		
		try {
	      while (resultSet.next()) {
	    	  CustomerModel customer = new CustomerModel();
	    	  
				customer.setId(resultSet.getInt("id"));
				  customer.setCustomerName(resultSet.getString("customerName"));
		    	  customer.setCustomerTele(resultSet.getInt("customerTele"));
		    	  customer.setRentedVehicleNumber(resultSet.getString("rentedVehicle"));
		    	  customerList.add(customer);
		    	
			} }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
		 for(CustomerModel customerModel: customerList){
	    	  System.out.printf("%10d %20s %20d %20s\n",customerModel.getId(),customerModel.getCustomerName(),customerModel.getCustomerTele(),customerModel.getRentedVehicleNumber());
	      } 
		
	}

	@Override
	public void getCusByID() {
		int customerId;
				
				System.out.print("Enter Customer ID : ");
				customerId = Integer.parseInt(scan.nextLine().trim());
				
				String getById = "SELECT * FROM customerdetails WHERE id = '"+ customerId +"'";
				
				try {
					statement = connection.createStatement();
					resultSet = statement.executeQuery(getById);
				      while (resultSet.next()) { 
				    	
				    	  System.out.printf("\n%20d %20s %20d %20s \n",resultSet.getInt("id"),resultSet.getString("customerName"),resultSet.getInt("customerTele"),resultSet.getString("rentedVehicle"));		    	
				      }
				} catch (SQLException exc) {
					System.out.println("Error in get Customer by Id");
					System.out.println(exc.getMessage());
				}
		
	}

	@Override
	public void deleteCustomer() {
		int customerId;
				
				System.out.print("Enter Customer ID To Delete : ");
				customerId = Integer.parseInt(scan.nextLine().trim());
				String deleteCustomer ="\ndelete from customerdetails where id='"+customerId+"' ";
						
				try {
					
					statement = connection.createStatement();
					statement.executeUpdate(deleteCustomer);
					System.out.println("Customer Successfully deleted\n");
		
				} catch (SQLException exc) {
					System.out.println("Error with delete Customer");
					System.out.println(exc.getMessage());
				}
		
		
	}

}
