package evanther.resteasy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
        return Response.status(Status.OK).entity(users).build();
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
            return Response.status(Status.OK).entity(user).build();
        } else {
            return Response.status(Status.NOT_FOUND).entity("User Id not found").build();
        }
    }
}
