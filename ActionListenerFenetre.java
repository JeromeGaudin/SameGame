import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
/**
 * La class ActionListenerFenetre est utiliser dans la classe EcranMenu, cette classe
 * permet de faire des action suivant les différents boutons appuyer
 *
 * @author Ranto RALIJAONA - Jerôme GAUDIN
 */
public class ActionListenerFenetre implements ActionListener{
    /**
   *objet de la classe fenetre
   */
  private JFrame fenetre;
  /**
   * objet Fenetre utile pour pouvoir utiliser la methode chargerPartie qui est
   * dans la classe Fenetre
   */
  private Fenetre pere;
  /**
   * constructeur
   * @param fenetre la fenetre (objet JFrame)
   * @param objetFenetre l'objet de la classe Fenetre
   */

  public ActionListenerFenetre(JFrame fenetre, Fenetre objetFenetre){
    this.fenetre = fenetre;
    this.pere = objetFenetre;
  }
  /**
   * Redéfinition de la méthode actionPerformed
   */
  @Override
  public void actionPerformed(ActionEvent x){
    String composant = x.getActionCommand();
  	if(composant.equals("Exit")) {
  	  System.exit(0);
    }else if(composant.equals("Random Choice")) {
      this.pere.afficherJeuRandom();
    } else if(composant.equals("Own Configuration")) {
      JFrame fenetreChoix = new JFrame();
      this.pere.chargerPartie();
    }
  }
}
