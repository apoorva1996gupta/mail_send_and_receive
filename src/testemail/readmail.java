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
import javax.mail.internet.InternetAddress;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;

public class readmail {

   public static void check(String host, String storeType, String user,
      String password) 
   {
      try {

      //create properties field
      Properties properties = new Properties();

      properties.put("mail.pop3.host", host);
      properties.put("mail.pop3.port", "995");
      properties.put("mail.pop3.starttls.enable", "true");
      Session emailSession = Session.getDefaultInstance(properties);
  
      //create the POP3 store object and connect with the pop server
      Store store = emailSession.getStore("pop3s");

      store.connect(host, user, password);

      //create the folder object and open it
      Folder emailFolder = store.getFolder("INBOX");
      emailFolder.open(Folder.READ_ONLY);

      // retrieve the messages from the folder in an array and print it
     //Message[] messages = emailFolder.getMessages();
      //System.out.println("messages.length---" + messages.length);
      System.out.println("now searching folder for specific email address");
      SearchTerm sender = new FromTerm(new InternetAddress("ayushig515@gmail.com"));
      Message[] messages = emailFolder.search(sender);
      System.out.println("messages.length---" + messages.length);
      for (int i = 1; i < messages.length; i++) {
    	   // read_message(messages[i]);
       Message message = messages[i];
        
        System.out.println("---------------------------------");
        System.out.println("Email Number " + (i + 1));
        System.out.println("Subject: " + message.getSubject());
        System.out.println("From: " + message.getFrom()[0]);
        System.out.println("Text: " + message.getContent().toString());
    	}

      
//      for (int i =messages.length-3, n = messages.length; i < n; i++) {
//         Message message = messages[i];
//         
//         System.out.println("---------------------------------");
//         System.out.println("Email Number " + (i + 1));
//         System.out.println("Subject: " + message.getSubject());
//         System.out.println("From: " + message.getFrom()[0]);
//         System.out.println("Text: " + message.getContent().toString());
//        
//        
//
//      }

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