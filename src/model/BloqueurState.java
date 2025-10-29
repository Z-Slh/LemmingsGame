package model;

import java.awt.Color;

import controlleur.Controlleur;

public class BloqueurState extends State {
	
	
	public BloqueurState(Lemming lemming) {
		super(lemming);
	}

	
	@Override
	public void doAction() {

		Controlleur controlleur = lemming.getControlleur();
		lemming.stop();
		int x = lemming.getX();
		int y = lemming.getY();		
		controlleur.getBlockConstructor().construct(x/Utils.CELL_SIZE, y/Utils.CELL_SIZE, CellType.Type.NotDestructibleCell, Color.BLACK);
		controlleur.addCellToRemove(controlleur.searchForCell(x, y));
	
	}

}
