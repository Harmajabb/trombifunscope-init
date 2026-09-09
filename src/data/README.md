# Parler à PostgreSQL depuis Java : le PreparedStatement

Ce dossier porte tout le SQL du projet. Une seule opération y est écrite, `count()`, et les
quatre autres sont à vous. Ce document explique l'outil que vous allez utiliser pour les
écrire.

> **Les exemples ci-dessous portent volontairement sur une autre table que la vôtre**. 
> Ils sont donnés à titre d'illustration : à vous de les transposer.

## 1. Le problème

Vous voulez chercher une ville par son nom. Le réflexe est de coller la valeur dans la
requête :

```java
String nom = "Nantes";
String sql = "SELECT * FROM city WHERE name = '" + nom + "'";
```

Ça marche. Jusqu'à ce que la ville s'appelle **L'Isle-Adam** :

```sql
SELECT * FROM city WHERE name = 'L'Isle-Adam'
```

PostgreSQL voit la chaîne `'L'`, puis `Isle-Adam'` qu'il ne sait pas lire, et refuse la
requête.

Et c'est le cas gentil. Si la valeur vient d'un formulaire, quelqu'un peut y écrire
`'; DROP TABLE city; --` et votre requête devient deux requêtes, dont une qui détruit la
table. C'est ce qu'on appelle une **injection SQL**, et c'est l'une des
failles de sécurité les plus exploitées du web.

## 2. Ce qu'est un PreparedStatement

Une requête **à trous**, dans laquelle chaque trou est un `?`.

```java
String sql = "SELECT * FROM city WHERE name = ?";
```

Ce texte-là part au serveur tel quel. PostgreSQL l'analyse, comprend qu'on veut lire dans
`city` en filtrant sur `name`, et attend une valeur. Les valeurs arrivent **ensuite, par un
autre canal**, et le serveur les range dans les trous sans jamais les relire comme du SQL.

C'est là toute la différence. Une valeur qui contient un `DROP TABLE` reste une valeur : elle
est comparée au nom, littéralement, comme si on avait écrit

```java
String sql = "SELECT * FROM city WHERE name = 'DROP TABLE'";
```

et le résultat est simplement qu'aucune ville ne s'appelle comme ça.

## 3. Le mode d'emploi, en trois temps

**Préparer**, **remplir**, **exécuter**.

```java
String sql = "SELECT * FROM city WHERE country = ?";

try (Connection connection = Database.getConnection();                  // ouvre la connexion
     PreparedStatement statement = connection.prepareStatement(sql)) {  // prépare la requête

    statement.setString(1, "France");                                   // remplit les trous

    try (ResultSet resultSet = statement.executeQuery()) {              // exécute la requête
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
        }
    }
}
```

Trois choses à retenir de cette forme.

**Les trous se comptent à partir de 1**, pas de 0.
S'il y a trois `?`, ce sont les positions 1, 2 et 3, dans l'ordre où ils
apparaissent dans le texte de la requête.

**Le `?` ne se met pas entre guillemets.** On écrit `WHERE name = ?`, jamais `WHERE name =
'?'`.

**Un `?` remplace une valeur, jamais un nom.** Vous ne pouvez pas écrire
`SELECT * FROM ?` ni `ORDER BY ?` : le serveur analyse la requête avant de connaître les
valeurs, il doit donc savoir dès le départ dans quelle table et quelles colonnes il travaille.

## 4. Remplir : une méthode par type

Le `set` que vous appelez doit correspondre au type de la colonne.

| Colonne PostgreSQL | Méthode Java |
|---|---|
| `VARCHAR`, `TEXT` | `setString(1, valeur)` |
| `INTEGER`, `SERIAL` | `setInt(1, valeur)` |
| `BOOLEAN` | `setBoolean(1, valeur)` |
| `DATE` | `setDate(1, java.sql.Date.valueOf(uneLocalDate))` |

## 5. Exécuter : deux méthodes, deux retours

**`executeQuery()`** pour un `SELECT`. Il renvoie un `ResultSet`, c'est-à-dire un curseur qui
se déplace de ligne en ligne. Il commence **avant** la première ligne, donc il faut appeler
`next()` avant de lire quoi que ce soit.

```java
try (ResultSet resultSet = statement.executeQuery()) {
    while (resultSet.next()) {              // avance d'une ligne, false quand il n'y en a plus
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
    }
}
```

Pour une requête qui ne peut ramener qu'une ligne, `if (resultSet.next())` plutôt que `while`.
Lisez les colonnes **par leur nom**, pas par leur position : `getString("name")` reste juste
même lorsque le nombre de colonnes change.

**`executeUpdate()`** pour un `INSERT`, un `UPDATE` ou un `DELETE`. Il ne renvoie pas de
lignes, il renvoie **combien de lignes ont été touchées**. C'est très utile :

```java
int lignesTouchees = statement.executeUpdate();
if (lignesTouchees == 0) {
    // aucune ligne ne portait cet id : rien n'a été modifié
}
```

## 6. Pourquoi le `try (...)`

C'est un **try-with-resources**. Tout ce qu'on ouvre entre les parenthèses est refermé
automatiquement à la sortie du bloc, dans l'ordre inverse, même si une exception est levée au
milieu.

Sans lui, il faut penser à refermer soi-même, et le risque d'oubli est réel. Une connexion
oubliée ne fait pas planter le programme : elle reste ouverte, silencieusement, et au bout de
quelques dizaines, PostgreSQL refuse les suivantes.

## 7. À vous de jouer

Regardez `count()` dans `StudentDao.java`, c'est votre modèle pour construire les autres requêtes.