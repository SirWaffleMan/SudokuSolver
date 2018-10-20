package com.supertask;

public class SudokuBoard {
	
	private int board[][];
	
	public SudokuBoard() {
		board = new int[9][9];
	}
	
	public void setSquare(int x, int y, int val) {
		board[x][y] = val;
	}
	
	public int getSquare(int x, int y) {
		return board[x][y];
	}
	
	public int[] getSquares() {
		int result[] = new int[81];
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				result[i + 9 * j] = board[i][j];
			}
		}
		return result;
	}
}
