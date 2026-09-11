import data.DisneyCharactersDao;
import model.DisneyCharacter;

import java.sql.SQLException;

/**
 * Point d'entrée du Trombifunscope.
 *
 * Une règle : aucune requête SQL ici. Le sql est porté par StudentDao.
 * Le main appelle juste les méthode du DAO et affiche le résultat.
 */
public class Main {

    private static final DisneyCharactersDao dao = new DisneyCharactersDao();

    public static void main() {
        System.out.println("=== Trombifunscope ===");

        DisneyCharacter characterToInsert = new DisneyCharacter("Princess", "Léa", "raiponce", "raiponce", "Moi j'ai un rêve", "Pedro", "jkldjfklsdj");
        try {
            System.out.println("Liaison établie. Test : " + dao.count()
                    + " ligne(s) dans la table student.");
            System.out.println("Nombre de lignes insérées : " + dao.addCharacter(characterToInsert));
            System.out.println("CharactersList : " + dao.getAllCharacters());
            characterToInsert.setStudentId(3);
            dao.updateCharacter(characterToInsert);
            System.out.println("character by name : " + dao.getCharacterByLastName("Cabon"));
        } catch (SQLException e) {
            System.out.println("La base n'a pas répondu : " + e.getMessage());
        }

        int idToDelete = 4;
        try {
            int suppressLine = dao.deleteCharacter(idToDelete);

            if (suppressLine == 0) {
                System.out.println("This user already doesn't exist");
            } else {
                System.out.println("This student has been deleted, goodbye poor student");
            }
        } catch (SQLException e) {
            System.out.println("La base n'a pas répondu : " + e.getMessage());
        }
    }
}
