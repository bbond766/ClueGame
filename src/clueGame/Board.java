package clueGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Board {
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 23;
	private BoardCell board[][];
	private Map<Character, String> rooms;
	private Map<BoardCell, LinkedList<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private String boardConfigFile;
	private String roomConfigFile;
	String[] id;
	
	
	public Board(){
		board = new BoardCell[NUM_ROWS][NUM_COLUMNS];
		rooms = new HashMap<Character, String>();
		adjMatrix = new HashMap<BoardCell, LinkedList<BoardCell>>();
		targets = new HashSet<BoardCell>();
		boardConfigFile = "ClueLayout.csv";		//if set to "", very large result error; check error checking for FNFE
		roomConfigFile = "ClueLegend.txt";
	}
	public Board(String roomConfigFile, String BoardConfigFile){
		board = new BoardCell[NUM_ROWS][NUM_COLUMNS];
		rooms = new HashMap<Character, String>();
		adjMatrix = new HashMap<BoardCell, LinkedList<BoardCell>>();
		targets = new HashSet<BoardCell>();
		boardConfigFile = BoardConfigFile;
		this.roomConfigFile = roomConfigFile;
	}
	
	public void initialize(){
		try{
			loadRoomConfig();
			loadBoardConfig();
		}
		catch(BadConfigFormatException e){
			System.out.println(e.getMessage());
		}
		catch(FileNotFoundException fnfe){
			System.out.println(fnfe.getMessage());
		}
	}
	public Map<Character, String> getRooms(){
		return rooms;
	}
	public int getNumRows(){
		return NUM_ROWS;
	}

	public int getNumColumns(){
		return NUM_COLUMNS;
	}

	//NONPARAMETERIZED VERSION OF THIS FUNCTION NEEDED ACCORDING TO ClueGameTests IMPLEMENTATION	(C, Conservatory, Card)
	public void loadRoomConfig()throws BadConfigFormatException, FileNotFoundException{
		BufferedReader br;
		try{
			String line = new String();
			char abbreviation;
			String name = new String();
			String type = new String();
			String cvsSplitBy = (",[ ]*");
			br = new BufferedReader(new FileReader(roomConfigFile));
			while ((line = br.readLine()) != null) {
				if (line.length() > NUM_COLUMNS){
					throw new BadConfigFormatException("The number of columns in the input config file for the room layout does not match NUM_COLUMNS");
				}
				id = line.split(cvsSplitBy);// use comma as separator
				abbreviation = id[0].charAt(0);
				name = id[1];
				type = id[2];
				rooms.put(abbreviation, name);
			}
			br.close();
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();	
		} 
	}
	public void loadRoomConfig(String roomConfigFile)throws BadConfigFormatException, FileNotFoundException{
		this.roomConfigFile = roomConfigFile;
		loadRoomConfig();
	}
//	NONPARAMETERIZED VERSION OF THIS FUNCTION NEEDED ACCORDING TO ClueGameTests IMPLEMENTATION
	public void loadBoardConfig() throws BadConfigFormatException, FileNotFoundException{
		BufferedReader br;
		try{
			String line = new String();
			char init, dd;
			DoorDirection doorDirection = null;
			boolean isWalkway, isDoorway;
			String cvsSplitBy = ",";
			String[] id;
			br = new BufferedReader(new FileReader(boardConfigFile));
			for (int i = 0; i < NUM_ROWS; i++){
				line = br.readLine();
				id = line.split(cvsSplitBy);
				if (id.length > NUM_COLUMNS){		
					throw new BadConfigFormatException("The number of columns in the input config file for the room layout does not match NUM_COLUMNS");
				}
					for (int j = 0; j < NUM_COLUMNS; j++){
						init = id[j].charAt(0);
						isDoorway = false;
						if (id[j].length() > 1){
							if (id[j].charAt(1) == 'L'){
								isDoorway = true;
								doorDirection = DoorDirection.LEFT;
							}
							else if (id[j].charAt(1) == 'R'){
								isDoorway = true;
								doorDirection = DoorDirection.RIGHT;
							}
							else if (id[j].charAt(1) == 'U'){
								isDoorway = true;
								doorDirection = DoorDirection.UP;
							}
							else if (id[j].charAt(1) == 'D'){
								isDoorway = true;
								doorDirection = DoorDirection.DOWN;
							}
						}
						if (init == 'W'){
							isWalkway = true;
						}
						else{
							isWalkway = false;
						}
						BoardCell bc = new BoardCell(i, j, init, isWalkway, isDoorway, doorDirection);
						board[i][j] = bc;
						}
					}
		//		}
			br.close();
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();	
		}
	}
	
	public void loadBoardConfig(String filename) throws BadConfigFormatException, FileNotFoundException {
			boardConfigFile = filename;
			loadBoardConfig();
	}
	public void calcAdjacencies(){		
		for(int i = 0; i<NUM_ROWS; i++){
			for(int j =0; j<NUM_COLUMNS; j++){
				LinkedList<BoardCell> adj = new LinkedList<BoardCell>();
				BoardCell adjCell = board[i][j];
				BoardCell nextCell = new BoardCell();
				
				if(adjCell.isRoom() == false){

					if (adjCell.getRow() - 1 >= 0){
						nextCell = board[i - 1][j];
						if (checkIfValidAdjCell(nextCell) == true)
							adj.add(nextCell);
					}
					if (adjCell.getRow() + 1 < NUM_COLUMNS){
						nextCell = board[i + 1][j];
						if (checkIfValidAdjCell(nextCell) == true)
							adj.add(nextCell);
					}
					if (adjCell.getCol() - 1 >= 0){
						nextCell = board[i][j - 1];
						if (checkIfValidAdjCell(nextCell) == true)
							adj.add(nextCell);
					}
					if (adjCell.getCol() + 1 < NUM_ROWS){
						nextCell = board[i][j + 1];
						if (checkIfValidAdjCell(nextCell) == true)
							adj.add(nextCell);
					}
				}
				else{
					
				}
				adjMatrix.put(adjCell,adj);
			}
		}
	}
	
	public boolean checkIfValidAdjCell(BoardCell bc){
		if(bc.isWalkway() == true || bc.isDoorway() == true){
			return true;
		}
		return false;
	}
	
	public void calcTargets(int row, int col, int pathLength){
		
	}
	public BoardCell getCellAt(int row, int col){
		System.out.println("row, col, initial" + board[row][col].getInitial());
		return board[row][col];
	}
	
	public LinkedList<BoardCell> getAdjList(int i, int j) {
		//get linked list from adjMatrix map 
		BoardCell bc = board[i][j];
		return adjMatrix.get(bc);
	}
	public Set<BoardCell> getTargets() {
		return targets;
	}
	
	public static void main(String[] args) {
		Board boardHere = new Board();
		boardHere.initialize();
	}

}
