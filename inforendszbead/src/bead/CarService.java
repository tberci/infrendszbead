package bead;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class CarService {

	private final static String[] gyartok;
    private final static String[] state;
    private final static String[] type;
	
	 static {
	        gyartok = new String[10];
	        gyartok[0] = "Black";
	        gyartok[1] = "White";
	        gyartok[2] = "Green";
	        gyartok[3] = "Red";
	        gyartok[4] = "Blue";
	        gyartok[5] = "Orange";
	        gyartok[6] = "Silver";
	        gyartok[7] = "Yellow";
	        gyartok[8] = "Brown";
	        gyartok[9] = "Maroon";
	         
	        state = new String[3];
	        state[0] = "szabad";
	        state[1] = "foglalt";
	        state[2] = "rossz";
	        
	        type = new String[2];
	        type[0] = "földi";
	        type[1] = "vizi";
	        
	   
	    }
	
	
	public List<Vehicle> createCars(int size) {
        List<Vehicle> list = new ArrayList<Vehicle>();
        for(int i = 0 ; i < size ; i++) {
            list.add(new Vehicle(getRandomType(),getRandomGyartok(), getRandomId(),getRandomAlvazId(), getRandomYear(), getRandomPrice(),getRandomKmPrice(),  getRandomState()));
        }
         
        return list;
    }
	
		public  String getTipus() {
			return type[(int) (Math.random() * 2)];	
		}
	
	 public String getRandomId() {
	        return UUID.randomUUID().toString().substring(0, 8);
	    }
	 
	 public String getRandomAlvazId() {
	        return UUID.randomUUID().toString().substring(0, 6);
	    }
	 
	     
	    public int getRandomYear() {
	        return (int) (Math.random() * 50 + 1960);
	    }
	     
	  public String getRandomGyartok() {
	        return gyartok[(int) (Math.random() * 10)];
	    }
	    
	  public String getRandomType() {
	        return type[(int) (Math.random() * 2)];
	    }
	     
	   public String getRandomState() {
	        return state[(int) (Math.random() * 3)];
	    }
	     
	   public int getRandomPrice() {
	        return (int) (Math.random() * 100000);
	    }
	    
	   public int getRandomKmPrice() {
	        return (int) (Math.random() * 20000);
	    }
	     
	     
	    public List<String> getGyartok() {
	        return Arrays.asList(gyartok);
	    }
	     
	    public List<String> getType() {
	        return Arrays.asList(type);
	    }
	    
	    public List<String> getState() {
	        return Arrays.asList(state);
	    }
}
