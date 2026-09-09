# Les bibliothèques, ajoutées à la main

Il n'y a **pas d'outil de build** dans ce projet : ni Maven, ni Gradle. C'est une contrainte
du brief, et elle a une raison. Ajouter un `.jar` à la main, le mettre dans le classpath et
constater que la compilation passe est la façon la plus directe de comprendre ce qu'un outil
de build fera plus tard à votre place.

Le `.jar` est versionné dans ce dépôt : votre clone compile sans que vous ayez rien à
télécharger. Le tableau ci-dessous dit quand même d'où il vient, parce que vous aurez à
refaire la manipulation vous-mêmes au sprint suivant.

## Ce qu'il y a ici

| Fichier | Rôle | Sprint | Où le prendre |
|---|---|---|---|
| `postgresql-42.7.9.jar` | pilote JDBC PostgreSQL | 1 | https://jdbc.postgresql.org/download/ ou Maven Central, `org/postgresql/postgresql/42.7.9/` |

Jackson arrivera au sprint 2. Pour l'instant, un seul jar suffit.

## Comment il entre dans la compilation

Par le classpath, et nulle part ailleurs :

```
javac -encoding UTF-8 -cp "lib/*" -d out src/Main.java src/model/*.java src/data/*.java
java -cp "out;lib/*" Main
```

Sous Windows, le séparateur de classpath est `;` ; sous Linux et macOS, c'est `:`. C'est la
seule chose à changer pour que ces commandes tournent ailleurs.

**Depuis un IDE, c'est le même travail sous un autre nom.** Il faut déclarer le `.jar` comme
dépendance du projet, sinon la compilation passe et l'exécution échoue sur
`No suitable driver found` — le code n'importe rien de `org.postgresql`, il ne connaît que
`java.sql`, donc le compilateur ne voit pas qu'il manque quelque chose.
