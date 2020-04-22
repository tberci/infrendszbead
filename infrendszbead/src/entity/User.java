package entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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

	  public User() {
	        
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
	
	@Override
	public String toString() {
		return "Ugyfel [nev=" + nev + ", cim=" + cim + ", id=" + id + ", phonenum=" + phonenum + "]";
	}

	
}
