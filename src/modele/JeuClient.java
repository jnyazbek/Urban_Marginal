package modele;

import controleur.Controle;
import outils.connexion.Connection;

/**
 * Gestion du jeu c�t� client
 *
 */
public class JeuClient extends Jeu {
	
	/**
	 * objet de connexion pour communiquer avec le serveur
	 */
	private Connection connection;
	
	/**
	 * Controleur
	 * @param controle instance du contr�leur pour les �changes
	 */
	public JeuClient(Controle controle) {
		super.controle = controle;
	}
	
	@Override
	public void connexion(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void reception(Connection connection, Object info) {
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers le serveur
	 * fais appel une fois � l'envoi dans la classe Jeu
	 * @param info information � envoyer au serveur
	 */
	public void envoi(String info) {
		super.envoi(this.connection, info);
	}

}
