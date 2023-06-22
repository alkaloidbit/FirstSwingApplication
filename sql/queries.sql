select * from document;

-- info on users
select * from user;

-- Info on documents + edition
select d.title, d.pages_nbr, d.year, e.name as edition
from document d inner join edition e
on e.id_edition = d.id_edition;

-- Info on documents + edition + genre
select d.title, d.pages_nbr, d.year, e.name as edition, g.name as genre
from document d inner join edition e
on e.id_edition = d.id_edition
inner join belongs b
on b.id_document = d.id_document
inner join genre g
on g.id_genre = b.id_genre


-- Complete info about a document
SELECT
	d.title, d.pages_nbr, d.year,
	e.name as edition,
	CONCAT(a.first_name, ' ', a.last_name) as author,
	g.name as genre
FROM
	document d
LEFT JOIN edition e
ON
	e.id_edition = d.id_edition
LEFT JOIN compose c
ON
	c.id_document = d.id_document
LEFT JOIN author a
ON
	a.id_author = c.id_author
LEFT JOIN belongs b
ON
	b.id_document = d.id_document
LEFT JOIN genre g
ON
	g.id_genre = b.id_genre

-- Get author + documents he published
SELECT
	a.first_name,
	a.last_name,
	d.title,
	d.pages_nbr,
	d.year,
	e.name as edition,
	g.name as genre
FROM
	author a
LEFT JOIN compose c
ON
	c.id_author = a.id_author
LEFT JOIN document d
ON
	d.id_document = c.id_document
LEFT JOIN edition e
on e.id_edition = d.id_edition
left join belongs b
on b.id_document = d.id_document
left join genre g
on g.id_genre = b.id_genre


-- Query to search on document title field

select * from document where title  like "%Pyth%";

