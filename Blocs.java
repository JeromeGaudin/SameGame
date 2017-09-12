import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.JComponent;

/**
 * Classe qui permet que l'utilisateur puisse intéragir avec les blocs existant
 *
 * @author Ranto RALIJAONA & Jerôme GAUDIN
 */
public class Blocs extends JComponent{
  /**
   *tableau de toutes les images des différents blocs de couleur
   */
  private static final String tabImage[][] = {{"images/champi_bleu.png", "B"}, {"images/champi_rouge.png", "R"}, {"images/champi_vert.png", "V"}};
  /**
   * Image de fond quand la souris est sur un groupe de bloc
   */
   private Image imageFond;
  /**
   * Boolan pour savoir si l'image de fond est activé( true = activé)
   */
  private boolean fond;
  /**
   * La position X du bloc dans le tableau(x entre 0 et 14 y compris)
   */
  private int positionX;
    /**
   * La position Y du bloc dans le tableau(x entre 0 et 9 y compris)
   */
  private int positionY;
  /**
   * Image du blocs
   */
  private Image image;
  /**
   * Couleur du bloc (R ou V ou B)
   */
  private char couleur;
  /**
   * Boolean pour savoir si le bloc est utiliable(true = utilisable)
   */
  private boolean utilisable;

  /**
   * Constructeur de Blocs
   *
   * @param coordonneeY coordonnée du blocY dans le tableau
   * @param coordonneeX coordonnée du blocX dans le tableau
   */

  public Blocs(int coordonneeY, int coordonneeX) {
    super();
    this.positionX = coordonneeX;
    this.positionY = coordonneeY;
    Random rand = new Random();
    int alea = rand.nextInt(tabImage.length);
    this.image = Toolkit.getDefaultToolkit().getImage(tabImage[alea][0]);
    this.imageFond = Toolkit.getDefaultToolkit().getImage("images/fondBloc.png");
    // convertie le String en char et l'enregistre dans la variable
    this.couleur = tabImage[alea][1].charAt(0);
    this.fond = false;
    this.utilisable = true;
  }

  public Blocs(int coordonneeY, int coordonneeX, char clr) {
    super();
    this.positionX = coordonneeX;
    this.positionY = coordonneeY;
    this.couleur = clr;
    boolean bonneCouleur = false;
    for(int i=0; i<tabImage.length && !bonneCouleur; i++) {
      if(this.couleur == tabImage[i][1].charAt(0)) {
        this.image = Toolkit.getDefaultToolkit().getImage(tabImage[i][0]);
        bonneCouleur = true;
      }
    }
    this.imageFond = Toolkit.getDefaultToolkit().getImage("images/fondBloc.png");
    this.fond = false;
    this.utilisable = true;
  }
  /**
   * Constructeur de Blocs
   *
   * @param coordonneeY coordonnée du blocY dans le tableau
   * @param coordonneeX coordonnée du blocX dans le tableau
   * @param clr couleur du bloc(R ou V ou B)
   */

  @Override
  public void paintComponent(Graphics pinceau) {
    // on crée un deuxième pinceau pour pas changer les paramètres du premier
    Graphics p = pinceau.create();
    if(this.isOpaque()) {
      // On repeint toute la surface avec la couleur de fond
      p.setColor(this.getBackground());
      p.fillRect(0,0,this.getWidth(),this.getHeight());
    }
    if(utilisable) {
      if(fond) {
        p.drawImage(imageFond,0,0,this);
      }
      p.drawImage(image,0,0,this);
    }
  }

  /**
   * change tous les attributs entre deux blocs mais pas les positionX et les positionY
   * @param b Blocs avec qui changer les attributs
   */

  public void changerPosition(Blocs b) {
    b.fond = this.fond;
    b.image = this.image;
    b.couleur = this.couleur;
    this.utilisable = false;
    this.repaint();
    b.utilisable = true;
    b.repaint();
  }

/**
   * Compare deux blocs : si les attributs couleur sont égales
   * 
   * @param b Blocs a comparer
   * @return boolean si il sont égaux ou non
   */

  public boolean equals(Blocs b) {
    if(this.couleur == b.couleur) {
      return true;
    } else {
      return false;
    }
  }

  // geter
  /**
   * retourne la postionX du blocs
   * @return int positionX du blocs
   */
  public int getPositionX() {
    return this.positionX;
  }
  /**
   * retourne la postionY du blocs
   * @return int positionY du blocs
   */

  public int getPositionY() {
    return this.positionY;
  }
  /**
   * retourne la si le blocs est utilisable ou non
   * @return boolean true si le bloc est utilisable
   */

  public boolean getUtilisable() {
    return this.utilisable;
  }

  //seter
  /**
   * modifie l'attribut font
   * @param x boolean si le fond est actif ou non
   */
  public void setFond(boolean x) {
    this.fond = x;
    repaint();
  }
/**
   * modifie l'attribut utilisable
   * @param x boolean si il est utilisable ou non
   */
  public void setUtilisable(boolean x) {
    this.utilisable = x;
    repaint();
  }

}
