package modele;

import javax.swing.JLabel;

import controleur.Global;

/**
 * Informations communes é tous les objets (joueurs, murs, boules)
 * permet de mémoriser la position de l'objet et de gérer les  collisions
 *
 */
public abstract class Objet implements Global {

	/**
	 * position X de l'objet
	 */
	protected Integer posX ;
	/**
	 * position Y de l'objet
	 */
	protected Integer posY ;
	/**
	 * label contenant l'objet graphique (personnage, mur, boule)
	 */
	protected JLabel jLabel;
	
	/**
	 * @return the jLabel
	 */
	public JLabel getjLabel() {
		return jLabel;
	}

	/**
	 * contréle si l'objet actuel touche l'objet passé en paramétre
	 * @param objet contient l'objet é contréler
	 * @return true si les 2 objets se touchent
	 */
	public int toucheObjet(Objet objet) {
	    // Vérifie si l'objet ou son JLabel est null
	    if (objet.jLabel == null || this.jLabel == null) {
	        return 0; // Si l'un des objets ou les deux JLabel sont null, retourne 0 (aucun contact)
	    } else {
	        // Vérifie si les rectangles englobants des deux objets se chevauchent
	        if (this.posX + this.jLabel.getWidth() > objet.posX &&
	            this.posX < objet.posX + objet.jLabel.getWidth() &&
	            this.posY + this.jLabel.getHeight() > objet.posY &&
	            this.posY < objet.posY + objet.jLabel.getHeight()) {
	            // Détermine quel bord de l'objet actuel touche l'objet passé en paramètre
	            int overlapX = Math.min(this.posX + this.jLabel.getWidth(), objet.posX + objet.jLabel.getWidth()) - Math.max(this.posX, objet.posX);
	            int overlapY = Math.min(this.posY + this.jLabel.getHeight(), objet.posY + objet.jLabel.getHeight()) - Math.max(this.posY, objet.posY);

	            // Compare les valeurs de recouvrement pour déterminer quel bord touche
	            if (overlapX > overlapY) {
	                // La collision est principalement horizontale
	                if (this.posY < objet.posY) {
	                    return UP; // Bord supérieur de l'objet actuel touche
	                } else {
	                    return DOWN; // Bord inférieur de l'objet actuel touche
	                }
	            } else {
	                // La collision est principalement verticale
	                if (this.posX < objet.posX) {
	                    return RIGHT; // Bord gauche de l'objet actuel touche
	                } else {
	                    return LEFT; // Bord droit de l'objet actuel touche
	                }
	            }
	        } else {
	            return 0 ; // Aucun contact
	        }
	    }
	}

	
}
