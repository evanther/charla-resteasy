package evanther.resteasy.server.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidTokenExceptionMapper implements ExceptionMapper<InvalidTokenException> {

    @Override
    public Response toResponse(InvalidTokenException e) {

        return Response.status(Status.FORBIDDEN).entity(e.getMessage()).build();

    }

}
