import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Classe abstraite pour que chaque JPanel du jeu puisse avoir les mêmes methodes
 *
 * @author Ranto RALIJAONA & Jerôme GAUDIN
 */
public abstract class Ecran extends JPanel{
	/**
	 * fenetre JFrame du jeu
	 */
	protected JFrame fenetre;
	
/**
	 * constructeur de la classe Ecran
	 *@param fenetre JFrame du jeu
	 */
	public Ecran(JFrame fenetre) {
		super();
		this.fenetre = fenetre;
	}
/**
	 * fonction abstraite qui soit afficher l'écran
	 */
	abstract void afficher();
}
