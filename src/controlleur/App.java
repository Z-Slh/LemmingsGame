package controlleur;

import vue.CustomJFrame;
import vue.Gameplay;

public class App {

	
	public static void main(String[] args) {
		
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Gameplay gameplay = new Gameplay();
            	new CustomJFrame("Game", gameplay);
            	new Controlleur(gameplay);             	
            }
        });

	}
	
}
