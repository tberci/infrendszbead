package bead;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean(name="db")
@ViewScoped
public class View {

    private List<Vehicle> cars;
    
    @Inject
    private CarService service;
 
    @PostConstruct
    public void init() {
        cars = service.createCars(10);
    }
     
    public List<Vehicle> getCars() {
        return cars;
    }
 
    public void setService(CarService service) {
        this.service = service;
    }
	
}
