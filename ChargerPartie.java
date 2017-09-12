import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * Cette classe permet d'ouvrir un filechooser et de choisir le fichier a charger
 * puis l'ouvre et renvoi un tableau de String
 *
 * @author  Ranto RALIJAONA - Jerôme GAUDIN
 */
public class ChargerPartie {

  /**
  * Ouvre un filechooser et demande a l'utilisateur de choisir un fichier
  * Renvoie un tableau de 10 Strings, chaque string est composé de 15 caractères
  * si il y a une Erreur le tableau ne possède aucune ligne
  *
  * @param fenetre JFrame de la fenetre
  * @return un tableau de 10 Strings, chaque string est composé de 15 caractères
  * si il y a une Erreur le tableau ne possède aucune ligne
  */
  public String[] fenetreSelectionFichier(JFrame fenetre) {
    JFileChooser fenetreFichier = new JFileChooser("./sauvegardes");
    fenetre.add(fenetreFichier);

    // permet de séléctionner que les fichiers
    fenetreFichier.setFileSelectionMode(JFileChooser.FILES_ONLY);
    //filtre pour afficher que les données data (désactivable par l'utilisateur)
    FileNameExtensionFilter filtre = new FileNameExtensionFilter(
        "données (data)", "dat", "data");
    fenetreFichier.setFileFilter(filtre);

    String tabCouleur[] = new String[10];
    int retour = fenetreFichier.showOpenDialog(fenetre);
    if(retour == JFileChooser.APPROVE_OPTION) {
      // un fichier a été choisi (sortie par OK)
      // le fichier choisi
      File fichier = fenetreFichier.getSelectedFile();
      tabCouleur = this.ChargerPartie(fichier);
    }
    return tabCouleur;
  }

 /**
  * Verifie si le fichier selectionner est bien lisible et pas corrompu
  * Renvoie un tableau de 10 Strings, chaque string est composé de 15 caractères
  * si il y a une Erreur le tableau ne possède aucune ligne
  *
  * @param fenetre JFrame de la fenetre
  * @return un tableau de 10 Strings, chaque string est composé de 15 caractères
  * si il y a une Erreur le tableau ne possède aucune ligne
  */  private String[] ChargerPartie(File fichier) {
    String tabCouleur[] = new String[10];
    boolean flagCorompu = false;
    try {
       FileReader f1 = new FileReader(fichier);
       BufferedReader flux = new BufferedReader(f1);
       try {
         for(int i=0; i<tabCouleur.length && !flagCorompu; i++) {
           tabCouleur[i] = flux.readLine();
           if(tabCouleur[i].length() != 15) {
             flagCorompu = true;
           }
           for(int j=0; j<15 && !flagCorompu; j++) {
             char test = tabCouleur[i].charAt(j);
             if( !(test == 'R' || test == 'V' || test == 'B')) {
               flagCorompu = true;
             }
           }
         }
         if(flagCorompu) {
           System.err.println("Erreur le fichier a été corrompu");
           tabCouleur = new String[0];
         }
         try {
           flux.close();
         } catch(IOException e) {
           System.err.println("Erreur de fermeture du fichier");
         }
       } catch(IOException e) {
         System.err.println("Erreur le programme n'a pas pus lire dans le fichier");
         tabCouleur = new String[0];
       }
    } catch(FileNotFoundException e) {
      System.err.println("Erreur le fichier n'a pas pus être ouvert");
      tabCouleur = new String[0];
    }
    return tabCouleur;
  }
}
