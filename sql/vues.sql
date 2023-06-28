
-- vue des documents par genre

CREATE VIEW documents_by_genre AS
SELECT d.title, g.name AS genre
FROM document d
JOIN genre g ON d.id_genre = g.id_genre;

-- Vues des documeents par un auteur specifique

CREATE VIEW documents_by_author AS
SELECT d.title, CONCAT(a.first_name, ' ', a.last_name) AS author
FROM document d
JOIN compose c ON d.id_document = c.id_document
JOIN author a ON c.id_author = a.id_author;


--Vue des utilisateurs administrateurs :

CREATE VIEW admin_users AS
SELECT id_user, first_name, last_name, email
FROM user
WHERE is_admin = true;



-- Vue des auteurs ayant ecrit des documents avec un genre specifiguqj

CREATE VIEW authors_in_genre AS
SELECT a.id_author, CONCAT(a.first_name, ' ', a.last_name) AS author, g.name AS genre
FROM author a
JOIN compose c ON a.id_author = c.id_author
JOIN document d ON c.id_document = d.id_document
JOIN genre g ON d.id_genre = g.id_genre;


-- Vue des documents écrits par chaque auteur avec le nombre de documents associés :

CREATE VIEW documents_per_author AS
SELECT a.id_author, CONCAT(a.first_name, ' ', a.last_name) AS author, COUNT(c.id_document) AS document_count
FROM author a
LEFT JOIN compose c ON a.id_author = c.id_author
GROUP BY a.id_author, author;

