package net.sytes.csongi.beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringReader;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

@MessageDriven(name = "RegistrationMailEJB", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/userServiceTopic"),
    @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "event='userCreated' and result='success'")
})
public class RegistrationMailBean implements javax.jms.MessageListener {

  @Inject
  Logger logger;

  public RegistrationMailBean() {
  }

  @Override
  public void onMessage(Message message) {
    logger.warning("user created! sending registration mail");
    try {
      TextMessage receivedMessage = (TextMessage) message;
      logger.fine("messageText: "+receivedMessage.getText());
      String messageText=receivedMessage.getText();
      JsonObject registeredUser = Json.createReader(new StringReader(messageText)).readObject();
      String name = registeredUser.getString("username");
      String email = registeredUser.getString("email");
      String lang = registeredUser.getString("lang");
      if (name != null && email != null) {
        logger.fine("sending registration mail to: " + name + ", address: " + email+", lang: "+lang);

        logger.warning(ResourceBundle.getBundle("registration_mail",new Locale(lang)).getString("mail_body_0"));
      }
    } catch (JMSException e) {
      logger.fine("error reading message");
      e.printStackTrace();
    }
  }
}
