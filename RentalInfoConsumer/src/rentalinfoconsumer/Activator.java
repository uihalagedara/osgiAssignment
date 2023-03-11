package rentalinfoconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import rentalinfopublisher.IRentalInfoService;

public class Activator implements BundleActivator {

	ServiceReference RentalServiceReference;


	public void start(BundleContext context) throws Exception {
		System.out.println("Start Subscriber Service");
		//Register Consumer Service
		RentalServiceReference = context.getServiceReference(IRentalInfoService.class.getName());
		@SuppressWarnings("unchecked")
		IRentalInfoService rental = (IRentalInfoService)context.getService(RentalServiceReference);	
		displayRentalInfo(rental);
		//rental.test();
	}


	private void displayRentalInfo(IRentalInfoService rental) {
		
		
		int option;
		String subOption = "y";
		
		Scanner scan = new Scanner(System.in);
		System.out.println("\n\n\n");
		System.out.println("----------Rental Management Section ----------\n");
		System.out.println("1  - Add Rental Info to the Database");
		System.out.println("2  - Get all Rental Info in the Database");
		System.out.println("3  - Get Rental Info By the Id ");
		System.out.println("4  - Delete Rental Info by the Id");
		System.out.println("\n--------------------------------------------------");
		System.out.println("\n--------------------------------------------------");
		
		System.out.print("\n\nChoose an option : \n\n");
		
		option = Integer.parseInt(scan.nextLine().trim());
		
		switch(option) {
			case 1:
				rental.addRentalInfo();
				
				while(subOption.equals("y")) {
					System.out.println("\n\nDo you want to Add Another Rental Info (y/n)");
					subOption = scan.nextLine().trim();
		
					if(subOption.equals("y")||subOption.equals("Y")) {
						rental.addRentalInfo();
					}
				}
				displayRentalInfo(rental);
				break;
			case 2:
				rental.getAllRentalInfo();
				displayRentalInfo(rental);
				break;
			case 3:
				rental.getRentalInfoById();
				displayRentalInfo(rental);
				break;
			case 4:
				rental.deleteRentalInfo();
				displayRentalInfo(rental);
				break;
			
			default:
				System.out.println("Incorrect Input. Try Again...");
				displayRentalInfo(rental);
		}
		
		
	
	}


	public void stop(BundleContext context) throws Exception {
		System.out.println("Good Bye!");
		context.ungetService(RentalServiceReference);
	}
}
