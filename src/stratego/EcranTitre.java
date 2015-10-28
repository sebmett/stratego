package stratego;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class EcranTitre extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	JPanel panelIntro;
	JLabel jLabel1 = new JLabel();
	JButton start = new JButton();
	JButton quit = new JButton();

	ImageIcon introBackground = new ImageIcon("stratego.jpg");

	public void ecranInitTitre() {

		panelIntro = (JPanel) this.getContentPane();

		quit.setBounds(new Rectangle(450, 450, 150, 30));
		quit.setFocusPainted(false);
		quit.addMouseListener(this);
		quit.setText("Quit");

		start.setBounds(new Rectangle(450, 420, 150, 30));
		start.setFocusPainted(false);
		start.addMouseListener(this);
		start.setText("Start");

		this.setResizable(false);
		this.setSize(new Dimension(1050, 605));
		this.setTitle("Stratego");

		jLabel1.setIcon(introBackground);
		jLabel1.setBounds(new Rectangle(0, 0, 1050, 605));

		panelIntro.add(quit, null);
		panelIntro.add(start, null);
		panelIntro.add(jLabel1, null);

	}

	public EcranTitre() {

		ecranInitTitre();

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		Object source = e.getSource();
		if (source == start) {
			EcranJeu frame = new EcranJeu();
			frame.setLocationRelativeTo(null);
			this.setVisible(false);
			frame.setVisible(true);

		}
		if (source == quit) {
			int quit = JOptionPane.showConfirmDialog(null, "Etes-vous sur ?",
					"Quitter", JOptionPane.YES_NO_OPTION);

			if (quit == 0)
				System.exit(0);
		}

	}

	public void mouseReleased(MouseEvent e) {

	}

}
