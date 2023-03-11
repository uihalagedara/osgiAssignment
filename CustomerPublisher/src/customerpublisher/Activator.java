package customerpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

private ServiceRegistration serviceRegistration;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("VehicleRental Publisher service started");
		ICustomerService vehicle = new CustomerService();
		serviceRegistration = context.registerService(ICustomerService.class.getName(), vehicle, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("VehicleRental Publisher service stopped");
		serviceRegistration.unregister();
	}


}
