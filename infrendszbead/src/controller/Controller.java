package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Controller {

	
public String add() {
		
		System.out.println(" Ügyfél felvéve");
		System.out.println(toString());
		
		return "success";
	}
	
	
public String toReg() {

		return "register";
		
	}

public String toRent() {

	return "welcome";
	
}

public String toMain() {

	return "index";
	
}

public String newVehicle() {

	return "vehicle";
	
}
	
}
