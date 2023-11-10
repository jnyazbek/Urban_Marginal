package modele;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controleur.Global;

/**
 * Gestion des murs
 *
 */
public class Mur extends Objet implements Global {

	/**
	 * Constructeur : cr�e un mur (position al�atoire, image)
	 */
	public Mur() {
		// calcul position al�atoire du mur
		posX = (int) Math.round(Math.random() * (LARGEURARENE - LARGEURMUR)) ;
		posY = (int) Math.round(Math.random() * (HAUTEURARENE - HAUTEURMUR)) ;
		// cr�ation du jLabel pour ce mur
		jLabel = new JLabel();
		// caract�ristiques du mur (position, image)
		jLabel.setBounds(posX, posY, LARGEURMUR, HAUTEURMUR);
		URL resource = getClass().getClassLoader().getResource(MUR);
		jLabel.setIcon(new ImageIcon(resource));

	}
	
}
