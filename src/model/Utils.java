package model;

public class Utils {
	
	public static final int JFRAME_WIDTH   = 720;
	public static final int JFRAME_HEIGHT  = 720;
	public static final int CELL_SIZE = 30;
	public static final int ALL_CELL_SIZE  = (JFRAME_WIDTH*JFRAME_HEIGHT)/CELL_SIZE;
	public static final int NUMBER_OF_CELLS_IN_A_LINE = JFRAME_WIDTH/CELL_SIZE;
	public static final int NUMBER_OF_CELLS_IN_A_ROW  = JFRAME_HEIGHT/CELL_SIZE;;
	public static final int FLOOR_START = NUMBER_OF_CELLS_IN_A_LINE - 2;

}
