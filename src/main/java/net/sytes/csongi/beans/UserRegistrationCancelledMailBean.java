package net.sytes.csongi.beans;


import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringReader;
import java.util.logging.Logger;

@MessageDriven(name = "UserRegistrationCancelledMailEJB", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/userServiceTopic"),
    @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "event='userRegistrationCancelled'")
})
public class UserRegistrationCancelledMailBean implements MessageListener {

  @Inject
  Logger logger;

  public UserRegistrationCancelledMailBean() {
  }

  @Override
  public void onMessage(Message message) {
    try {
      TextMessage receivedMessage = (TextMessage) message;
      String messageText = receivedMessage.getText();
      JsonObject registeredUser = Json.createReader(new StringReader(messageText)).readObject();
      String name = registeredUser.getString("username");
      String email = registeredUser.getString("email");
      String language = registeredUser.getString("lang");
      if (name != null && email != null) {
        logger.fine("sending --cancel-- registration mail to: " + name + ", address: " + email+", lang: "+language);
      }
    } catch (JMSException e) {
      logger.fine("error reading message");
      e.printStackTrace();
    }
  }
}
