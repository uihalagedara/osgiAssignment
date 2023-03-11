package driverpublisher;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vehiclerentaldatabase.IVehicleRentalDb;
import vehiclerentaldatabase.VehicleRentalDb;

public class DriverService implements IDriverService {

	
	private Connection connection = null;
	private Statement statement = null;
	private  IVehicleRentalDb vehicleRentalDatabase;
	private ResultSet resultSet;
	
	Scanner scan=new Scanner(System.in);

	public DriverService() {
		super();
		vehicleRentalDatabase = (IVehicleRentalDb) new VehicleRentalDb();
		connection = vehicleRentalDatabase.connection();
	}
	@Override
	public void getAll() {
		
		ArrayList<DriverModel>driverList=new ArrayList<DriverModel>();
		try {
			statement=connection.createStatement();
			String SelectAll = "SELECT * FROM DriverDetails";
			resultSet = statement.executeQuery(SelectAll);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		
		try {
	      while (resultSet.next()) {
	    	  DriverModel driver = new DriverModel();
	    	  
	    	  driver.setId(resultSet.getInt("id"));
	    	  driver.setDriverId(resultSet.getString("driverId"));
	    	  driver.setName(resultSet.getString("name"));
	    	  driver.setAge(resultSet.getInt("age"));
	    	  driverList.add(driver);
		    	
			} }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
		 for(DriverModel driverModel: driverList){
	    	  System.out.printf("%10d %20s %20s %20d\n",driverModel.getId(),driverModel.getDriverId(),driverModel.getName(),driverModel.getAge());
	      } 
	   
	}
	@Override
	public void getById() {
		int driverId;
		
		System.out.print("Enter Record ID : ");
		driverId = Integer.parseInt(scan.nextLine().trim());
		
		String getById = "SELECT * FROM DriverDetails WHERE id = '"+ driverId +"'";
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(getById);
		      while (resultSet.next()) { 
		    	  System.out.printf("\n%20d %20s %20s %20d \n",resultSet.getInt("id"),resultSet.getString("driverId"),resultSet.getString("name"),resultSet.getInt("age"));		    	
		      }
		} catch (SQLException exc) {
			System.out.println("Error in get Driver by Id");
			System.out.println(exc.getMessage());
		}
		
	}
	@Override
	public void addDriver() {
		
		
		DriverModel driverModel = new DriverModel();
		
	
		
		System.out.println("Enter Driver ID : ");
		driverModel.setDriverId(scan.nextLine().trim());
		
		System.out.println("Enter Driver Name : ");
		driverModel.setName(scan.nextLine().trim());
		
		System.out.println("Enter Driver Age : ");
		driverModel.setAge(Integer.parseInt(scan.nextLine()));
		
		
		String insertDriver = "INSERT INTO DriverDetails(driverId,name,age) "
				+ "VALUES('"+ driverModel.getDriverId() +"', '"+ driverModel.getName() +"', '"+ driverModel.getAge() + "')";
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(insertDriver);
			System.out.println("Driver successfully inserted ...");
		} catch (SQLException exc) {
			System.out.println("Error with insert Driver");
			System.out.println(exc.getMessage());
		}
		
	}
	@Override
	public void updateDriver() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteDriver() {
	int driverId;
		
		System.out.print("Enter Driver ID To Delete : ");
		driverId = Integer.parseInt(scan.nextLine().trim());
		String deleteDriver ="\ndelete from DriverDetails where id='"+driverId+"' ";
				
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(deleteDriver);
			System.out.println("Driver Successfully deleted\n");

		} catch (SQLException exc) {
			System.out.println("Error with deleting Driver");
			System.out.println(exc.getMessage());
		}
		
	}

}
