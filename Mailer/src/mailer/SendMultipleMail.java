package mailer;

import java.util.ArrayList;

/*
 * SendMultipleMail class contains main method
 * initialize the number of emails to be picked from database default 10
 * get total number of processes and current process from arguments default 1
 * fetch email queue from database in a series based on total number of processes and current process
 * Initialize and start thread for each email object in queue
 */
public class SendMultipleMail {
	   public static void main(String args[]) {
	   
		   try{
			   int mailLimit = 10;
			   int currentProcess = 1;
			   int totalProcess = 1;
			   
			   if(args.length != 0)
				   mailLimit = Integer.parseInt(args[0]);
			   
			   if(args.length > 1)
				   totalProcess = Integer.parseInt(args[1]);
			   
			   if(args.length > 2)
				   currentProcess = Integer.parseInt(args[2]);
			   
			   if (currentProcess > totalProcess){
				      throw new IllegalArgumentException(
				        "Current Process can not be more than total number of process"
				      );
				    }
			   
			   ArrayList<EmailQueue> emailQueue = EmailQueue.fetchPendingEmailQueue(mailLimit,totalProcess,currentProcess);
			   for (EmailQueue emailDetailsObject : emailQueue) {

				   RunnableThreads R1 = new RunnableThreads(emailDetailsObject);
				      R1.start();
			    }
		   }catch (NumberFormatException nfe) {
		        System.out.println(nfe);
		    }
		   
		   
	  }   
	}
