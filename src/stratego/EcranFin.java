package stratego;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class EcranFin extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	JPanel panelFin;
	JLabel jLabel1 = new JLabel();
	JButton recommencer = new JButton();
	JButton quit = new JButton();

	public void ecranInitFin() {

		panelFin = (JPanel) this.getContentPane();

		quit.setBounds(new Rectangle(50, 120, 150, 30));
		quit.setFocusPainted(false);
		quit.addMouseListener(this);
		quit.setText("Quitter");

		recommencer.setBounds(new Rectangle(50, 170, 150, 30));
		recommencer.setFocusPainted(false);
		recommencer.addMouseListener(this);
		recommencer.setText("Nouvelle partie");

		this.setResizable(false);
		this.setSize(new Dimension(253, 253));
		this.setTitle("Fin de partie");

		ImageIcon gameBackground = new ImageIcon("strategoFin.jpg");
		jLabel1.setIcon(gameBackground);
		jLabel1.setBounds(new Rectangle(0, 0, 253, 253));

		panelFin.add(quit, null);
		panelFin.add(recommencer, null);
		panelFin.add(jLabel1, null);

	}

	public EcranFin() {
		ecranInitFin();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if (source == recommencer) {
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

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

}
