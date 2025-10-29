package model;

public class TunnelierState extends MarcheurState{

	public TunnelierState(Lemming lemming) {
		super(lemming);
	}

	@Override
	public void collideSides(Cell cell) {
		cell.destruct();
	}
	
	@Override
	public void doAction() {
		super.doAction();
	}
	
}
