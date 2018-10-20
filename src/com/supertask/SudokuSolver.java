package com.supertask;

public class SudokuSolver {
	
	public static int[] findNextOpenSquare(SudokuBoard board) {
		
		int loc[] = new int[2];
		loc[0] = -1;
		loc[1] = -1;
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				int value = board.getSquare(i, j);
				if(value == 0) {
					loc[0] = i;
					loc[1] = j;
					return loc;
				}
			}
		}
		
		return loc;
	}
	
}
