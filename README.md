# FirstSwingApplication

Bonjour Marwa,

Voici le rendu de notre application de gesion d'une base de donnéé universitaire.

### Installation

Pour la telecharger, tu as plusieurs solutions:

1. Si tu as git installé, le plus simple est de cloner ce depot avec la commande suivante:
    ```sh
    git clone https:./github.com/alkaloidbit/FirstSwingApplication`
    ```

    Sinon, tu peux telecharger le [zip](https://https://github.com/alkaloidbit/FirstSwingApplication/archive/refs/heads/main.zip) ici


2. A la racine du dossier FirstSwingApplication, tu trouveras un dossier sql contenant le script `install_db.sql` de creation de la base de donnée, ainsi qu'un script `fixtures.sql` qui va peupler la base avec un jeu de donnees de test. Importe / execute ces deux scripts.

3. Une fois la base installée, lance intellij, puis "File" / "Open" et choisi le
   dossier que tu as extrait precedement. Ensuite, ouvre le fichier `Utilities/ConnectionToBDD.java` et renseigne les varibles url, user et password avec les valeurs correspondantes a ton SGBD

4. Lance le programme avec le bouton `run`
### Usage

#### Login
![Login page](https://github.com/alkaloidbit/FirstSwingApplication/blob/main/docs/FirstSwingApp_Login.png)

#### Fenetre principale
![Fenetre
principale](https://github.com/alkaloidbit/FirstSwingApplication/blob/main/docs/FirstSwingApp_Main.png)

Ci-dessous les liens vers les diagrammes UML et vers le MCD.

- [MCD](https://github.com/alkaloidbit/FirstSwingApplication/blob/main/docs/mocodo_notebook/sandbox.svg)

- [Diagramme entites-relations](https://github.com/alkaloidbit/FirstSwingApplication/blob/main/docs/diagrams/Le%20mod%C3%A8le%20relationnel%20de%20la%20base%20de%20donn%C3%A9es.pdf)

- [Uses Cases Diagram](https://github.com/alkaloidbit/FirstSwingApplication/blob/main/docs/diagrams/Projet_bibli_CU_V2.drawio.png)

- [Diagramme de classe 1](https://github.com/alkaloidbit/FirstSwingApplication/blob/main/docs/diagrams/Diagramme%20de%20classes%201.pdf)

- [Diagramme de classe 2](https://github.com/alkaloidbit/FirstSwingApplication/blob/main/docs/diagrams/Diagramme%20de%20classes%202.pdf)

- [Diagramme de classe 3](https://github.com/alkaloidbit/FirstSwingApplication/blob/main/docs/diagrams/Diagramme%20de%20classes%203.pdf)


Les [40 requetes sql](https://github.com/alkaloidbit/FirstSwingApplication/blob/main/sql/fourtyqueries.sql)
