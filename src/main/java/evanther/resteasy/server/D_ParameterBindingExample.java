package evanther.resteasy.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import evanther.resteasy.server.c.Drink;
import evanther.resteasy.server.c.Fruit;

@Path("/d")
public class D_ParameterBindingExample {

    @GET
    @Path("/drink/{id}")
    public Response drink(@PathParam("id") Drink drink) {
        return Response.status(Status.OK).entity(drink.getName()).build();
    }

    @GET
    @Path("/fruit/{id}")
    public Response fruit(@PathParam("id") Fruit fruit) {
        return Response.status(Status.OK).entity(fruit.getName()).build();
    }

}
