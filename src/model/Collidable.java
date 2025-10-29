package model;

public class Collidable {

	Cell[] cells = new Cell[4];
	
	public void setCells(Cell[] cells) {
		this.cells = cells;
	}
	
	public Cell[] getAllCells() {
		return cells;
	}

	public Cell getRightCell() {
		return cells[0];
	}
	
	public Cell getLeftCell() {
		return cells[1];
	}
	
	public Cell geTopCell() {
		return cells[2];
	}
	
	public Cell getBottomCell() {
		return cells[3];
	}
	
}