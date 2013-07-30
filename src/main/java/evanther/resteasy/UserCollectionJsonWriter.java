package evanther.resteasy;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import evanther.resteasy.UserRepository.User;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class UserCollectionJsonWriter implements MessageBodyWriter<Collection<User>> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public long getSize(Collection<User> t, Class<?> type, Type genericType, Annotation[] annotations,
            MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Collection<User> users, Class<?> type, Type genericType, Annotation[] annotations,
            MediaType mediaType,
            MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException,
            WebApplicationException {

        PrintWriter writer = new PrintWriter(entityStream);
        writer.print("[");
        boolean first = true;
        for (User user : users) {
            if (first) {
                first = false;
            } else {
                writer.print(",");
            }
            writer.print("{\"id\":" + user.getId() + ",\"nombre\":\"" + user.getName() + "\"}");
        }
        writer.print("]");

        writer.flush();
        writer.close();
    }

}
