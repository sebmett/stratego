package stratego;

public class Stratego {
	Sons begin = new Sons("file:trompette.wav");

	public Stratego() {
		EcranTitre test = new EcranTitre();
		test.setLocationRelativeTo(null);
		test.setVisible(true);
		begin.playSound();
	}

	public static void main(String[] args) {

		new Stratego();

	}

}
