package evanther.resteasy.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * NOTA: Requiere generar el war con 'mvn clean package' y referenciarlo
 */
public class I_StandAloneWithJetty {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8001);

        WebAppContext context = new WebAppContext();

        context.setWar("/...path_to_project.../target/resteasy-tutorial-0.1-SNAPSHOT.war");
        context.setContextPath("/");

        server.setHandler(context);

        server.start();
        server.join();
    }

}
