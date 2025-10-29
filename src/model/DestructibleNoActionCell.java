package model;

import controlleur.Controlleur;

public class DestructibleNoActionCell extends DestructibleCell {

	public DestructibleNoActionCell(Controlleur controlleur) {
		super(controlleur);
	}

	@Override
	public void doEffects(Cell cell) {
		return;
	}
	
}
