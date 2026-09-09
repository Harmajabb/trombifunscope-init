package model;

/**
 * Une fiche du Trombifunscope : une personne de la promo.
 * Elle porte un prénom et un nom, et c'est tout : c'est à vous
 * d'y mettre les colonnes que votre groupe a décidées.
 */
public class Student {

    private int id;
    private String firstName;
    private String lastName;

    /** Constructeur utilisé quand on lit une fiche déjà enregistrée : elle a un id. */
    public Student(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Constructeur utilisé quand on prépare une fiche qui n'est pas encore en base.
     * L'id vaut 0 tant que la base ne l'a pas attribué : c'est elle qui le donne,
     * pas nous.
     */
    public Student(String firstName, String lastName) {
        this(0, firstName, lastName);
    }
}
