Archtype de l'application modele
==

Sert de modele pour creer une nouvelle application ou convertir une application existante.
directement dans le format standards de la gouvernance.

Utilisation
==

Creer un nouveau projet Eclipse :
 
 - Faire un clic droit, 
 - Positionner la souris sur "New"
 - Selectionner "Other"
 - Dans la zone de saisie, ecrire "Maven"
 - Selectionner "Maven Project"
 - Laisser les choix par defaut (Use default Workspace location)
 - Dans la zone filter, saisir "lq"
 - Selection l'entree qui correspond a : GrouId : com.lq.ref, ArtifactId : app-modele-archetype, Version: 2.0.1
 - Choisir un groupId qui commence par com.lq, un ArtifactId... 
 
Deployer le projet sous JBoss

  - Clic droit sur le projet / Run As / Maven install
  - Aller dans le projet MonApp-web/target, clic droit sur MonApp-web-0.0.1-SNAPSHOT.war et selectionner "Mark as Deployable"
  - Demarrer JBoss, attendre 10 secs puis saisir http://localhost:8080/MonApp/ sur votre navigateur. 
  
   
  
