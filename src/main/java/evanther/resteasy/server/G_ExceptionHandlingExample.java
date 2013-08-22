package evanther.resteasy.server;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import evanther.resteasy.server.entity.Book;
import evanther.resteasy.server.other.InvalidTokenException;
import evanther.resteasy.server.other.TokenManager;
import evanther.resteasy.server.repository.BookRepository;

@Path("/g")
public class G_ExceptionHandlingExample {

    private BookRepository bookRepository = BookRepository.getInstance();

    @GET
    @Path("/books")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Wrapped(element = "books")
    public List<Book> listBooks(@HeaderParam("token") String token) {

        boolean valid = TokenManager.isValid(token);

        if (valid) {
            List<Book> bookList = bookRepository.list();
            return bookList;
        } else {
            throw new InvalidTokenException();
        }
    }

}
