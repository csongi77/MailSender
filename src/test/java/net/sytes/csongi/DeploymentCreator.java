package net.sytes.csongi;

import net.sytes.csongi.HelloClass;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import java.io.File;

public class DeploymentCreator {

    private static final String mainPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main";
    private static final String metaInfPath = mainPath + File.separator + "resources" + File.separator + "META-INF";
    private static final String webPath = mainPath + File.separator + "webapp";
    private static JavaArchive jar;

    public static JavaArchive getWebArchive() {
        jar = ShrinkWrap.create(JavaArchive.class);
        jar.addAsManifestResource(new File(metaInfPath, "MANIFEST.MF"));
        jar.addAsResource(new File(metaInfPath, "persistence.xml"), "META-INF/persistence.xml");
        jar.addPackages(true, HelloClass.class.getPackage());
        System.out.println(jar.toString(true));
        return jar;
    }

}
