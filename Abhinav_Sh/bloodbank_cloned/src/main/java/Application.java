import org.apache.catalina.startup.Tomcat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class Application {
    private static final int PORT = getPort();
    private static final Logger logger = LogManager.getLogger("vehicles");

    public static void main(String[] args) throws Exception {
        logger.info("Staring the Server , feeding the WebAppDirectory");
        String webAppDirLocation = "src/main/webapp";
        final Tomcat tomcat = new Tomcat();
        logger.info("Creating a Tomcat Instance");
        tomcat.setBaseDir(createTempDir());
        tomcat.setPort(PORT);
        logger.info("Setting the Port as 8080");
        tomcat.getConnector();
        tomcat.getHost().setAppBase("src/main");
        tomcat.addWebapp("", new File(webAppDirLocation).getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
    }

    private static int getPort() {
        String port = System.getenv("PORT");
        if (port != null) {
            return Integer.parseInt(port);
        }
        return 8080;
    }

    private static String createTempDir() {
        try {
            File tempDir = File.createTempFile("tomcat.", "." + PORT);
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit();
            return tempDir.getAbsolutePath();
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Unable to create tempDir. java.io.tmpdir is set to " + System.getProperty("java.io.tmpdir"), ex
            );
        }
    }
}