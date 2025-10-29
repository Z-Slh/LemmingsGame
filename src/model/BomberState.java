package model;

import java.util.ArrayList;
import java.util.List;

import controlleur.Controlleur;

public class BomberState extends MarcheurState {

	
	protected int bombeurStepsCounter = 0;
	protected static final int actionRadius = 2;
	protected List<Cell> cellsToRemove = new ArrayList<>();
	
	public BomberState(Lemming lemming) {
		super(lemming);
	}

	
	
	@Override
	public void doAction() {
		
		super.doAction();	
		
		bombeurStepsCounter++;

		if( bombeurStepsCounter < 3) {return;}

		Controlleur controlleur = lemming.getControlleur();
		
		int startX = lemming.x - actionRadius * Utils.CELL_SIZE;
		int startY = lemming.y - actionRadius * Utils.CELL_SIZE;
		
		int longeur = 2 * actionRadius + 1; 
		int largeur = longeur;


		int passed_rows = 0;		
		for (int i = startX, j = 0 ; j < ((longeur*largeur) * Utils.CELL_SIZE); i+=(Utils.CELL_SIZE), j+=(Utils.CELL_SIZE)) {
			
			if(i >= startX + longeur * Utils.CELL_SIZE) {i = startX; passed_rows++;}
						
			int x_pos = i;
			int y_pos = startY + (passed_rows * Utils.CELL_SIZE);
			
			Cell cell = controlleur.searchForCell(x_pos,y_pos);
			
			if(cell != null) {cell.destruct();}
		}
		
		lemming.setState(new MarcheurState(lemming));
		
	}
	
	
}
