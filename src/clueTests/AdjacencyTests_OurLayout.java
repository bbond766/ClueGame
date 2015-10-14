package clueTests;

import java.util.LinkedList;
import java.util.Set;

//Doing a static import allows me to write assertEquals rather than
//assertEquals
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class AdjacencyTests_OurLayout {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 23;
	
	@BeforeClass
	public static void setUp() {
		board = new Board("Clue_Map.csv", "ClueLegend.txt");
		board.initialize();
	}

	// Ensure that player does not move around within room
	// These cells are ORANGE on the planning spreadsheet
	@Test
	public void testAdjacenciesInsideRooms()
	{
		// Test a corner
		LinkedList<BoardCell> testList = board.getAdjList(1, 1);
		assertEquals(0, testList.size());
		// Test one that has walkway underneath
		testList = board.getAdjList(8, 1);
		assertEquals(0, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(16, 2);
		assertEquals(0, testList.size());
		// Test one that is in middle of room
		testList = board.getAdjList(8, 22);
		assertEquals(0, testList.size());
		// Test one beside a door
		testList = board.getAdjList(2, 15);
		assertEquals(0, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(18, 10);
		assertEquals(0, testList.size());
	}

	// Ensure that the adjacency list from a doorway is only the
	// walkway. NOTE: This test could be merged with door 
	// direction test. 
	// These tests are PURPLE on the planning spreadsheet
	@Test
	public void testAdjacencyRoomExit()
	{
		// TEST DOORWAY RIGHT 
		LinkedList<BoardCell> testList = board.getAdjList(11, 5);
;
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(11, 6)));
		// TEST DOORWAY LEFT 
		testList = board.getAdjList(17, 12);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(17, 11)));
		//TEST DOORWAY DOWN
		testList = board.getAdjList(4, 12);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(5, 12)));
		//TEST DOORWAY UP
		testList = board.getAdjList(16, 0);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(15, 0)));
		
	}
	
	// Test adjacency at entrance to rooms
	// These tests are GREEN in planning spreadsheet
	@Test
	public void testAdjacencyDoorways()
	{
		// Test beside a door direction RIGHT
		LinkedList<BoardCell> testList = board.getAdjList(6, 5);
		assertTrue(testList.contains(board.getCellAt(5, 5)));
//		assertTrue(testList.contains(board.getCellAt(6, 5)));
		assertTrue(testList.contains(board.getCellAt(6, 6)));
		assertTrue(testList.contains(board.getCellAt(6, 4)));
		assertEquals(4, testList.size());
		// Test beside a door direction DOWN
		testList = board.getAdjList(5, 11);
		assertTrue(testList.contains(board.getCellAt(5, 12)));
		assertTrue(testList.contains(board.getCellAt(5, 10)));
		assertTrue(testList.contains(board.getCellAt(6, 11)));
		assertTrue(testList.contains(board.getCellAt(4, 11)));
		assertEquals(4, testList.size());
		// Test beside a door direction LEFT
		testList = board.getAdjList(14, 15);
		assertTrue(testList.contains(board.getCellAt(15, 15)));
		assertTrue(testList.contains(board.getCellAt(13, 15)));
		assertTrue(testList.contains(board.getCellAt(14, 16)));
		assertTrue(testList.contains(board.getCellAt(14, 14)));
		assertEquals(4, testList.size());
		// Test beside a door direction UP
		testList = board.getAdjList(9, 0);
//		assertTrue(testList.contains(board.getCellAt(8, 0)));
		assertTrue(testList.contains(board.getCellAt(9, 1)));
		assertEquals(2, testList.size());
	}

	// Test a variety of walkway scenarios
	// These tests are LIGHT PURPLE on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways()
	{
		// Test on left edge of board, just one walkway piece
		LinkedList<BoardCell> testList = board.getAdjList(1, 4);
		System.out.println(testList.isEmpty());
//		assertTrue(testList.contains(board.getCellAt(2, 4)));
		assertEquals(1, testList.size());
		
		// Test near room edge, three walkway pieces
		testList = board.getAdjList(11, 18);
		assertTrue(testList.contains(board.getCellAt(11, 17)));
		assertTrue(testList.contains(board.getCellAt(11, 19)));
		assertTrue(testList.contains(board.getCellAt(10, 18)));
		assertEquals(3, testList.size());

		// Test between two rooms, walkways right and left
		testList = board.getAdjList(21, 9);
		assertTrue(testList.contains(board.getCellAt(21, 8)));
		assertTrue(testList.contains(board.getCellAt(20, 9)));
		assertEquals(2, testList.size());

		// Test surrounded by 4 walkways
		testList = board.getAdjList(16, 9);
		assertTrue(testList.contains(board.getCellAt(16, 8)));
		assertTrue(testList.contains(board.getCellAt(16, 10)));
		assertTrue(testList.contains(board.getCellAt(15, 9)));
		assertTrue(testList.contains(board.getCellAt(17, 9)));
		assertEquals(4, testList.size());
		
		// Test on Top edge of board, next to 1 room piece
		testList = board.getAdjList(0, 6);
		assertTrue(testList.contains(board.getCellAt(0, 5)));
		assertTrue(testList.contains(board.getCellAt(1, 6)));
		assertEquals(2, testList.size());
		
		// Test on right edge of board, next to 1 room piece
		testList = board.getAdjList(10, 22);
		assertTrue(testList.contains(board.getCellAt(11, 22)));
		assertTrue(testList.contains(board.getCellAt(10, 21)));
		assertEquals(2, testList.size());

		// Test on walkway next to  door that is not in the needed
		// direction to enter
		testList = board.getAdjList(15, 12);
		assertTrue(testList.contains(board.getCellAt(14, 12)));
		assertTrue(testList.contains(board.getCellAt(15, 11)));
		assertTrue(testList.contains(board.getCellAt(15, 13)));
		assertEquals(3, testList.size());
		
		//Test on bottom edge of board, next to 1 room piece
//		testList = board.getAdjList(22, 11);
//		assertTrue(testList.contains(board.getCellAt(22, 8)));
//		assertTrue(testList.contains(board.getCellAt(21, 9)));
//		assertEquals(2, testList.size());
	}
	
	
	// Tests of just walkways, 1 step, includes on edge of board
	// and beside room
	// Have already tested adjacency lists on all four edges, will
	// only test two edges here
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(3, 6, 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(2, 6)));
		assertTrue(targets.contains(board.getCellAt(4, 6)));
		assertTrue(targets.contains(board.getCellAt(3, 5)));
		
		board.calcTargets(15, 4, 1);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(15, 5)));
		assertTrue(targets.contains(board.getCellAt(15, 3)));			
	}
	
	// Tests of just walkways, 2 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(3, 6, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(5, targets.size());
		assertTrue(targets.contains(board.getCellAt(1, 6)));
		assertTrue(targets.contains(board.getCellAt(5, 6)));
		assertTrue(targets.contains(board.getCellAt(2, 5)));
		assertTrue(targets.contains(board.getCellAt(4, 7)));
		assertTrue(targets.contains(board.getCellAt(4, 5)));
		
		board.calcTargets(15, 4, 2);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(15, 6)));
		assertTrue(targets.contains(board.getCellAt(15, 2)));	
		}
	
	// Tests of just walkways, 4 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsFourSteps() {
		board.calcTargets(3, 6, 4);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(11, targets.size());
		assertTrue(targets.contains(board.getCellAt(0, 5)));
		assertTrue(targets.contains(board.getCellAt(2, 5)));
		assertTrue(targets.contains(board.getCellAt(1, 6)));
		assertTrue(targets.contains(board.getCellAt(4, 3)));
		assertTrue(targets.contains(board.getCellAt(6, 8)));
		assertTrue(targets.contains(board.getCellAt(6, 6)));
		assertTrue(targets.contains(board.getCellAt(7, 5)));
		assertTrue(targets.contains(board.getCellAt(7, 7)));
		assertTrue(targets.contains(board.getCellAt(6, 8)));
		assertTrue(targets.contains(board.getCellAt(6, 5)));
		assertTrue(targets.contains(board.getCellAt(6, 7)));		
		
		// Includes a path that doesn't have enough length
		board.calcTargets(15, 4, 4);
		targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(15, 0)));
		assertTrue(targets.contains(board.getCellAt(15, 8)));	
		assertTrue(targets.contains(board.getCellAt(13, 6)));	
		assertTrue(targets.contains(board.getCellAt(14, 7)));	
	}	
	
	// Tests of just walkways plus one door, 6 steps
	// These are LIGHT BLUE on the planning spreadsheet
	
	// Test getting into a room
	// These are LIGHT BLUE on the planning spreadsheet

	@Test 
	public void testTargetsIntoRoom()
	{
		// One room is exactly 2 away
		board.calcTargets(5, 10, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(5, targets.size());
		// directly left (can't go right 2 steps)
		assertTrue(targets.contains(board.getCellAt(5, 8)));
		//directly right
//		assertTrue(targets.contains(board.getCellAt(6, 12)));
		// one up/down, one left/right
//		assertTrue(targets.contains(board.getCellAt(5, 11)));
		assertTrue(targets.contains(board.getCellAt(6, 9)));
		assertTrue(targets.contains(board.getCellAt(6, 11)));
	}
	
	// Test getting into room, doesn't require all steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsIntoRoomShortcut() 
	{
		board.calcTargets(6, 21, 3);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(3, targets.size());
		// directly left (can't go right)
		assertTrue(targets.contains(board.getCellAt(6, 18)));
		// left then up
		assertTrue(targets.contains(board.getCellAt(5, 19)));
		// into the rooms
		assertTrue(targets.contains(board.getCellAt(5, 22)));
		}

	// Test getting out of a room
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testRoomExit()
	{
		// Take one step, essentially just the adj list
		board.calcTargets(4, 11, 1);
		Set<BoardCell> targets= board.getTargets();
		// Ensure doesn't exit through the wall
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(5, 11)));
		// Take two steps
		board.calcTargets(4, 11, 2);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(5, 12)));
		assertTrue(targets.contains(board.getCellAt(5, 10)));
		assertTrue(targets.contains(board.getCellAt(6, 11)));
	}

}