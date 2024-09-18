package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Controle de Notas API")
                        .version("1.0")
                        .description("API para o gerenciamento de notas e avaliações no Sistema de Controle de Notas. " +
                                "Este sistema permite que professores e alunos interajam em um ambiente de controle acadêmico, " +
                                "com funcionalidades para cadastro, autenticação e manipulação de notas e avaliações."));
    }
}

