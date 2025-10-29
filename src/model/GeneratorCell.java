package model;

import java.awt.Color;

import controlleur.Controlleur;

public class GeneratorCell extends DestructibleCell {

	public GeneratorCell(Controlleur controlleur) {
		super(controlleur);
	}


	@Override
	public void doEffects(Cell cell) {
		controlleur.getBlockConstructor().construct(1, 12, 1, 2, CellType.Type.DestructibleNoActionCell, Color.BLACK);		
	}

}
