package mailer;

/*
 * Class RunnableThreads used for thread processing
 * Each thread processes one item from email queue(EmailQueue object)
 * Constructor initializes email details and thread name
 */
class RunnableThreads implements Runnable {
	   private Thread t;
	   private EmailQueue emailDetails;
	   private String threadName;
	   
	   RunnableThreads(EmailQueue emailDetails){
		   this.emailDetails = emailDetails;
		   threadName = "email";
	   }
	   
	   /*
	    * Calls SMTP SendMail function to send mail
	    * After mail is sent update the status of mail queue to 2
	    * If any exception comes status of mail queue will be updated as 3 and retry status will be incremented by 1 
	    * @see java.lang.Runnable#run()
	    */
	   public void run() {
	      try {
	    	  SMTP mailerObject = new SMTP();
	    	  mailerObject.SendMail(emailDetails.from,emailDetails.to, emailDetails.subject, emailDetails.body);
	    	  
	    	  emailDetails.updateEmailStatus(2);
			   
	     } catch (Exception e) {
	    	 emailDetails.updateEmailStatus(3);
	    	 emailDetails.incrementRetryStatus();
	    	 System.out.println(e);
	      }
	   }
	   
	   /*
	    * Update the status of email queue to 1
	    * Start the thread
	    */
	   public void start ()
	   {
		  emailDetails.updateEmailStatus(1);
	      if (t == null)
	      {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }

	}