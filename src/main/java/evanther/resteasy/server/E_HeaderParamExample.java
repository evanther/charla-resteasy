package evanther.resteasy.server;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import evanther.resteasy.server.entity.User;
import evanther.resteasy.server.other.TokenManager;
import evanther.resteasy.server.repository.UserRepository;

@Path("/e")
public class E_HeaderParamExample {

    private UserRepository userRepository = UserRepository.getInstance();

    @GET
    @Path("/user/{id}")
    @Produces({
        MediaType.APPLICATION_JSON, 
        MediaType.APPLICATION_XML
    })
    public Response getUser(@PathParam("id") Long id, @HeaderParam("token") String token) {

        boolean valid = TokenManager.isValid(token);

        if (valid) {
            if (userRepository.exists(id)) {
                User user = userRepository.get(id);
                return Response.status(Status.OK).entity(user).build();
            } else {
                return Response.status(Status.NOT_FOUND).entity("User Id not found").build();
            }
        } else {
            return Response.status(Status.FORBIDDEN).entity("Invalid token").build();
        }

    }
}
