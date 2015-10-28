package stratego;

import java.awt.*;

public class GestionClic {

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Point mousePoint = new Point(15, 15);
	private static Image icon;
	private static Cursor cursor;
	private static Unite unite;

	// Change l'image d'un bouton
	public static void setIcon(Frame frame, String target) {
		icon = tk.getImage(target);
		frame.setIconImage(icon);
	}

	// Remet l'icone de la souris par defaut
	public static void setMouseIcon(Frame frame) {
		unite = null;
		frame.setCursor(null); 
	}

	// Permet a la souris d'afficher l'unite qu'elle transporte
	public static void setMouseIcon(Frame frame, Unite target) {
		unite = target;
		icon = tk.getImage(unite.getChemin());
		cursor = tk.createCustomCursor(icon, mousePoint, "UnitIcon");
		frame.setCursor(cursor);
	}

	// Recupere les informations d'une unite dans la souris
	public static Unite getUnit() {
		return unite;
	}
}
