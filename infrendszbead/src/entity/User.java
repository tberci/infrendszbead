package entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ugyfelek")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotNull(message="A név mezõ nem lehet üres")
	private String nev;
	
	@NotNull(message="A lakhely nem lehet üres")
	private String cim;

	@NotNull(message="Telefonszám nem lehet üres")
	private String phonenum;
	
	@NotNull()
	private String pw;
	
	@OneToMany(mappedBy = "ugyfelek")
	private List<Vehicle> vehicleList;

	  public User() {
	        
	    }
	
	
	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getNev() {
		return nev;
	}


	public void setNev(String nev) {
		this.nev = nev;
	}


	public String getCim() {
		return cim;
	}


	public void setCim(String cim) {
		this.cim = cim;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getPhonenum() {
		return phonenum;
	}


	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	
	
	
	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}


	public void setVehicleList(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}


	@Override
	public String toString() {
		return "Ugyfel [nev=" + nev + ", cim=" + cim + ", id=" + id + ", phonenum=" + phonenum + "]";
	}

	
}
