package club.fiverr.user.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI userServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FIVERREX. — User & Seller Profile Service API")
                        .version("1.0.0")
                        .description("User profile, freelancer seller leveling, skills, and availability management microservice for FIVERREX.")
                        .contact(new Contact().name("FIVERREX Engineering").email("engineering@fiverrex.club")));
    }
}
