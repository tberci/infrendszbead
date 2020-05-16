package bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.inject.Named;

import db.Dbconn;
import entity.User;
import entity.Vehicle;

@Named
@SessionScoped
public class VehicleBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Vehicle vehicle;
	private User user;
	private List<Vehicle> vehicles;
	private List<Vehicle> rented;
	private List<Vehicle> rentable;
	private List<Vehicle> vehicles2;
	private List<Vehicle> vehiclesRsz;
	
	
	@PostConstruct
    public void init(){
       
        this.user = new User();
        this.vehicle = new Vehicle();
    }


	public List<Vehicle> getVehicles() throws SQLException, ClassNotFoundException  {

		Dbconn db = new Dbconn();
		Connection connect = db.Connect();
		
		
		 vehicles = new ArrayList<Vehicle>();
		PreparedStatement pstmt = connect
				.prepareStatement("select * from vehicles  GROUP BY id");
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			
			vehicle.setId(rs.getInt("id"));
			vehicle.setRendszam(rs.getString("rendszam"));
			vehicle.setTipus(rs.getString("tipus"));
			vehicle.setGyarto(rs.getString("gyarto"));
			vehicle.setAlvazid(rs.getInt("alvazid"));
			vehicle.setDatum(rs.getDate("datum"));
			vehicle.setPrice(rs.getInt("price"));
			vehicle.setKmprice(rs.getInt("kmprice"));
			vehicle.setStatusz(rs.getString("statusz"));
			
			vehicles.add(vehicle);

		}

		// close resources
		rs.close();
		pstmt.close();
		connect.close();

		return vehicles;

	}
	
	public void addVehicle() throws SQLException, ClassNotFoundException {
		
		
		try {
		Dbconn db = new Dbconn();
		Connection connect = db.Connect();
		
		
		String sql = "INSERT INTO vehicles(rendszam,tipus,gyarto,alvazid,datum,price,kmprice,statusz) VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = connect.prepareStatement(sql);
		
		stmt.setString(1, vehicle.getRendszam());
		stmt.setString(2, vehicle.getTipus());
		stmt.setString(3, vehicle.getGyarto());
		stmt.setLong(4, vehicle.getAlvazid());
		stmt.setDate(5,  new java.sql.Date(vehicle.getDatum().getTime()));
		stmt.setLong(6, vehicle.getPrice());
		stmt.setLong(7, vehicle.getKmprice());
		stmt.setString(8, vehicle.getStatusz());
   

    stmt.executeUpdate();
    
    
   
	stmt.close();
	connect.close();
	
		}catch(Exception e) {
			throw new FacesException(e);
		}
		
    
    System.out.println("added to db");

	}

	
	public List<Vehicle> RentedVehicles() throws SQLException, ClassNotFoundException  {

		Dbconn db = new Dbconn();
		Connection connect = db.Connect();
		
		
		 rented = new ArrayList<Vehicle>();
		PreparedStatement pstmt = connect
				.prepareStatement("select * from vehicles where statusz='foglalt'");
		ResultSet rs = pstmt.executeQuery();
		rs = pstmt.getResultSet();
		while (rs.next()) {

			
			vehicle.setId(rs.getInt("id"));
			vehicle.setRendszam(rs.getString("rendszam"));
			vehicle.setTipus(rs.getString("tipus"));
			vehicle.setGyarto(rs.getString("gyarto"));
			vehicle.setAlvazid(rs.getInt("alvazid"));
			vehicle.setDatum(rs.getDate("datum"));
			vehicle.setPrice(rs.getInt("price"));
			vehicle.setKmprice(rs.getInt("kmprice"));
			vehicle.setStatusz(rs.getString("statusz"));
			
			rented.add(vehicle);

		}

		// close resources
		rs.close();
		pstmt.close();
		connect.close();

		return rented;

	}
	
	
	public List<Vehicle> rentableVehicles() throws SQLException, ClassNotFoundException  {

		Dbconn db = new Dbconn();
		Connection connect = db.Connect();
		
		
		 rentable = new ArrayList<Vehicle>();
		PreparedStatement pstmt = connect
				.prepareStatement("select * from vehicles where statusz='szabad'");
		ResultSet rs = pstmt.executeQuery();

		rs = pstmt.getResultSet();
		while (rs.next()) {

		
			vehicle.setId(rs.getInt("id"));
			vehicle.setRendszam(rs.getString("rendszam"));
			vehicle.setTipus(rs.getString("tipus"));
			vehicle.setGyarto(rs.getString("gyarto"));
			vehicle.setAlvazid(rs.getInt("alvazid"));
			vehicle.setDatum(rs.getDate("datum"));
			vehicle.setPrice(rs.getInt("price"));
			vehicle.setKmprice(rs.getInt("kmprice"));
			vehicle.setStatusz(rs.getString("statusz"));
			
			rentable.add(vehicle);

		}

		// close resources
		rs.close();
		pstmt.close();
		connect.close();

		return rentable;

	}
	
	
	public void rent(Integer id) throws ClassNotFoundException, SQLException
	{
		Connection connect = null;
	    PreparedStatement statement = null;
	    PreparedStatement stmt = null;
	   	    
	    try {
	    	Dbconn db = new Dbconn();
			connect = db.Connect();
	        statement = connect.prepareStatement("UPDATE vehicles SET statusz ='foglalt' WHERE id = ?");
	        statement.setInt(1, vehicle.getId());
	        statement.executeUpdate();

	        String sql = "Update ugyfelek  INNER JOIN vehicles  ON ugyfelek.vehicle_id = vehicles.id SET vehicle_id = ?" ;
	        stmt = connect.prepareStatement(sql);
	        
	        stmt.setInt(1, vehicle.getId());
	        stmt.executeUpdate();
	        
	    } finally {
	       
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
	        if (connect != null) try { connect.close(); } catch (SQLException ignore) {}

	    }	   
}
	
	public void Return(Integer id) throws ClassNotFoundException, SQLException
	{
		Connection connect = null;
	    PreparedStatement statement = null;
	    PreparedStatement statement2 = null;
	    PreparedStatement statement3 = null;
	    
	    vehicles2 = new ArrayList<Vehicle>();
	    
	    try {
	    	Dbconn db = new Dbconn();
			connect = db.Connect();
			calculatePrice(id) ;
			
	        statement = connect.prepareStatement("UPDATE vehicles SET statusz ='szabad' WHERE id = ? GROUP BY statusz");
	        statement.setInt(1, vehicle.getId());
	        statement.executeUpdate();
	        
	        statement3 = connect.prepareStatement("UPDATE ugyfelek SET vehicle_id ='0' where id =?");
	        statement3.setLong(1,  user.getId());
	        statement3.executeUpdate();
	        
	        statement2 = connect.prepareStatement("select price from vehicles where id=?");
	        statement2.setInt(1, vehicle.getId());
	        statement2.executeQuery();
	        
	    } finally {
	       
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
	        if (statement2 != null) try { statement2.close(); } catch (SQLException ignore) {}
	        if (statement3 != null) try { statement3.close(); } catch (SQLException ignore) {}
	        if (connect != null) try { connect.close(); } catch (SQLException ignore) {}

	    	}	   
		}
	
	public void broken(Integer id) throws ClassNotFoundException, SQLException
	{
		Connection connect = null;
	    PreparedStatement statement = null;

	    try {
	    	Dbconn db = new Dbconn();
			connect = db.Connect();
		
			
	        statement = connect.prepareStatement("UPDATE vehicles SET statusz ='sérült' WHERE id = ?");
	        statement.setInt(1, vehicle.getId());
	        statement.executeUpdate();

	        
	    } finally {
	       
	        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}

	        if (connect != null) try { connect.close(); } catch (SQLException ignore) {}

	    	}	   
		}


	 public List<Vehicle> select(String tipus) throws SQLException, ClassNotFoundException {
		 
		    Connection connect = null;
		    PreparedStatement statement = null;
		    ResultSet rs = null;
		    vehicles2 = new ArrayList<Vehicle>();

		    try {
		    	Dbconn db = new Dbconn();
				connect = db.Connect();
		        statement = connect.prepareStatement("select * from vehicles where tipus = ?");
		        statement.setString(1, vehicle.getTipus());
		        rs = statement.executeQuery();

		        while (rs.next()) {
		        	
		        	vehicle.setId(rs.getInt("id"));
					vehicle.setRendszam(rs.getString("rendszam"));
					vehicle.setTipus(rs.getString("tipus"));
					vehicle.setGyarto(rs.getString("gyarto"));
					vehicle.setAlvazid(rs.getInt("alvazid"));
					vehicle.setDatum(rs.getDate("datum"));
					vehicle.setPrice(rs.getInt("price"));
					vehicle.setKmprice(rs.getInt("kmprice"));
					vehicle.setStatusz(rs.getString("statusz"));
					
		            vehicles2.add(vehicle);
		        }
		    } finally {
		        if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
		        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		        if (connect != null) try { connect.close(); } catch (SQLException ignore) {}

		    }

		    return vehicles2;
		}
	 
	 public List<Vehicle> listByRsz(String rendszam) throws SQLException, ClassNotFoundException {
		 
		    Connection connect = null;
		    PreparedStatement statement = null;
		    ResultSet rs = null;
		    vehiclesRsz = new ArrayList<Vehicle>();

		    try {
		    	Dbconn db = new Dbconn();
				connect = db.Connect();
		        statement = connect.prepareStatement("select * from vehicles where rendszam = ?");
		        statement.setString(1, vehicle.getRendszam());
		        rs = statement.executeQuery();

		        while (rs.next()) {
		        	
		        	vehicle.setId(rs.getInt("id"));
					vehicle.setRendszam(rs.getString("rendszam"));
					vehicle.setTipus(rs.getString("tipus"));
					vehicle.setGyarto(rs.getString("gyarto"));
					vehicle.setAlvazid(rs.getInt("alvazid"));
					vehicle.setDatum(rs.getDate("datum"));
					vehicle.setPrice(rs.getInt("price"));
					vehicle.setKmprice(rs.getInt("kmprice"));
					vehicle.setStatusz(rs.getString("statusz"));
					
		            vehiclesRsz.add(vehicle);
		        }
		    } finally {
		        if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
		        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		        if (connect != null) try { connect.close(); } catch (SQLException ignore) {}

		    }

		    return vehiclesRsz;
		}
	 
	 
	 public int calculatePrice(Integer id) throws ClassNotFoundException, SQLException {
		 
		 
		  Connection connect = null;
		    PreparedStatement statement = null;
		    ResultSet rs = null;
		    vehicle.setCostifBroken(0);

		    try {
		    	Dbconn db = new Dbconn();
				connect = db.Connect();
				
		        statement = connect.prepareStatement("select price from vehicles where id = ?");
		        statement.setInt(1, vehicle.getId());
		        rs = statement.executeQuery();

		        while (rs.next()) {
		   
					vehicle.setPrice(rs.getInt("price"));
							if(vehicle.getStatusz() == "sérült") {
									vehicle.setCostifBroken(50000);
									return vehicle.getPrice() + vehicle.getCostifBroken();
							}
		        }
		        

		    } finally {
		    	
		        if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
		        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		        if (connect != null) try { connect.close(); } catch (SQLException ignore) {}

		    }
		    System.out.println(vehicle.getCostifBroken());
		    
		    return vehicle.getPrice() ;
		    
		}
		 
	 
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public List<Vehicle> getVehicles2() {
		return vehicles2;
	}

	public void setVehicles2(List<Vehicle> vehicles2) {
		this.vehicles2 = vehicles2;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}


	public List<Vehicle> getRented() {
		return rented;
	}

	public void setRented(List<Vehicle> rented) {
		this.rented = rented;
	}

	public List<Vehicle> getRentable() {
		return rentable;
	}

	public void setRentable(List<Vehicle> rentable) {
		this.rentable = rentable;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public List<Vehicle> getVehiclesRsz() {
		return vehiclesRsz;
	}


	public void setVehiclesRsz(List<Vehicle> vehiclesRsz) {
		this.vehiclesRsz = vehiclesRsz;
	}
	 
}
