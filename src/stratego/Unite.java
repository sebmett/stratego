package stratego;

import javax.swing.ImageIcon;

public class Unite {
	private int puissance;
	private String description;
	private ImageIcon image;
	private String chemin;

	public Unite(int puissance, String description, String chemin) {
		this.puissance = puissance;
		this.description = description;
		this.chemin = chemin;
		this.image = new ImageIcon(chemin);
	}

	public int getPuissance() {
		return puissance;
	}

	public String getChemin() {
		return chemin;
	}

	public String getDescription() {
		return description;
	}

	public ImageIcon getImage() {
		return image;
	}
}
