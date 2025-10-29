package model;

import java.awt.Color;

public class Cell extends RectGameObject {

	private CellType type;
	

	public Cell(int x, int y, int width, int height, Color c, CellType type) {
		super(x, y, width, height, c);
		this.type = type;
	}

	public void setType(CellType type) {
		this.type = type;
	}

	public CellType getType() {
		return type;
	}
	
	public boolean isDestructible() {
		return type.isDestructible();
	}

	public void destruct() {
		type.destruct(this);
	}
	
}