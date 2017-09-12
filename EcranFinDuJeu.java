import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

/**
 * Affiche l'écran de fin de jeu avec le score que l'utilisateur a produit
 *
 * @author Ranto RALIJAONA & Jerôme GAUDIN
 */
public class EcranFinDuJeu extends Ecran {
    /**
   * correspond au score accumuler au cours de la partie
   */
  private int score;
/**
   * constructeur de l'écrant de fin de jeu
   * @param fenetre JFrame du jeu
   * @param score a ajouter
   */
	public EcranFinDuJeu(JFrame fenetre,int  score) {
		super(fenetre);
    this.score = score;
	}
/**
   * redéfinition de la fonction afficher
   */
	@Override
	public void afficher() {
    JLabel jLabelScore = new JLabel("Votre score est de " + this.score + " points.");
    Font font = new Font("Arial",Font.BOLD,12);

    jLabelScore.setFont(font);
    jLabelScore.setForeground(Color.BLACK);
    this.add(jLabelScore, BorderLayout.CENTER);

	}
}
