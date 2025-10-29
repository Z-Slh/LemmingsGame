package model;

public class ForreurState extends MarcheurState {

	
	protected int cellRemovedByForreurCounter = 0;
	
	public ForreurState(Lemming lemming) {
		super(lemming);
	}
	
	@Override
	public void collideBottomCell(Cell bottomCell) {
		
		if(noFloor()) {
			return;
		}
		
		if(cellRemovedByForreurCounter >= 5) {
			super.collideBottomCell(bottomCell); 
			return;
		}
		
		bottomCell.destruct();
		
		cellRemovedByForreurCounter++;
		
		lemming.setDirY(Directions.DOWN);
		lemming.setDirX(Directions.NONE);
	}
	
	
	@Override
	public void doAction() {
		super.doAction();
	}
	
	
}
