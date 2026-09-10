-- ============================================================
-- 01-structure-and-ownership.sql
-- Ce script doit être rejouable en entier sur une base vide.
-- ============================================================

-- ------------------------------------------------------------
-- US 1 LA BASE ET SES ACCES : Connecté en tant que postgres (superutilisateur)
-- Objectif : créer la base et les deux rôles
-- À exécuter avec : psql -U postgres -d postgres -v ON_ERROR_STOP=1 -h localhost -p 5432 -f 01-structure-and-ownership.sql
-- ------------------------------------------------------------

DROP TABLE IF EXISTS disney_characters CASCADE; --possible bug
CREATE DATABASE disney_trombifunscope;

CREATE ROLE trombifunscope_admin LOGIN PASSWORD 'ILoveDisney'; 

CREATE ROLE trombifunscope_member LOGIN PASSWORD 'MeTooILoveDisney';

ALTER DATABASE disney_trombifunscope OWNER TO trombifunscope_admin;

\c disney_trombifunscope trombifunscope_admin
