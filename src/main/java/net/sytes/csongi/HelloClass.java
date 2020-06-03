package net.sytes.csongi;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class HelloClass {

    public String getHello() {
        return "Hello";
    }
}
