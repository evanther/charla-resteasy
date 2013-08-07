package evanther.resteasy.server;

import java.util.List;

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

    @AddLinks
    @LinkResource(value = Book.class, rel = "list")
    @GET
    @Path("/books")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Wrapped(element = "books")
    public List<Book> listBooks() {
        List<Book> bookList = bookRepository.list();
        return bookList;
    }

    @AddLinks
    @LinkResource(value = Book.class, rel = "self", pathParameters = { "${id}" })
    @GET
    @Path("/book/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getBook(@PathParam("id") Long id) {

        if (bookRepository.exists(id)) {
            Book book = bookRepository.get(id);
            return Response.status(Status.OK).entity(book).build();
        } else {
            return Response.status(Status.NOT_FOUND).entity("Book Id not found").build();
        }
    }

    @LinkResource(value = Book.class, rel = "add", pathParameters = { "${author}", "${title}" })
    @POST
    @Path("/book/create/{author}/{title}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response create(@PathParam("author") String author, @PathParam("title") String title) {
        Book book = new Book(0l, author, title);
        bookRepository.add(book);
        return Response.status(Status.OK).entity("created id " + book.getId()).build();
    }

    @LinkResource(value = Book.class, rel = "update", pathParameters = { "${id}", "${author}", "${title}" })
    @PUT
    @Path("/book/update/{id}/{author}/{title}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(@PathParam("id") Long id, @PathParam("author") String author,
            @PathParam("title") String title) {

        if (bookRepository.exists(id)) {
            Book book = new Book(id, author, title);
            bookRepository.update(book);
            return Response.status(Status.OK).entity("updated").build();
        } else {
            return Response.status(Status.NOT_FOUND).entity("Book Id not found").build();
        }
    }

    @LinkResource(value = Book.class, rel = "remove", pathParameters = { "${id}" })
    @DELETE
    @Path("/book/delete/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response delete(@PathParam("id") Long id) {

        if (bookRepository.exists(id)) {
            bookRepository.remove(id);
            return Response.status(Status.OK).entity("deleted").build();
        } else {
            return Response.status(Status.NOT_FOUND).entity("Book Id not found").build();
        }
    }
}
