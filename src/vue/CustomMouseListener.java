package vue;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controlleur.Controlleur;
import model.BloqueurState;
import model.BomberState;
import model.CharpentierState;
import model.ForreurState;
import model.GrimpeurState;
import model.Lemming;
import model.MarcheurState;
import model.ParachutisteState;
import model.State;
import model.TunnelierState;
import model.Utils;

public class CustomMouseListener implements MouseListener  {

	private final Controlleur controlleur;
	private int x;
	private int y;
	
	public CustomMouseListener(Controlleur controlleur) {
		this.controlleur = controlleur;
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
				
		int[] new_coord = getTransformedCoordinatesFromClick(e.getX(), e.getY());
		
		if(new_coord.length != 2) {throw new ArrayIndexOutOfBoundsException();}
		
		x = new_coord[0];
		y = new_coord[1];
		
		for(Lemming lemming : controlleur.getLemmings()) {
			
			if(lemming.getX() == x && lemming.getY() == y) {
				
				
				State state;
				
				switch (controlleur.getLastStateClicked()) {
					case "MA"   : state = new MarcheurState(lemming); break;
					case "BLO"  : state = new BloqueurState(lemming); break; 
					case "TUN"  : state = new TunnelierState(lemming); break;
					case "FOR"  : state = new ForreurState(lemming); break;
					case "BO"   : state = new BomberState(lemming); break;
					case "CHAR" : state = new CharpentierState(lemming); break;
					case "GR"   : state = new GrimpeurState(lemming); break;
					case "PAR"  : state = new ParachutisteState(lemming); break;
					default     : state = new MarcheurState(lemming); break;
				}

				lemming.setState(state);
				
				break;
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	public int[] getTransformedCoordinatesFromClick(int x, int y) {
		
		int newX = x - (x % Utils.CELL_SIZE);
		int newY = y - (y % Utils.CELL_SIZE);
			
		return (new int[]{newX, newY});
	}
	

	public int getTransformedX() {
		return x;
	}	
	
	public int getTransformedY() {
		return y;
	}	
	
}
