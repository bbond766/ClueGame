package clueGame;

import java.util.*;

public class Board {
	private int numRows;
	private int numColumns;
	public final static int BOARD_SIZE = 20;
	private BoardCell board[][];
	private static Map<Character, String> rooms;
	private Map<BoardCell, LinkedList<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private String boardConfigFile;
	private String roomConfigFile;
	
	public void initialize(){
		
	}
	public void loadRoomConfig(String roomConfigFile){
		
	}
	public void loadBoardConfig(String filename){
		
	}
	public void calcAdjancies(){
		
	}
	public void calcTargets(int row, int col, int pathLength){
		
	}
	public BoardCell getCellAt(int row, int col){
		
		return null;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
