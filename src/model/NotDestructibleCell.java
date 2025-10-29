package model;

public class NotDestructibleCell implements CellType {

	@Override
	public boolean isDestructible() {
		return false;
	}

	@Override
	public void destruct(Cell cell) {
		return;
	}

}
