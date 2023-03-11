package vehicleconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import vehiclepublisher.IVehicleService;

public class Activator implements BundleActivator {

	ServiceReference serviceReference;


	public void start(BundleContext context) throws Exception {
		System.out.println("Start Subscriber Service");
		//Register Consumer Service
		serviceReference = context.getServiceReference(IVehicleService.class.getName());
		@SuppressWarnings("unchecked")
		IVehicleService vehicle = (IVehicleService)context.getService(serviceReference);	
		//displayVehicles(vehicle);
	}


	private void displayVehicles(IVehicleService vehicle) {
		
		
		int option;
		String subOption = "y";
		
		Scanner scan = new Scanner(System.in);
		System.out.println("\n\n\n");
		System.out.println("----------Vehicle Management Section ----------\n");
		System.out.println("1  - Add Vehicle to the Database");
		System.out.println("2  - Get all Vehicles in the Database");
		System.out.println("3  - Get Vehicle By the Id ");
		System.out.println("4  - Delete Vehicle by the Id");
		System.out.println("\n--------------------------------------------------");
		System.out.println("\n--------------------------------------------------");
		
		System.out.print("\n\nChoose an option : \n\n");
		
		option = Integer.parseInt(scan.nextLine().trim());
		
		switch(option) {
			case 1:
				vehicle.addVehicle();
				
				while(subOption.equals("y")) {
					System.out.println("\n\nDo you want to Add Another Vehicle (y/n)");
					subOption = scan.nextLine().trim();
		
					if(subOption.equals("y")||subOption.equals("Y")) {
						vehicle.addVehicle();
					}
				}
				displayVehicles(vehicle);
				break;
			case 2:
				vehicle.getAll();
				displayVehicles(vehicle);
				break;
			case 3:
				vehicle.getById();
				displayVehicles(vehicle);
				break;
			case 4:
				vehicle.deleteVehicle();
				displayVehicles(vehicle);
				break;
			
			default:
				System.out.println("Incorrect Input. Try Again...");
				displayVehicles(vehicle);
		}
		
		
	
	}


	public void stop(BundleContext context) throws Exception {
		System.out.println("Good Bye!");
		context.ungetService(serviceReference);
	}

}
