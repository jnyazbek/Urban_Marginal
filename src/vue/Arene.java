package vue;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controle;
import controleur.Global;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

/**
 * frame de l'aréne du jeu
 * 
 * @author emds
 *
 */
public class Arene extends JFrame implements Global {

	/**
	 * Panel général
	 */
	private JPanel contentPane;
	/**
	 * Panel contenant les murs
	 */
	private JPanel jpnMurs;
	/**
	 * Panel contenant les joueurs et les boules
	 */
	private JPanel jpnJeu;
	/**
	 * Zone de saisie du t'chat
	 */
	private JTextField txtSaisie;
	/**
	 * Zone d'affichage du t'chat
	 */
	private JTextArea txtChat;
	/**
	 * Instance du contréleur pour communiquer avec lui
	 */
	private Controle controle;
	/**
	 * Permet de savoir si c'est une aréne client ou serveur
	 */
	private Boolean client;

	/**
	 * @return the jpnMurs
	 */
	public JPanel getJpnMurs() {
		return jpnMurs;
	}

	/**
	 * @param jpnMurs the jpnMurs to set
	 */
	public void setJpnMurs(JPanel jpnMurs) {
		this.jpnMurs.add(jpnMurs);
		this.jpnMurs.repaint();
	}

	/**
	 * @return the jpnJeu
	 */
	public JPanel getJpnJeu() {
		return jpnJeu;
	}

	/**
	 * @param jpnJeu the jpnJeu to set
	 */
	public void setJpnJeu(JPanel jpnJeu) {
		this.jpnJeu.removeAll();
		this.jpnJeu.add(jpnJeu);
		this.jpnJeu.repaint();
	}

	/**
	 * @return the txtChat
	 */
	public String getTxtChat() {
		return txtChat.getText();
	}

	/**
	 * @param txtChat the txtChat to set
	 */
	public void setTxtChat(String txtChat) {
		this.txtChat.setText(txtChat);
		this.txtChat.setCaretPosition(this.txtChat.getDocument().getLength());
	}

	/**
	 * Ajoute un mur dans le panel des murs
	 * 
	 * @param unMur le mur é ajouter
	 */
	public void ajoutMurs(Object unMur) {
		jpnMurs.add((JLabel) unMur);
		jpnMurs.repaint();
	}

	/**
	 * Ajout d'une phrase é insérer é la fin du tchat
	 * 
	 * @param phrase phase é insérer
	 */
	public void ajoutTchat(String phrase) {
		this.txtChat.setText(this.txtChat.getText() + phrase + "\r\n");
		this.txtChat.setCaretPosition(this.txtChat.getDocument().getLength());
	}

	/**
	 * Ajout d'un joueur, son message ou sa boule, dans le panel de jeu
	 * 
	 * @param unJLabel le label é ajouter
	 */
	public void ajoutJLabelJeu(JLabel unJLabel) {
		this.jpnJeu.add(unJLabel);
		this.jpnJeu.repaint();
	}

	/**
	 * Evénément touche pressée dans la zone de saisie
	 * 
	 * @param e informations sur la touche
	 */
	public void txtSaisie_KeyPressed(KeyEvent e) {
		// si validation
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// si la zone de saisie n'est pas vide
			if (!this.txtSaisie.getText().equals("")) {
				this.controle.evenementArene(this.txtSaisie.getText());
				this.txtSaisie.setText("");
				this.contentPane.requestFocus();		}
		}
	}

	/**
	 * Evenement touche déplacement du personnage
	 *      *37 Left 
	 *		*38 Up 
	 *		*39 Right 
	 *		*40  Down
	 *
	 * @param e
	 */
	public void contentPane_KeyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if( keyCode == LEFT || keyCode == UP || keyCode == RIGHT || keyCode == DOWN) {
			
			controle.evenementArene(keyCode);
		}
		else {
			keyCode = -1;
		}
		
	}

	/**
	 * Create the frame.
	 * 
	 * @param controle instancedu conrtéleur
	 * @param typeJeu  "client" ou "serveur"
	 */
	public Arene(Controle controle, String typeJeu) {
		this.client = typeJeu.equals(CLIENT);
		// Dimension de la frame en fonction de son contenu
		this.getContentPane().setPreferredSize(new Dimension(LARGEURARENE, HAUTEURARENE + 25 + 140));
		this.pack();
		// interdiction de changer la taille
		this.setResizable(false);

		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				contentPane_KeyPressed(e);
			}
		});

		jpnJeu = new JPanel();
		jpnJeu.setBounds(0, 0, LARGEURARENE, HAUTEURARENE);
		jpnJeu.setOpaque(false);
		jpnJeu.setLayout(null);
		contentPane.add(jpnJeu);

		jpnMurs = new JPanel();
		jpnMurs.setBounds(0, 0, LARGEURARENE, HAUTEURARENE);
		jpnMurs.setOpaque(false);
		jpnMurs.setLayout(null);
		contentPane.add(jpnMurs);

		if (this.client) {
			txtSaisie = new JTextField();
			txtSaisie.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					txtSaisie_KeyPressed(e);
				}
			});
			txtSaisie.setBounds(0, 600, 800, 25);
			contentPane.add(txtSaisie);
			txtSaisie.setColumns(10);
		}

		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setBounds(0, 625, 800, 140);
		contentPane.add(jspChat);

		txtChat = new JTextArea();
		txtChat.setEditable(false);
		jspChat.setViewportView(txtChat);

		JLabel lblFond = new JLabel("");
		URL resource = getClass().getClassLoader().getResource(FONDARENE);
		lblFond.setIcon(new ImageIcon(resource));
		lblFond.setBounds(0, 0, 800, 600);
		contentPane.add(lblFond);
		contentPane.requestFocus();
		// récupération de l'instance de Controle
		this.controle = controle;

	}

}
