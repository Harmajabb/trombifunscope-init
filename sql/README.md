# Vos scripts SQL

Ajoutez ici les fichiers qui créent et remplissent votre table : ils vous serviront à rejouer
vos données et à les tester.

Deux exigences qui reviennent dans les critères d'acceptation :

- **rejouables** : les relancer sur un poste où tout existe déjà ne doit pas planter ;
- **cumulatifs** : joués dans l'ordre sur une machine vierge, ils doivent reconstituer la base
  entière.

Jouez-les avec `ON_ERROR_STOP`, sinon `psql` continue après une erreur et vous laisse croire
que tout s'est bien passé. Exemple :

```sh
psql -U postgres -v ON_ERROR_STOP=1 -f votre-script.sql
```

Une seule contrainte de nommage : **la table s'appelle `student`**. Le code fourni compte les
fiches avec un `SELECT count(*) FROM student` — sous un autre nom, il ne trouvera rien.
