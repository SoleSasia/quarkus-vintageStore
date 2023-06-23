package ar.ssasia.quarkus.microservices.book;

import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;

@Path("/api/books")
@Tag(name = "Book REST Endpoint")
public class BookResource {

    @Inject
    Logger logger;
    @RestClient
    NumberProxy proxy;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) //is necessary @FormParam
    @Operation(
            summary = "Creates a Book",
            description = "Creates a book with an ISBN number")
    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(fallbackMethod = "fallingbackOnCreatingABook")

    public Response createABook(@FormParam("title") String title, @FormParam("author") String author,
                                @FormParam("year") Integer yearOfPublication, @FormParam("genre") String genre) {
        Book book = new Book();
        book.isbn13 = proxy.generateIsbnNumbers().isbn13;
        book.title = title;
        book.author = author;
        book.yearOfPublication = yearOfPublication;
        book.genre = genre;
        book.creationDate = Instant.now();
        logger.info("Book created: " + book);
        return Response.status(201).entity(book).build();
    }

    public Response fallingbackOnCreatingABook(String title, String author, Integer yearOfPublication,
                                               String genre) throws FileNotFoundException {
        Book book = new Book();
        book.isbn13 = "Will be set later";
        book.title = title;
        book.author = author;
        book.yearOfPublication = yearOfPublication;
        book.genre = genre;
        book.creationDate = Instant.now();
        saveBookOnDisk(book);
        logger.warn("Book saved on disk: " + book);
        return Response.status(206).entity(book).build(); //recurso parcialmente creado
    }

    private void saveBookOnDisk(Book book) throws FileNotFoundException {
        String bookJson = JsonbBuilder.create().toJson(book);
        try(PrintWriter out = new PrintWriter("book-" + Instant.now().toEpochMilli() + ".json")) {
            out.println(bookJson) ;
        }
    }
}
