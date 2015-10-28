package stratego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Regles extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel panelRegles;
	JLabel jLabel1 = new JLabel();
	private JScrollPane defil;
	ImageIcon regles = new ImageIcon("regles.gif");

	public Regles() {

		this.setTitle("Regles");
		this.setSize(new Dimension(780, 691));
		panelRegles = new JPanel();
		Dimension d = new Dimension(755, 1100);
		panelRegles.setPreferredSize(d);
		panelRegles.setBackground(Color.white);
		defil = new JScrollPane(panelRegles);
		getContentPane().add(defil);
		this.setResizable(false);

		jLabel1.setIcon(regles);
		jLabel1.setBounds(new Rectangle(0, 0, 760, 691));

		panelRegles.add(jLabel1, null);

	}
}
