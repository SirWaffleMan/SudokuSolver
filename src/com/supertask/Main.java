package com.supertask;

public class Main {
	
	static SudokuBoard startingBoard;
	static SudokuBoard workingBoard;
	static Window window;

	public static void main(String[] args) {
		init();
	}
	
	public static void init() {
		startingBoard = new SudokuBoard();
		workingBoard = new SudokuBoard();
		window = new Window();
	}
	
	public static void solve() {
		int x = SudokuSolver.findNextOpenSquare(workingBoard)[0];
		int y = SudokuSolver.findNextOpenSquare(workingBoard)[1];
		int value = 1;
		while(!guessSquare(x, y, value)) {
			value++;
		}
		window.setSolvedPuzzle(workingBoard);
	}
	
	public static boolean guessSquare(int x, int y, int val) {
		
		workingBoard.setSquare(x, y, val);
		
		while(!checkValidSquare(x, y)) {
			return false;
		}
		
		int X = SudokuSolver.findNextOpenSquare(workingBoard)[0];
		int Y = SudokuSolver.findNextOpenSquare(workingBoard)[1];
		int value = 1;
		
		if(X == -1 && Y == -1) {
			return true;
		}
		
		while(!guessSquare(X, Y, value)) {
			value++;
			
			if(value > 9) {
				workingBoard.setSquare(X, Y, 0);
				return false;
			}
		}
			
		return true;
	}
	
	public static boolean checkValidSquare(int x, int y) {
		
		int value = workingBoard.getSquare(x, y);
		
		// Check valid value in row
		for(int i = 0; i < 9; i++) {
			if(i == y) {
				continue;
			}
			
			if(value == workingBoard.getSquare(x, i)) {
				return false;
			}
		}
		
		// Check valid value in column
		for(int j = 0; j < 9; j++) {
			if(j == x) {
				continue;
			}
			if(value == workingBoard.getSquare(j, y)) {
				return false;
			}
		}
		
		// Check valid group
		int groupX = x - (x % 3);
		int groupY = y - (y % 3);
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				
				if(groupX + i == x && groupY + j == y) {
					continue;
				}
				
				if(value == workingBoard.getSquare(groupX + i, groupY + j)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void printResult() {
		System.out.println();
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(workingBoard.getSquare(i, j) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void readValues() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				int value = window.getValue(i + 9 * j);
				startingBoard.setSquare(i, j, value);
				workingBoard.setSquare(i, j, value);
			}
		}
	}

}
