package clueGame;

public class BoardCell {
	private int row;
	private int column;
	private char initial;
	private boolean isWalkway;
	private boolean isDoorway;
	private DoorDirection doorDirection;
	
	BoardCell(){
		isWalkway = false;
		isDoorway = false;
	}
	
	BoardCell(int row, int col, char initial, boolean isWalk, boolean isDoorway, DoorDirection dd){
		this.row = row;
		this.column = col;
		this.initial = initial;
		this.isWalkway = isWalk;
		this.isDoorway = isDoorway;
		this.doorDirection = dd;
	}
	
	public DoorDirection getDoorDirection(){
		return this.doorDirection;
	}
	public int getRow(){
		return this.row;
	}
	public char getInitial(){
		return this.initial;
	}
	public boolean isWalkway(){
		return this.isWalkway;
	}
	public boolean isRoom(){
		return !this.isWalkway;
	}
	public boolean isDoorway(){
		return this.isDoorway;
	}
	
	
}
