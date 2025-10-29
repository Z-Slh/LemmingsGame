package model;

import java.awt.Color;
import java.awt.Graphics;

public class RectGameObject extends GameObject{

	public RectGameObject(int p_x, int p_y, int p_width, int p_height, Color c) {
		super(p_x, p_y, p_width, p_height, c);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(this.getColor());
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	

}
