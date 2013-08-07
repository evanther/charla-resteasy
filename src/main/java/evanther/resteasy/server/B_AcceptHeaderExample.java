package evanther.resteasy.server;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import evanther.resteasy.server.util.User;
import evanther.resteasy.server.util.UserRepository;

@Path("/b")
public class B_AcceptHeaderExample {

    private UserRepository userRepository = UserRepository.getInstance();

    @GET
    @Path("/users")
    @Produces({
        MediaType.APPLICATION_JSON, 
        MediaType.APPLICATION_XML
    })
    @Wrapped(element = "users")
    public List<User> listUsers() {
        List<User> users = userRepository.list();
        return users;
    }

    @GET
    @Path("/user/{id}")
    @Produces({ 
        MediaType.APPLICATION_JSON, 
        MediaType.APPLICATION_XML 
    })
    public Response getUser(@PathParam("id") Long id) {

        if (userRepository.exists(id)) {
            User user = userRepository.get(id);
            return Response.status(Status.OK).entity(user).build();
        } else {
            return Response.status(Status.NOT_FOUND).entity("User Id not found").build();
        }
    }

    @POST
    @Path("/user/create")
    @Consumes({
        MediaType.APPLICATION_JSON, 
        MediaType.APPLICATION_XML 
    })
    public Response create(User user) {
        userRepository.add(user);
        return Response.status(Status.OK).entity("created id " + user.getId()).build();
    }

    @PUT
    @Path("/user/update")
    @Consumes({ 
        MediaType.APPLICATION_JSON, 
        MediaType.APPLICATION_XML 
    })
    public Response update(User user) {

        if (userRepository.exists(user.getId())) {
            userRepository.update(user);
            return Response.status(Status.OK).entity("updated").build();
        } else {
            return Response.status(Status.NOT_FOUND).entity("User Id not found").build();
        }
    }
}
