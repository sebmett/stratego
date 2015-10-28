package stratego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EcranJeu extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;

	final int NBOUTONS = 10;

	private JButton boutons[][];	// vont representer le plateau (herbe et  eau)
	private JButton boutonsP[][];	// seront les pions a placer sur le plateau
	private JButton boutonsInfo[];	// boutons qui servent d'information a cote du plateau
	
	private JButton commencer;
	private JButton suivant;
	private JButton finDeTour;
	private JButton cacherPions;
	private JButton quit;
	private JButton regles;
	private JButton boutonUniteGagne;
	private JButton boutonUnitePerd;
	
	private JLabel gagne;
	private JLabel perd;
	private JLabel info1;
	private JLabel info2;
	private JLabel info3;
	private JLabel info4;
	private JLabel info5;
	private JLabel info6;
	private JLabel info7;
	private JLabel info8;
	private JLabel info9;
	private JLabel info10;
	private JLabel info11;
	
	private int i;		// i et j servent pour faire les boucles
	private int j;
	private int totalR;		// servent a verifier que tous les pions sont places
	private int totalB;
	private int sourceI = 0;	// serviront a effectuer les deplacements
	private int sourceJ = 0;
	private int etat = 1; 	// 1 = par defaut tour du joueur rouge   //  2 = tour du joueur bleu
	
	private boolean toutPlacer;		// true quand R et B ont places tous les pions
	private boolean toutPlacerR;	// true quand R a place tous ses pions

	Unite[][] plateauP = new Unite[3][4]; // pour replacer les pions dans stock
	Unite[][] plateau = new Unite[10][10]; // gestion des unites sur le plateau
    Unite[][] plateauTemp = new Unite [10][10];	// plateau temporaire pour permuter

	// unites rouges
	Unite espionR = new Unite(1, "ESPION", "espionR.gif");
	Unite eclaireurR = new Unite(2, "ECLAIREUR", "eclaireurR.gif");
	Unite demineurR = new Unite(3, "DEMINEUR", "demineurR.gif");
	Unite sergentR = new Unite(4, "SERGENT", "sergentR.gif");
	Unite lieutenantR = new Unite(5, "LIEUTENANT", "lieutenantR.gif");
	Unite capitaineR = new Unite(6, "CAPITAINE", "capitaineR.gif");
	Unite commandantR = new Unite(7, "COMMANDANT", "commandantR.gif");
	Unite colonelR = new Unite(8, "COLONEL", "colonelR.gif");
	Unite generalR = new Unite(9, "GENERAL", "generalR.gif");
	Unite marechalR = new Unite(10, "MARECHAL", "marechalR.gif");
	Unite drapeauR = new Unite(11, "DRAPEAU", "drapeauR.gif");
	Unite bombeR = new Unite(12, "BOMBE", "bombeR.gif");

	// unites bleues
	Unite espionB = new Unite(1, "ESPION", "espionB.gif");
	Unite eclaireurB = new Unite(2, "ECLAIREUR", "eclaireurB.gif");
	Unite demineurB = new Unite(3, "DEMINEUR", "demineurB.gif");
	Unite sergentB = new Unite(4, "SERGENT", "sergentB.gif");
	Unite lieutenantB = new Unite(5, "LIEUTENANT", "lieutenantB.gif");
	Unite capitaineB = new Unite(6, "CAPITAINE", "capitaineB.gif");
	Unite commandantB = new Unite(7, "COMMANDANT", "commandantB.gif");
	Unite colonelB = new Unite(8, "COLONEL", "colonelB.gif");
	Unite generalB = new Unite(9, "GENERAL", "generalB.gif");
	Unite marechalB = new Unite(10, "MARECHAL", "marechalB.gif");
	Unite drapeauB = new Unite(11, "DRAPEAU", "drapeauB.gif");
	Unite bombeB = new Unite(12, "BOMBE", "bombeB.gif");

	
	// unites neutres
	Unite deplaceR = new Unite(0, "DEPLACE", "deplaceR.gif"); // un drapeau avec personnage si mouvement
	Unite deplaceB =  new Unite(0, "DEPLACE", "deplaceB.gif");
	Unite defendR = new Unite(0, "DEFEND", "defendR.gif");	// un drapeau avec bouclier si attaquant + faible
	Unite defendB = new Unite(0, "DEFEND", "defendB.gif");
	Unite gagneR = new Unite(0, "GAGNE", "gagneR.gif");	// une epee si attaquant plus fort
	Unite gagneB = new Unite(0, "GAGNE", "gagneB.gif");
	
	Unite herbeSuicide = new Unite(0, "HERBESUICIDE", "herbeSuicide.gif");
	Unite herbeSource = new Unite(0, "HERBESOURCE", "herbeSource.gif"); //herbe avec cible, source du d�placement
	Unite herbe = new Unite(0, "HERBE", "herbe.jpg");
	Unite eau1 = new Unite(0, "EAU", "eaupartghaut.jpg");
	Unite eau2 = new Unite(0, "EAU", "eaupartgbas.jpg");
	Unite eau3 = new Unite(0, "EAU", "eaupartdhaut.jpg");
	Unite eau4 = new Unite(0, "EAU", "eaupartdbas.jpg");
	Unite espionN = new Unite(0, "", "espionN.gif");
	Unite eclaireurN = new Unite(0, "", "eclaireurN.gif");
	Unite demineurN = new Unite(0, "", "demineurN.gif");
	Unite sergentN = new Unite(0, "", "sergentN.gif");
	Unite lieutenantN = new Unite(0, "", "lieutenantN.gif");
	Unite capitaineN = new Unite(0, "", "capitaineN.gif");
	Unite commandantN = new Unite(0, "", "commandantN.gif");
	Unite colonelN = new Unite(0, "", "colonelN.gif");
	Unite generalN = new Unite(0, "", "generalN.gif");
	Unite marechalN = new Unite(0, "", "marechalN.gif");
	Unite bombeN = new Unite(0, "", "bombeN.gif");
	Unite fondR = new Unite(-1, "UNITE ROUGE", "rouge.gif");
	Unite fondB = new Unite(-2, "UNITE BLEUE", "bleu.gif");


	int nbDrapeauR = 1;		// nombre de pions par camp
	int nbBombeR = 6;
	int nbEspionR = 1;
	int nbMarechalR = 1;
	int nbColonelR = 2;
	int nbGeneralR = 1;
	int nbCommandantR = 3;
	int nbCapitaineR = 4;
	int nbLieutenantR = 4;
	int nbSergentR = 4;
	int nbDemineurR = 5;
	int nbEclaireurR = 8;

	int nbDrapeauB = 1;
	int nbBombeB = 6;
	int nbEspionB = 1;
	int nbMarechalB = 1;
	int nbColonelB = 2;
	int nbGeneralB = 1;
	int nbCommandantB = 3;
	int nbCapitaineB = 4;
	int nbLieutenantB = 4;
	int nbSergentB = 4;
	int nbDemineurB = 5;
	int nbEclaireurB = 8;
	
	Sons take = new Sons("file:combat.wav");		// quand combat
	Sons bombe = new Sons("file:bombe.wav");		// quand bombe explose
	Sons error = new Sons("file:error.wav");		// coup non autorise
	Sons victoire = new Sons("file:victoire.wav");

	public void ecranJeuInit(){
		JPanel panelJeu;

		this.setTitle("Stratego v2.0");
		this.setSize(new Dimension(1050, 605));
		this.setResizable(false);
		panelJeu = (JPanel) getContentPane();
		boutons = new JButton[NBOUTONS][NBOUTONS];	//tableau de boutons de 10 par 10
		boutonsP = new JButton[3][4];				// tableau pour les boutons a placer
		boutonsInfo = new JButton[11];				// tableau pour les boutons info
		
		commencer = new JButton();
		commencer.setBounds(new Rectangle(730, 250, 150, 30));
		commencer.setFocusPainted(false);
		commencer.addMouseListener(this);
		commencer.setText("Commencer");

		finDeTour = new JButton();
		finDeTour.setBounds(new Rectangle(730, 250, 150, 30));
		finDeTour.setFocusPainted(false);
		finDeTour.addMouseListener(this);
		finDeTour.setText("Tour rouge"); // changera en fonction du tour
		finDeTour.setVisible(false);

		suivant = new JButton();
		suivant.setBounds(new Rectangle(730, 220, 150, 30));
		suivant.setFocusPainted(false);
		suivant.addMouseListener(this);
		suivant.setText("Suivant");
		suivant.setForeground(Color.blue);

		cacherPions = new JButton();
		cacherPions.setBounds(new Rectangle(730, 220, 150, 30));
		cacherPions.setFocusPainted(false);
		cacherPions.addMouseListener(this);
		cacherPions.setText("Cacher pions");
		cacherPions.setVisible(false);

		quit = new JButton();
		quit.setBounds(new Rectangle(980, 0, 60, 60));
		quit.setFocusPainted(false);
		quit.addMouseListener(this);
		quit.setIcon(new ImageIcon("quitIm.jpg"));
		
		regles = new JButton();
		regles.setBounds(new Rectangle(980, 70, 60, 60));
		regles.setFocusPainted(false);
		regles.addMouseListener(this);
		regles.setIcon(new ImageIcon("reglesS.jpg"));
		
		panelJeu.add(commencer);
		panelJeu.add(suivant);
		panelJeu.add(finDeTour);
		panelJeu.add(cacherPions);
		panelJeu.add(quit);
		panelJeu.add(regles);
		
		JLabel jLabel1 = new JLabel();

		ImageIcon gameBackground = new ImageIcon("stratego1.jpg");
		jLabel1.setIcon(gameBackground);
		jLabel1.setBounds(new Rectangle(0, 0, 1050, 605));

		while ((i < NBOUTONS) && (j < NBOUTONS)) {	// boucle qui cr�e le plateau de jeu (herbe + eau)
			for (int l = 10; l < 510; l += 50) {
				for (int k = 10; k < 510; k += 50) {

					boutons[i][j] = new JButton();
					boutons[i][j].setBounds(k, l, 50, 50);
					boutons[i][j].setBorderPainted(false);
					boutons[i][j].setDoubleBuffered(true);
					boutons[i][j].setToolTipText(herbe.getDescription());	//herbe partout
					boutons[i][j].setFocusPainted(true);
					boutons[i][j].setIcon(herbe.getImage());
					panelJeu.add(boutons[i][j]);
					boutons[i][j].addMouseListener(this);
					plateau[i][j] = herbe;
					plateauTemp[i][j] = herbe;

					if (i == 2 | i == 6) // serie de cases contenant de l'eau
						if (j == 4) {
							boutons[i][j].setToolTipText(eau1.getDescription());
							boutons[i][j].setIcon(eau1.getImage());
							boutons[i][j].setPressedIcon(eau1.getImage());
							boutons[i][j].removeMouseListener(this); // pas d'action possible avec ces boutons
							plateau[i][j] = eau1;
						}
					if (i == 2 | i == 6)
						if (j == 5) {
							boutons[i][j].setToolTipText(eau2.getDescription());
							boutons[i][j].setIcon(eau2.getImage());
							boutons[i][j].setPressedIcon(eau2.getImage());
							boutons[i][j].removeMouseListener(this);
							plateau[i][j] = eau2;
						}
					if (i == 3 | i == 7)
						if (j == 4) {
							boutons[i][j].setToolTipText(eau3.getDescription());
							boutons[i][j].setIcon(eau3.getImage());
							boutons[i][j].setPressedIcon(eau3.getImage());
							boutons[i][j].removeMouseListener(this);
							plateau[i][j] = eau3;
						}
					if (i == 3 | i == 7)
						if (j == 5) {
							boutons[i][j].setToolTipText(eau4.getDescription());
							boutons[i][j].setIcon(eau4.getImage());
							boutons[i][j].setPressedIcon(eau4.getImage());
							boutons[i][j].removeMouseListener(this);
							plateau[i][j] = eau4;
						}
					i++;
					if (i == 10) { // condition permettant d'obtenir un tableau de 10 par 10
						i = 0;
					}
				}
				j++;
			}
		}

		i = 0;
		j = 0;
		while (i < 11) { // boucle affichant la colone d'unit�s descriptives (noir / blanc)
			boutonsInfo[i] = new JButton();
			boutonsInfo[i].setBounds(530, j, 50, 50);
			boutonsInfo[i].setBorderPainted(false);
			boutonsInfo[i].setDoubleBuffered(true);

			if (i == 0) {
				boutonsInfo[i].setIcon(marechalN.getImage());
				boutonsInfo[i].setPressedIcon(marechalN.getImage());
			}
			if (i == 1) {
				boutonsInfo[i].setIcon(generalN.getImage());
				boutonsInfo[i].setPressedIcon(generalN.getImage());
			}
			if (i == 2) {
				boutonsInfo[i].setIcon(colonelN.getImage());
				boutonsInfo[i].setPressedIcon(colonelN.getImage());
			}
			if (i == 3) {
				boutonsInfo[i].setIcon(commandantN.getImage());
				boutonsInfo[i].setPressedIcon(commandantN.getImage());
			}
			if (i == 4) {
				boutonsInfo[i].setIcon(capitaineN.getImage());
				boutonsInfo[i].setPressedIcon(capitaineN.getImage());
			}
			if (i == 5) {
				boutonsInfo[i].setIcon(lieutenantN.getImage());
				boutonsInfo[i].setPressedIcon(lieutenantN.getImage());
			}
			if (i == 6) {
				boutonsInfo[i].setIcon(sergentN.getImage());
				boutonsInfo[i].setPressedIcon(sergentN.getImage());
			}
			if (i == 7) {
				boutonsInfo[i].setIcon(demineurN.getImage());
				boutonsInfo[i].setPressedIcon(demineurN.getImage());
			}
			if (i == 8) {
				boutonsInfo[i].setIcon(eclaireurN.getImage());
				boutonsInfo[i].setPressedIcon(eclaireurN.getImage());
			}
			if (i == 9) {
				boutonsInfo[i].setIcon(espionN.getImage());
				boutonsInfo[i].setPressedIcon(espionN.getImage());
			}
			if (i == 10) {
				boutonsInfo[i].setIcon(bombeN.getImage());
				boutonsInfo[i].setPressedIcon(bombeN.getImage());
			}
			panelJeu.add(boutonsInfo[i]);
			i++;
			j += 50;
		}

		i = 0;
		j = 0;
		while ((i < 2) && (j < 3)) {	// boucle qui affiche les boutons contenant les unites a placer
			for (int l = 10; l <= 160; l += 50) {
				for (int k = 730; k <= 830; k += 50) {
					boutonsP[i][j] = new JButton();
					boutonsP[i][j].setBounds(k, l, 50, 50);
					boutonsP[i][j].setDoubleBuffered(true);
					boutonsP[i][j].addMouseListener(this);

					if (i == 0 && j == 0) {
						boutonsP[i][j].setToolTipText(drapeauR.getDescription()); // initialisation avec les rouges
						boutonsP[i][j].setIcon(drapeauR.getImage());
						plateauP[i][j] = drapeauR;
					}
					if (i == 1 && j == 0) {
						boutonsP[i][j].setToolTipText(marechalR.getDescription());
						boutonsP[i][j].setIcon(marechalR.getImage());
						plateauP[i][j] = marechalR;
					}
					if (i == 2 && j == 0) {
						boutonsP[i][j].setToolTipText(generalR.getDescription());
						boutonsP[i][j].setIcon(generalR.getImage());
						plateauP[i][j] = generalR;
					}
					if (i == 0 && j == 1) {
						boutonsP[i][j].setToolTipText(colonelR.getDescription());
						boutonsP[i][j].setIcon(colonelR.getImage());
						plateauP[i][j] = colonelR;
					}
					if (i == 1 && j == 1) {
						boutonsP[i][j].setToolTipText(commandantR.getDescription());
						boutonsP[i][j].setIcon(commandantR.getImage());
						plateauP[i][j] = commandantR;
					}
					if (i == 2 && j == 1) {
						boutonsP[i][j].setToolTipText(capitaineR.getDescription());
						boutonsP[i][j].setIcon(capitaineR.getImage());
						plateauP[i][j] = capitaineR;
					}
					if (i == 0 && j == 2) {
						boutonsP[i][j].setToolTipText(lieutenantR.getDescription());
						boutonsP[i][j].setIcon(lieutenantR.getImage());
						plateauP[i][j] = lieutenantR;
					}
					if (i == 1 && j == 2) {
						boutonsP[i][j].setToolTipText(sergentR.getDescription());
						boutonsP[i][j].setIcon(sergentR.getImage());
						plateauP[i][j] = sergentR;
					}
					if (i == 2 && j == 2) {
						boutonsP[i][j].setToolTipText(demineurR.getDescription());
						boutonsP[i][j].setIcon(demineurR.getImage());
						plateauP[i][j] = demineurR;
					}
					if (i == 0 && j == 3) {
						boutonsP[i][j].setToolTipText(eclaireurR.getDescription());
						boutonsP[i][j].setIcon(eclaireurR.getImage());
						plateauP[i][j] = eclaireurR;
					}
					if (i == 1 && j == 3) {
						boutonsP[i][j].setToolTipText(espionR.getDescription());
						boutonsP[i][j].setIcon(espionR.getImage());
						plateauP[i][j] = espionR;
					}
					if (i == 2 && j == 3) {
						boutonsP[i][j].setToolTipText(bombeR.getDescription());
						boutonsP[i][j].setIcon(bombeR.getImage());
						plateauP[i][j] = bombeR;
					}
					panelJeu.add(boutonsP[i][j], null);
					i++;
					if (i == 3) {
						i = 0;
					}
				}
				j++;
			}
		}

		info1 = new JLabel("1 Marechal"); // affiche la description des unit�s et leur nombre
		info1.setBounds(580, 15, 80, 20);
		info1.setForeground(Color.white);
		
		info2 = new JLabel("1 General");
		info2.setBounds(580, 65, 70, 20);
		info2.setForeground(Color.white);
		
		info3 = new JLabel("2 Colonel");
		info3.setBounds(580, 115, 70, 20);
		info3.setForeground(Color.white);
		
		info4 = new JLabel("3 Commandant");
		info4.setBounds(580, 165, 100, 20);
		info4.setForeground(Color.white);
		
		info5 = new JLabel("4 Capitaine");
		info5.setBounds(580, 215, 80, 20);
		info5.setForeground(Color.white);
		
		info6 = new JLabel("4 Lieutenant");
		info6.setBounds(580, 265, 90, 20);
		info6.setForeground(Color.white);
		
		info7 = new JLabel("4 Sergent");
		info7.setBounds(580, 315, 80, 20);
		info7.setForeground(Color.white);
		
		info8 = new JLabel("5 Demineur");
		info8.setBounds(580, 365, 90, 20);
		info8.setForeground(Color.white);
		
		info9 = new JLabel("8 Eclaireur");
		info9.setBounds(580, 415, 90, 20);
		info9.setForeground(Color.white);
		
		info10 = new JLabel("1 Espion");
		info10.setBounds(580, 465, 70, 20);
		info10.setForeground(Color.white);
		
		info11 = new JLabel("6 Bombe");
		info11.setBounds(580, 515, 60, 20);
		info11.setForeground(Color.white);
		
		gagne = new JLabel("Winner");
		gagne.setBounds(755, 280, 50, 20);
		gagne.setForeground(Color.white);
	
		perd = new JLabel("Looser");
		perd.setBounds(815, 280, 50, 20);
		perd.setForeground(Color.white);

		boutonUniteGagne = new JButton();		//affiche l'unite ayant gagne un affrontement
		boutonUniteGagne.setBounds(750, 300, 50, 50);
		boutonUniteGagne.setDoubleBuffered(true);
		boutonUniteGagne.setIcon(null);

		boutonUnitePerd = new JButton();		//affiche l'unite ayant perdu un affrontement
		boutonUnitePerd.setBounds(810, 300, 50, 50);
		boutonUnitePerd.setDoubleBuffered(true);
		boutonUnitePerd.setIcon(null);
		
		panelJeu.add(info1, null);
		panelJeu.add(info2, null);
		panelJeu.add(info3, null);
		panelJeu.add(info4, null);
		panelJeu.add(info5, null);
		panelJeu.add(info6, null);
		panelJeu.add(info7, null);
		panelJeu.add(info8, null);
		panelJeu.add(info9, null);
		panelJeu.add(info10, null);
		panelJeu.add(info11, null);
		panelJeu.add(gagne, null);
		panelJeu.add(perd, null);
		panelJeu.add(boutonUniteGagne, null);
		panelJeu.add(boutonUnitePerd, null);
		panelJeu.add(jLabel1, null);

	
		
	}
	
	public EcranJeu() {
		ecranJeuInit();
	}
	
	public void mousePressed(MouseEvent e) {
		if (toutPlacer == false) {				// si les deux joueurs n'ont pas plac�s tous les pions
			if (toutPlacerR == false) { 		// si tous les pions rouges n'ont pas ete places			
				placerPionsR(e); 				// on ne peut poser que sur les cases pour joueur rouge (4 lignes du bas)
			} else if (toutPlacerR == true) {	// si rouge a tout pose, alors les 4 premieres lignes pour bleu
				placerPionsB(e);
			}
		} else if (toutPlacer == true) { 		// si tous les pions se trouvent sur le plateau				
			jouer(e);							
		}
		suivant(e);
		commencer(e);
		cacherPions(e);
		finDeTour(e);
		regles(e);
		quitter(e);
	}

	public void placerPionsR(MouseEvent e) { // permet de placer les pions rouges sur le plateau
		Object source = e.getSource();
		
		for (i = 0; i < 10; i++) { 			// on ne peut cliquer que sur les 6 dernieres lignes
			for (j = 6; j < 10; j++) {
				if (source == boutons[i][j]) { // bouton sur lequel l'utilisateur a cliqu�
					if (GestionClic.getUnit() == null) { // si souris est vide
						if (source == boutons[i][j] && boutons[i][j].getToolTipText() != "HERBE") { //si autre chose que herbe
							GestionClic.setMouseIcon(this, plateau[i][j]); //la souris prend l'image de l'unite
							GestionClic.getUnit(); // la souris prend les information de l'unite
							boutons[i][j].setIcon(herbe.getImage()); // le bouton du plateau prend l'image et description d'herbe
							boutons[i][j].setToolTipText(herbe.getDescription());
							plateau[i][j] = herbe; // le plateau de type herbe de nouveau
						}
					}

					else if (GestionClic.getUnit() != null) { // si souris contient une unite
						if (source == boutons[i][j] && boutons[i][j].getToolTipText() == "HERBE") { // et que l'on clique sur une case herbe
							boutons[i][j].setIcon(GestionClic.getUnit().getImage()); // le bouton prend l'image de la souris
							boutons[i][j].setToolTipText(GestionClic.getUnit().getDescription()); // sa description
							plateau[i][j] = GestionClic.getUnit(); // le plateau l'unite
							GestionClic.setMouseIcon(this); // la souris redevient une fleche
						}

						else if (source == boutons[i][j] && boutons[i][j].getToolTipText() != "HERBE"
								&& boutons[i][j].getToolTipText() != "") { // clic sur case contenant une unite avec souris non vide
							plateauTemp[i][j] = plateau[i][j]; //utilisation d'un autre plateau pour stocker infos
							boutons[i][j].setIcon(GestionClic.getUnit().getImage()); // le bouton prend l'image de la souris
							boutons[i][j].setToolTipText(GestionClic.getUnit().getDescription()); // sa description
							plateau[i][j] = GestionClic.getUnit(); // le plateau l'unite de la souris
							GestionClic.setMouseIcon(this, plateauTemp[i][j]); // la souris prend l'image qui etait sur le bouton
							GestionClic.getUnit();
						}
					} // fin souris prise
				}
			}
		}

		for (j = 0; j < 4; j++) {	//boucles pour prendre les pions du stock
			for (i = 0; i < 3; i++) {
				if (source == boutonsP[i][j]) {
					if (GestionClic.getUnit() == null) { // souris vide
						// si il reste des pions a placer on decremente
						if (nbDrapeauR > 0 && boutonsP[i][j].getToolTipText() == "DRAPEAU") {
							GestionClic.setMouseIcon(this, drapeauR);
							GestionClic.getUnit();
							nbDrapeauR--;

							if (nbDrapeauR == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbMarechalR > 0 && boutonsP[i][j].getToolTipText() == "MARECHAL") {
							GestionClic.setMouseIcon(this, marechalR);
							GestionClic.getUnit();
							nbMarechalR--;

							if (nbMarechalR == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbGeneralR > 0 && boutonsP[i][j].getToolTipText() == "GENERAL") {
							GestionClic.setMouseIcon(this, generalR);
							GestionClic.getUnit();
							nbGeneralR--;

							if (nbGeneralR == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbColonelR > 0 && boutonsP[i][j].getToolTipText() == "COLONEL") {
							GestionClic.setMouseIcon(this, colonelR);
							GestionClic.getUnit();
							nbColonelR--;

							if (nbColonelR == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbCommandantR > 0 && boutonsP[i][j].getToolTipText() == "COMMANDANT") {
							GestionClic.setMouseIcon(this, commandantR);
							GestionClic.getUnit();
							nbCommandantR--;

							if (nbCommandantR == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbCapitaineR > 0 && boutonsP[i][j].getToolTipText() == "CAPITAINE") {
							GestionClic.setMouseIcon(this, capitaineR);
							GestionClic.getUnit();
							nbCapitaineR--;

							if (nbCapitaineR == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbLieutenantR > 0 && boutonsP[i][j].getToolTipText() == "LIEUTENANT") {
							GestionClic.setMouseIcon(this, lieutenantR);
							GestionClic.getUnit();
							nbLieutenantR--;

							if (nbLieutenantR == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbSergentR > 0 && boutonsP[i][j].getToolTipText() == "SERGENT") {
							GestionClic.setMouseIcon(this, sergentR);
							GestionClic.getUnit();
							nbSergentR--;

							if (nbSergentR == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbDemineurR > 0 && boutonsP[i][j].getToolTipText() == "DEMINEUR") {
							GestionClic.setMouseIcon(this, demineurR);
							GestionClic.getUnit();
							nbDemineurR--;

							if
							(nbDemineurR == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbEclaireurR > 0 && boutonsP[i][j].getToolTipText() == "ECLAIREUR") {
							GestionClic.setMouseIcon(this, eclaireurR);
							GestionClic.getUnit();
							nbEclaireurR--;

							if (nbEclaireurR == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbEspionR > 0 && boutonsP[i][j].getToolTipText() == "ESPION") {
							GestionClic.setMouseIcon(this, espionR);
							GestionClic.getUnit();
							nbEspionR--;

							if (nbEspionR == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbBombeR > 0 && boutonsP[i][j].getToolTipText() == "BOMBE") {
							GestionClic.setMouseIcon(this, bombeR);
							GestionClic.getUnit();
							nbBombeR--;

							if (nbBombeR == 0)
								boutonsP[i][j].setIcon(null);
						}
					}// fin souris vide
					else { // si souris contient unite

						if (testStock(GestionClic.getUnit())) // si pas vide juste incrementer
							ajouterAuStock(GestionClic.getUnit());
						else {
							ajouterImageAuStock(GestionClic.getUnit());// sinon incrementer et ajouter image
							ajouterAuStock(GestionClic.getUnit());
						}
						GestionClic.setMouseIcon(this);
					}
				}
			}
		}// fin boucle boutonsP
	} // fin placerPionsR

	public void placerPionsB(MouseEvent e) {	// idem que placerPionsR
		Object source = e.getSource();

		for (i = 0; i < 10; i++) {
			for (j = 0; j < 4; j++) {
				if (source == boutons[i][j]) {
					if (GestionClic.getUnit() == null) { // si souris est vide
						if (source == boutons[i][j] && boutons[i][j].getToolTipText() != "HERBE") {
							GestionClic.setMouseIcon(this, plateau[i][j]);
							GestionClic.getUnit();
							boutons[i][j].setIcon(herbe.getImage());
							boutons[i][j].setToolTipText(herbe.getDescription());
							plateau[i][j] = herbe;
						}
					}
					else if (GestionClic.getUnit() != null) { // si souris contient une unite
						if (source == boutons[i][j] && boutons[i][j].getToolTipText() == "HERBE") {
							boutons[i][j].setIcon(GestionClic.getUnit().getImage());
							boutons[i][j].setToolTipText(GestionClic.getUnit().getDescription());
							plateau[i][j] = GestionClic.getUnit();
							GestionClic.setMouseIcon(this);
						}
						else if (source == boutons[i][j] && boutons[i][j].getToolTipText() != "HERBE"
								&& boutons[i][j].getToolTipText() != "") { // permuter les pions sur le plateau
							plateauTemp[i][j] = plateau[i][j];
							boutons[i][j].setIcon(GestionClic.getUnit().getImage());
							boutons[i][j].setToolTipText(GestionClic.getUnit().getDescription());
							plateau[i][j] = GestionClic.getUnit();
							GestionClic.setMouseIcon(this, plateauTemp[i][j]);
							GestionClic.getUnit();
						}
					} // fin souris prise
				}
			}
		}
		for (j = 0; j < 4; j++) {
			for (i = 0; i < 3; i++) {
				if (source == boutonsP[i][j]) {
					if (GestionClic.getUnit() == null) { // souris vide
						if (nbDrapeauB > 0 && boutonsP[i][j].getToolTipText() == "DRAPEAU") {
							GestionClic.setMouseIcon(this, drapeauB);
							GestionClic.getUnit();
							nbDrapeauB--;

							if (nbDrapeauB == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbMarechalB > 0 && boutonsP[i][j].getToolTipText() == "MARECHAL") {
							GestionClic.setMouseIcon(this, marechalB);
							GestionClic.getUnit();
							nbMarechalB--;

							if (nbMarechalB == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbGeneralB > 0 && boutonsP[i][j].getToolTipText() == "GENERAL") {
							GestionClic.setMouseIcon(this, generalB);
							GestionClic.getUnit();
							nbGeneralB--;

							if (nbGeneralB == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbColonelB > 0 && boutonsP[i][j].getToolTipText() == "COLONEL") {
							GestionClic.setMouseIcon(this, colonelB);
							GestionClic.getUnit();
							nbColonelB--;

							if (nbColonelB == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbCommandantB > 0 && boutonsP[i][j].getToolTipText() == "COMMANDANT") {
							GestionClic.setMouseIcon(this, commandantB);
							GestionClic.getUnit();
							nbCommandantB--;

							if (nbCommandantB == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbCapitaineB > 0 && boutonsP[i][j].getToolTipText() == "CAPITAINE") {
							GestionClic.setMouseIcon(this, capitaineB);
							GestionClic.getUnit();
							nbCapitaineB--;

							if (nbCapitaineB == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbLieutenantB > 0 && boutonsP[i][j].getToolTipText() == "LIEUTENANT") {
							GestionClic.setMouseIcon(this, lieutenantB);
							GestionClic.getUnit();
							nbLieutenantB--;

							if (nbLieutenantB == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbSergentB > 0 && boutonsP[i][j].getToolTipText() == "SERGENT") {
							GestionClic.setMouseIcon(this, sergentB);
							GestionClic.getUnit();
							nbSergentB--;

							if (nbSergentB == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbDemineurB > 0 && boutonsP[i][j].getToolTipText() == "DEMINEUR") {
							GestionClic.setMouseIcon(this, demineurB);
							GestionClic.getUnit();
							nbDemineurB--;

							if (nbDemineurB == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbEclaireurB > 0 && boutonsP[i][j].getToolTipText() == "ECLAIREUR") {
							GestionClic.setMouseIcon(this, eclaireurB);
							GestionClic.getUnit();
							nbEclaireurB--;

							if (nbEclaireurB == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbEspionB > 0
								&& boutonsP[i][j].getToolTipText() == "ESPION") {
							GestionClic.setMouseIcon(this, espionB);
							GestionClic.getUnit();
							nbEspionB--;

							if (nbEspionB == 0)
								boutonsP[i][j].setIcon(null);
						}
						if (nbBombeB > 0 && boutonsP[i][j].getToolTipText() == "BOMBE") {
							GestionClic.setMouseIcon(this, bombeB);
							GestionClic.getUnit();
							nbBombeB--;

							if (nbBombeB == 0)
								boutonsP[i][j].setIcon(null);
						}
					}// fin souris vide
					else { // si souris contient unitÈ
						if (testStock(GestionClic.getUnit()))// si pas vide juste incrementer
							ajouterAuStock(GestionClic.getUnit());
						else {
							ajouterImageAuStock(GestionClic.getUnit());// sinon incrementer et ajouter image
							ajouterAuStock(GestionClic.getUnit());
						}
						GestionClic.setMouseIcon(this);
					}
				}
			}
		}// fin boucle boutonsP
	} // fin placerBoutonsB

	public boolean testStock(Unite selection) { // test si les unites sont presentes dans le stock
		if (selection == drapeauR) {
			if (nbDrapeauR == 0)
				return false;
			else
				return true;
		}
		if (selection == drapeauB) {
			if (nbDrapeauB == 0)
				return false;
			else
				return true;
		}
		if (selection == marechalR) {
			if (nbMarechalR == 0)
				return false;
			else
				return true;
		}
		if (selection == marechalB) {
			if (nbMarechalB == 0)
				return false;
			else
				return true;
		}
		if (selection == generalR) {
			if (nbGeneralR == 0)
				return false;
			else
				return true;
		}
		if (selection == generalB) {
			if (nbGeneralB == 0)
				return false;
			else
				return true;
		}
		if (selection == colonelR) {
			if (nbColonelR == 0)
				return false;
			else
				return true;
		}
		if (selection == colonelB) {
			if (nbColonelB == 0)
				return false;
			else
				return true;
		}
		if (selection == commandantR) {
			if (nbCommandantR == 0)
				return false;
			else
				return true;
		}
		if (selection == commandantB) {
			if (nbCommandantB == 0)
				return false;
			else
				return true;
		}
		if (selection == capitaineR) {
			if (nbCapitaineR == 0)
				return false;
			else
				return true;
		}
		if (selection == capitaineB) {
			if (nbCapitaineB == 0)
				return false;
			else
				return true;
		}
		if (selection == lieutenantR) {
			if (nbLieutenantR == 0)
				return false;
			else
				return true;
		}
		if (selection == lieutenantB) {
			if (nbLieutenantB == 0)
				return false;
			else
				return true;
		}
		if (selection == sergentR) {
			if (nbSergentR == 0)
				return false;
			else
				return true;
		}
		if (selection == sergentB) {
			if (nbSergentB == 0)
				return false;
			else
				return true;
		}
		if (selection == demineurR) {
			if (nbDemineurR == 0)
				return false;
			else
				return true;
		}
		if (selection == demineurB) {
			if (nbDemineurB == 0)
				return false;
			else
				return true;
		}
		if (selection == eclaireurR) {
			if (nbEclaireurR == 0)
				return false;
			else
				return true;
		}
		if (selection == eclaireurB) {
			if (nbEclaireurB == 0)
				return false;
			else
				return true;
		}
		if (selection == espionR) {
			if (nbEspionR == 0)
				return false;
			else
				return true;
		}
		if (selection == espionB) {
			if (nbEspionB == 0)
				return false;
			else
				return true;
		}
		if (selection == bombeR) {
			if (nbBombeR == 0)
				return false;
			else
				return true;
		}
		if (selection == bombeB) {
			if (nbBombeB == 0)
				return false;
			else
				return true;
		}
		return false;
	}

	public void ajouterAuStock(Unite selection) {	//incremente le nombre d'unite quand repose dans les boutonsP
		if (selection == drapeauR)	//rouges
			nbDrapeauR++;
		else if (selection == marechalR)
			nbMarechalR++;
		else if (selection == generalR)
			nbGeneralR++;
		else if (selection == colonelR)
			nbColonelR++;
		else if (selection == commandantR)
			nbCommandantR++;
		else if (selection == capitaineR)
			nbCapitaineR++;
		else if (selection == lieutenantR)
			nbLieutenantR++;
		else if (selection == sergentR)
			nbSergentR++;
		else if (selection == demineurR)
			nbDemineurR++;
		else if (selection == eclaireurR)
			nbEclaireurR++;
		else if (selection == espionR)
			nbEspionR++;
		else if (selection == bombeR)
			nbBombeR++;
		else if (selection == drapeauB) // Bleus
			nbDrapeauB++;
		else if (selection == marechalB)
			nbMarechalB++;
		else if (selection == generalB)
			nbGeneralB++;
		else if (selection == colonelB)
			nbColonelB++;
		else if (selection == commandantB)
			nbCommandantB++;
		else if (selection == capitaineB)
			nbCapitaineB++;
		else if (selection == lieutenantB)
			nbLieutenantB++;
		else if (selection == sergentB)
			nbSergentB++;
		else if (selection == demineurB)
			nbDemineurB++;
		else if (selection == eclaireurB)
			nbEclaireurB++;
		else if (selection == espionB)
			nbEspionB++;
		else if (selection == bombeB)
			nbBombeB++;
	}
	
	public void ajouterImageAuStock(Unite selection) { // remet l'image dans la bonne case quand replacer dans stock
		if (selection == drapeauR)			//rouges			
			boutonsP[0][0].setIcon(drapeauR.getImage());
		else if (selection == marechalR)
			boutonsP[1][0].setIcon(marechalR.getImage());
		else if (selection == generalR)
			boutonsP[2][0].setIcon(generalR.getImage());
		else if (selection == colonelR)
			boutonsP[0][1].setIcon(colonelR.getImage());
		else if (selection == commandantR)
			boutonsP[1][1].setIcon(commandantR.getImage());
		else if (selection == capitaineR)
			boutonsP[2][1].setIcon(capitaineR.getImage());
		else if (selection == lieutenantR)
			boutonsP[0][2].setIcon(lieutenantR.getImage());
		else if (selection == sergentR)
			boutonsP[1][2].setIcon(sergentR.getImage());
		else if (selection == demineurR)
			boutonsP[2][2].setIcon(demineurR.getImage());
		else if (selection == eclaireurR)
			boutonsP[0][3].setIcon(eclaireurR.getImage());
		else if (selection == espionR)
			boutonsP[1][3].setIcon(espionR.getImage());
		else if (selection == bombeR)
			boutonsP[2][3].setIcon(bombeR.getImage());
		else if (selection == drapeauB) 				// Bleus
			boutonsP[0][0].setIcon(drapeauB.getImage());
		else if (selection == marechalB)
			boutonsP[1][0].setIcon(marechalB.getImage());
		else if (selection == generalB)
			boutonsP[2][0].setIcon(generalB.getImage());
		else if (selection == colonelB)
			boutonsP[0][1].setIcon(colonelB.getImage());
		else if (selection == commandantB)
			boutonsP[1][1].setIcon(commandantB.getImage());
		else if (selection == capitaineB)
			boutonsP[2][1].setIcon(capitaineB.getImage());
		else if (selection == lieutenantB)
			boutonsP[0][2].setIcon(lieutenantB.getImage());
		else if (selection == sergentB)
			boutonsP[1][2].setIcon(sergentB.getImage());
		else if (selection == demineurB)
			boutonsP[2][2].setIcon(demineurB.getImage());
		else if (selection == eclaireurB)
			boutonsP[0][3].setIcon(eclaireurB.getImage());
		else if (selection == espionB)
			boutonsP[1][3].setIcon(espionB.getImage());
		else if (selection == bombeB)
			boutonsP[2][3].setIcon(bombeB.getImage());
	}

	public void cacherRouge() {		// cache les pions quand le tour rouge est fini
		for (i = 0; i < 10; i++) {
			for (j = 0; j < 10; j++) {
				if (testSiRouge(i, j) == true) { //si un pion est rouge
					if(plateauTemp[i][j] == deplaceR ){	// si le pion a ete deplace
						boutons[i][j].setIcon(deplaceR.getImage());	// le joueur adverse pourra voir le pion en question
						plateauTemp[i][j] = herbe;	// le plateauTemp prend la valeur herbe par defaut
					}
					else if (plateauTemp[i][j] == gagneR){	// si le pion a gagne un combat
						boutons[i][j].setIcon(gagneR.getImage()); // le joueur adverse pourra voir ou le combat a eu lieu
						plateauTemp[i][j] = herbe;	// le plateauTemp prend la valeur herbe par defaut
					}
					else {	// pour toutes les autres pions rouges
					boutons[i][j].setToolTipText(fondR.getDescription()); // on affiche un drapeau rouge
					boutons[i][j].setIcon(fondR.getImage());
					}
				}
			}
		}
	}
	
	public void cacherBleu() {	// idem que cacherRouge
		for (i = 0; i < 10; i++) {
			for (j = 0; j < 10; j++) {
				if (testSiBleue(i, j) == true) {
					if (plateauTemp[i][j] == deplaceB) {
						boutons[i][j].setIcon(deplaceB.getImage());
						plateauTemp[i][j] = herbe;
					} else if (plateauTemp[i][j] == gagneB) {
						boutons[i][j].setIcon(gagneB.getImage());
						plateauTemp[i][j] = herbe;
					} else {
						boutons[i][j].setToolTipText(fondB.getDescription());
						boutons[i][j].setIcon(fondB.getImage());
					}
				}
			}
		}
	}
	
	public void montrerRouge() {  // va reafficher les images des unites quand tour rouge
		for (i = 0; i < 10; i++) {
			for (j = 0; j < 10; j++) {
				if (testSiRouge(i, j) == true) { // pour chaque pion rouge
					boutons[i][j].setToolTipText(plateau[i][j].getDescription()); // on test le plateau pour retrouver les unites
					boutons[i][j].setIcon(plateau[i][j].getImage()); // recupere l'image a l'aide du plateau
				}
			if(boutons[i][j].getToolTipText()=="HERBESOURCE" || boutons[i][j].getToolTipText()=="HERBESUICIDE"){ 
					boutons[i][j].setIcon(herbe.getImage()); // on reaffiche de l'herbe
					boutons[i][j].setToolTipText(herbe.getDescription());
				}
			}
		}
	}

	public void montrerBleu() { 	// idem que montrerRouge
		for (i = 0; i < 10; i++) {
			for (j = 0; j < 10; j++) {
				if (testSiBleue(i, j) == true) {
					boutons[i][j].setToolTipText(plateau[i][j].getDescription());
					boutons[i][j].setIcon(plateau[i][j].getImage());
				}
				if(boutons[i][j].getToolTipText()=="HERBESOURCE" || boutons[i][j].getToolTipText()=="HERBESUICIDE"){	
					boutons[i][j].setIcon(herbe.getImage());
					boutons[i][j].setToolTipText(herbe.getDescription());
				}
			}
		}
	}
	
	public void commencer(MouseEvent e) {
		Object source = e.getSource();
		// totalR et totalB representent le nombre total d'unite pour verifier leur placement
	
		  totalR = nbDrapeauR + nbMarechalR + nbGeneralR + nbColonelR +
		  nbCommandantR + nbCapitaineR + nbLieutenantR + nbSergentR +
		  nbDemineurR + nbEclaireurR + nbEspionR + nbBombeR;
		  
		  totalB = nbDrapeauB + nbMarechalB + nbGeneralB + nbColonelB +
		  nbCommandantB + nbCapitaineB + nbLieutenantB + nbSergentB +
		  nbDemineurB + nbEclaireurB + nbEspionB + nbBombeB;

		if (source == commencer) {	// si on clique sur le bouton commencer
			if (totalR == 0 && totalB == 0) { // et que les 2 joueurs ont plac�s tous leurs pions
				toutPlacer = true; 	
				commencer.setVisible(false);  //on cache le bouton
				for(i=0;i<3;i++){
					for(j=0;j<4;j++){
					boutonsP[i][j].setVisible(false);	// on cache les boutonsP
					}
				}
				cacherBleu();		// les pions du joueur bleu sont caches en prevision du tour joueur rouge
				etat = -2;		// le joueur bleu ne peut plus rien faire
				finDeTour.setVisible(true);		// affiche le bouton pour permettre au joueur rouge de jouer son tour
			} 
			else {		// si il reste des pions a placer
				error.playSound() ;
				JOptionPane.showMessageDialog(this,"Il reste des pions a placer !", "Attention", JOptionPane.WARNING_MESSAGE);
				toutPlacer = false;
			}
		}
	}

	public void quitter(MouseEvent e){
		Object source = e.getSource();
								// bouton pour quitter le programme
		if(source == quit){
			int quit = JOptionPane.showConfirmDialog(null, "Etes-vous sur ?", "Quitter", JOptionPane.YES_NO_OPTION);
			if (quit == 0)
				System.exit(0);
		}
	}
	
	public void regles(MouseEvent e){
		Object source = e.getSource();
								// bouton pour afficher les regles
		if (source== regles){
			Regles frame = new Regles();
			frame.setLocationRelativeTo(null);
			this.setVisible(true);
			frame.setVisible(true);
		}
	}
	
	public void cacherPions(MouseEvent e) {
		Object source = e.getSource();
				
		if (source == cacherPions) {
			if (etat == -1) {		// si le joueur rouge a fini son tour
				cacherRouge();		// le bouton va cacher ses pions
				finDeTour.setVisible(true);		// afficher le bouton permettant au joueur bleu de jouer son tour
				finDeTour.setText("Tour bleu");
				cacherPions.setVisible(false);	// cache le bouton cacherPions
			} else if (etat == -2) {	// l'inverse si le joueur bleu a fini son tour
				cacherBleu();
				finDeTour.setVisible(true);
				finDeTour.setText("Tour rouge");
				cacherPions.setVisible(false);
			}
		}
	}

	public void suivant(MouseEvent e) {  // bouton sur lequel le joueur rouge cliquera quand il aura place ses pions
		Object source = e.getSource();

		if (source == suivant) {
			if (totalR != 0) {	// si le joueur rouge n'a pas place tous ses pions
				error.playSound() ;
				JOptionPane.showMessageDialog(this, "Il reste des pions a placer !", "Attention",
				JOptionPane.WARNING_MESSAGE);
				toutPlacerR = false;
			}
			if (totalR == 0 && totalB != 0) {	//quand le joueur rouge a place tous ses pions
				toutPlacerR = true;	
				cacherRouge();	// cache les pions rouges
				boutonsP[0][0].setToolTipText(drapeauB.getDescription()); // affiche les boutons bleus a placer
				boutonsP[0][0].setIcon(drapeauB.getImage());
				plateauP[0][0] = drapeauB;

				boutonsP[1][0].setToolTipText(marechalB.getDescription());
				boutonsP[1][0].setIcon(marechalB.getImage());
				plateauP[1][0] = marechalB;

				boutonsP[2][0].setToolTipText(generalB.getDescription());
				boutonsP[2][0].setIcon(generalB.getImage());
				plateauP[2][0] = generalB;

				boutonsP[0][1].setToolTipText(colonelB.getDescription());
				boutonsP[0][1].setIcon(colonelB.getImage());
				plateauP[0][1] = colonelB;

				boutonsP[1][1].setToolTipText(commandantB.getDescription());
				boutonsP[1][1].setIcon(commandantB.getImage());
				plateauP[1][1] = commandantB;

				boutonsP[2][1].setToolTipText(capitaineB.getDescription());
				boutonsP[2][1].setIcon(capitaineB.getImage());
				plateauP[2][1] = capitaineB;

				boutonsP[0][2].setToolTipText(lieutenantB.getDescription());
				boutonsP[0][2].setIcon(lieutenantB.getImage());
				plateauP[0][2] = lieutenantB;

				boutonsP[1][2].setToolTipText(sergentB.getDescription());
				boutonsP[1][2].setIcon(sergentB.getImage());
				plateauP[1][2] = sergentB;

				boutonsP[2][2].setToolTipText(demineurB.getDescription());
				boutonsP[2][2].setIcon(demineurB.getImage());
				plateauP[2][2] = demineurB;

				boutonsP[0][3].setToolTipText(eclaireurB.getDescription());
				boutonsP[0][3].setIcon(eclaireurB.getImage());
				plateauP[0][3] = eclaireurB;

				boutonsP[1][3].setToolTipText(espionB.getDescription());
				boutonsP[1][3].setIcon(espionB.getImage());
				plateauP[1][3] = espionB;

				boutonsP[2][3].setToolTipText(bombeB.getDescription());
				boutonsP[2][3].setIcon(bombeB.getImage());
				plateauP[2][3] = bombeB;

				suivant.setVisible(false);
			}
			if (toutPlacerR == true && totalB != 0) {
				JOptionPane.showMessageDialog(this, "Au joueur bleu de placer ses pions !", "Attention", 
				JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	public void finDeTour(MouseEvent e) {  // a action a la fin d'un tour
		Object source = e.getSource();

		if (source == finDeTour) {
			if (etat == -1) { // quand joueur rouge a fini son tour (au bleu de jouer)
				montrerBleu();  //affiche ses pions
				finDeTour.setVisible(false);	// cache le bouton
				etat = 2;		// tour joueur bleu
			} 
			else if (etat == -2) { // quand joueur bleu a fini son tour (au rouge de jouer)
				montrerRouge();
				finDeTour.setVisible(false);
				etat = 1;
			}
		}
	}

	public void jouer(MouseEvent e) { // parcours le plateau de jeu pour permettre de jouer
		Object source = e.getSource();
		
		for (i = 0; i < 10; i++) {
			for (j = 0; j < 10; j++) {
				if (source == boutons[i][j]) {
					if (etat == 1) {		// tour rouge
						tourJoueurRouge(boutons[i][j], i, j);
					} 
					else if (etat == 2) {	// tour bleu
						tourJoueurBleu(boutons[i][j], i, j);
					}
				}
			}
		}
	}

	public boolean testSiRouge(int i, int j) {
		if (plateau[i][j] == drapeauR || plateau[i][j] == espionR
				|| plateau[i][j] == bombeR || plateau[i][j] == eclaireurR
				|| plateau[i][j] == demineurR || plateau[i][j] == sergentR
				|| plateau[i][j] == lieutenantR || plateau[i][j] == capitaineR
				|| plateau[i][j] == commandantR || plateau[i][j] == colonelR
				|| plateau[i][j] == generalR || plateau[i][j] == marechalR)
			return true;
		else
			return false;
	}

	public boolean testSiBleue(int i, int j) {
		if (plateau[i][j] == drapeauB || plateau[i][j] == espionB
				|| plateau[i][j] == bombeB || plateau[i][j] == eclaireurB
				|| plateau[i][j] == demineurB || plateau[i][j] == sergentB
				|| plateau[i][j] == lieutenantB || plateau[i][j] == capitaineB
				|| plateau[i][j] == commandantB || plateau[i][j] == colonelB
				|| plateau[i][j] == generalB || plateau[i][j] == marechalB)
			return true;
		else
			return false;
	}

	public boolean testMove(int i, int j) {		//test si un mouvement d'unite est valide
		int n;

		if (sourceI == i && sourceJ == j)  // si on repose une unite sur sa case d'origine
			return true;

		if (sourceI < i && sourceJ == j) { // Test si source est a gauche de la destination
			n = sourceI;
			n++;
			if (n == i) {
				return true;
			} else
				return false;
		}
	
		if (sourceI > i && sourceJ == j) {	// Test si source est a droite de la destination
			n = sourceI;
			n--;
			if (n == i) {
				return true;
			} else
				return false;
		}
	
		if (sourceI == i && sourceJ < j) {	// Test si source est en haut de la destination
			n = sourceJ;
			n++;
			if (n == j) {
				return true;
			} else
				return false;
		}		
		if (sourceI == i && sourceJ > j) {  // Test si source est en bas de la destination
			n = sourceJ;
			n--;
			if (n == j) {
				return true;
			} else
				return false;
		}
		return false;  // Autre case, non valide (diagonales)
	}

	public boolean testEclaireurMove(int i, int j) {
		if (sourceI == i && sourceJ == j) // si on repose le pion sur la case source
			return true;

		if (sourceI < i && sourceJ == j) {	// Test si source a gauche de la destination
			for (int x = sourceI; x < i; x++) {
				if (plateau[x][sourceJ] != herbe) // verifie si il n'y a que de l'herbe entre source et destination
					return false;
			}
			return true;
		}
		if (sourceI > i && sourceJ == j) {	// Test si source a droite de la destination
			for (int x = sourceI; x > i; x--) {
				if (plateau[x][sourceJ] != herbe)
					return false;
			}
			return true;
		}
		if (sourceI == i && sourceJ < j) {	// Test si source en haut de la destination
			for (int y = sourceJ; y < j; y++) {
				if (plateau[sourceI][y] != herbe)
					return false;
			}
			return true;
		}
		if (sourceI == i && sourceJ > j) {	// Test si source en bas de la destination
			for (int y = sourceJ; y > j; y--) {
				if (plateau[sourceI][y] != herbe)
					return false;
			}
			return true;
		}
		return false;	// Autre case, non valide (diagonales)
	}

	public void tourJoueurRouge(JButton jButton, int i, int j) {
		if (GestionClic.getUnit() == null) // souris ne contient pas d'unite
		{
			montrerRouge();  // montre les pions rouges
			if (testSiRouge(i, j) == false) // faut que ce soit rouge
				 error.playSound();
			else // si rouge
			{
				if (plateau[i][j] == drapeauR || plateau[i][j] == bombeR) // ne prend pas si drapeau ou bombe
					 error.playSound();
				else { // pour toutes les autres pieces
					sourceI = i;
					sourceJ = j;
					GestionClic.setMouseIcon(this, plateau[i][j]);
					jButton.setIcon(herbeSource.getImage());	// indique la case de laquelle l'unite a ete prise
					jButton.setToolTipText(herbeSource.getDescription());
					plateau[i][j] = herbe;
				}
			}
		} 
		else // si souris contient unite
		{
			if (testSiRouge(i, j) == true) // si on veut aller sur un pion rouge
				 error.playSound();
			else if (testSiBleue(i, j) == true) // si on va sur un pion bleu
			{
				if (GestionClic.getUnit() == eclaireurR) { // si on va sur pion bleu avec eclaireur
					if (testEclaireurMove(i, j) == true) {
						combat(jButton, i, j);				
						etat = -1;		// un combat s'effectue et le joueur rouge ne peut plus rien faire
						cacherPions.setVisible(true);  // pour pouvoir passer la main au joueur bleu
					} else
						 error.playSound();
				} 
				else {
					if (testMove(i, j) == true) { // si n'importe quel autre pion attaque un bleu
						combat(jButton, i, j);
						etat = -1;		// le tour est fini
						cacherPions.setVisible(true);
					} 
					else
						 error.playSound();
				}
			} 
			else // deplacements sur une case herbe
			{
				if (GestionClic.getUnit() == eclaireurR) { // conditions pour l'eclaireur
					if (testEclaireurMove(i, j) == true && sourceI == i && sourceJ == j) { // si on repose sur la meme case
						jButton.setToolTipText(GestionClic.getUnit().getDescription());											
						jButton.setIcon(GestionClic.getUnit().getImage());
						plateau[i][j] = GestionClic.getUnit();
						GestionClic.setMouseIcon(this);
						etat = 1; // ne compte pas de tour
					}

					else if (testEclaireurMove(i, j) == true) { // si on a deplace
						jButton.setToolTipText(GestionClic.getUnit().getDescription());
						jButton.setIcon(GestionClic.getUnit().getImage());
						plateau[i][j] = GestionClic.getUnit();
						plateauTemp[i][j] = deplaceR;
						etat = -1;		// le tour est fini
						cacherPions.setVisible(true);
						GestionClic.setMouseIcon(this);
					}
					else
						 error.playSound();
				}
				else {	// conditions pour tous les autres pions
					if (testMove(i, j) == true && sourceI == i && sourceJ == j) { // si repose au meme endroit
						jButton.setToolTipText(GestionClic.getUnit().getDescription());
						jButton.setIcon(GestionClic.getUnit().getImage());
						plateau[i][j] = GestionClic.getUnit();
						etat = 1;
						GestionClic.setMouseIcon(this);
					} 
					else if (testMove(i, j) == true) { // si mouvement sur autre case
						jButton.setToolTipText(GestionClic.getUnit().getDescription());
						jButton.setIcon(GestionClic.getUnit().getImage());
						plateau[i][j] = GestionClic.getUnit();
						plateauTemp[i][j] = deplaceR;	
						etat = -1;	// compte un tour
						cacherPions.setVisible(true);
						GestionClic.setMouseIcon(this);
					}
					else
						 error.playSound();
				}
			}
		} // fin cas ou la souris contient une unite
	}

	public void tourJoueurBleu(JButton jButton, int i, int j) {  // idem tourJoueurBleu
		if (GestionClic.getUnit() == null) // souris fleche
		{
			if (testSiBleue(i, j) == false) // pas unite bleue
				error.playSound();
			else {// unite bleue
				if (plateau[i][j] == drapeauB || plateau[i][j] == bombeB)	// ne prend pas si drapeau ou bomb
					error.playSound();
				else {
					sourceI = i;
					sourceJ = j;
					GestionClic.setMouseIcon(this, plateau[i][j]);
					jButton.setIcon(herbeSource.getImage());
					jButton.setToolTipText(herbeSource.getDescription());
					plateau[i][j] = herbe;
				}
			}
		} 
		else { // si souris contient unite
			if (testSiBleue(i, j) == true) // si unite bleue sur case
				error.playSound();
			else if (testSiRouge(i, j) == true) // si unite rouge sur case
			{
				if (GestionClic.getUnit() == eclaireurB) {
					if (testEclaireurMove(i, j) == true) {
						combat(jButton, i, j);
						cacherPions.setVisible(true);
						etat = -2;
					} else
						error.playSound();
				} else {
					if (testMove(i, j) == true) {
						combat(jButton, i, j);
						cacherPions.setVisible(true);
						etat = -2; // doit cacher ses pions
					} else
						error.playSound();
				}
			} 
			else {// Herbe...
				if (GestionClic.getUnit() == eclaireurB) {
					if (testEclaireurMove(i, j) == true && sourceI == i && sourceJ == j) {
						jButton.setToolTipText(GestionClic.getUnit().getDescription());
						jButton.setIcon(GestionClic.getUnit().getImage());
						plateau[i][j] = GestionClic.getUnit();
						etat = 2;
						GestionClic.setMouseIcon(this);
					} 
					else if (testEclaireurMove(i, j) == true) {
						jButton.setToolTipText(GestionClic.getUnit()
								.getDescription());
						jButton.setIcon(GestionClic.getUnit().getImage());
						plateau[i][j] = GestionClic.getUnit();
						plateauTemp[i][j] = deplaceB;
						etat = -2;
						cacherPions.setVisible(true);
						GestionClic.setMouseIcon(this);
					}
					else
						error.playSound();
				}
				
				else { // autre
					if (testMove(i, j) == true && sourceI == i && sourceJ == j) {
						jButton.setToolTipText(GestionClic.getUnit().getDescription());
						jButton.setIcon(GestionClic.getUnit().getImage());
						plateau[i][j] = GestionClic.getUnit();
						etat = 2;
						GestionClic.setMouseIcon(this);
					} 
					else if (testMove(i, j) == true) {
						jButton.setToolTipText(GestionClic.getUnit().getDescription());
						jButton.setIcon(GestionClic.getUnit().getImage());
						plateau[i][j] = GestionClic.getUnit();
						plateauTemp[i][j] = deplaceB;
						etat = -2;
						cacherPions.setVisible(true);
						GestionClic.setMouseIcon(this);
					}
					else
						 error.playSound(); // peut etre mauvais
				}
			}
		}
	}

	public void combat(JButton jButton, int i, int j) {
		if (plateau[i][j].getPuissance() == 11) {		// unite sur drapeau
			jButton.setIcon(GestionClic.getUnit().getImage());
			jButton.setToolTipText(GestionClic.getUnit().getDescription());
			plateau[i][j] = GestionClic.getUnit();
			GestionClic.setMouseIcon(this);
			EcranFin frame = new EcranFin();		// la partie est gagn�e
			frame.setLocationRelativeTo(null);
			
			if(etat==1){		  // si le joueur rouge va sur le drapeau bleu
				frame.setTitle("victoire de rouges");
				JOptionPane.showMessageDialog(this,"victoire des rouges", "Bravo!",JOptionPane.WARNING_MESSAGE);
				victoire.playSound();
				this.setVisible(false);
			}
				else if(etat==2){	// si le joueur bleu va sur le drapeau rouge
					frame.setTitle("victoire des bleus");
					JOptionPane.showMessageDialog(this,"victoire des bleus", "Bravo",JOptionPane.WARNING_MESSAGE);
					victoire.playSound();
					this.setVisible(false);
				}
			frame.setVisible(true);	
		}
		
		 else if (plateau[i][j].getPuissance() == 12 && GestionClic.getUnit().getPuissance() == 3) {// demineur sur bombe											 
			if (testSiBleue(i, j) == true) { 	// si demineur rouge prend bombe bleue	
				boutonUniteGagne.setIcon(GestionClic.getUnit().getImage());  												
				boutonUnitePerd.setIcon(plateau[i][j].getImage());
				plateauTemp[i][j] = gagneR;
			} 
				else {		// si demineur bleu prend bombe rouge	
					boutonUniteGagne.setIcon(GestionClic.getUnit().getImage()); 														
					boutonUnitePerd.setIcon(plateau[i][j].getImage());
					plateauTemp[i][j] = gagneB;
				}
			//	victoire.playSound();    // modifier son desamorcage			
			jButton.setIcon(GestionClic.getUnit().getImage());
			jButton.setToolTipText(GestionClic.getUnit().getDescription());
			plateau[i][j] = GestionClic.getUnit();
			GestionClic.setMouseIcon(this);
		}
		else if (plateau[i][j].getPuissance() == 12 && GestionClic.getUnit().getPuissance() != 3) { // toute unite sauf demineur sur bombe										
			if (testSiBleue(i, j) == true) { 		// unite rouge sur bombe bleue
				boutonUniteGagne.setIcon(plateau[i][j].getImage()); 													
				boutonUnitePerd.setIcon(GestionClic.getUnit().getImage());
				boutons[i][j].setIcon(defendB.getImage());
			} 
				else {		// unite bleue sur bombe rouge
					boutonUniteGagne.setIcon(plateau[i][j].getImage());															
					boutonUnitePerd.setIcon(GestionClic.getUnit().getImage());
					boutons[i][j].setIcon(defendR.getImage());
				}
			bombe.playSound();
			GestionClic.setMouseIcon(this);
		}
		else if (plateau[i][j].getPuissance() == 10 && GestionClic.getUnit().getPuissance() == 1) { // espion sur marechal						
			if (testSiBleue(i, j) == true) { // espion rouge sur marechal bleu
				boutonUniteGagne.setIcon(GestionClic.getUnit().getImage()); 																		 
				boutonUnitePerd.setIcon(plateau[i][j].getImage());
				plateauTemp[i][j] = gagneR;			
			} 
				else { 	// espion bleu sur marechal rouge
					boutonUniteGagne.setIcon(GestionClic.getUnit().getImage()); 
					boutonUnitePerd.setIcon(plateau[i][j].getImage());
					plateauTemp[i][j] = gagneB;	
				}									 
			jButton.setIcon(GestionClic.getUnit().getImage());
			jButton.setToolTipText(GestionClic.getUnit().getDescription());
			plateau[i][j] = GestionClic.getUnit();
			take.playSound();
			GestionClic.setMouseIcon(this);
		} 
		else if (plateau[i][j].getPuissance() == GestionClic.getUnit().getPuissance()) { // Unite egales, sucide
			if (testSiBleue(i, j) == true) { // rouge attaque
				boutonUniteGagne.setIcon(GestionClic.getUnit().getImage()); 																		
				boutonUnitePerd.setIcon(plateau[i][j].getImage());
			} 
				else {  // bleu attaque
					boutonUniteGagne.setIcon(GestionClic.getUnit().getImage()); 																
					boutonUnitePerd.setIcon(plateau[i][j].getImage());	
				}
			//jButton.setIcon(herbe.getImage());
			//jButton.setToolTipText(herbe.getDescription());
			jButton.setIcon(herbeSuicide.getImage());
			jButton.setToolTipText(herbeSuicide.getDescription());
			GestionClic.setMouseIcon(this);
			take.playSound();
			plateau[i][j] = herbe;
		}
		else if (plateau[i][j].getPuissance() < GestionClic.getUnit().getPuissance()) { // Attaquant plus fort
			if (testSiBleue(i, j) == true) { // rouge attaque
				boutonUniteGagne.setIcon(GestionClic.getUnit().getImage()); 																	
				boutonUnitePerd.setIcon(plateau[i][j].getImage());
				plateauTemp[i][j] = gagneR;		
			} 
				else {  // bleu attaque
					boutonUniteGagne.setIcon(GestionClic.getUnit().getImage()); 																			
					boutonUnitePerd.setIcon(plateau[i][j].getImage());
					plateauTemp[i][j] = gagneB; 	
				}
			jButton.setIcon(GestionClic.getUnit().getImage());
			jButton.setToolTipText(GestionClic.getUnit().getDescription());
			plateau[i][j] = GestionClic.getUnit();
			GestionClic.setMouseIcon(this);
			take.playSound();
		} 
		else { // Attaquant plus faible
			if (testSiBleue(i, j) == true) { // rouge attaque
				boutonUniteGagne.setIcon(plateau[i][j].getImage()); // si rouge sur bleue
				boutonUnitePerd.setIcon(GestionClic.getUnit().getImage());
				boutons[i][j].setIcon(defendB.getImage());

			} 
				else {		// bleu attaque
					boutonUniteGagne.setIcon(plateau[i][j].getImage()); // si bleu sur rouge 
					boutonUnitePerd.setIcon(GestionClic.getUnit().getImage());
					boutons[i][j].setIcon(defendR.getImage());
				}
			take.playSound();
			GestionClic.setMouseIcon(this);
		}
	}

	public void mouseClicked(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}
}
