package model;

import java.awt.Color;
import java.util.Map;

import controlleur.Controlleur;

public class BlockConstructor {

	private Controlleur controlleur;
	private Map<String, Cell> map; 
	private FactoryCellType factoryCellType = new FactoryCellType();
	
	public BlockConstructor(Map<String, Cell> map, Controlleur controlleur ) {
		this.map = map;
		this.controlleur = controlleur;
	}
	
	
	private void construct (int cell_X, int cell_Y, int longeur, int largeur, CellType type, Color color ) {
		
		int startX = cell_X * Utils.CELL_SIZE;
		int startY = cell_Y * Utils.CELL_SIZE;
		int passed_rows = 0;
		
		for (int i = startX, j = 0 ; j < ((longeur*largeur) * Utils.CELL_SIZE); i+=(Utils.CELL_SIZE), j+=(Utils.CELL_SIZE)  ) {
			
			if(i == startX + longeur * Utils.CELL_SIZE) {i = startX; passed_rows++;}
						
			int x_pos = i;
			int y_pos = startY + (passed_rows * Utils.CELL_SIZE);
						
			
			String key = controlleur.stringifyAndConcat(x_pos, y_pos);

			Cell cell = new Cell(x_pos, y_pos, Utils.CELL_SIZE, Utils.CELL_SIZE, color, type);

			map.put(key, cell);
						
		}		
	
	}	
	
	
	public void construct(int[][] array, CellType.Type type, Color color) {	
		
		for(int[] arr : array) {
			construct(arr[0], arr[1], arr[2], arr[3], factoryCellType.get(type, controlleur) , color);
		}
	
	}
	
	public void construct(int x, int y, CellType.Type type, Color color) {
		construct(x, y, 1, 1, factoryCellType.get(type, controlleur), color);
	}
	
	public void construct(int x, int y, int width, int height, CellType.Type type, Color color) {
		construct(x, y, width, height, factoryCellType.get(type, controlleur), color);
	}

}
