package vehiclerentaldatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class VehicleRentalDb implements IVehicleRentalDb {


	private Connection connection;
	private static String driverName;
	private static String databaseConnectionLink;
	private static String databaseUser;
	private static String databasePassword;

	public VehicleRentalDb() {
		this.driverName = "com.mysql.jdbc.Driver";
		this.databaseConnectionLink = "jdbc:mysql://localhost:3306/VehicleRentalDb?characterEncoding=latin1&useConfigs=maxPerformance";
		this.databaseUser = "VehicleAdmin";
		this.databasePassword = "admin#123Abc";
	}
	@Override
	public Connection connection() {
		Connection con = null; 
	    try {
	      Class.forName(driverName);
	      con = DriverManager.getConnection(databaseConnectionLink, databaseUser, databasePassword); // connecting to our database
	      System.out.println("Connected!");
	    } catch (ClassNotFoundException | SQLException e ) {
	      
	      System.out.println(e+"");
	    }
	    
	    return con; 
	}

}
