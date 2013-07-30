package evanther.resteasy;

import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import evanther.resteasy.UserRepository.User;

@Path("/a")
public class A_SimpleUserABM {

    @GET
    @Path("/users")
    public Response list() {
        Collection<User> users = UserRepository.list();
        return Response.status(200).entity(users).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/user/{id}")
    public Response getUser(@PathParam("id") Long id) {

        if (UserRepository.exists(id)) {
            User user = UserRepository.get(id);
            return Response.status(200).entity(user).type(MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(404).entity("User Id not found").build();
        }
    }

    @POST
    @Path("/user/create/{name}")
    public Response create(@PathParam("name") String name) {
        User user = new User(0l, name);
        UserRepository.add(user);
        return Response.status(200).entity("created id " + user.getId()).type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("/user/update/{id}/{name}")
    public Response update(@PathParam("id") Long id, @PathParam("name") String name) {

        if (UserRepository.exists(id)) {
            User user = new User(id, name);
            UserRepository.update(user);
            return Response.status(200).entity("updated").type(MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(404).entity("User Id not found").build();
        }
    }

    @DELETE
    @Path("/user/delete/{id}")
    public Response delete(@PathParam("id") Long id) {

        if (UserRepository.exists(id)) {
            UserRepository.remove(id);
            return Response.status(200).entity("deleted").build();
        } else {
            return Response.status(404).entity("User Id not found").build();
        }
    }
}
