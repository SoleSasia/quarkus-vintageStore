package ar.ssasia.quarkus.microservices.number;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@ApplicationPath("/")
@OpenAPIDefinition(
        info = @Info(title = "Number Microservice",
            description = "This microservice generates ISBN book numbers",
            version = "1.0",
            contact = @Contact(name = "Soledad Sasia", email = "soledadsasia@gmail.com")),
        externalDocs = @ExternalDocumentation(url = "https://github.com/SoleSasia", description = "Here, all the Number Microservice code"))
public class NumberMicroservice extends Application {
}
