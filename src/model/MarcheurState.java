package model;

import controlleur.Controlleur;

public class MarcheurState extends ClassicalState {

		
	public MarcheurState(Lemming lemming) {
		super(lemming);
	}

	protected int fallCounter = 0;
	protected int maxFallingHeight = 6;
	protected int maxClimbingHeight = 1;
	
	@Override
	public void collideBottomCell(Cell bottomCell) {
		
		if(noFloor()) {
			if (lemming.isClimbing()) {
				lemming.setDirX(lemming.getAutomaticWay());
				lemming.setDirY(Directions.NONE);
				lemming.setClimbing(false);
			} 
			
			else {
				lemming.fall();
			}
			
			fallCounter++;
			return;
		}

		if(fallCounter >= maxFallingHeight) {lemming.setNotAlive();return;}
		fallCounter = 0;		
		lemming.setDirY(Directions.NONE);
		lemming.setDirX(lemming.getAutomaticWay());
		
	}

	@Override
	public void collideTopCell(Cell topCell) {
		lemming.setDirY(Directions.DOWN);
		lemming.setClimbing(false);
		lemming.reverseAutomaticWay();
	}

	@Override
	public void collideSides(Cell cell) {
				
		if((getWallHeightFromCell(cell) <= maxClimbingHeight && cell.isDestructible())) {
			lemming.setClimbing(true);
			lemming.setDirY(Directions.UP);
			lemming.setDirX(Directions.NONE);
		}
		
		else {
			lemming.reverseAutomaticWay();
			lemming.setDirX(lemming.getAutomaticWay());
		}
	}
	
	private int getWallHeightFromCell(Cell c) {
						
		int counter = 0;
		int nextY = c.y - Utils.CELL_SIZE;
		int nextX = c.x;
		
		Controlleur controlleur = lemming.getControlleur();
		
		for (Cell cell : controlleur.getMap().values()) {
		
			if(cell.equals(c))continue;
			
			if(controlleur.searchForCell(nextX, nextY) != null) {
				counter++;
				nextY -= Utils.CELL_SIZE;
			}else {
				break;
			}
			
		}
		
		
		return counter+1;
	}
	
	protected boolean noFloor() {
		return (lemming.getCollidable().getBottomCell() == null);
	}
	
	@Override
	public void doAction() {
		doBasicAction();
	}

}
