package driverconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import driverpublisher.IDriverService;

public class Activator implements BundleActivator {

	ServiceReference serviceReference;


	public void start(BundleContext context) throws Exception {
		System.out.println("Start Subscriber Service");
		//Register Consumer Service
		serviceReference = context.getServiceReference(IDriverService.class.getName());
		@SuppressWarnings("unchecked")
		IDriverService driver = (IDriverService)context.getService(serviceReference);	
		//
		displayVehicles(driver);
	}
	
	public void stop(BundleContext context) throws Exception {
		System.out.println("Good Bye!");
		context.ungetService(serviceReference);
	}


	private void displayVehicles(IDriverService driver) {
		
		
		int option;
		String subOption = "y";
		
		Scanner scan = new Scanner(System.in);
		System.out.println("\n\n\n");
		System.out.println("----------Driver Management Section ----------\n");
		System.out.println("1  - Add Driver to the Database");
		System.out.println("2  - Get all Drivers in the Database");
		System.out.println("3  - Get Driver By the Id ");
		System.out.println("4  - Delete Driver by the Id");
		System.out.println("\n--------------------------------------------------");
		System.out.println("\n--------------------------------------------------");
		
		System.out.print("\n\nChoose an option : \n\n");
		
		option = Integer.parseInt(scan.nextLine().trim());
		
		switch(option) {
			case 1:
				driver.addDriver();
				
				while(subOption.equals("y")) {
					System.out.println("\n\nDo you want to Add Another Driver (y/n)");
					subOption = scan.nextLine().trim();
		
					if(subOption.equals("y")||subOption.equals("Y")) {
						driver.addDriver();
					}
				}
				displayVehicles(driver);
				break;
			case 2:
				driver.getAll();
				displayVehicles(driver);
				break;
			case 3:
				driver.getById();
				displayVehicles(driver);
				break;
			case 4:
				driver.deleteDriver();
				displayVehicles(driver);
				break;
			
			default:
				System.out.println("Incorrect Input. Try Again...");
				displayVehicles(driver);
		}
		
		
	
	}


	

}
