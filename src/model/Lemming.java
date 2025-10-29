package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import controlleur.Controlleur;

public class Lemming extends RectGameObject implements Observable{
		
	private Controlleur controlleur;
	
	private List<Observer> observers = new ArrayList<>();	
	
	private State state = new MarcheurState(this);
	
	private boolean isAlive = true;
	private boolean gotTeleported = false;
	private boolean isClimbing = false;
	private int index;
	
	private int automaticWay = Directions.RIGHT;
	private int dirX = Directions.NONE;
	private int dirY = Directions.DOWN;
	

	
	Collidable collidable = new Collidable();

	public Lemming(int x, int y, int width, int height, Color c, Controlleur controlleur, int index) {
		super(x, y, width, height, c);
		this.controlleur = controlleur;
		this.index = index;
	}

	public boolean doAction() {
				
		checkTeleportation();
		
		if (touchFloor() || touchExits()) {
			setNotAlive();
		}
		
		if (reachedLimits()) {  
			reverseAutomaticWay(); 
			setDirX(getAutomaticWay());  
		}
			
		
		Cell topCell    = controlleur.getFromMap(this.x, this.y - Utils.CELL_SIZE);
		
		Cell bottomCell = controlleur.getFromMap(this.x, this.y + Utils.CELL_SIZE);
		
		Cell rightCell  = controlleur.getFromMap(this.x + Utils.CELL_SIZE, this.y);
		
		Cell leftCell   = controlleur.getFromMap(this.x - Utils.CELL_SIZE, this.y);
		
		
		Cell[] cells = {rightCell, leftCell, topCell, bottomCell};
		collidable.setCells(cells);
		
		state.doAction();
		move();
		notifyObservers();
		
		return true;
		
	}
	

	boolean checkTeleportation() {
		if (gotTeleported) {
			return false; 
		}
	
		for (GameObject g : controlleur.getTeleporters()) {
			if (isIn(g)) {
				if (controlleur.getTeleporters().size() <= 1) {
					return false; 
				}
	
				GameObject teleporter;
				do {
					teleporter = controlleur.getRandomGameObjectFromList(controlleur.getTeleporters());
				} while (teleporter.equals(g)); 

				fall(); 
				this.x = teleporter.getX();
				this.y = teleporter.getY();
	
				gotTeleported = true;
				return true;
			}
		}
	
		return false;
	}
	

	public boolean touchExits() {
		
		for(GameObject g : controlleur.getExits()) {
			
			if(isIn(g)) {
				return true;
			}
		}
		
		return false;
	}

	private boolean isIn(GameObject g) {
		return (this.x == g.getX() && this.y == g.getY());
	}

	boolean touchFloor() {
		return ( (this.y + this.height) == Utils.FLOOR_START * Utils.CELL_SIZE );
	}

	private void move() {
		this.x += ((dirX) * Utils.CELL_SIZE);
		this.y += ((dirY) * Utils.CELL_SIZE);
	}

	boolean reachedLimits() {
		return (((this.x + this.width) == Utils.JFRAME_WIDTH) || ((this.x == 0) && dirX == -1)) ;
	}

	public void reverseAutomaticWay() {
		automaticWay = automaticWay * (-1);
	}

	public void fall() {
		dirY = 1;
		dirX = 0;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public State getState() {
		return state;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setNotAlive() {
		isAlive = false;
	}
	
	public void setDirX(int direction) {
		dirX = direction;
	}
	
	public void setDirY(int direction) {
		dirY = direction;
	}
	
	public int getDirX() {
		return dirX;
	}

	public int getDirY() {
		return dirY;
	}
	
	public int getAutomaticWay() {
		return automaticWay;
	}

	public void setAutomaticWay(int automaticWay) {
		this.automaticWay = automaticWay;
	}
	
	public Collidable getCollidable() {
		return collidable;
	}
	
	public void stop() {
		dirX = Directions.NONE;
		dirY = Directions.NONE;
	}
	
	public boolean isClimbing() {
		return isClimbing;
	}
	
	@Override
	public void register(Observer o) {
		observers.add(o);
	}

	@Override
	public void unregister(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update();
		}
	}

	public Controlleur getControlleur() {
		return controlleur;
	}

	public void setClimbing(boolean b) {
		isClimbing = b;
	}

	public int getIndex() {
		return index;
	}
	
}