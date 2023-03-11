package costomerconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import customerpublisher.ICustomerService;


public class Activator implements BundleActivator {

	ServiceReference CustomerServiceReference;
	


	public void start(BundleContext context) throws Exception {
		System.out.println("Start Subscriber Service");
		//Register Consumer Service
		CustomerServiceReference = context.getServiceReference(ICustomerService.class.getName());
		@SuppressWarnings("unchecked")
		ICustomerService customer = (ICustomerService)context.getService(CustomerServiceReference);	
		
		displayCustomer(customer);
	}


	private void displayCustomer(ICustomerService customer) {
		
		
		int option;
		String subOption = "y";
		
		Scanner scan = new Scanner(System.in);
		System.out.println("\n\n\n");
		System.out.println("----------Customer Management Section ----------\n");
		System.out.println("1  - Add Customer to the Database");
		System.out.println("2  - Get all Customer in the Database");
		System.out.println("3  - Get Customer By the Id ");
		System.out.println("4  - Delete Customer by the Id");
		System.out.println("\n--------------------------------------------------");
		System.out.println("\n--------------------------------------------------");
		
		System.out.print("\n\nChoose an option : \n\n");
		
		option = Integer.parseInt(scan.nextLine().trim());
		
		switch(option) {
			case 1:
				customer.addCustomer();
				
				while(subOption.equals("y")) {
					System.out.println("\n\nDo you want to Add Another Customer (y/n)");
					subOption = scan.nextLine().trim();
		
					if(subOption.equals("y")||subOption.equals("Y")) {
						customer.addCustomer();
					}
				}
				displayCustomer(customer);
				break;
			case 2:
				customer.getAllCus();
				displayCustomer(customer);
				break;
			case 3:
				customer.getCusByID();
				displayCustomer(customer);
				break;
			case 4:
				customer.deleteCustomer();
				displayCustomer(customer);
				break;
			
			default:
				System.out.println("Incorrect Input. Try Again...");
				displayCustomer(customer);
		}
		
		
	
	}


	public void stop(BundleContext context) throws Exception {
		System.out.println("Good Bye!");
		context.ungetService(CustomerServiceReference);
	}
}
