package outils.son;

import java.io.File;
import outils.son.exceptions.*;
import java.io.Serializable;
import java.net.URL;

/**
 * Gestion des sons
 *
 * @author non attribuable
 * @version 1.0
 */

public class Son implements Serializable {

	/**
	 * son qui peut étre joué
	 */
    private Sound sound ;

    /**
     * Création d'un objet de type Sound, é partir d'un fichier de son
     * @param nomfic url du fichier
     */
    public Son (URL nomfic) {
        try {
//            this.sound = new Sound(new File(nomfic));
            this.sound = new Sound(nomfic);
        } catch (SonException ex) {
        }
    }

    /**
     * Joue le son une fois
     */
    public void play() {
        this.sound.boucle(1) ;
    }

    /**
     * ferme le son (libére l'objet de la mémoire)
     */
    public void close() {
        this.sound.fermer();
    }

    /**
     * Joue le son en boucle (musique de fond)
     */
    public void playContinue () {
        sound.boucle() ;
    }

}
