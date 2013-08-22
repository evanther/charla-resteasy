package evanther.resteasy.server;

import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

/**
 * Netty is a NIO (New Java I/O) client server framework which enables quick and
 * easy development of network applications such as protocol servers and
 * clients. It greatly simplifies and streamlines network programming such as
 * TCP and UDP socket server.
 */
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
