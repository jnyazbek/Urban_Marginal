package controleur;

import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;
import outils.connexion.*;

/*
 * Contr�leur et point d'entr�e de l'applicaton
 * 
 * @author emds
 *
 */
public class Controle implements AsyncResponse {

	private EntreeJeu frmEntreeJeu;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	private String typeJeu;
	public static final int Port = 6666;

	/**
	 * M�thode de d�marrage
	 * 
	 * @param args non utilis�
	 */
	public static void main(String[] args) {
		new Controle();
	}

	/**
	 * Constructeur
	 */
	private Controle() {
		this.frmEntreeJeu = new EntreeJeu(this);
		this.frmEntreeJeu.setVisible(true);

		
	}

	public void evenementEntreeJeu(String message) {

		if (message == "serveur") {
			typeJeu = "serveur";
			frmArene = new Arene(this);
			frmArene.setVisible(true);
			ServeurSocket serveurSocket = new ServeurSocket(this, Port);
			frmEntreeJeu.dispose();
		} else {
			typeJeu = "client";
			// frmChoixJoueur.setVisible(true);
			ClientSocket clientSocket = new ClientSocket(this, message, Port);
			// frmEntreeJeu.dispose();
		}
		System.out.println("evenementEntreeJeu"+typeJeu);
	}

	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch(ordre) {
			case "connexion":
				
				if(typeJeu == "serveur") {
					
				}
				else {
					this.frmArene = new Arene(this);
					this.frmChoixJoueur = new ChoixJoueur(this);
					this.frmChoixJoueur.setVisible(true);
					frmEntreeJeu.dispose();
				}
				break;
			
			case "reception":
				break;
		
			case "deconnexion":
				break;
		}
		
	}
}
