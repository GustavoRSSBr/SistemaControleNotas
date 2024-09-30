package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigGson {
    @Bean
    public Gson gson() {
        return new Gson();
    }
}
