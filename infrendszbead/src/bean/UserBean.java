package bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import db.Dbconn;
import entity.User;


@ManagedBean
@SessionScoped
public class UserBean implements Serializable{

	
	private static final long serialVersionUID = 6081417964063918994L;
	private User u;
	
	
	@PostConstruct
    public void init(){
        System.out.println("Called once on Object init!");
        this.u = new User();
    }
	
	
	public UserBean() {
		
	}


	public List<User> getUsers() throws SQLException, ClassNotFoundException  {

		Dbconn db = new Dbconn();
		Connection connect = db.Connect();
		
		
		List<User> users = new ArrayList<User>();
		PreparedStatement pstmt = connect
				.prepareStatement("select * from ugyfelek");
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			
			u.setId(rs.getInt("id"));
			u.setNev(rs.getString("nev"));
			u.setCim(rs.getString("cim"));
			u.setPhonenum(rs.getString("phonenum"));
			
			users.add(u);

		}

		// close resources
		rs.close();
		pstmt.close();
		connect.close();

		return users;

	}
	
	public void add() throws SQLException, ClassNotFoundException {
		
		
		try {
		Dbconn db = new Dbconn();
		Connection connect = db.Connect();
	
	String sql = "INSERT INTO ugyfelek(nev,cim,phonenum) VALUES(?,?,?)";
   PreparedStatement stmt = connect.prepareStatement(sql);
   stmt.setString(1, u.getNev());
   stmt.setString(2, u.getCim());
   stmt.setString(3, u.getPhonenum());
   

    stmt.executeUpdate();
    
    
   
	stmt.close();
	connect.close();
	
		}catch(Exception e) {
			throw new FacesException(e);
		}
		
    
    System.out.println("added to db");

	}


	public User getU() {
		return u;
	}


	public void setU(User u) {
		this.u = u;
	}
	
	
	
	
}
