package evanther.resteasy.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class ClientExample {

    public static void main(String[] args) throws Exception {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/resteasy-tutorial/b/users");
        Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
        String value = response.readEntity(String.class);
        response.close();

        System.out.println(value);
    }

}
