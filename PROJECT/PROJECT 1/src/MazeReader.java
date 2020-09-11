package maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeReader {
	String newmaze;
	
	public MazeReader() {
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MazeReader maze = new MazeReader();
		maze.printMaze(maze.readMaze());
	}
	
	//Function that accepts user input for filepath, then appends the file to a string to be returned
	public String readMaze() {
		newmaze = "";
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the file name of the maze: ");
		String fileName = input.nextLine();
		File file = new File(fileName);
		try {
			Scanner inFile = new Scanner(file);
			while(inFile.hasNextLine()) {
				newmaze += inFile.next();
				newmaze += "\n";
			}
			inFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File was not found, try a new maze or check file path");
			input.close();
			return newmaze;
		}
		input.close();
		return newmaze;
	}
	
	//Function that takes the newmaze string and prints it to the user screen
	public void printMaze(String newmaze) {
		System.out.println("The maze entered was: ");
		for(int i =0; i < newmaze.length(); i++) {
			System.out.print(newmaze.charAt(i));
		}
	}
	
	//Function that accepts a char as input and determines what the char symbol represents in the maze
	public static String fromChar(char ch) {
		try {
			String charValue = "";
			switch(ch) {
			case '#':
				charValue = "Walls";
			case '.':
				charValue = "Open Spaces";
			case 'o':
				charValue = "Start";
			case '*':
				charValue = "Finish (or goal)";				
			}
			return charValue;
			
		}
		catch (IllegalArgumentException e){
			return "Illegal argument detected, check the character entered";
		}
	}
	
	//Function that accepts a location and returns the char at that location in the maze
	public String charLocation(int location) {
		char ch = newmaze.charAt(location);
		String loc = String.valueOf(ch);
		return loc;
	}
	
}
