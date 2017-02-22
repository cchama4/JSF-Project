package edu.uic.ids517.DAO;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import edu.uic.ids517.model.UserBean;
@ManagedBean
@SessionScoped
public class DbAccess {
	private String url;
	private boolean status;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private UserBean userBean=new UserBean();
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public DbAccess(){
		
	}
	public DbAccess(String dbType, String uName, String passwd,String dbs, String svr) {
		// TODO Auto-generated constructor stub
		userBean.setCredentials(dbType,uName,passwd,dbs,svr);
		url=userBean.getDbms() + userBean.getDbServer()+userBean.getPort()+userBean.getDbSchema();
		try {
			Class.forName(userBean.getJdbcDriver());
			this.status=true;
		} catch (ClassNotFoundException c) {
			System.out.println(c.getMessage());
			// TODO Auto-generated catch block
			this.status=false;
		}
		catch (NullPointerException e1) {
			System.out.println(e1.getMessage());
			this.status=false;
		}
		try {
			connection = (Connection) DriverManager.getConnection(url, userBean.getUserName(), userBean.getPassword());
			this.status=true;
		} catch (SQLException s) {
			// TODO Auto-generated catch block
			System.out.println(s.getMessage());
			this.status=false;
		}
		catch(NullPointerException e){
			System.out.println(e.getMessage());
			this.status=false;
		}
	}
	public Boolean isStatus() {
		return status;
	}
	public ResultSet processSelect(String query)
	{
		try {
			statement=(Statement) connection.createStatement();
			resultSet=statement.executeQuery(query);
			return resultSet;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			return null;
		}		
	}
	public boolean processExecute(String query)
	{
		try {
			statement=(Statement) connection.createStatement();
			statement.execute(query);
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			return false;
		}		
	}
	public int processUpdate(String query)
	{
		try {
			statement=(Statement) connection.createStatement();
			int rows=statement.executeUpdate(query);
			return rows;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			return 0;
		}		
	}
}
