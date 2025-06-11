package org.example.expedfacil.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API - ExpedFacil")
                        .version("v1")
                        .description("Esta é a API RESTful do sistema **ExpedFacil**, projetada para otimizar a **gestão completa do gerenciamento de expedição**." +
                                "\n\nEla oferece recursos robustos para controle de cargas, produtos, estoque local e processos de entrega, visando a eficiência  das operações logísticas." +
                                "\n\n**Equipe de Desenvolvimento:**\n" +
                                "- Ricardo Issa de Sousa (ricardoissadesousa@gmail.com)\n" +
                                "- Erick Gonçalves Maia (erickgm.developer@gmail.com)\n" +
                                "- Rafael de Oliveira Silva (rafaelsilva.orizona@gmail.com)")
                        .contact(new Contact().name("Rafael de Oliveira Silva").email("rafaelsilva.orizona@gmail.com"))
                        .termsOfService("https://ifgoiano.urt.gov/projetos")
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("https://ifgoiano.urt.gov/projetos")
                        )
                );
    }
}