package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = PropertiesReader.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (input == null) {
                throw new RuntimeException("application.properties não encontrado no classpath");
            }

            PROPERTIES.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar application.properties", e);
        }
    }

    private PropertiesReader() {
        // impede instanciação
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static String getRequired(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new RuntimeException("Propriedade obrigatória ausente: " + key);
        }
        return value;
    }
}