package bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import db.Dbconn;
import entity.Vehicle;

@ManagedBean
@SessionScoped
public class VehicleBean implements Serializable {


	private static final long serialVersionUID = 1L;

	public List<Vehicle> getVehicles() throws SQLException, ClassNotFoundException  {

		Dbconn db = new Dbconn();
		Connection connect = db.Connect();
		
		
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		PreparedStatement pstmt = connect
				.prepareStatement("select * from vehicles");
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			Vehicle v = new Vehicle();
			v.setId(rs.getInt("id"));
			v.setTipus(rs.getString("tipus"));
			v.setGyarto(rs.getString("gyarto"));
			v.setAlvazid(rs.getInt("alvazid"));
			v.setDatum(rs.getDate("datum"));
			v.setPrice(rs.getInt("price"));
			v.setKmprice(rs.getInt("kmprice"));
			v.setStatusz(rs.getString("statusz"));
			
			vehicles.add(v);

		}

		// close resources
		rs.close();
		pstmt.close();
		connect.close();

		return vehicles;

	}
	
}
