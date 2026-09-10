-- ------------------------------------------------------------
-- US 3 , le jeu d'essai : en tant que développeur de l'équipe, je veux un script qui remplit la table, pour qu'on travaille tous sur
-- les mêmes données et qu'on puisse repartir de zéro à tout moment.
-- Critères d'acceptation :
-- • le script insère la fiche de chaque membre du groupe ou de la promo, dans l'univers choisi
-- • je peux vider la table et rejouer le script pour retrouver exactement le même contenu
-- • je vérifie mon chargement avec un SELECT que j'écris moi même
-- ------------------------------------------------------------

INSERT INTO disney_characters (lastname, firstname, disney_character, disney_movie, image_url, creation_date) VALUES
('Cabon', 'Thibaut', 'Dingo', 'Dingo et Max', 'https://upload.wikimedia.org/wikipedia/en/5/5f/GoofyDisney.png', NOW()),
('Harmajabb','Jeanne', 'Merida', 'Rebelle', 'Je ne suis pas une princesse, je suis une reine!', 'Archibald', 'https://upload.wikimedia.org/wikipedia/en/0/0b/Merida.png', NOW()),
(('Princess', 'Milady', 'Tiana', 'The Princess and the Frog', 'http://quotesgram.com/princess-tiana-quotes/', NOW()));



SELECT * FROM disney_characters;

-- DELETE FROM disney_characters;