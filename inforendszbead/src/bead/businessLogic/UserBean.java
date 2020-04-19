package bead.businessLogic;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

import bead.businessLogic.UserBeanRemote;


/**
 * Session Bean implementation class UserBean
 */
@ManagedBean
@SessionScoped
public class UserBean implements UserBeanRemote {

	@PersistenceContext(unitName = "vehicledb")
	private EntityManager entityManager;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotNull(message="A név mezõ nem lehet üres")
	private String nev;
	
	@NotNull(message="A lakhely nem lehet üres")
	private String cim;

	@NotNull(message="Telefonszám nem lehet üres")
	private int phonenum;

	  public UserBean() {
	        
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


	public int getPhonenum() {
		return phonenum;
	}


	public void setPhonenum(int phonenum) {
		this.phonenum = phonenum;
	}
	
	@Override
	public String toString() {
		return "Ugyfel [nev=" + nev + ", cim=" + cim + ", id=" + id + ", phonenum=" + phonenum + "]";
	}


	public String add() {
		
		System.out.println(" Ügyfél felvéve");
		System.out.println(toString());
		
		return "success";
		
	}
	
	
public String toReg() {

		return "register";
		
	}

public String toMain() {

	return "welcome";
	
}
	

	@Override
	public void saveUser(UserBean ugyfel) {
		entityManager.persist(ugyfel);
		
	}

}
