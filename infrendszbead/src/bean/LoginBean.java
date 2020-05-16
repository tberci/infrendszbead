package bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.enterprise.context.SessionScoped;

import javax.inject.Named;

import db.Dbconn;
import java.io.Serializable;


@Named
@SessionScoped
public class LoginBean implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	private String userName;
    private String password;
    private String dbuserName;
  
    private String dbpassword;
    
    Connection connect;
    Statement statement;
    ResultSet resultSet;
    String SQL;
     
  
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getDbuserName() {
        return dbuserName;
    }
 
    public void setDbuserName(String dbuserName) {
        this.dbuserName = dbuserName;
    }
 
    public String getDbpassword() {
        return dbpassword;
    }
 
    public void setDbpassword(String dbpassword) {
        this.dbpassword = dbpassword;
    }
 
    public void dbData(String userName)
    {
        try
        {
        	Dbconn db = new Dbconn();
			connect = db.Connect();
            
            statement = connect.createStatement();
            SQL = "Select * from ugyfelek where nev like ('" + userName +"')";
            resultSet = statement.executeQuery(SQL);
            while(resultSet.next()){
            
            dbuserName = resultSet.getString(2).toString();
            dbpassword = resultSet.getString(4).toString();
            }
            
            resultSet.close();
            connect.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Exception Occured in the process :" + ex);
        }
    }
     
    public String checkValidUser()
    {
        dbData(userName);
  
        if(userName.equalsIgnoreCase(dbuserName))
        {
  
            if(password.equals(dbpassword))
                return "rent";
            else
            {
                return "index";
            }
        }
        else
        {
            return "index";
        }
    }
}
