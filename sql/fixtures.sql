
USE `firstswingapp`;

-- Users --
INSERT INTO `user` (`id_user`, `is_admin`, `first_name`, `last_name`, `email`, `password`) VALUES (NULL, 1, 'Frédéric', 'Oudjoudi', 'fredoudjoudi@gmail.com', 'foo');
INSERT INTO `user` (`id_user`, `is_admin`, `first_name`, `last_name`, `email`, `password`) VALUES (NULL, 0, 'Clive', 'Roberts', 'cliveroberts@ucsql.com', 'foo');
INSERT INTO `user` (`id_user`, `is_admin`, `first_name`, `last_name`, `email`, `password`) VALUES (NULL, 1, 'fred', 'oudjoudi', 'fo', 'foo');
INSERT INTO `user` (`id_user`, `is_admin`, `first_name`, `last_name`, `email`, `password`) VALUES (NULL, 0, 'Clive', 'Roberts', 'cr', 'foo');

INSERT INTO `edition` (`id_edition`, `name`) VALUES (NULL, 'Hoboken, NJ : John Wiley & Sons, Inc.');
INSERT INTO `genre` (`id_genre`, `name`) VALUES (NULL, 'For dummies');
INSERT INTO `author` (`id_author`, `first_name`, `last_name`) VALUES (NULL, 'Mueller', 'John Paul');
INSERT INTO `document` (`id_document`, `title`, `pages_nbr`, `id_edition`, `id_genre`, `year`) VALUES (NULL, 'Beginning programming with Python ', '392', 1, 1,  '2018');
INSERT INTO `compose` (`id_author`, `id_document`) VALUES (1, 1);


INSERT INTO `edition` (`id_edition`, `name`) VALUES (NULL, 'Paris: First interactive');
INSERT INTO `genre` (`id_genre`, `name`) VALUES (NULL, 'Pour les nuls');
INSERT INTO `document` (`id_document`, `title`, `pages_nbr`, `id_edition`, `id_genre`, `year`) VALUES (NULL, 'Python', '388', 2, 2, '2018');
INSERT INTO `compose` (`id_author`, `id_document`) VALUES (1, 2);

INSERT INTO `edition` (`id_edition`, `name`) VALUES (NULL, 'Birmingham, UK : Packt Publishing');
INSERT INTO `document` (`id_document`, `title`, `pages_nbr`, `id_edition`, `id_genre`, `year`) VALUES (
  NULL, 'Python 3 object-oriented programming ', '452', 3, NULL, '2018');
INSERT INTO `author` (`id_author`, `first_name`, `last_name`) VALUES (NULL, 'Dusty', 'Phillips');
INSERT INTO `compose` (`id_author`, `id_document`) VALUES (2, 3);


INSERT INTO `edition` (`id_edition`, `name`) VALUES (NULL, "New York : Apress");
INSERT INTO `genre` (`id_genre`, `name`) VALUES (NULL, "The expert's voice in open source");
INSERT INTO `document` (`id_document`, `title`, `pages_nbr`, `id_edition`, `id_genre`, `year`) VALUES (
  NULL, 'Dive into Python 3', '360', 4, 3, '2008');
INSERT INTO `author` (`id_author`, `first_name`, `last_name`) VALUES (NULL, 'Mark', 'Pilgrim');
INSERT INTO `compose` (`id_author`, `id_document`) VALUES (3, 4);

-- A second author for document 4
INSERT INTO `compose` (`id_author`, `id_document`) VALUES (2, 4);

