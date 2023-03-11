package vehiclepublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {



	private ServiceRegistration serviceRegistration;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("VehicleRental Publisher service started");
		IVehicleService vehicle = new VehicleService();
		serviceRegistration = context.registerService(IVehicleService.class.getName(), vehicle, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("VehicleRental Publisher service stopped");
		serviceRegistration.unregister();
	}

}
