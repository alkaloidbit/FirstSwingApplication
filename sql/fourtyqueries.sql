-- Selectionner tous les utilisateurs:
SELECT * FROM `user`;

-- Selectioner tous les auteurs
SELECT * FROM author;

-- Sélectionner tous les documents avec leurs éditions et genres associés :
SELECT d.*, e.name AS edition_name, g.name AS genre_name
FROM document d
LEFT JOIN edition e ON d.id_edition = e.id_edition
LEFT JOIN genre g ON d.id_genre = g.id_genre;

-- Sélectionner les documents publiés après l'année 2000
SELECT * FROM document WHERE year > '2000';

-- Insérer un nouvel auteur:
INSERT INTO author (first_name, last_name) VALUES ('John', 'Doe');

-- Insérer un nouveau document avec son édition et son genre associés :
INSERT INTO document (title, pages_nbr, id_edition, id_genre, year)
VALUES ('Mon livre', 200, 1, 2, '2022');

-- Mettre à jour le prénom d'un auteur en utilisant son ID:
UPDATE author SET first_name = 'Jane' WHERE id_author = 1;

-- Mettre à jour le nombre de pages d'un document en utilisant son ID :
UPDATE document SET pages_nbr = 250 WHERE id_document = 1;

-- Supprimer un auteur en utilisant son ID:
DELETE FROM author WHERE id_author = 1;

-- Supprimer un document en utilisant son ID :
DELETE FROM document WHERE id_document = 1;


-- Sélectionner tous les documents écrits par un auteur spécifique:
SELECT d.*
FROM document d
JOIN compose c ON d.id_document = c.id_document
WHERE c.id_author = 1;

-- Sélectionner tous les documents publiés par une certaine édition:
SELECT d.*
FROM document d
WHERE d.id_edition = 2;

-- Selectionner tous les docs appartenant au genre 
SELECT d.*
FROM document d
WHERE d.id_genre = 3;

-- Insérer une nouvelle édition et mettre à jour le document pour utiliser cette édition :

INSERT INTO edition (name)
VALUES ('Nouvelle édition');

UPDATE document
SET id_edition = LAST_INSERT_ID()
WHERE id_document = 4;

-- Mettre à jour le nom d'une édition en utilisant son ID :
UPDATE edition
SET name = "Nouveau nom d'édition"
WHERE id_edition = 4;

-- Mettre a jour le genre d'un document
UPDATE document
SET id_genre = 3
WHERE id_document = 4;

-- Supprimer tous les documents d'un auteur spécifique
DELETE FROM compose WHERE id_author = 2;
DELETE FROM document WHERE id_document IN (SELECT id_document FROM compose WHERE id_author = 2);


-- Supprimer tous les documents publiés par une certaine édition (en utilisant l'ID de l'édition) :
DELETE FROM document WHERE id_edition = 2;

-- Sélectionner tous les auteurs et le nombre de documents associés à chaque auteur :
SELECT a.*, COUNT(c.id_document) AS document_count
FROM author a
LEFT JOIN compose c ON a.id_author = c.id_author
GROUP BY a.id_author;

-- Sélectionner tous les documents publiés entre deux années spécifiques :
SELECT *
FROM document
WHERE year BETWEEN '2000' AND '2020';

-- Sélectionner tous les utilisateurs qui sont des administrateurs :
SELECT *
FROM user
WHERE is_admin = true;

-- Insérer un nouveau genre et associer ce dernier à un document existant :
INSERT INTO genre (name)
VALUES ('Nouveau genre');

INSERT INTO document (title, pages_nbr, id_genre, year)
VALUES ('Nouveau document', 100, LAST_INSERT_ID(), '2022');

-- Insérer un nouvel utilisateur avec les informations nécessaires :
INSERT INTO user (is_admin, first_name, last_name, email, password)
VALUES (false, 'John', 'Doe', 'johndoe@example.com', 'password123');

-- Mettre à jour le nom d'un genre en utilisant son ID :
UPDATE genre
SET name = 'Nouveau nom de genre'
WHERE id_genre = <ID_GENRE>;

-- Mettre à jour le mot de passe d'un utilisateur en utilisant son ID :
UPDATE user
SET password = 'newpassword'
WHERE id_user = <ID_USER>;

DELETE FROM document WHERE id_genre = <ID_GENRE>;

DELETE FROM user WHERE id_user = <ID_USER>;





