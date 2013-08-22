package evanther.resteasy.server;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import evanther.resteasy.server.util.User;

public class B_ClientExample {

    public static void main(String[] args) throws Exception {
        Client client = ClientBuilder.newClient();

        User newUser = new User(0l, "Sonic");
        Entity<User> newEntity = Entity.entity(newUser, MediaType.APPLICATION_JSON);

        WebTarget target = client.target("http://localhost:8080/resteasy-tutorial/b/users");
        Response response = target.request().post(newEntity);
        String value = response.readEntity(String.class);
        response.close();

        System.out.println(value);

        target = client.target("http://localhost:8080/resteasy-tutorial/b/users");
        response = target.request().accept(MediaType.APPLICATION_JSON).get(); // Opcional APPLICATION_XML
        value = response.readEntity(String.class);
        response.close();

        System.out.println(value);
    }

}
