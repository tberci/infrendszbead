package bead.businessLogic;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bead.Vehicle;

/**
 * Session Bean implementation class VehicleBean
 */

public class VehicleBean implements VehicleBeanRemote {

	@PersistenceContext(unitName = "vehicledb")
	private EntityManager entityManager;

    public VehicleBean() {
       
    }

	@Override
	public void saveVehicle(Vehicle vehicle) {
		entityManager.persist(vehicle);
		
	}

	@Override
	public Vehicle findBook(Vehicle vehicle) {
		Vehicle veh = entityManager.find(Vehicle.class, vehicle.getId());
		return veh;

	}

	@Override
	public List<Vehicle> retrieveAllVehicles() {
		String q = "SELECT * from" + Vehicle.class.getName() + " p";
		Query query = entityManager.createQuery(q);
		@SuppressWarnings("unchecked")
		List<Vehicle> vehicles = query.getResultList();
		return vehicles;

	}

}
