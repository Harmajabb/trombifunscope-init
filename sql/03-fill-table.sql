-- ------------------------------------------------------------
-- US 3 , le jeu d'essai : en tant que développeur de l'équipe, je veux un script qui remplit la table, pour qu'on travaille tous sur
-- les mêmes données et qu'on puisse repartir de zéro à tout moment.
-- Critères d'acceptation :
-- • le script insère la fiche de chaque membre du groupe ou de la promo, dans l'univers choisi
-- • je peux vider la table et rejouer le script pour retrouver exactement le même contenu
-- • je vérifie mon chargement avec un SELECT que j'écris moi même
-- • pour exécuter: psql -U trombifunscope_admin -d disney_trombifunscope -v ON_ERROR_STOP=1 -h localhost -p 5432 -f 03-fill-table.sql
-- ------------------------------------------------------------

DELETE FROM disney_characters;

INSERT INTO disney_characters (lastname, firstname, disney_character, disney_movie, iconic_quote, compagnon, image_url, creation_date) VALUES
('Cabon', 'Thibaut', 'Dingo', 'Dingo et Max', null, null, 'https://upload.wikimedia.org/wikipedia/en/5/5f/GoofyDisney.png', NOW()),
('Princess', 'Milady', 'Tiana', 'The Princess and the Frog', null, null, 'http://quotesgram.com/princess-tiana-quotes/', NOW()),
('Harmajabb','Jeanne', 'Merida', 'Rebelle', 'Je ne suis pas une princesse, je suis une reine!', 'Archibald', 'https://upload.wikimedia.org/wikipedia/en/0/0b/Merida.png', NOW());

SELECT * FROM disney_characters;