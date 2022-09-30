package customvalidator;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Custom Validator",
                description = "" +
                        "Spring boot project with Custom Validations",
                contact = @Contact(
                        name = "Somesh",
                        email = "abcDummy@gmail.com"
                )))
public class CustomValidatorApplication {

    public static void main(String[] args) {

        SpringApplication.run(CustomValidatorApplication.class);
    }
}
