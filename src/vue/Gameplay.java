package vue;

import controlleur.Controlleur;
import model.*;

import java.awt.*;


import javax.swing.*;

public class Gameplay extends JPanel implements Observer {

	private Controlleur controlleur;
	private JButton[] buttonsList = new JButton[9];
	private JPanel jPanelForButtons = new JPanel();

	private JLabel inGameLabel = new JLabel("Lemmings in game: 0");
	private JLabel deadLabel = new JLabel("Lemmings dead: 0");
	private JLabel escapedLabel = new JLabel("Lemmings escaped: 0");

	public Gameplay() {
		super();

		setPreferredSize(new Dimension(Utils.JFRAME_WIDTH, Utils.JFRAME_HEIGHT));
		jPanelForButtons.setPreferredSize(
			new Dimension(Utils.JFRAME_WIDTH, Utils.JFRAME_HEIGHT - (Utils.CELL_SIZE * Utils.FLOOR_START))
		);
		jPanelForButtons.setLayout(new GridBagLayout());

		setButtons();

		JPanel counterPanel = new JPanel();
		counterPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); 
		counterPanel.add(inGameLabel);
		counterPanel.add(deadLabel);
		counterPanel.add(escapedLabel);
		counterPanel.setOpaque(false); 

		setLayout(new BorderLayout());
		add(counterPanel, BorderLayout.NORTH); 
		add(jPanelForButtons, BorderLayout.SOUTH); 
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	private void draw(Graphics g) {
		drawMap(g);
		drawViewables(g);
		drawLemmings(g);
	}

	private void drawViewables(Graphics g) {
		controlleur.getViewables().forEach((list) -> {
			list.forEach((gameobject) -> gameobject.render(g));
		});
	}

	private void drawLemmings(Graphics g) {
		for (Lemming lemming : controlleur.getLemmings()) {
			lemming.render(g);
		}
	}

	private void drawMap(Graphics g) {
		for (Cell cell : controlleur.getMap().values()) {
			cell.render(g);
		}

		for (int i = 0; i < Utils.JFRAME_WIDTH; i += Utils.CELL_SIZE) {
			g.drawLine(0, i, Utils.JFRAME_WIDTH, i);
			g.drawLine(i, 0, i, Utils.JFRAME_HEIGHT);
		}
	}

	public void setControlleur(Controlleur controlleur) {
		this.controlleur = controlleur;
	}

	private void setButtons() {
		String[] names = { "*", "MA", "BLO", "TUN", "FOR", "BO", "CHAR", "GR", "PAR" };

		for (int i = 0; i < buttonsList.length; i++) {
			JButton btn = new JButton(names[i]);
			btn.setFont(new Font("Arial", Font.PLAIN, 13));
			jPanelForButtons.add(btn);
			buttonsList[i] = btn;
		}

		jPanelForButtons.setBackground(Color.GREEN);
	}

	public JButton[] getButtons() {
		return buttonsList;
	}

	public void updateCounters(int inGame, int dead, int escaped) {
		inGameLabel.setText("Lemmings in game: " + inGame);
		deadLabel.setText("Lemmings dead: " + dead);
		escapedLabel.setText("Lemmings escaped: " + escaped);
	}

	@Override
	public void update() {
		repaint();
	}
}
