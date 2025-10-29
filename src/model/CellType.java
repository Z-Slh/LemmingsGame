package model;

public interface CellType {
	
	public enum Type {
		DestructibleNoActionCell, DestructibleCell, GeneratorCell, LemmingRemoverCell,
		NotDestructibleCell, 
	}
	
	public boolean isDestructible();
	public void destruct(Cell cell);
}

