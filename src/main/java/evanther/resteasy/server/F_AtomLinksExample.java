package evanther.resteasy.server;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import org.jboss.resteasy.links.AddLinks;
import org.jboss.resteasy.links.LinkResource;

import evanther.resteasy.server.util.Book;
import evanther.resteasy.server.util.BookRepository;

@Path("/f")
public class F_AtomLinksExample {

    private BookRepository bookRepository = BookRepository.getInstance();

    @GET
    @Path("/books")
    @AddLinks
    @LinkResource(value = Book.class)
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Wrapped(element = "books")
    public List<Book> listBooks() {
        List<Book> bookList = bookRepository.list();
        return bookList;
    }

    @GET
    @Path("/book/{id}")
    @AddLinks
    @LinkResource(value = Book.class, pathParameters = { "${id}" })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getBook(@PathParam("id") Long id) {

        if (bookRepository.exists(id)) {
            Book book = bookRepository.get(id);
            return Response.status(Status.OK).entity(book).build();
        } else {
            return Response.status(Status.NOT_FOUND).entity("Book Id not found").build();
        }
    }

    @POST
    @Path("/books")
    @LinkResource(value = Book.class)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response createBook(Book book) {
        bookRepository.add(book);
        return Response.status(Status.OK).entity("Resource created with id " + book.getId()).build();
    }

    @PUT
    @Path("/book/{id}")
    @LinkResource(value = Book.class, pathParameters = { "${id}" })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateBook(@PathParam("id") Long id, Book book) {

        if (bookRepository.exists(id)) {
            book.setId(id);
            bookRepository.update(book);
            return Response.status(Status.OK).entity("Resource updated").build();
        } else {
            return Response.status(Status.NOT_FOUND).entity("Book Id not found").build();
        }
    }

    @DELETE
    @Path("/book/{id}")
    @LinkResource(value = Book.class, pathParameters = { "${id}" })
    public Response delete(@PathParam("id") Long id) {

        if (bookRepository.exists(id)) {
            bookRepository.remove(id);
            return Response.status(Status.OK).entity("Resource deleted").build();
        } else {
            return Response.status(Status.NOT_FOUND).entity("Book Id not found").build();
        }
    }
}
