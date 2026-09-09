import data.StudentDao;

import java.sql.SQLException;

/**
 * Point d'entrée du Trombifunscope.
 *
 * En l'état, il fait une seule chose : demander à la base combien de fiches elle
 * porte, et l'afficher. C'est le test qui prouve que la chaîne tient — le pilote
 * JDBC, la connexion, le rôle applicatif, la table — avant que vous écriviez quoi
 * que ce soit dessus.
 *
 * Au premier lancement, il échoue, et c'est voulu : la base n'existe pas encore,
 * et c'est vous qui allez l'écrire. Ce dépôt ne tourne que quand vous vous en
 * êtes emparés.
 *
 * Il vous revient ensuite de le compléter pour montrer au PO les quatre
 * opérations qu'il a demandées. Écrivez vos fiches en dur, avec un
 * new Student(...) : l'enjeu de ce sprint est de montrer que le CRUD fonctionne
 * depuis Java, pas de fabriquer une interface. L'écran viendra plus tard, et il
 * n'aura rien à voir avec une console.
 *
 * Une règle : aucune requête SQL ici. Le main appelle data, et data parle à la
 * base.
 */
public class Main {

    private static final StudentDao dao = new StudentDao();

    public static void main(String[] args) {
        System.out.println("=== Trombifunscope ===");

        try {
            System.out.println("Liaison établie. Test : " + dao.count()
                    + " ligne(s) dans la table student.");
        } catch (SQLException e) {
            System.out.println("La base n'a pas répondu : " + e.getMessage());
        }
    }
}
