package vue;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class CustomJFrame extends JFrame {	
	
	public CustomJFrame (String name, Gameplay panel) {
		
		super(name);
				
		setContentPane(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setFocusable(true);
		pack();
		
		Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dimensions.width/2  - getSize().width /2, dimensions.height/2 - getSize().height/2 - 21);

	}
	
	
}
