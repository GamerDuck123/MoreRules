package me.gamerduck.rules.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {

    private static final Properties PROPS = new Properties();
    private static Path FILE;

    private Config() {
    }

    public static void load(Path file) {
        FILE = file;
        try {
            if (!Files.exists(FILE.getParent())) {
                Files.createDirectories(FILE.getParent());
            }

            if (!Files.exists(FILE)) {
                try (InputStream in = Config.class.getClassLoader().getResourceAsStream("morerules.properties")) {
                    if (in == null) {
                        throw new FileNotFoundException("Default morerules.properties not found in JAR!");
                    }
                    Files.copy(in, FILE);
                }
            }

            try (InputStream in = Files.newInputStream(FILE)) {
                PROPS.load(in);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }

    public static String get(String key) {
        return PROPS.getProperty(key);
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    public static int getInt(String key) {
        try {
            return Integer.parseInt(get(key));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static double getDouble(String key) {
        try {
            return Double.parseDouble(get(key));
        } catch (NumberFormatException e) {
            return 0.0D;
        }
    }

    private static void setDefault(String key, String value) {
        if (!PROPS.containsKey(key)) {
            PROPS.setProperty(key, value);
        }
    }

}