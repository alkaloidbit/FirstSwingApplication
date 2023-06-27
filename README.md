# FirstSwingApplication

Bonjour Marwa,

Voici le rendu de notre application de gesion d'une base de donnéé universitaire.

### Installation

Pour la telecharger, tu as plusieurs solutions:

  1) si tu as git installé, le plus simple est de cloner ce depot avec la commande suivante:

        git clone https://github.com/alkaloidbit/FirstSwingApplication.git
     
     Sinon, clique sur le bouton ![Pasted image](https://github.com/alkaloidbit/FirstSwingApplication/assets/38055982/789d8749-cc35-48d8-967e-2d7e891936c0) ci dessus, puis "download zip"

     A la racine du dossier FirstSwingApplication, tu trouveras un dossier sql contenant le script de

     creation de la base de donnee, ainsi qu'un script fixtures.sql qui va

     peupler la base avec un jeu de donnees de test. Depuis ce dossier,  tu peux lancer un terminal, ou une invite de commande

     sous window et faire:

    mysql -u root -pRvTC6F8D < install_db.sql

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


<svg width="410.5200073242187" height="234.1073555281355" viewBox="0 0 370 211" class="css-1j8o68f"><defs id="SvgjsDefs1439"></defs><g id="SvgjsG1440" featurekey="rootContainer" transform="matrix(1,0,0,1,0,0)" fill="#111111"><rect y="0" height="1" width="1" opacity="0"></rect><rect y="206" width="370" height="5"></rect></g><g id="SvgjsG1441" featurekey="symbolFeature-0" transform="matrix(1.958625019892499,0,0,1.958625019892499,-44.92302507011164,8.191207036055713)" fill="#46cdcf"><g xmlns="http://www.w3.org/2000/svg"><path fill="#46cdcf" d="M25.589,34.212l1.573-1.859l18.183-22.239l8.735,2.767L36.285,35.623l0.001,0.013   C31.458,38.804,26.093,36.095,25.589,34.212z"></path><path d="M71.551,10.622l-3.25,3.817l-8.158-2.412l-0.026-2.005l-0.014-1.68l-1.866,2.326l-0.003-3.13l-4.256,4.844l-7.256-2.308   l0.116-5.066L23.263,32.972l-0.098,0.117l-0.229,55.265l0,0.151l0.095,0.116c0.144,0.176,3.577,4.328,8.347,4.394   c2.497,0.033,4.873-1.067,7.087-3.282l0.01,1.18c0,0,7.846,9.309,15.684-0.13l18.415-24.432L71.551,10.622z M53.377,13.099   l-17.4,22.236c-3.2,2.056-6.247,1.249-7.416,0.803c-1.381-0.526-2.221-1.282-2.493-1.843l19.422-23.695L53.377,13.099z    M37.994,35.899c0-0.019,0.025,0.744,0.03,0.751l0.43,51.861c-2.208,2.454-4.581,3.695-7.061,3.663   c-3.962-0.051-7.045-3.326-7.617-3.973l0.226-54.804L45.975,7.331l-0.013,2.537l-0.761-0.241L26.842,32.081l-1.715,2.027   l0.056,0.212c0.259,0.967,1.467,1.989,3.079,2.604c1.298,0.495,4.701,1.394,8.254-0.937l0.201-0.145l-0.005-0.084l17.899-22.874   l0.021,0.006l2.762-3.132l0.002,1.955L37.994,35.899z M50.491,37.965l0.001,0.013c-4.772,3.251-10.183,0.635-10.719-1.239   l1.541-1.886l17.797-22.549l8.781,2.616L50.491,37.965z"></path></g></g><g id="SvgjsG1442" featurekey="nameFeature-0" transform="matrix(2.9801671620756025,0,0,2.9801671620756025,112.82776710993771,-8.808344836191239)" fill="#111111"><path d="M1.4 12 l4.4 0 l0 21.6 c0 2 0.88 2.72 2.28 2.72 s2.28 -0.72 2.28 -2.72 l0 -21.6 l4.16 0 l0 21.32 c0 4.48 -2.24 7.04 -6.56 7.04 s-6.56 -2.56 -6.56 -7.04 l0 -21.32 z M28.825 29.560000000000002 l4.16 0 l0 3.72 c0 4.48 -2.24 7.04 -6.56 7.04 s-6.56 -2.56 -6.56 -7.04 l0 -14.56 c0 -4.48 2.24 -7.04 6.56 -7.04 s6.56 2.56 6.56 7.04 l0 2.72 l-4.16 0 l0 -3 c0 -2 -0.88 -2.76 -2.28 -2.76 s-2.28 0.76 -2.28 2.76 l0 15.12 c0 2 0.88 2.72 2.28 2.72 s2.28 -0.72 2.28 -2.72 l0 -4 z M37.809999999999995 18.72 c0 -4.48 2.2 -7.04 6.48 -7.04 s6.48 2.56 6.48 7.04 l0 0.88 l-4.16 0 l0 -1.16 c0 -2 -0.8 -2.76 -2.2 -2.76 s-2.2 0.76 -2.2 2.76 c0 2.04 0.88 3.56 3.76 6.08 c3.68 3.24 4.84 5.56 4.84 8.76 c0 4.48 -2.24 7.04 -6.56 7.04 s-6.56 -2.56 -6.56 -7.04 l0 -1.72 l4.16 0 l0 2 c0 2 0.88 2.72 2.28 2.72 s2.28 -0.72 2.28 -2.72 c0 -2.04 -0.88 -3.56 -3.76 -6.08 c-3.68 -3.24 -4.84 -5.56 -4.84 -8.76 z M55.675 33.28 l0 -14.56 c0 -4.48 2.36 -7.04 6.68 -7.04 s6.68 2.56 6.68 7.04 l0 14.56 c0 1.56 -0.28 2.88 -0.84 3.92 c0.2 0.52 0.52 0.6 1.24 0.6 l0.4 0 l0 3.92 l-0.6 0 c-1.96 0 -3.2 -0.72 -3.8 -1.92 c-0.88 0.32 -1.92 0.52 -3.08 0.52 c-4.32 0 -6.68 -2.56 -6.68 -7.04 z M60.074999999999996 18.44 l0 15.12 c0 2 0.88 2.76 2.28 2.76 s2.28 -0.76 2.28 -2.76 l0 -15.12 c0 -2 -0.88 -2.76 -2.28 -2.76 s-2.28 0.76 -2.28 2.76 z M74.57999999999998 40 l0 -28 l4.4 0 l0 24 l7.24 0 l0 4 l-11.64 0 z"></path></g><g id="SvgjsG1443" featurekey="nameFeature-1" transform="matrix(2.183825293976482,0,0,2.183825293976482,113.50588077923248,99.79409647228222)" fill="#111111"><path d="M1.6 40 l0 -28 l4.4 0 l0 24 l7.24 0 l0 4 l-11.64 0 z M17.865000000000002 40 l0 -28 l4.4 0 l0 28 l-4.4 0 z M34.81 12 c4.56 0 6.52 2.12 6.52 6.44 l0 1 c0 2.88 -0.88 4.68 -2.84 5.6 l0 0.08 c2.36 0.92 3.28 3 3.28 5.96 l0 2.28 c0 4.32 -2.28 6.64 -6.68 6.64 l-6.92 0 l0 -28 l6.64 0 z M34.53 27.4 l-1.96 0 l0 8.6 l2.52 0 c1.48 0 2.28 -0.68 2.28 -2.76 l0 -2.44 c0 -2.6 -0.84 -3.4 -2.84 -3.4 z M34.69 16 l-2.12 0 l0 7.4 l1.72 0 c1.64 0 2.64 -0.72 2.64 -2.96 l0 -1.56 c0 -2 -0.68 -2.88 -2.24 -2.88 z M60.795 40 l-4.48 0 c-0.24 -0.72 -0.4 -1.16 -0.4 -3.44 l0 -4.4 c0 -2.6 -0.88 -3.56 -2.88 -3.56 l-1.52 0 l0 11.4 l-4.4 0 l0 -28 l6.64 0 c4.56 0 6.52 2.12 6.52 6.44 l0 2.2 c0 2.88 -0.92 4.72 -2.88 5.64 l0 0.08 c2.2 0.92 2.92 3 2.92 5.92 l0 4.32 c0 1.36 0.04 2.36 0.48 3.4 z M53.635 16 l-2.12 0 l0 8.6 l1.72 0 c1.64 0 2.64 -0.72 2.64 -2.96 l0 -2.76 c0 -2 -0.68 -2.88 -2.24 -2.88 z M75.66000000000001 12 l4.48 28 l-4.44 0 l-0.76 -5.08 l-5.4 0 l-0.76 5.08 l-4.04 0 l4.48 -28 l6.44 0 z M72.18 16.96 l-2.08 14.16 l4.24 0 l-2.08 -14.16 l-0.08 0 z M98.56500000000001 40 l-4.48 0 c-0.24 -0.72 -0.4 -1.16 -0.4 -3.44 l0 -4.4 c0 -2.6 -0.88 -3.56 -2.88 -3.56 l-1.52 0 l0 11.4 l-4.4 0 l0 -28 l6.64 0 c4.56 0 6.52 2.12 6.52 6.44 l0 2.2 c0 2.88 -0.92 4.72 -2.88 5.64 l0 0.08 c2.2 0.92 2.92 3 2.92 5.92 l0 4.32 c0 1.36 0.04 2.36 0.48 3.4 z M91.40500000000002 16 l-2.12 0 l0 8.6 l1.72 0 c1.64 0 2.64 -0.72 2.64 -2.96 l0 -2.76 c0 -2 -0.68 -2.88 -2.24 -2.88 z M107.39000000000001 40 l0 -9.28 l-5.56 -18.72 l4.6 0 l3.32 12.76 l0.08 0 l3.32 -12.76 l4.2 0 l-5.56 18.72 l0 9.28 l-4.4 0 z"></path></g></svg>
