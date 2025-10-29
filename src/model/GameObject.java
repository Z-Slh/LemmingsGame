package model;

import java.awt.Color;
import java.awt.Graphics;

public abstract class GameObject  {

	protected int x, y, width, height;

	private Color color;

	public GameObject(int p_x, int p_y, int p_width, int p_height, Color c) {
		x = p_x;
		y = p_y;
		width = p_width;
		height = p_height;
		color = c;
	}

	public abstract void render(Graphics g);
	
	public int getX() {
		return x;
	}

	public void setX(int p_x) {
		x = p_x;
	}

	public int getY() {
		return y;
	}

	public void setY(int p_y) {
		y = p_y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setColor(Color c) {
		color = c;
	}

	@Override
	public boolean equals(Object obj) {
    	if (this == obj) return true;
    	if (obj == null || getClass() != obj.getClass()) return false;
    	GameObject that = (GameObject) obj;
    	return x == that.x && y == that.y;
	}
	
}