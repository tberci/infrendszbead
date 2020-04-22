package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Vehicle implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String tipus;
	private String gyarto;
	
	private int alvazid;
	private Date datum;
	private int price;
	private int kmprice;
	private String statusz;
	
	public Vehicle()	{
		
	}
	public Vehicle(String tipus, String gyarto, int id, int alvazid ,Date datum , int price, int kmprice,
			String statusz) {
		super();
		this.tipus = tipus;
		this.gyarto = gyarto;
		this.id = id;
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
	
	
	
	
	
}
