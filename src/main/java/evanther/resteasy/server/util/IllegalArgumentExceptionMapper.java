package evanther.resteasy.server.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

    @Override
    public Response toResponse(IllegalArgumentException e) {

        return Response.status(Status.FORBIDDEN).entity("IllegalArgumentException: " + e.getMessage()).build();

    }

}
