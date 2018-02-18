package testemail;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class outlookreceivemail {

   public static void check(String host, String storeType, String user,
      String password) 
   {
      try {

    	  String protocol="imaps";
    	  Properties props = new Properties();
    	  props.setProperty("mail.store.protocol", protocol);
    	                   
    	  //extra codes required for reading OUTLOOK mails during IMAP-start
    	      props.setProperty("mail.imaps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    	      props.setProperty("mail.imaps.socketFactory.fallback", "false");
    	      props.setProperty("mail.imaps.port", "993");
    	      props.setProperty("mail.imaps.socketFactory.port", "993");
    	  //extra codes required for reading OUTLOOK mails during IMAP-end
    	                   
    	  Session session = Session.getDefaultInstance(props, null);
    	  Store store = session.getStore(protocol);
    	  store.connect("imap-mail.outlook.com", "testsender.1@outlook.com", "password");
    	  Folder emailFolder = store.getFolder("INBOX");
    	  emailFolder.open(Folder.READ_WRITE);

      // retrieve the messages from the folder in an array and print it
      Message[] messages = emailFolder.getMessages();
      System.out.println("messages.length---" + messages.length);

      for (int i = 0, n = messages.length; i < n; i++) {
    
         Message message = messages[i];
         
         System.out.println("---------------------------------");
         System.out.println("Email Number " + (i + 1));
         System.out.println("Subject: " + message.getSubject());
         System.out.println("From: " + message.getFrom()[0]);
         System.out.println("Text: " + message.getContent().toString());
         
         //Object obj = message.getContent();
         //Multipart mp = (Multipart)obj;

//         Multipart multipart = (Multipart) message.getContent();
//         for(int j=0;j<multipart.getCount();j++) {
//        	    BodyPart bodyPart = multipart.getBodyPart(j);
//        	    if (bodyPart.isMimeType("text/*")) {
//        	        String s = (String) bodyPart.getContent();
//        	    }
//        	}

      }

      //close the store and folder objects
      emailFolder.close(false);
      store.close();

      } catch (NoSuchProviderException e) {
         e.printStackTrace();
      } catch (MessagingException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static void main(String[] args) {

      String host = "pop.gmail.com";// change accordingly
      String mailStoreType = "pop3";
      String username = "apoorva1996gupta@gmail.com";// change accordingly
      String password = "@poorvakeshu";// change accordingly

      check(host, mailStoreType, username, password);

   }

}