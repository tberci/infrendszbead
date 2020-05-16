package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


public class Vehicle implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String rendszam;
	private String tipus;
	private String gyarto;
	
	private int alvazid;
	private Date datum;
	private int price;
	private int kmprice;
	private String statusz;
	private int costifBroken;
	
	@ManyToOne()
	@JoinColumn(name="ugyfel_id",nullable = false) 
	private User user;
	
	
	public Vehicle()	{
		
	}
	
	
	
	public Vehicle(int id, String tipus, String gyarto, int alvazid, Date datum, int price, int kmprice, String statusz) {
	
		this.id = id;
		this.tipus = tipus;
		this.gyarto = gyarto;
		this.alvazid = alvazid;
		this.datum = datum;
		this.price = price;
		this.kmprice = kmprice;
		this.statusz = statusz;
		
	}



	


	public String getTipus() {
		return tipus;
	}


	public void setTipus(String tipus) {
		this.tipus = tipus;
	}


	public String getGyarto() {
		return gyarto;
	}


	public void setGyarto(String gyarto) {
		this.gyarto = gyarto;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getAlvazid() {
		return alvazid;
	}


	public void setAlvazid(int alvazid) {
		this.alvazid = alvazid;
	}


	public Date getDatum() {
		return datum;
	}


	public void setDatum(Date datum) {
		this.datum = datum;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getKmprice() {
		return kmprice;
	}


	public void setKmprice(int kmprice) {
		this.kmprice = kmprice;
	}


	public String getStatusz() {
		return statusz;
	}


	public void setStatusz(String statusz) {
		this.statusz = statusz;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}



	public String getRendszam() {
		return rendszam;
	}



	public void setRendszam(String rendszam) {
		this.rendszam = rendszam;
	}



	public int getCostifBroken() {
		return costifBroken;
	}



	public void setCostifBroken(int costifBroken) {
		this.costifBroken = costifBroken;
	}



	
}
