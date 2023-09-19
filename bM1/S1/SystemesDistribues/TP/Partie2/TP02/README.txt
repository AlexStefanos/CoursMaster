Il y a malheureusement plusieurs fonctionnalités que je n'ai pas réussi à mettre en place. 
Premier problème : Je n'ai pas réussi à créer dans mes méthodes les topics et queues que je voulais. Donc, pour tester mon projet sur une nouvelle machine, il faut créer depuis l'interface graphique 1 topic ("ComptesGeres") et 1 queue ("OperationsEnAttente"). C'est d'ailleurs pour cette raison que je n'avais pas réussi à tester mon projet dans la salle Cyber. Mon projet n'est donc pas déployé pour qu'un utilisateur l'exécute comme demandé.
Deuxieme problème : je n'ai pas réussi à accéder aux données que j'envoyais dans mon topic. J'ai pourtant réussi à récupérer un MapMessage depuis une queue.
Troisième problème : je n'ai pas réussi à accéder au dernier élément d'une queue.
J'ai arrangé mes méthodes pour que cela fonctionne malgré tout après avoir créer manuelle le topic et la queue et j'ai mis plusieurs commentaires afin que vous puissiez voir ce que j'ai essayé de faire.

Afin de tester mon projet, vous pouvez exécuter manuellement les .java dans le fichier src/, ou alors directement faire la commande suivante afin d'exécuter le .jar :
	java -jar STEFANOS.jar
