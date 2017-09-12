import javax.swing.JFrame;

/**
 * Cette class permet de géré tout les composant graphique déjà crée, elle se lance en
* première pour afficher le menu puis alterne avec les autres ecran
 *
 * @author Ranto RALIJAONA - Jerôme GAUDIN
 */
public class Fenetre {
   /**
   * fenetre Jframe du jeu
   */
  private JFrame fenetre;

   /**
   * Objet ecranMenu
   */
  private EcranMenu ecranMenu;

    /**
   * Objet ecranJeu
   */
  private EcranJeu ecranJeu;

    /**
   * Objet ecranFinDuJeu
   */
  private EcranFinDuJeu ecranFinDuJeu;

  /*certain objet ne arguments ne sont pas initialiset dans les constructeurs car
  lors de la créations de Fenetre on ne connais pas tous les paramètres pour construire
  les arguments*/

  /**
   * Constructeur de la Fenetre
   */
  public Fenetre() {
    this.ecranMenu = new EcranMenu(fenetre, this);
    this.fenetre = new JFrame("SameGame");
  }

/**
 * affiche l'objet EcranMenu
 */
  public void afficher() {
		this.fenetre.setSize(1000,600);
		this.fenetre.setLocation(20,20);
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.fenetre.setVisible(true);

    this.fenetre.setContentPane(this.ecranMenu);
    this.ecranMenu.afficher();
  }

  /**
   * affiche l'objet EcranDeJeu, quand le mode de jeu choisi est de construire une nouvelle
   * grille aléatoirement
   */
  public void afficherJeuRandom() {
    this.ecranJeu = new EcranJeu(this.fenetre, this);
    this.fenetre.setContentPane(this.ecranJeu);
    this.ecranJeu.afficher();
    this.fenetre.validate();
  }

  /**
  * affiche l'objet EcranDeJeu, quand l'utilisateur veut charger une grille
  */
  public void chargerPartie() {
    ChargerPartie ch = new ChargerPartie();
    String[] chargerPartie = ch.fenetreSelectionFichier(this.fenetre);
    if(chargerPartie[0].length() == 15) {
      this.ecranJeu = new EcranJeu(this.fenetre, chargerPartie, this);
      this.fenetre.setContentPane(this.ecranJeu);
      this.ecranJeu.afficher();
      this.fenetre.validate();
    }
  }

  /**
   * affiche l'objet EcranFinDuJeu
   * @param score le score a afficher
   */
  public void FinDuPartie(int score) {
    this.ecranFinDuJeu = new EcranFinDuJeu(fenetre, score);
    this.fenetre.setContentPane(this.ecranFinDuJeu);
    this.ecranFinDuJeu.afficher();
    this.fenetre.validate();
  }
}
