package driverpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {



	private ServiceRegistration serviceRegistration;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Driver Publisher service started");
		IDriverService vehicle = new DriverService();
		serviceRegistration = context.registerService(IDriverService.class.getName(), vehicle, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Driver Publisher service stopped");
		serviceRegistration.unregister();
	}

}
