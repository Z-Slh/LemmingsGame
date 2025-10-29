package model;

public abstract class ClassicalState extends State  {
	
	public ClassicalState(Lemming lemming) {
		super(lemming);
	}

	public abstract void collideBottomCell(Cell bottomCell);
	public abstract void collideTopCell(Cell topCell);
	public abstract void collideSides(Cell cell);
	public abstract void doAction();
	
	public void doBasicAction() {
				
		Collidable collidable = lemming.getCollidable();
		
		
		Cell topCell = collidable.geTopCell();
		if((lemming.getDirY() == Directions.UP) && topCell != null) collideTopCell(topCell);
				
		collideBottomCell(collidable.getBottomCell());
		
		Cell rightCell = collidable.getRightCell();
		if((lemming.getDirX() == Directions.RIGHT) && rightCell != null) collideSides(rightCell);
		
		Cell leftCell = collidable.getLeftCell();
		if((lemming.getDirX() == Directions.LEFT) && leftCell != null) collideSides(leftCell);
		
	}
	
}