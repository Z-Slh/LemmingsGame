package model;

import controlleur.Controlleur;

public class LemmingsRemoverCell extends DestructibleCell{

	public LemmingsRemoverCell(Controlleur controlleur) {
		super(controlleur);
	}
	
	@Override
	public void doEffects(Cell cell) {
				
		for(Lemming lemming : controlleur.getLemmings()) {
			
			if((isBetween(lemming.getX(), cell.getX() - Utils.CELL_SIZE, cell.getX() + cell.width + Utils.CELL_SIZE))  
			 ||(isBetween(lemming.getY(), cell.getY() - Utils.CELL_SIZE, cell.getY() + cell.height + Utils.CELL_SIZE))) {
				lemming.setNotAlive();				
			}
			
		}
	}
	
	private boolean isBetween(int n, int min, int max) {
		if(n >= min && n <= max) {
			return true;
		}
		return false;
	}
	
}

