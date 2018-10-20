package com.supertask;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Window implements ActionListener{
	JFrame frame;
	JPanel panel;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem resetItem;
	JMenuItem solveItem;
	JTextField[] inputs;
	Font font;
	Font solvedFont;
	
	int[] squares;
	int[] solvedSquares;
	
	public Window() {
		squares = new int[81];
		solvedSquares = new int[81];
		frame = new JFrame("Sudoku Puzzle Solver");
		panel = new JPanel();
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		resetItem = new JMenuItem("Reset", KeyEvent.VK_R);
		solveItem = new JMenuItem("Solve", KeyEvent.VK_S);
		resetItem.getAccessibleContext().setAccessibleDescription("Reset the Sudoku puzzle");
		solveItem.getAccessibleContext().setAccessibleDescription("Solve Sudoku puzzle");
		
		resetItem.addActionListener(this);
		solveItem.addActionListener(this);
		
		menu.add(resetItem);
		menu.add(solveItem);
		menuBar.add(menu);
		
		panel.setPreferredSize(new Dimension(500, 500));
		inputs = new JTextField[81];
		font = new Font("Arial", Font.BOLD, 32);
		solvedFont = new Font("Arial", Font.ITALIC, 28);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for(int i = 0; i < 81; i++) {
			inputs[i] = new JTextField();
			inputs[i].setFont(font);
			inputs[i].setPreferredSize(new Dimension(50, 50));
			inputs[i].setHorizontalAlignment(JTextField.CENTER);
			panel.add(inputs[i]);
		}
		frame.setJMenuBar(menuBar);
		frame.add(panel);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public void setSolvedPuzzle(SudokuBoard board) {
		solvedSquares = board.getSquares();
	}
	
	private void resetAll() {
		for(int i = 0; i < squares.length; i++) {
			squares[i] = 0;
			inputs[i].setText(null);
		}
	}
	
	private void saveHints() {
		for(int i = 0; i < inputs.length; i++) {
			if(inputs[i].getText().equals("")) {
				squares[i] = 0;
			}else {
				squares[i] = Integer.parseInt(inputs[i].getText());
			}
		}
	}
	
	private void displaySolution() {
		for(int i = 0; i < 81; i++) {
			inputs[i].setText(String.valueOf(solvedSquares[i]));
		}
	}
	
	public int getValue(int i) {
		return squares[i];
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == resetItem) {
			resetAll();
		}
		
		if(e.getSource() == solveItem) {
			saveHints();
			Main.readValues();
			Main.solve();
			displaySolution();
		}
	}
}
