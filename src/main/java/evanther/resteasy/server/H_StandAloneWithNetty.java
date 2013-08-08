package evanther.resteasy.server;

import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;


public class H_StandAloneWithNetty {

    public static void main(String[] args) throws Exception {
        List<String> resources = new ArrayList<String>();
        resources.add(B_AcceptHeaderExample.class.getName());

        ResteasyDeployment deployment = new ResteasyDeployment();
        deployment.setResourceClasses(resources);

        NettyJaxrsServer netty = new NettyJaxrsServer();
        netty.setDeployment(deployment);

        int port = 8001;
        netty.setPort(port);
        // netty.setRootResourcePath("netty");

        netty.start();
        System.out.println("Netty server listening on port " + port);
    }

}
