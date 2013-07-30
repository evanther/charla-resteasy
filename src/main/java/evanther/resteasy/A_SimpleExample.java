package evanther.resteasy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import evanther.resteasy.UserRepository.User;

@Path("/a/user")
public class A_SimpleExample {

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") Long id) {

        if (UserRepository.exists(id)) {
            User user = UserRepository.get(id);
            return Response.status(200).entity(user).type(MediaType.APPLICATION_JSON).build();

        } else {
            return Response.status(404).entity("User Id not found").build();
        }
    }
}
