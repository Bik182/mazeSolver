package PROJECT.PROJECT1.MazeProj_Mod1.src;
import javax.swing.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 * Driver.java
 * A class to read and solve a maze, using MazeReader and MazeSolver
 * Jasen LaBolle
 * Illinois State University
 * IT 340 - Sept. 15 2020
 *
 * @authors Jasen LaBolle
 * @version 1.0
 * @since 2020-09-15
 */
public class Driver {
    private static MazeReader m;

    /**
     * Main method.
     * Get filename, and pass to MazeReader, then run MazeSolver.
     * @param args Unused.
     */
    public static void main(String[] args) {


        String file;
//        System.out.print("Welcome to the maze solver. Please enter maze.txt name: ");
//        Scanner in = new Scanner(System.in);
//        file = in.nextLine();
        file = "maze-2.txt";
        try {
            m = new MazeReader(new Scanner(new FileReader(file)) ); //set up map
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Program will exit.");
            System.exit(1);
        }

        System.out.println(m.toString());
        MazeSolver solver = new MazeSolver(m);
        System.out.println(solver.toString());

    }


}
