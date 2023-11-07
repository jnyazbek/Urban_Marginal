package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import controleur.Controle;
/**
 * Frame du choix du joueur
 * @author emds
 *
 */
public class ChoixJoueur extends JFrame {

	/**
	 * Panel g�n�ral
	 */
	private JPanel contentPane;
	/**
	 * Zone de saisie du pseudo
	 */
	private JTextField txtPseudo;
	
	/**
	 * Zone d'affichage du personnage
	 */
	private JLabel lblPersonnage;
	
	/**
	 * Constante nombre de personnage
	 */
	public static final int NBPERSO = 3;
	/**
	 * Numero de personnage selectionne
	 */
	private int selectedPerso;
	/**
	 * Clic sur la fl�che "pr�c�dent" pour afficher le personnage pr�c�dent
	 */
	private void lblPrecedent_clic() {
		System.out.println("Clic sur precedent");
		if(selectedPerso <= 1) {
			selectedPerso = NBPERSO;
		}
		else {
			selectedPerso--;
		}
		lblPersonnage.setIcon(AffichePerso());
	}
	
	/**
	 * Clic sur la fl�che "suivant" pour afficher le personnage suivant
	 */
	private void lblSuivant_clic() {
		System.out.println("Clic sur suivant");
		if(selectedPerso >= NBPERSO) {
			selectedPerso = 1;
		}
		else {
			selectedPerso++;
		}
		lblPersonnage.setIcon(AffichePerso());
	}
	
	/**
	 * Clic sur GO pour envoyer les informations
	 */
	private void lblGo_clic() {
		(new Arene(controle)).setVisible(true);
		this.dispose();
	}

	private Controle controle;
	/**
	 * Create the frame.
	 */
	public ChoixJoueur(Controle controle) {
		// Dimension de la frame en fonction de son contenu
		this.getContentPane().setPreferredSize(new Dimension(400, 275));
	    this.pack();
	    // interdiction de changer la taille
		this.setResizable(false);
		 
		setTitle("Choice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		selectedPerso = 1;
		lblPersonnage = new JLabel("");
		lblPersonnage.setBounds(142, 114, 120, 119);
		lblPersonnage.setHorizontalAlignment(JLabel.CENTER);
		lblPersonnage.setIcon(AffichePerso());
		contentPane.add(lblPersonnage);
		
		JLabel lblPrecedent = new JLabel("");
		lblPrecedent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblPrecedent_clic();
			}
		});
		
		JLabel lblSuivant = new JLabel("");
		lblSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSuivant_clic();
			}
		});
		
		JLabel lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblGo_clic();
			}
		});
		
		txtPseudo = new JTextField();
		txtPseudo.setBounds(142, 245, 120, 20);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		lblGo.setBounds(311, 202, 65, 61);
		contentPane.add(lblGo);
		lblSuivant.setBounds(301, 145, 25, 46);
		contentPane.add(lblSuivant);
		lblPrecedent.setBounds(65, 146, 31, 45);
		contentPane.add(lblPrecedent);
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 400, 275);
		String chemin = "fonds/fondchoix.jpg";
		URL resource = getClass().getClassLoader().getResource(chemin);
		lblFond.setIcon(new ImageIcon(resource));		
		contentPane.add(lblFond);
		
		
		
		// positionnement sur la zone de saisie
		txtPseudo.requestFocus();
		this.controle = controle;
	}
	/**
	 * Le nom du fichier est construit de la
	façon suivante :
	"perso" + num_perso + état_perso + num_étape_état_perso + "d" + direction
	Avec :
	- num_perso : numéro du personnage (ici, 3 personnages numérotés de 1 à 3).
	- état_perso : "marche", "touche" ou "mort"
	 num_étape_état_perso : 4 étapes pour la marche, 2 pour touché et 2 pour mort
	- direction : 0 pour gauche et 1 pour droite
	 * @return
	 */
	public ImageIcon AffichePerso() {
		
		String chemin = "personnages/"+ "perso"+ String.valueOf(selectedPerso)+ "marche1d1.gif";
		URL perso = getClass().getClassLoader().getResource(chemin);
		System.out.println(chemin);
		ImageIcon imgPerso = new ImageIcon(perso);
		return imgPerso;
	}
	
}
