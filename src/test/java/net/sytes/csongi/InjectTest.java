package net.sytes.csongi;

import java.io.File;
import java.net.URL;

import net.sytes.csongi.HelloClass;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.inject.Inject;

/**
 * Sample integration test: demonstrates how to create the WAR file using the ShrinkWrap API.
 * 
 * Delete this file if no integration test is required.
 */
@RunWith(Arquillian.class)
public class InjectTest {

    /**
     * Creates the WAR file that is deployed to the server.
     * 
     * @return WAR archive
     */
    @Deployment
    public static JavaArchive getArchive() {
        return DeploymentCreator.getWebArchive();
    }

    @Inject
    private HelloClass helloClass;
    /**
     * A sample test...
     * 
     */
    @Test
    public void test() {
        Assert.assertEquals("Should be: Hello","Hello",helloClass.getHello());
    }
}
