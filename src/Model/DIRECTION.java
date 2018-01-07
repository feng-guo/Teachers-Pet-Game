package Model;

public enum DIRECTION {
	
	NORTH(0,1),
	EAST(1,0),
	SOUTH(0,-1),
	WEST(-1,0),
	;

	private int xChange, yChange;
	
	private DIRECTION(int xChange, int yChange) {
		this.xChange = xChange;
		this.yChange = yChange;
	}
	
	public int getXChange() {
		return xChange;
	}
	
	public int getYChange() {
		return yChange;
	}
}
