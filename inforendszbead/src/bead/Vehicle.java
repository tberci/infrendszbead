package bead;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;





@ManagedBean
@SessionScoped
@Entity(name = "vehicle")
public class Vehicle implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	
	private String tipus;
	private String gyarto;
	
	private String alvazid;
	private int datum;
	private int price;
	private int kmprice;
	private String statusz;
	
	public Vehicle()	{
		
	}
	public Vehicle(String tipus, String gyarto, String id, String alvazid ,int datum , int price, int kmprice,
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


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAlvazid() {
		return alvazid;
	}


	public void setAlvazid(String alvazid) {
		this.alvazid = alvazid;
	}


	public int getDatum() {
		return datum;
	}


	public void setDatum(int datum) {
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
