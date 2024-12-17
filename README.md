# Spring_Boot-Mini-projet
une application web permettant à une organisation de gérer des événements (conférences, séminaires, ateliers) et les inscriptions des participants.

Liste des acteurs :
Administrateur : Responsable de la gestion des événements et des inscriptions des participants après authentification. 
Utilisateur (Participant) : Personne souhaitant consulter les événements et s’inscrire en ligne. 

Gestion des événements : 
Ajout d'un événement : L’administrateur peut créer de nouveaux événements.
Modification d'un événement : L’administrateur peut modifier les détails d’un événement existant. 
Suppression d'un événement : L’administrateur peut supprimer un événement. 
Consultation des événements : L’administrateur peut consulter la liste des événements créés avec un résumé des informations importantes.
Affichage des événements : Les utilisateurs peuvent consulter la liste des événements à venir, affichant les informations importantes comme le titre, la description, le lieu, la date, les places disponibles et le coût de participation. 

Gestion des participants (Backend) : 
Consultation des inscriptions : L’administrateur peut consulter les inscriptions des participants par événement et gérer celles-ci (validation, annulation).

Gestion des événements (Frontend) : 
Affichage des événements : Les utilisateurs peuvent consulter la liste des événements à venir, affichant les informations importantes comme le titre, la description, le lieu, la date, les places disponibles et le coût de participation. 
Recherche d’événements : Les utilisateurs peuvent rechercher des événements par Catégorie et Date. 
Inscription en ligne : Les utilisateurs peuvent s’inscrire à un événement. Les informations nécessaires sont Nom, Email, Téléphone, Mode de paiement (Carte, espèces)

Framework et Technologies : 

Spring boot MVC pour l'architecture Model-View-Controller et la gestion du rendu côté serveur (SSR). 
Spring security pour authentification de admin.
Thymeleaf  pour le rendu des pages HTML côté serveur. 
MySQL pour Base de données.  

