import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;




/**
 * Classe abstraite pour que chaque JPanel du jeu puisse avoir les mêmes methodes
 *
 * @author Ranto RALIJAONA & Jerôme GAUDIN
 */
public class EcranMenu extends Ecran {
	/**
	 * classe de la l'objet père
	 */
	private Image imageFond;
	private Fenetre pere;


/**
	 * constructeur de la classe
	 * @param fenetre JFrame de la fenetre du jeu
	 * @param objetFenetre objet de la classe Fenetre
	 */
	
	public EcranMenu(JFrame fenetre, Fenetre objetFenetre) {
		super(fenetre);
		this.pere = objetFenetre;
	}


/**
	 * redéfinition de la fonction afficher, permet d'afficher le menu
	 */
	@Override
	public void afficher() {
		this.setLayout(null);

		//création des JLabel
		JLabel game_title = new JLabel("Bienvenue dans le jeu SameGame!");
	 	Font font = new Font("Arial",Font.BOLD,40);

	 	JLabel creator_name = new JLabel("By Jerôme GAUDIN & Ranto RALIJAONA");
	 	Font font_creator = new Font("Arial",Font.BOLD,12);

		game_title.setFont(font);
		game_title.setForeground(Color.BLACK);
		game_title.setBounds(100,0,800,100);

		creator_name.setFont(font_creator);
		creator_name.setForeground(Color.BLACK);
		creator_name.setBounds(720,500,300,100);

		//création des boutons
		JButton bouton_random = new JButton("Random Choice");
		JButton bouton_config = new JButton("Own Configuration");
		JButton bouton_exit = new JButton("Exit");

		//Utilisation de l'ActionListener pour que les boutons soient cliquables
		ActionListenerFenetre obsFenetre = new ActionListenerFenetre(this.fenetre, this.pere);
		bouton_random.addActionListener(obsFenetre);
		bouton_config.addActionListener(obsFenetre);
		bouton_exit.addActionListener(obsFenetre);

		/*Couleur du texte dans les boutons*/
		bouton_random.setForeground(Color.BLUE);
		bouton_config.setForeground(Color.BLUE);
		bouton_exit.setForeground(Color.BLUE);

		/*Ajout de couleur pour les boutons*/
		bouton_random.setBackground(new Color(142, 162, 198));
		bouton_config.setBackground(new Color(142, 162, 198));
		bouton_exit.setBackground(new Color(142, 162, 198));

		// position des boutons
		bouton_random.setBounds(0,400,200,100);
		bouton_config.setBounds(800,400,200,100);
		bouton_exit.setBounds(400,400,200,100);

		this.add(bouton_random);
		this.add(bouton_config);
		this.add(bouton_exit);
		this.add(game_title);
		this.add(creator_name);
	}
}
