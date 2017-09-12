import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * Permet d'interragir avec les blocs dans la grille de jeu
 *
 * @author Ranto RALIJAONA - Jerôme GAUDIN
 */
public class MouseListenerBloc implements MouseListener {
  /**
   * tableau qui symbolise un groupe de blocs
   */
  private boolean[][] tabBoolean;
   /**
   * référence de la grille de jeu
   */
  private GrilleDeJeu grilleDeJeu;
  /**
   * version de la grille(cette version s'actualise quand on calcule le groupe de bloc)
   */
  private int versionGrille;
  /**
   * taille du groupe de blocs
   */
  private int taille; //taille du groupe

/**
   * constructeur
   * @param grille grilleDeJeu, utile pour utiliser ses methodes
   */
  public MouseListenerBloc(GrilleDeJeu grille) {
    super();
    this.grilleDeJeu = grille;
    this.versionGrille = 0;
    this.taille = 0;

    this.tabBoolean = new boolean[10][15];
    // initialiation de toutes les cases du tableau a false
    for(int i=0; i<tabBoolean.length; i++) {
      for(int j=0; j<tabBoolean[i].length; j++) {
        tabBoolean[i][j] = false;
      }
    }
  }

/**
   * change tabBoolean
   * @param x change la tabBoolean : table qui représent les groupes
   */
  public void setTabBoolean(boolean[][] x) {
    this.tabBoolean = x;
  }

/**
   * change la taille du groupe
   * @param x change la taille du groupe
   */
  public void setTaille(int x) {
    this.taille = x;
  }

/**
   * retourne la taille du groupe
   * @return la taille du groupe
   */
  public int getTaille() {
    return this.taille;
  }

/**
   * change la version de la grille
   * @param x la nouvelle version de la grille
   */
  public void setVersionGrille(int x) {
    this.versionGrille = x;
  }

/**
   * retourne la version de la grille
   * @return la cersion de la grille
   */
  public int getVersionGrille() {
    return this.versionGrille;
  }

 /**
   * Redéfinition de la méthode mouseCliked, si la souris est cliqué, on fait
   * disparaitre le groupe de blocs sur lequel elle se trouve
   * @param e MouseEvent, evenement
   */
  @Override
  public void mouseClicked(MouseEvent e) {    // un bouton cliqué
    if(taille > 1) {
      Blocs[][] grille = grilleDeJeu.getGrille();
      for(int i=0; i<tabBoolean.length; i++) {
        for(int j=0; j<tabBoolean[i].length; j++) {
          if(tabBoolean[i][j]) grille[i][j].setUtilisable(false);
        }
      }
      grilleDeJeu.ajouterScore(taille);
      grilleDeJeu.faireTomberGroupe();
      mouseEntered(e);
    }
  }

  /**
   * Redéfinition de la méthode mouseEvent, si la souris survol un bloc alors,
   * clalcule le groupe de bloc si ce n'est pas déjà fait et affiche le fond de chaque
   * bloc du groupe
   * @param e MouseEvenet, evenement
   */
  @Override
  public void mouseEntered(MouseEvent e) {    // debut du survol

    if(this.versionGrille != grilleDeJeu.getVersionGrille()) {
      this.taille = grilleDeJeu.estDansLeGroupe(tabBoolean, (Blocs) e.getSource());
      this.versionGrille = grilleDeJeu.getVersionGrille();
    }
    if(taille > 1) {
      Blocs[][] grille = grilleDeJeu.getGrille();
      for(int i=0; i<tabBoolean.length; i++) {
        for(int j=0; j<tabBoolean[i].length; j++) {
          if(tabBoolean[i][j]) grille[i][j].setFond(true);
        }
      }
    }
  }

/**
   * Redéfition de mouseExited,  enlève le fond de chaque bloc du groupe
   * @param e mOuseEvenet, evenement
   */
  @Override
  public void mouseExited(MouseEvent e) {   // fin du survol
    Blocs[][] grille = grilleDeJeu.getGrille();
    for(int i=0; i<tabBoolean.length; i++) {
      for(int j=0; j<tabBoolean[i].length; j++) {
        if(tabBoolean[i][j]) grille[i][j].setFond(false);
      }
    }
  }
  @Override
  public void mousePressed(MouseEvent evenement) {}    // un bouton appuyé
  @Override
  public void mouseReleased(MouseEvent evenement){}    // un bouton relâché
}
