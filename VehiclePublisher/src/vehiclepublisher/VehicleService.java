package vehiclepublisher;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vehiclerentaldatabase.IVehicleRentalDb;
import vehiclerentaldatabase.VehicleRentalDb;

public class VehicleService implements IVehicleService {

	
	private Connection connection = null;
	private Statement statement = null;
	private  IVehicleRentalDb vehicleRentalDatabase;
	private ResultSet resultSet;
	
	Scanner scan=new Scanner(System.in);

	public VehicleService() {
		super();
		vehicleRentalDatabase = (IVehicleRentalDb) new VehicleRentalDb();
		connection = vehicleRentalDatabase.connection();
	}
	@Override
	public void getAll() {
		
		ArrayList<VehicleModel>vehiclesList=new ArrayList<VehicleModel>();
		try {
			statement=connection.createStatement();
			String SelectAll = "SELECT * FROM VehicleDetails";
			resultSet = statement.executeQuery(SelectAll);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		
		try {
	      while (resultSet.next()) {
	    	  VehicleModel vehicle = new VehicleModel();
	    	  
				vehicle.setId(resultSet.getInt("id"));
				  vehicle.setVehicleId(resultSet.getString("vehicleId"));
		    	  vehicle.setModel(resultSet.getString("model"));
		    	  vehicle.setRegisteredYear(resultSet.getInt("registeredYear"));
		    	  vehiclesList.add(vehicle);
		    	
			} }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
		 for(VehicleModel vehicleModel: vehiclesList){
	    	  System.out.printf("%10d %20s %20s %20d\n",vehicleModel.getId(),vehicleModel.getVehicleId(),vehicleModel.getModel(),vehicleModel.getRegisteredYear());
	      } 
	   
	}
	@Override
	public void getById() {
		int vehicleId;
		
		System.out.print("Enter Record ID : ");
		vehicleId = Integer.parseInt(scan.nextLine().trim());
		
		String getById = "SELECT * FROM VehicleDetails WHERE id = '"+ vehicleId +"'";
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(getById);
		      while (resultSet.next()) { 
		    	  System.out.printf("\n%20d %20s %20s %20d \n",resultSet.getInt("id"),resultSet.getString("vehicleId"),resultSet.getString("model"),resultSet.getInt("registeredYear"));		    	
		      }
		} catch (SQLException exc) {
			System.out.println("Error in get Vehicle by Id");
			System.out.println(exc.getMessage());
		}
		
	}
	@Override
	public void addVehicle() {
		
		
		VehicleModel vehicleModel = new VehicleModel();
		
	
		
		System.out.println("Enter Vehicle ID : ");
		vehicleModel.setVehicleId(scan.nextLine().trim());
		
		System.out.println("Enter vehicle Model : ");
		vehicleModel.setModel(scan.nextLine().trim());
		
		System.out.println("Enter Registered Year : ");
		vehicleModel.setRegisteredYear(Integer.parseInt(scan.nextLine()));
		
		
		String insertVehicle = "INSERT INTO VehicleDetails(vehicleId,model,registeredYear) "
				+ "VALUES('"+ vehicleModel.getVehicleId() +"', '"+ vehicleModel.getModel() +"', '"+ vehicleModel.getRegisteredYear() + "')";
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(insertVehicle);
			System.out.println("Vehivle successfully inserted ...");
		} catch (SQLException exc) {
			System.out.println("Error with insert Vehicle");
			System.out.println(exc.getMessage());
		}
		
	}
	@Override
	public void updateVehicle() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteVehicle() {
	int vehicleId;
		
		System.out.print("Enter Vehicle ID To Delete : ");
		vehicleId = Integer.parseInt(scan.nextLine().trim());
		String deleteVehicle ="\ndelete from VehicleDetails where id='"+vehicleId+"' ";
				
		try {
			
			statement = connection.createStatement();
			statement.executeUpdate(deleteVehicle);
			System.out.println("Vehicle Successfully deleted\n");

		} catch (SQLException exc) {
			System.out.println("Error with delete Vehicle");
			System.out.println(exc.getMessage());
		}
		
	}

}
