package model;

/**
 * Une fiche du Trombifunscope : une personne de la promo.
 * Elle porte un prénom et un nom, et c'est tout : c'est à vous
 * d'y mettre les colonnes que votre groupe a décidées.
 */
public class Disney_characters {

    private String firstName;
    private String lastName;
    private String disney_character;
    private String disney_movie;
    private String iconic_quote;
    private String compagnon;
    private String image_url;

    public Disney_characters(String firstName,
                             String lastName,
                             String disney_character,
                             String disney_movie,
                             String iconic_quote,
                             String compagnon,
                             String image_url
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.disney_character = disney_character;
        this.disney_movie = disney_movie;
        this.iconic_quote = iconic_quote;
        this.compagnon = compagnon;
        this.image_url = image_url;
    }

}
