package evanther.resteasy;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import evanther.resteasy.UserRepository.User;
import evanther.resteasy.UserRepository.UserList;

@Path("/b")
public class B_AcceptHeaderExample {

    @GET
    @Path("/users")
    @Produces({
        MediaType.APPLICATION_JSON, 
        MediaType.APPLICATION_XML
    })
    public Response list() {
        UserList users = UserRepository.list();
        return Response.status(200).entity(users).build();
    }

    @GET
    @Path("/user/{id}")
    @Produces({ 
        MediaType.APPLICATION_JSON, 
        MediaType.APPLICATION_XML 
    })
    public Response getUser(@PathParam("id") Long id) {

        if (UserRepository.exists(id)) {
            User user = UserRepository.get(id);
            return Response.status(200).entity(user).build();
        } else {
            return Response.status(404).entity("User Id not found").build();
        }
    }

    @POST
    @Path("/user/create/{name}")
    public Response create(@PathParam("name") String name) {
        User user = new User(0l, name);
        UserRepository.add(user);
        return Response.status(200).entity("created id " + user.getId()).build();
    }

    @PUT
    @Path("/user/update/{id}/{name}")
    public Response update(@PathParam("id") Long id, @PathParam("name") String name) {

        if (UserRepository.exists(id)) {
            User user = new User(id, name);
            UserRepository.update(user);
            return Response.status(200).entity("updated").build();
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
