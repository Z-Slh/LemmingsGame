package model;

import controlleur.Controlleur;

public class FactoryCellType {
	
	public CellType get(CellType.Type type, Controlleur controlleur ) {
		
		CellType c = null;
		
		switch (type) {
			case DestructibleNoActionCell : c = new DestructibleNoActionCell(controlleur); 	break;
			case NotDestructibleCell      : c = new NotDestructibleCell();                 	break;
			case GeneratorCell            : c = new GeneratorCell(controlleur);            	break;
			case DestructibleCell         : c = new DestructibleNoActionCell(controlleur); 	break;
			case LemmingRemoverCell       : c = new LemmingsRemoverCell(controlleur);      	break;
			
			default:break;
		}
	
		return c;
	}

}
