package model;

import java.awt.Color;

import controlleur.Controlleur;

public class CharpentierState extends MarcheurState {

	private final int stairsNumber = 5;
	
	public CharpentierState(Lemming lemming) {
		super(lemming);
	}

	@Override
	public void doAction() {

		super.doAction();
		
		if(lemming.getDirX() == Directions.NONE) return;
		
		Controlleur controlleur = lemming.getControlleur();
		
		int factor = (lemming.getDirX() == Directions.RIGHT) ? 1 : ((lemming.getDirX() == Directions.LEFT) ? -1 : 0);
		
		for (int i = 0; i < stairsNumber ; i++) {
		
			int newX = lemming.x + factor * 2 * lemming.width + factor * i * Utils.CELL_SIZE;			
			int newY = lemming.y -  i * Utils.CELL_SIZE;
			
			controlleur.getBlockConstructor().construct(newX/Utils.CELL_SIZE, newY/Utils.CELL_SIZE, CellType.Type.DestructibleNoActionCell, Color.BLACK);
		}
		
		
		lemming.setState(new MarcheurState(lemming));
	}

}
