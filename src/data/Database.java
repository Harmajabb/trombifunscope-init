package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Cette classe ouvre une connexion à PostgreSQL.
 * C'est getConnection() que vous devez appeler pour ouvrir une connexion.
 */
public class Database {

    private static Properties config;

    private Database() {
    }

    /**
     * Ouvre une connexion. L'appelant est responsable de la refermer.
     */
    public static Connection getConnection() throws SQLException {
        Properties c = loadConfig();
        return DriverManager.getConnection(
                c.getProperty("db.url"),
                c.getProperty("db.user"),
                c.getProperty("db.password"));
    }

    private static Properties loadConfig() throws SQLException {
        if (config != null) {
            return config;
        }
        Properties loaded = new Properties();
        try (InputStream in = new FileInputStream("config.properties")) {
            loaded.load(in);
        } catch (IOException e) {
            throw new SQLException("Fichier config.properties introuvable.", e);
        }
        config = loaded;
        return config;
    }
}
