package ar.ssasia.quarkus.microservices.book;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;
@Schema(description = "This is a schema for a book")
public class Book {

    @JsonbProperty("isbn_13")
    public String isbn13;
    @Schema(required = true)
    public String title;
    @Schema(required = true)
    public String author;
    @JsonbProperty("year_of_publication")
    public Integer yearOfPublication;
    @Schema(required = true)
    public String genre;
    @JsonbProperty("creation_date")
    @JsonbDateFormat("yyyy-MM-dd")
    @Schema(implementation = String.class, format = "date")
    public Instant creationDate;

    @Override
    public String toString() {
        return "Book{" +
                "isbn13='" + isbn13 + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", genre='" + genre + '\'' +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }
}
