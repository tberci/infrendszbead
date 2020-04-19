package bead.businessLogic;

import java.util.List;
import bead.Vehicle;


public interface VehicleBeanRemote {
	public void saveVehicle(Vehicle vehicle);
	public Vehicle findBook(Vehicle vehicle);
	public List<Vehicle> retrieveAllVehicles();
}
