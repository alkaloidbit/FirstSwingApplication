# FirstSwingApplication

Bonjour Marwa,

Voici le rendu de notre application de gesion d'une base de donnéé universitaire.

### Installation

Pour la telecharger, tu as plusieurs solutions:
1) Si tu as git installé, le plus simple est de cloner ce depot avec la commande suivante:

        `git clone https://github.com/alkaloidbit/FirstSwingApplication.git`
     
2) Sinon, telecharge un zip du code source [ici](https://github.com/alkaloidbit/FirstSwingApplication/archive/refs/heads/main.zip)


A la racine du dossier FirstSwingApplication, tu trouveras un dossier sql contenant le script de

creation de la base de donnee, ainsi qu'un script fixtures.sql, qui va peupler la base avec un jeu de donnees de test. 

Depuis ce dossier,  tu peux lancer un terminal, ou une invite de commande sous window et faire:

`mysql -u <user bdd> -p<mdp> install_db.sql`

en remplacant user bdd par l'utilisateur de ta bdd, ex: root, et <mdp>

Cela devrait avoir cree la BDD, ensuite tu peu faire:

mysql -u root -pRvTC6F8D < fixtures.sql

Cela va installer les donner de test.


     Une fois la base installée,

     lance intellij, puis "File" / "Open" et choisi le dossier que tu as extrait
     
     precedement.

### Usage

Ci-dessous les liens vers les diagrammes UML et vers le MCD.

[MCD](https://github.com/alkaloidbit/FirstSwingApplication/blob/main/docs/mocodo_notebook/sandbox.svg)



[Uses Cases Diagram](https://github.com/alkaloidbit/FirstSwingApplication/blob/main/docs/diagrams/Projet_bibli_CU_V2.drawio.png)

[Diagramme entites-relations](https://github.com/alkaloidbit/FirstSwingApplication/blob/main/docs/diagrams/Le%20mod%C3%A8le%20relationnel%20de%20la%20base%20de%20donn%C3%A9es.pdf)

Les [40 requetes sql](https://github.com/alkaloidbit/FirstSwingApplication/blob/main/sql/fourtyqueries.sql)

[Diagramme classes UML DAO](https://github.com/alkaloidbit/FirstSwingApplication/blob/main/docs/diagrams/Diagramme%20de%20classes%201.pdf)

[Diagramme classes User](https://github.com/alkaloidbit/FirstSwingApplication/blob/main/docs/diagrams/Diagramme%20de%20classes%203.pdf)
