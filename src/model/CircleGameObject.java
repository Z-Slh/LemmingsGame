package model;

import java.awt.Color;
import java.awt.Graphics;

public class CircleGameObject extends GameObject{

	public CircleGameObject(int p_x, int p_y, int p_width, int p_height, Color c) {
		super(p_x, p_y, p_width, p_height, c);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(getColor());
		g.fillOval(getX() + 1, getY() + 2, getWidth() - 2, getHeight() - 2);
	}

}
