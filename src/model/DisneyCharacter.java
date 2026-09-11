package model;

/**
 * Une fiche du Trombifunscope : une personne de la promo.
 * Elle porte un prénom et un nom, et c'est tout : c'est à vous
 * d'y mettre les colonnes que votre groupe a décidées.
 */
public class DisneyCharacter {

    private int id;
    private String firstName;
    private String lastName;
    private String disney_character;
    private String disney_movie;
    private String iconic_quote;
    private String companion;
    private String image_url;

    public DisneyCharacter(String firstName,
                           String lastName,
                           String disney_character,
                           String disney_movie,
                           String iconic_quote,
                           String companion,
                           String image_url
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.disney_character = disney_character;
        this.disney_movie = disney_movie;
        this.iconic_quote = iconic_quote;
        this.companion = companion;
        this.image_url = image_url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDisney_character() {
        return disney_character;
    }

    public String getDisney_movie() {
        return disney_movie;
    }

    public String getIconic_quote() {
        return iconic_quote;
    }

    public String getCompanion() {
        return companion;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDisney_character(String disney_character) {
        this.disney_character = disney_character;
    }

    public void setDisney_movie(String disney_movie) {
        this.disney_movie = disney_movie;
    }

    public void setIconic_quote(String iconic_quote) {
        this.iconic_quote = iconic_quote;
    }

    public void setCompanion(String companion) {
        this.companion = companion;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
