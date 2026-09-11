-- ============================================================
-- 02-create-table.sql
-- Ce script doit être rejouable en entier sur une base vide.
-- psql -U trombifunscope_admin -d disney_trombifunscope -v ON_ERROR_STOP=1 -h localhost -p 5432 -f 02-create-table.sql
-- ============================================================

-- ------------------------------------------------------------
-- US 2 TABLE DES FICHES: Connecté en trombifunscope - création des tables
-- Opération de structure => administrateur
-- ------------------------------------------------------------

DROP TABLE IF EXISTS disney_characters CASCADE;

CREATE TABLE disney_characters(
   student_id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
   lastname VARCHAR(30) NOT NULL,
   firstname VARCHAR(20) NOT NULL,
   disney_character VARCHAR(50) NOT NULL,
   disney_movie VARCHAR(50) NOT NULL,
   iconic_quote VARCHAR(50),
   companion VARCHAR(30),
   image_url TEXT NOT NULL,
   creation_date TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ------------------------------------------------------------
-- Connecté en trombifunscope_admin - attribution des droits
-- Rappel du besoin : member = lecture+écriture sur disney_characters,
-- ------------------------------------------------------------

GRANT SELECT, INSERT, UPDATE, DELETE ON disney_characters TO trombifunscope_member;
