package collabhubbr.users.infra.documentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI documentation() {
        return new OpenAPI()
                .info(this.info())
                .servers(List.of(this.serverLocal()))
                .components(this.components())
                ;
    }

    private Info info() {
        return new Info()
                .title("CollabHubBR API")
                .description("""
                        CollabHubBR é uma plataforma Open Source 100% brasileira voltada para a coordenação e organização de projetos de código aberto com alto engajamento da comunidade.
                                                
                        Integrada ao GitHub, a plataforma oferece um conjunto de ferramentas para facilitar a gestão colaborativa, tomada de decisões democráticas e o alinhamento contínuo entre contribuidores.
                                                
                        Entre as funcionalidades estão mecanismos de votação, organização de tarefas, fóruns de discussão e gestão de roadmap, tudo pensado para ser *driven-by-community* e manter a participação ativa dos colaboradores.
                        """)
                .summary("CollabHubBR API Documentation")
                .version("1.0.0")
                .contact(this.contact());
    }

    private Contact contact() {
        return new Contact()
                .name("") // TODO: Adicionar um nome
                .email("") // TODO: Adicionar um email
                .url("") // TODO: Adicionar uma URL de contato (de preferência, uma página de suporte na interface web do CollabHubBR)
                ;
    }

    private Server serverLocal() {
        return new Server()
                .url("http://localhost:8080")
                .description("Servidor local");
    }

    private Components components(){
        return new Components()
                .addSecuritySchemes("bearer",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                );
    }

}
