package model;

public class ParachutisteState extends MarcheurState {

	private int counter = -1;
	private int lastXdirection;
	private int lastYdirection;
	
	public ParachutisteState(Lemming lemming) {
		super(lemming);
		maxFallingHeight = Utils.JFRAME_HEIGHT;
	}

	@Override
	public void doAction() {
				
		counter = (counter+1)%2;
		if(noFloor() && (counter == 0)) {
			lastXdirection = lemming.getDirX();
			lastYdirection = lemming.getDirY();
			lemming.stop();
			return;
		}
	
		lemming.setDirX(lastXdirection);
		lemming.setDirY(lastYdirection);
		
		super.doAction();
		
	}

}
