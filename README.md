# AssurTeche - Application Console

## Contexte du projet
Ce projet vise à digitaliser les services d'une société d’assurance. L'application console facilite la gestion des conseillers, clients, contrats et sinistres, en appliquant les bonnes pratiques de Java 8 et de programmation fonctionnelle.

---

## Objectifs
- Créer une application console fonctionnelle pour gérer les assurés et leurs sinistres.
- Appliquer les concepts suivants :
    - **Streams API** pour le traitement des collections.
    - **Expressions Lambda et Method References** pour simplifier le code.
    - **Optional** pour la gestion sécurisée des valeurs nulles.

---

## Fonctionnalités

### Gestion des conseillers
### Gestion des clients
### Gestion des contrats
### Gestion des sinistres

---

## Architecture de l’application

### Couche Model
- `Person` : nom, prénom, email
- `Conseiller` : hérite de Person
- `Client` : hérite de Person, possède un conseiller
- `Contrat` : id, typeContrat (ENUM), dateDebut, dateFin, client
- `Sinistre` : id, typeSinistre (ENUM), dateTime, cout, description, contrat

### Couche Enum
- Types de contrats : `AUTOMOBILE`, `MAISON`, `MALADIE`
- Types de sinistres : `ACCIDENT_VOITURE`, `ACCIDENT_MAISON`, `MALADIE`

### Couche DAO
- `ClientDAO`, `ContratDAO`, `SinistreDAO`  
  Gestion des opérations CRUD et communication avec la base de données via JDBC.

### Couche Service
- `ConseillerService`, `ClientService`, `ContratService`, `SinistreService`  
  Contient la logique métier, les filtrages et tris avec Streams et Optional.

### Couche View
- `ClientView`, `ContratView`, `SinistreView`  
  Gestion des menus et interactions avec l’utilisateur.

---

## Spécifications techniques
- **Java 8**
- **JDBC** pour la persistance des modèles
- Encapsulation : toutes les propriétés sont privées avec getters/setters
- Utilisation de **Streams API**, **Lambda Expressions** et **Method References**
- Gestion sécurisée des valeurs nulles avec **Optional**
- Intégration de **Java Time API** pour gérer les dates des sinistres et contrats

---
