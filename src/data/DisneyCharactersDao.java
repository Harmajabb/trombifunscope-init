package data;

import model.DisneyCharacter;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Une seule méthode vous est donnée, count(). Inspirez-vous de cet exemple pour écrire
 * les quatre opérations que le PO vous demande (ajouter, consulter, corriger,
 * retirer).
 *
 * Le fonctionnement d'un PreparedStatement est expliqué dans le README de ce dossier.
 */
public class DisneyCharactersDao {

    /***
     * C'est la requête la plus simple du projet, elle sert de
     * premier test : si elle répond, c'est que la base tourne et est accessible depuis ce projet.
     */
    public int count() throws SQLException {
        String sql = "SELECT count(*) as nbr_disney_characters FROM disney_characters";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            return resultSet.getInt("nbr_disney_characters");
        }
    }

    public int addCharacter(DisneyCharacter character) throws SQLException {
        String sql = "INSERT INTO disney_characters (lastname, " +
                "firstname," +
                "disney_character," +
                "disney_movie," +
                "iconic_quote," +
                "companion," +
                "image_url," +
                "creation_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, character.getLastName());
            statement.setString(2, character.getFirstName());
            statement.setString(3, character.getDisney_character());
            statement.setString(4, character.getDisney_movie());
            statement.setString(5, character.getIconic_quote());
            statement.setString(6, character.getCompanion());
            statement.setString(7, character.getImage_url());
            statement.setDate(8, java.sql.Date.valueOf(LocalDate.now()));
            return statement.executeUpdate();
        }
    }

    public int updateCharacter(DisneyCharacter character) throws SQLException {
        return 1;

    }

    public int deleteCharacter(int id) throws SQLException {
        String sql = "DELETE FROM disney_characters WHERE student_id = ?;";

        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        }
    }

    public List<DisneyCharacter> getAllCharacters() throws SQLException {
        return null;
    }

    public DisneyCharacter getCharacterById(int id) throws SQLException {
        return null;
    }
}