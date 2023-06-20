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
