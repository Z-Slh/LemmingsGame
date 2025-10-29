package model;

import controlleur.Controlleur;

public abstract class DestructibleCell implements CellType{
		
	protected Controlleur controlleur;

	public DestructibleCell(Controlleur controlleur) {
		this.controlleur = controlleur;
	}
		
	@Override
	public boolean isDestructible() {
		return true;
	}
	
	@Override
	public void destruct(Cell cell) {
		doEffects(cell);
		controlleur.addCellToRemove(cell);
	};	
	
	public abstract void doEffects(Cell cell);
	
}
