package evanther.resteasy.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import evanther.resteasy.server.UserRepository.User;

@Path("/c")
public class C_QueryParamExample {

    @GET
    @Path("/user")
    @Produces({
        MediaType.APPLICATION_JSON, 
        MediaType.APPLICATION_XML 
    })
    public Response getUser(@QueryParam("id") Long id) {

        if (UserRepository.exists(id)) {
            User user = UserRepository.get(id);
            return Response.status(Status.OK).entity(user).build();
        } else {
            return Response.status(Status.NOT_FOUND).entity("User Id not found").build();
        }
    }
}
