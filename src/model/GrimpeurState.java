package model;

public class GrimpeurState extends MarcheurState {

	
	public GrimpeurState(Lemming lemming) {
		super(lemming);
	}

	
	@Override
	public void doAction() {
		maxClimbingHeight = Utils.NUMBER_OF_CELLS_IN_A_ROW;
		super.doAction();
	}

}
