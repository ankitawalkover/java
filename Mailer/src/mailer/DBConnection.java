package mailer;

import java.sql.*;

/*
 * DBConnection class contain mysql db connection attributes
 */
public class DBConnection {
	
		//set your database name
		static final String dbName = "mailer";
		
	   //  Set your Database credentials
	   static final String USER = "root";
	   static final String PASS = "root123";

	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/"+dbName;

	  
	   Connection conn = null;
	   
	   DBConnection(){
		   try{
			   
		      //Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //Open a connection
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }
	   }
	   
	  
}
