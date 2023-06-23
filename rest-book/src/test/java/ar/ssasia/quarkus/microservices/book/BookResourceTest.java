package ar.ssasia.quarkus.microservices.book;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

@QuarkusTest
public class BookResourceTest {

    @Test
    public void shouldCreateABook() {
        given()
                .formParam("author", "Antonio Goncalves")
                .formParam("title", "Understanding Quarkus")
                .formParam("genre", "IT")
                .formParam("year", 2020)
          .when()
                .post("/api/books")
          .then()
                .statusCode(201)
                .body("title", is("Understanding Quarkus"))
                .body("genre", is("IT"))
                //.body("year", is(2020))
                .body("author", is("Antonio Goncalves"))
                .body("isbn_13", startsWith("13-"))
                .body("creation_date", startsWith("20"));
    }

}