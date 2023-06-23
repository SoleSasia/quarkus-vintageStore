package ar.ssasia.quarkus.microservices.number;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import java.time.Instant;
import java.util.Random;

@Path("/api/numbers")
@Tag(name = "Number REST Endpoint")
public class NumberResource {

    @Inject
    Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation (
            summary = "Generates book numbers",
            description = "ISBN 13 and ISBN 10 numbers"
    )
    public IsbnNumber generateIsbnNumbers() {
        IsbnNumber isbnNumber = new IsbnNumber();
        isbnNumber.isbn13 = "13-" + new Random().nextInt(100000000);
        isbnNumber.isbn10 = "10-" + new Random().nextInt(100000);
        isbnNumber.generationDate = Instant.now();
        logger.info("Number generated: " + isbnNumber);
        return isbnNumber;
    }
}
