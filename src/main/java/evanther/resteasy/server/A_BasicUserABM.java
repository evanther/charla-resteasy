package evanther.resteasy.server;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import evanther.resteasy.server.entity.User;
import evanther.resteasy.server.repository.UserRepository;

@Path("/a")
public class A_BasicUserABM {

    private UserRepository userRepository = UserRepository.getInstance();

    /**
     * NOTA: Para evitar exception
     * "NoMessageBodyWriterFoundFailure: Could not find MessageBodyWriter" es
     * necesario usar el serializer de Jackson. Esto sucede porque Jackson no
     * requiere elemento root para serializar Json, a diferencia de Jettison que
     * sí lo requiere
     */
    @GET
    @Path("/users")
    public Response listUsers() {
        List<User> users = userRepository.list();
        return Response.status(200).entity(users).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/user/{id}")
    public Response getUser(@PathParam("id") Long id) {

        if (userRepository.exists(id)) {
            User user = userRepository.get(id);
            return Response.status(200).entity(user).type(MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Status.NOT_FOUND).entity("User Id not found").build();
        }
    }

    @POST
    @Path("/user/create/{name}")
    public Response createUser(@PathParam("name") String name) {
        User user = new User(0l, name);
        userRepository.add(user);
        return Response.status(Status.OK).entity("Resource created with id = " + user.getId())
                .type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("/user/update/{id}/{name}")
    public Response updateUser(@PathParam("id") Long id, @PathParam("name") String name) {

        if (userRepository.exists(id)) {
            User user = new User(id, name);
            userRepository.update(user);
            return Response.status(Status.OK).entity("Resource updated").type(MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Status.NOT_FOUND).entity("User Id not found").build();
        }
    }

    @DELETE
    @Path("/user/delete/{id}")
    public Response delete(@PathParam("id") Long id) {

        if (userRepository.exists(id)) {
            userRepository.remove(id);
            return Response.status(Status.OK).entity("Resource deleted").build();
        } else {
            return Response.status(Status.NOT_FOUND).entity("User Id not found").build();
        }
    }
}
