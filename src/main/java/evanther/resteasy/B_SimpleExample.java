package evanther.resteasy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import evanther.resteasy.UserRepository.User;

@Path("/b/user")
public class B_SimpleExample {

    @GET
    @Path("/{id}")
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
}
