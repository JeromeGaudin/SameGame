import javax.swing.JPanel;

/**
 * Génére une gille de jeu, cette classe possède toutes les méthodes pour géré correctement
 * la grille
 *
 * @author Ranto RALIJAONA - Jerôme GAUDIN
 */
public class GrilleDeJeu {
  /**
   * Tableau de Blocs où tous les blocs sont renger
   */
	private Blocs[][] grille;
    /**
   *version de la grille, icrémente a chaque changement
   */
	private int versionGrille;
    /**
   * Score que augmenta a chaque groupe de blocs cassé
   */
  private int score;
   /**
   * tableaude de chaque MouseListener qui surveillent chaqu'un un bloc
   */ 
  private MouseListenerBloc[][] tabObs;
    /**
   * fenetre, JFame du jeu
   */
	private Fenetre fenetre;

/**
   * Constructeur de la Grille de Jeu, construit chaque blocs
   * @param panneau ou est affiché la grille de jeu
   * @param fenetre Objet Fenetre
   */
	public GrilleDeJeu(JPanel panneau, Fenetre fenetre) {
    this.versionGrille=1;
    this.score = 0;
		this.fenetre = fenetre;
		grille = new Blocs[10][15];
    tabObs = new MouseListenerBloc[10][15];
		for(int i=0; i<grille.length; i++) {
      for(int j=0; j<grille[i].length; j++) {
				grille[i][j] = new Blocs(i,j);
        tabObs[i][j] = new MouseListenerBloc(this);
        grille[i][j].addMouseListener(tabObs[i][j]);
        panneau.add(grille[i][j]);
			}
		}
	}

/**
   * Constructeur de la Grille de Jeu, construit chaque blocs en fonction de du tableau
   * de string fournis
   * @param panneau ou est affiché la grille de jeu
   * @param couleurBloc couleur de chaque bloc, ils sont différenciés par leurs position
   * dans le tableau
   * @param fenetre Objet Fenetre
   */
	public GrilleDeJeu(JPanel panneau, String[] couleurBloc, Fenetre fenetre) {
    this.versionGrille=1;
    this.score = 0;
		this.fenetre = fenetre;
		grille = new Blocs[10][15];
    tabObs = new MouseListenerBloc[10][15];
		for(int i=0; i<grille.length; i++) {
      for(int j=0; j<grille[i].length; j++) {
				grille[i][j] = new Blocs(i,j,couleurBloc[i].charAt(j));
				tabObs[i][j] = new MouseListenerBloc(this);
        grille[i][j].addMouseListener(tabObs[i][j]);
        panneau.add(grille[i][j]);
			}
		}
	}

	/**
   * methode qui initialise les paramètres de la fonction de récursivité qui permet de
   * calculer un groupe de bloc
   *
   * @param tab tableau de boolean, représent un groupe, chaque bloc peut être dans le 
   * gorupe(si = true) ou non. Les blocs sont différencier par leurs coordonnée dans le
   * tableau
   * @param b bloc de ou commence l'algorithme pour déterminer le groupe
   * @return un tableau de boolean si true alors se bloc fait partie du groupe, les
   * blocs sont différencier par leur position dans le tableau
   *
   */
  public int estDansLeGroupe(boolean[][] tab, Blocs b) {
    //tabBoolean a false
    for(int i=0; i<tab.length; i++) {
      for(int j=0; j<tab[i].length; j++) {
        tab[i][j] = false;
      }
    }
    return estDansLeGroupe(b.getPositionY(), b.getPositionX(), tab, b, 0);
  }

	/**
   * méthode récursive qui détermine un groupe de bloc
   *
   * @param y du bloc a déterminer
   * @param x du bloc a déterminer
   * @param tab tableau de boolean qui représente un groupe
   * @param b bloc de ou commence l'algorithme pour déterminer le groupe
   * @param taille du groupe 
   *
   * @return un tableau de boolean si true alors se bloc fait partie du groupe, les
   * blocs sont différencier par leur position dans le tableau
   */
	public int estDansLeGroupe(int y, int x, boolean[][] tab, Blocs b, int taille) {
    if(tab[y][x] == false && this.grille[y][x].equals(b) && this.grille[y][x].getUtilisable()) {
      tab[y][x] = true;
      taille = taille + 1;
      if(y > 0) taille = estDansLeGroupe(y-1, x, tab, b, taille);
      if(y < tab.length-1) taille = estDansLeGroupe(y+1, x, tab, b, taille);
      if(x > 0) taille = estDansLeGroupe(y, x-1, tab, b, taille);
      if(x < tab[y].length-1) taille = estDansLeGroupe(y, x+1, tab, b, taille);
    }
    return taille;
  }
	/**
	 *fait tomber les blocs vers le bas de la grille
	 */
  public void faireTomberGroupe() {
  	boolean blocTomber = true;
  	while(blocTomber) {
  		blocTomber = false;
	  	for(int i=grille.length-1; i>0; i--) {
  			for(int j=0; j<grille[i].length; j++) {
  				if(grille[i][j].getUtilisable() == false && grille[i-1][j].getUtilisable()) {
  					grille[i-1][j].changerPosition(grille[i][j]);
  					blocTomber = true;
  					versionGrille = versionGrille + 1;
  				}
  			}
  		}
  	}
    blocAGauche();
    finDePartie();
  }

	/**
	 * pousse les blocs a gauche si il y a une colone de vide a gauche des blocs
	 */
  public void blocAGauche() {
    boolean blocDeplacer = true;
    while(blocDeplacer) {
      blocDeplacer = false;
      for(int i=0; i<grille[0].length-1; i++) {
        if(grille[grille.length-1][i].getUtilisable() == false) {
          for(int j=0; j<grille.length; j++) {
            if(grille[j][i+1].getUtilisable()) {
              grille[j][i+1].changerPosition(grille[j][i]);
              blocDeplacer = true;
              versionGrille = versionGrille + 1;
            }
          }
        }
      }
    }
  }

	/**
	 * verifie si la partie est fini en calaculant la taille des groupes qu'il reste
	 */
  public void finDePartie() {
    boolean finDejeu = true;

    for(int i=grille.length-1; i>0 && finDejeu; i--) {
      for(int j=0; j<grille[i].length && finDejeu; j++) {
				//calculer le groupe
        if((grille[i][j].getUtilisable()) && (tabObs[i][j].getVersionGrille() != this.versionGrille)) {
          boolean[][] tabGroupe = new boolean[10][15];
          //tabGroupe a false
          for(int k=0; k<tabGroupe.length; k++) {
            for(int l=0; l<tabGroupe[k].length; l++) {
              tabGroupe[i][j] = false;
            }
          }
					tabObs[i][j].setTaille(estDansLeGroupe(tabGroupe, grille[i][j]));
        }
        if(grille[i][j].getUtilisable() && tabObs[i][j].getTaille() > 1) {
          finDejeu = false;
        }
      }
    }
    if(finDejeu) {
			fenetre.FinDuPartie(this.score);
		}
  }

  /**
   * Ajoute le score du groupe de bloc casser au score déjà présent, la formule :
   * (taille -2)^2
   * @param tailleDuGroupe le score se calcule en fonction de la taille du groupe
   */
  public void ajouterScore(int tailleDuGroupe) {
    this.score = this.score + (int) Math.pow((tailleDuGroupe - 2), 2);
  }

	//geter
    /**
   * retourne le tableau de tous les blocs
   * @return le tableau de tous les blocs
   */
	public Blocs[][] getGrille() {
		return this.grille;
	}

  /**
   * retourne la version de la grille
   * @return la version de la grille(0 ou plus que 0)
   */
	public int getVersionGrille() {
		return this.versionGrille;
	}
}
