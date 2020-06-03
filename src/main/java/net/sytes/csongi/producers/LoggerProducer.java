package net.sytes.csongi.producers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;
import java.util.logging.Logger;

@ApplicationScoped
@Named
public class LoggerProducer {

  @Produces
  public Logger getLogger(InjectionPoint injectionPoint) {
    return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getCanonicalName());
  }
}
