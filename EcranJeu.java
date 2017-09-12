import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 * Affiche la grille de jeu avec les blocs
 *
 * @author Ranto RALIJAONA & Jerôme GAUDIN
 */
public class EcranJeu extends Ecran {
	/**
	 * Tableau de String ou chaque couleur de bloc y est enregister, utile pour charger
	 * une partie
	 */
	private String[] partieACharger;

	/**
	 * objet père de la fonction EcranJeu
	 */
	private Fenetre pere;


/**
	 * Construceur qui permet de construire l'objet
	 * @param fenetre JFrame de la fenetre de jeu
	 * @param objetFenetre l'objetFenetre
	 */
	public EcranJeu(JFrame fenetre, Fenetre objetFenetre) {
		super(fenetre);
		this.partieACharger = null;
		this.pere = objetFenetre;
	}


/**
	 * Construceur qui permet de construire l'objet
	 * @param fenetre JFrame de la fenetre de jeu
	 * @param partie : tableau de String qui regroupe les couleurs des blocs a charger
	 * @param objetFenetre l'objetFenetre
	 */

	public EcranJeu(JFrame fenetre,String[] partie, Fenetre objetFenetre) {
		super(fenetre);
		this.partieACharger = partie;
		this.pere = objetFenetre;
	}

/**
	 * redéfinition de la méthode afficher(), affiche l'écran de jeu
	 */
	@Override
	public void afficher() {

		this.setLayout(new GridLayout(10,15));
		if(partieACharger == null) {
			GrilleDeJeu grille = new GrilleDeJeu(this, this.pere);
		} else {
			GrilleDeJeu grille = new GrilleDeJeu(this, partieACharger, this.pere);
		}
	}
}
