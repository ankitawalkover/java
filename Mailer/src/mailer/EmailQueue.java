package mailer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * This class is for mail queue
 * It contains different functions to deal with email database queue
 * @attributes different fields of database
 * @constructor initializes attribute fields for different object
 * attribute id is unique for each object
 */
public class EmailQueue {
	
	int id;
	String from;
	String to;
	String subject;
	String body;
	
	EmailQueue(int id,String from,String to,String subject,String text){
		this.id = id;
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = text;
	}
	
	/**  
	 * fetchPendingEmailQueue: To fetch pending records from email queue
	 * @param int limit of records
	 * @return ArrayList of EmailQueue Object
	 * @throws All Exceptions 
	 * Example  fetchPendingEmailQueue(100,4,3);
	 */
	 public static ArrayList<EmailQueue> fetchPendingEmailQueue(int limit, int totalProcess, int currentProcess){
		  int currentProcessIndex;
		  ArrayList<EmailQueue> emailQueueList = new ArrayList<EmailQueue>();
		   try{
		
			  DBConnection db = new DBConnection();
			  Statement stmt = null;
			  
			  //Execute a query
		      stmt = db.conn.createStatement();
		      String sql;
		      
		      if(currentProcess == totalProcess)
		    	  currentProcessIndex = 0;
		      else 
		    	  currentProcessIndex = currentProcess;

		      sql = "SELECT * FROM email_queue where (status = 0 OR (status = 3 and retry < 2)) and id%"+totalProcess+" = "+currentProcessIndex+" limit "+limit;
		      ResultSet rs = stmt.executeQuery(sql);
		      
		    //Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		      
		    	 int id = rs.getInt("id");
		         String to_email = rs.getString("to_email_address");
		         String from_email = rs.getString("from_email_address");
		         String subject = rs.getString("subject");
		         String body = rs.getString("body");

		    	  EmailQueue emailDetails = new EmailQueue(id,from_email,to_email,subject,body);

		    	 //Add each EmailQueue object into an ArrayList
		         emailQueueList.add(emailDetails);
		      }
		      	
		      	 //close database connection
		         db.conn.close();

		   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }
		   return emailQueueList;
	   }
	   
	 	/**  
		 * updateEmailStatus: To update status of the EmailQueue Object
		 * @param int status to be updated
		 * @return void
		 * @throws SQL Exceptions 
		 * Example  updateEmailStatus(status);
		 */
	   public void updateEmailStatus(int status){
		   try{
			   
			   DBConnection db = new DBConnection();
			   
			   // create the java mysql update preparedstatement
		      String query = "update email_queue set status = ? where id = ?";
		      PreparedStatement preparedStmt = db.conn.prepareStatement(query);
		      preparedStmt.setInt   (1, status);
		      preparedStmt.setInt(2, this.id);
		 
		      // execute the java preparedstatement
		      preparedStmt.executeUpdate();
		      
		      db.conn.close();
		   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }
		   
	   }
	   
	   /**  
		 * incrementRetryStatus: To update retry of the EmailQueue Object
		 * @return void
		 * @throws SQL Exceptions 
		 * Example  incrementRetryStatus();
		 */
	   public void incrementRetryStatus(){
		   try{
			   
			   DBConnection db = new DBConnection();
			   
			   // create the java mysql update preparedstatement
		      String query = "update email_queue set retry = retry+1 where id = ?";
		      PreparedStatement preparedStmt = db.conn.prepareStatement(query);
		      preparedStmt.setInt(1, this.id);
		 
		      // execute the java preparedstatement
		      preparedStmt.executeUpdate();
		      
		      db.conn.close();
		   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }
		   
	   }
	   
}
