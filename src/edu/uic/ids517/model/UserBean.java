package edu.uic.ids517.model;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uic.ids517.DAO.DbAccess;

@ManagedBean
@SessionScoped
public class UserBean {
	private String password;
	private String dbms;
	private String dbServer;
	private String dbSchema;
	private String port;
	private String userName;
	private String jdbcDriver;
	public void setDbServer(String dbServer) {
		this.dbServer = dbServer;
	}
	public void setDbSchema(String dbSchema) {
		this.dbSchema = dbSchema;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getJdbcDriver() {
		return jdbcDriver;
	}
	public String getPassword() {
		return password;
	}
	public String getDbms() {
		return dbms;
	}

	public String getDbSchema() {
		return dbSchema;
	}
	public String getDbServer() {
		return dbServer;
	}
	public String getPort() {
		return port;
	}

	public String getUserName() {
		return userName;
	}

	public UserBean() {
		// TODO Auto-generated constructor stub
	}
	public void setCredentials(String dbType, String uName, String passwd, String dbs, String svr)
	{
		this.userName=uName;
		this.password=passwd;
		this.dbSchema=dbs;
		this.dbServer=svr;
		if(dbType.equals("mysql"))
		{ 

			this.dbms="jdbc:mysql://";
			this.jdbcDriver="com.mysql.jdbc.Driver";
			this.port="3306/";
		}
		else if(dbType.equals("oracle"))
		{
			this.dbms="jdbc:oracle:thin:@";
			this.jdbcDriver="oracle.jdbc.driver.OracleDriver";
			this.port="1521";
		}
		else if(dbType.equals("db2"))
		{
			this.dbms="jdbc:db2://";
			this.jdbcDriver="com.ibm.db2.jcc.DB2Driver";
			this.port="50000";
		}
	}
	
}
