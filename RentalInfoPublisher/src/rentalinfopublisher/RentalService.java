package rentalinfopublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import rentalinfopublisher.*;
import vehiclerentaldatabase.IVehicleRentalDb;
import vehiclerentaldatabase.VehicleRentalDb;

public class RentalService implements IRentalInfoService {

	private Connection connection = null;
	private Statement statement = null;
	private  IVehicleRentalDb vehicleRentalDatabase;
	private ResultSet resultSet;
	
	Scanner scan=new Scanner(System.in);

	public RentalService() {
		super();
		vehicleRentalDatabase = (IVehicleRentalDb) new VehicleRentalDb();
		connection = vehicleRentalDatabase.connection();
	}
	@Override
	public void getAllRentalInfo() {
		
		ArrayList<RentalInfoModel>rentalList=new ArrayList<RentalInfoModel>();
		try {
			statement=connection.createStatement();
			String SelectAll = "SELECT * FROM rentalinfo";
			resultSet = statement.executeQuery(SelectAll);
			
		} catch (Exception e) {
			System.out.println("Exception eka:"+e);
			e.printStackTrace();
		}
	
		
		try {
	      while (resultSet.next()) {
	    	  RentalInfoModel rental = new RentalInfoModel();
	    	  
				rental.setId(resultSet.getInt("rental_id"));
				  rental.setpDate(resultSet.getString("pickup_date"));
		    	  rental.setdDate(resultSet.getString("dropoff_date"));
		    	  rental.setCustomerId(resultSet.getString("customer_id"));
		    	  rental.setVehicleId(resultSet.getString("vehicle_id"));
		    	  rentalList.add(rental);
		    	
			} }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
		 for(RentalInfoModel rentalModel: rentalList){
	    	  System.out.printf("%10d %20s %20s %20s %20s\n",rentalModel.getId(),rentalModel.getpDate(),rentalModel.getdDate(),rentalModel.getCustomerId(),rentalModel.getVehicleId());
	      } 
	   
	}
	
	@Override
	public void addRentalInfo() {
		
		
		RentalInfoModel rentalModel = new RentalInfoModel();

		
		System.out.println("Enter pick up Date : ");
		rentalModel.setpDate(scan.nextLine().trim());
		
		System.out.println("Enter drop off Year : ");
		rentalModel.setdDate(scan.nextLine().trim());
		
		System.out.println("Enter Vehicle ID : ");
		rentalModel.setVehicleId(scan.nextLine().trim());
		
		System.out.println("Enter Customer ID : ");
		rentalModel.setCustomerId(scan.nextLine().trim());
		
		
		String insertRental = "INSERT INTO rentalinfo(pickup_date,dropoff_date,customer_id,vehicle_id) "
				+ "VALUES('"+ rentalModel.getpDate() +"', '"+ rentalModel.getdDate() +"', '"+ rentalModel.getCustomerId() + "', '"+ rentalModel.getVehicleId() +"')";
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(insertRental);
			System.out.println("Rental successfully inserted ...");
		} catch (SQLException exc) {
			System.out.println("Error with insert Rental");
			System.out.println(exc.getMessage());
		}
		
	}
	
	@Override
	public void getRentalInfoById() {
		int rentalId;
		
		System.out.print("Enter Rental ID : ");
		rentalId = Integer.parseInt(scan.nextLine().trim());
		
		String getById = "SELECT * FROM rentalinfo WHERE rental_id = '"+ rentalId +"'";
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(getById);
		      while (resultSet.next()) { 
		    	  System.out.printf("\n%10d %20s %20s %20s %20s\n",resultSet.getInt("rental_id"),resultSet.getString("pickup_date"),resultSet.getString("dropoff_date"),resultSet.getString("vehicle_id"),resultSet.getString("customer_id"));		    	
		      }
		} catch (SQLException exc) {
			System.out.println("Error in get Rental by Id");
			System.out.println(exc.getMessage());
		}
		
	}
	@Override
	public void updateRentalInfo() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteRentalInfo() {
	int RentalId;
		
		System.out.print("Enter Rental ID To Delete : ");
		RentalId = Integer.parseInt(scan.nextLine().trim());
		String deleteRental ="\ndelete from rentalinfo where rental_id='"+RentalId+"' ";
				
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(deleteRental);
			System.out.println("Rental Successfully deleted\n");

		} catch (SQLException exc) {
			System.out.println("Error with delete Rental");
			System.out.println(exc.getMessage());
		}
		
	}
	@Override
	public void test() {
		System.out.println("method return from service");
		
	}
	
	

}
