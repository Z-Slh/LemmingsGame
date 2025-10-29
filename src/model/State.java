package model;

public abstract class State{
	
	protected Lemming lemming;
		
	public State(Lemming lemming) {
		this.lemming = lemming;
	}

	public abstract void doAction();
	
}


