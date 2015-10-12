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
	private static Map<Character, String> rooms;
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
		boardConfigFile = "ClueLayout.csv";
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
			String cvsSplitBy = ",";
			br = new BufferedReader(new FileReader(this.roomConfigFile));
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
			int row, column;
			char initial, dd;
			boolean isWalkway, isDoorway;
			DoorDirection doorDirection = DoorDirection.UP;
			String cvsSplitBy = ",";
			String[] id;
			br = new BufferedReader(new FileReader(this.boardConfigFile));
			while ((line = br.readLine()) != null) {
				id = line.split(cvsSplitBy);
//				if (line.length() > NUM_COLUMNS){		//check me
//					throw new BadConfigFormatException("The number of columns in the input config file for the room layout does not match NUM_COLUMNS");
//				}
				for (int i = 0; i < NUM_ROWS; i++){
					for (int j = 0; j < NUM_COLUMNS; j++){
						initial = id[j].charAt(0);
						isDoorway = false;
						if (id[j].length() > 1){
							isDoorway = true;
							dd = id[j].charAt(1);
							switch (dd){
							case 'U':
								doorDirection = DoorDirection.UP;
								break;
							case 'D':
								doorDirection = DoorDirection.DOWN;
								break;
							case 'L':
								doorDirection = DoorDirection.LEFT;
								break;
							case 'R':
								doorDirection = DoorDirection.RIGHT;
								break;
							}
						}
						if (initial == 'W')
							isWalkway = true;
						else
							isWalkway = false;

						BoardCell bc = new BoardCell(i, j, initial, isWalkway, isDoorway, doorDirection);
						board[i][j] = bc;
						}
					}
				}
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();	
		}
	}
	
	public void loadBoardConfig(String filename) throws BadConfigFormatException, FileNotFoundException {
			this.boardConfigFile = filename;
			loadBoardConfig();
	}
	public void calcAdjacencies(){
		
	}
	public void calcTargets(int row, int col, int pathLength){
		
	}
	public BoardCell getCellAt(int row, int col){
		return board[row][col];
	}
	
	public LinkedList<BoardCell> getAdjList(int i, int j) {
		return null;
	}
	public Set<BoardCell> getTargets() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		Board board = new Board();
		board.initialize();

	}

}
