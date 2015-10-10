package clueGame;

public class BoardCell {
	private int row;
	private int column;
	private char initial;
	
	public char getInitial(){
		return initial;
	}
	public boolean isWalkway(){
		return false;
	}
	public boolean isRoom(){
		return false;
	}
	public boolean isDoorway(){
		return false;
	}
	
	
}
