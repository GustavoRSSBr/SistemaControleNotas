package br.com.gustavorssbr.Sistema.de.Controle.de.Notas.utils;

import br.com.gustavorssbr.Sistema.de.Controle.de.Notas.domain.enums.TipoUsuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RotasUtil {

    public static Map<String, List<TipoUsuario>> getRotas(ResourceLoader resourceLoader) {
        Map<String, List<TipoUsuario>> pathsAndRoles = new HashMap<>();
        Resource resource = resourceLoader.getResource("classpath:rotas.json");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<PathRoleMapping> rolePathList = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<PathRoleMapping>>() {
            });
            for (PathRoleMapping rolePath : rolePathList) {
                pathsAndRoles.put(rolePath.getPath(), rolePath.getRoles());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathsAndRoles;
    }

    private static class PathRoleMapping {
        private String path;
        private List<TipoUsuario> roles;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public List<TipoUsuario> getRoles() {
            return roles;
        }

        public void setRoles(List<TipoUsuario> roles) {
            this.roles = roles;
        }
    }
}

