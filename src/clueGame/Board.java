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
	
	public Board(){
		
	}
	public Board(String roomConfigFile, String BoardConfigFile){
		
	}
	
	public void initialize(){
		/*try{
			
		}
		catch(); */
	}
	public Map<Character, String> getRooms(){
		return rooms;
	}
	public int getNumRows(){
		return numRows;
	}

	public int getNumColumns(){
		return numColumns;
	}

	//NONPARAMETERIZED VERSION OF THIS FUNCTION NEEDED ACCORDING TO ClueGameTests IMPLEMENTATION
	public void loadRoomConfig(){
		
	}
	public void loadRoomConfig(String roomConfigFile){
		//to be used in CR_FileInitTests unit tests
	}
	//NONPARAMETERIZED VERSION OF THIS FUNCTION NEEDED ACCORDING TO ClueGameTests IMPLEMENTATION
	public void loadBoardConfig(){
		
	}
	public void loadBoardConfig(String filename){
		//to be used in CR_FileInitTests unit tests		
	}
	public void calcAdjacencies(){
		
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
