package week7;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
//p.kalkar@innopolis.university
public class Snakes {

	static String[][] maze = new String[8][8];
	static Snake snake0 = new Snake(2, 2);
	static Snake snake1 = new Snake(5, 5);
	static final String logFile = "snakes/output.txt";
	static int appler;
	static int applec;
	static boolean apple0;
	static boolean apple1;
	static Direction d0;
	static Direction d1;

	public static void main(String[] args) {
		Apple();
		// TODO Implement the stop criteria
		while (snake0.alive && snake1.alive) {
			//eat();
			System.out.println();
			showMaze();
			appendMazeToFile(logFile);
			Bot_p_kalkar b0 = new Bot_p_kalkar();
			Bot_p_kalkar b1 = new Bot_p_kalkar();
			
			//d0 = b0.chooseDirection();
			//d1 = b1.chooseDirection();
			
			do {
				d0 = b0.chooseDirection();
			} while (!validateMove(d0, snake0));
			
			do {
				d1 = b1.chooseDirection();
			} while (!validateMove(d1, snake1));
			//Scanner scanner = new Scanner(System.in);
			//String inputString = scanner.nextLine();
			String[] moves = new String[2]; /*inputString.split(" ");*/
			// TODO I need to check if moves[] has two positions
			// System.out.println(moves[0]); // snake 0
			
			
			/*do {
				moves[0] = snake0.randMove();
			} while(!validateMove(moves[0], snake0));
			do {
				moves[1] = snake1.randMove();
			} while(!validateMove(moves[1], snake1));*/
			
			eat();
			
			System.out.println("Snake0 moves " + d0.toString());
			System.out.println("Snake1 moves " + d1.toString());


			//boolean validMove0 = validateMove(moves[0], snake0);
			Position p0 = snake0.getHead();
			//if (validMove0) {
				if (d0 == Direction.NORTH)
					snake0.Move(p0.row - 1, p0.column, apple0);
				else if (d0 == Direction.SOUTH)
					snake0.Move(p0.row + 1, p0.column, apple0);
				else if (d0 == Direction.WEST)
					snake0.Move(p0.row, p0.column-1, apple0);
				else if (d0 == Direction.EAST)
					snake0.Move(p0.row, p0.column+1, apple0);
			//}
			/*else {
				snake0.alive = false;
			}*/
			// System.out.println(moves[1]); // snake 1
			//boolean validMove1 = validateMove(moves[1], snake1);
			

			Position p1 = snake1.getHead();
			
			// TODO update the position of the head of the snake 1
			//if (validMove1) {
		        if (d1 == Direction.NORTH)
		          snake1.Move(p1.row - 1, p1.column, apple1);
		        else if (d1 == Direction.SOUTH)
		          snake1.Move(p1.row + 1, p1.column, apple1);
		        else if (d1 == Direction.WEST)
		          snake1.Move(p1.row, p1.column-1, apple1);
		        else if (d1 == Direction.EAST)
		          snake1.Move(p1.row, p1.column+1, apple1);
		      /*}
		      else {
		        snake1.alive = false;
		      }*/
			
			Check();

			// TODO Print and log the moves and an indicator if each was legal
			/**
			 * For the future: when a snake takes an apple, the position of the apple
			 * becomes the new head position.
			 */
			try {
		        TimeUnit.SECONDS.sleep(1);
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
			apple0 = false;
			apple1 = false;
		}
		System.out.println("Game over");
		if (!snake0.alive && snake1.alive)
		 System.out.println("0 - 1");
		else if (snake0.alive && !snake1.alive)
	    	 System.out.println("1 - 0");
	    else 
	    	System.out.println("0 - 0");
	    // TODO Print and log the result of the game (1-0, 0-0, 0-1)
	}

	
	// The following method checks the direction
	private static boolean validateMove(Direction d, Snake snake) {
	    /*assert move.equalsIgnoreCase("N") || move.equalsIgnoreCase("S") || move.equalsIgnoreCase("W")
	        || move.equalsIgnoreCase("E") : "The input (move) should be in {N, S, W, E}.";*/
	    if (d == Direction.NORTH
	        && (snake.getHead().row == 0 || snake.getSec().row + 1 == snake.getHead().row))
	      return false;
	    if (d == Direction.SOUTH && (snake.getHead().row == 7
	        || snake.getSec().row - 1 == snake.getHead().row))
	      return false;
	    if (d == Direction.WEST && (snake.positions.get(0).column == 0
	        || snake.getSec().column + 1 == snake.getHead().column))
	      return false;
	    if (d == Direction.EAST && (snake.positions.get(0).column == 7
	        || snake.getSec().column - 1 == snake.getHead().column))
	      return false;
	    return true;
	}
	
	// This method checks the winner and looser
	private static void Check() {
	    if (snake0.positions.get(0).row == snake1.positions.get(0).row
	        && snake0.positions.get(0).column == snake1.positions.get(0).column) {
	      snake0.alive = false;
	      snake1.alive = false;
	    }
	    for (int i = 0; i < snake1.positions.size(); i++)
	      if (snake0.positions.get(0).row == snake1.positions.get(i).row
	          && snake0.positions.get(0).column == snake1.positions.get(i).column) {
	        snake0.alive = false;
	      }
	    for (int i = 0; i < snake0.positions.size(); i++)
	      if (snake1.positions.get(0).row == snake0.positions.get(i).row
	          && snake1.positions.get(0).column == snake0.positions.get(i).column) {
	        snake1.alive = false;
	      }
	  }

	
	// This method shows the maze
	private static void showMaze() {
		for (int r = 0; r < maze.length; r++) {
			for (int c = 0; c < maze[r].length; c++) {
				if (maze[r][c] == "$")
					System.out.print("$");
				else {
					Position pos = new Position(r, c);
					System.out.print(key(pos));
				}
			}
			System.out.println();
		}
	}

	// This method prints the maze
	private static String key(Position pos) {
	    if (snake0.getHead().row == pos.row && snake0.getHead().column == pos.column)
	      return ("X");
	    if (snake1.getHead().row == pos.row && snake1.getHead().column == pos.column)
	      return ("Y");
	    for (int i = 1; i < snake0.positions.size(); i++)
	      if (snake0.positions.get(i).row == pos.row && snake0.positions.get(i).column == pos.column)
	        return ("x");
	    for (int i = 1; i < snake1.positions.size(); i++)
	      if (snake1.positions.get(i).row == pos.row && snake1.positions.get(i).column == pos.column)
	        return ("y");
	    //Validate if it is an apple	
	    
	    return (".");
	  }

	private static void appendTextToFile(String file, String text) {
		// TODO To be implemented
	}

	private static void appendMazeToFile(String file) {
		try (FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			for (int r = 0; r < maze.length; r++) {
				for (int c = 0; c < maze[r].length; c++) {
					Files.write(Paths.get(logFile), ".".getBytes(), StandardOpenOption.APPEND);
				}
				Files.write(Paths.get(logFile), "\n".getBytes(), StandardOpenOption.APPEND);
			}
		} catch (IOException e) {
			// TODO Exception handling left as an exercise
		}
	}
	
	// This method generates the apples and places them randomly
	public static void Apple() {
		int X;
		int Y;
		boolean sn0 = false;
		boolean sn1 = false;
		do {
			X = (int)Math.round(Math.random()*7);
			Y = (int)Math.round(Math.random()*7);
			
			for(int i = 0; i < snake0.positions.size(); i++) {
				if (snake0.positions.get(i).row != X && snake0.positions.get(i).column != Y) {
					sn0 = true;
					}
				else {
					sn0 = false;
				}
				}
			for(int i = 0; i < snake1.positions.size(); i++) {
				if (snake1.positions.get(i).row != X && snake1.positions.get(i).column != Y) {
					sn1 = true;
					}
				else {
					sn1 = false;
				}
				}
		} while(!sn0 && !sn1);
		maze[X][Y] = "$";
		appler = X;
		applec = Y;
	}
	
	
	// This method to eat the apple and change the positions accordingly
	public static void eat() {
		if(d0 == Direction.NORTH && snake0.positions.get(0).row - 1 == appler && snake0.positions.get(0).column == applec || 
				d0 == Direction.SOUTH && snake0.positions.get(0).row + 1 == appler && snake0.positions.get(0).column == applec ||
				d0 == Direction.WEST && snake0.positions.get(0).row == appler && snake0.positions.get(0).column - 1 == applec ||
				d0 == Direction.EAST && snake0.positions.get(0).row == appler && snake0.positions.get(0).column + 1 == applec) {
			apple0 = true;
			maze[appler][applec] = null;
			Apple();
		}
		else if(d1 == Direction.NORTH && snake1.positions.get(0).row - 1 == appler && snake1.positions.get(0).column == applec || 
				d1 == Direction.SOUTH && snake1.positions.get(0).row + 1 == appler && snake1.positions.get(0).column == applec ||
				d1 == Direction.WEST && snake1.positions.get(0).row == appler && snake1.positions.get(0).column - 1 == applec ||
				d1 == Direction.EAST && snake1.positions.get(0).row == appler && snake1.positions.get(0).column + 1 == applec) {
			apple1 = true;
			maze[appler][applec] = null;
			Apple();
	}

}
}

