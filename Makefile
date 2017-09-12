SameGame: compil

.class: .java
	$(CC) .class

JAVAFILES = Blocs.class \
						ChargerPartie.class \
						GrilleDeJeu.class \
						MouseListenerBloc.class \
						ActionListenerFenetre.class \
						Ecran.class \
						EcranJeu.class \
						EcranMenu.class \
						EcranFinDuJeu.class \
						SameGame.class

CC = javac

ActionListenerFenetre.class: ActionListenerFenetre.java Fenetre.class
	$(CC) ActionListenerFenetre.java

Blocs.class: Blocs.java
	$(CC) Blocs.java

ChargerPartie.class: ChargerPartie.java
	$(CC) ChargerPartie.java

Ecran.class: Ecran.java
	$(CC) Ecran.java

EcranFinDuJeu.class: EcranFinDuJeu.java Ecran.class
	$(CC) EcranFinDuJeu.java

EcranJeu.class: EcranJeu.java Ecran.class
	$(CC) EcranJeu.java

EcranMenu.class: EcranMenu.java Ecran.class
	$(CC) EcranMenu.java

Fenetre.class: Fenetre.java EcranMenu.class EcranJeu.class EcranFinDuJeu.class Ecran.class
	$(CC) Fenetre.java

GrilleDeJeu.class: GrilleDeJeu.java Blocs.class Fenetre.class
	$(CC) GrilleDeJeu.java

MouseListenerBloc.class: MouseListenerBloc.java GrilleDeJeu.class
	$(CC) MouseListenerBloc.java

SameGame.class: SameGame.java Fenetre.class
	$(CC) SameGame.java

compil: $(JAVAFILES)

test: compil
	java SameGame

clean:
	rm -f $(JAVAFILES)
