package rentalinfopublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

private ServiceRegistration RentalServiceRegistration;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("RentalInfo Publisher service started");
		IRentalInfoService rental = new RentalService();
		RentalServiceRegistration = context.registerService(IRentalInfoService.class.getName(), rental, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("RentalInfo Publisher service stopped");
		RentalServiceRegistration.unregister();
	}

}
