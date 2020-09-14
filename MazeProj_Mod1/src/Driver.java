import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
    private static MazeReader m;

    public static void main(String[] args) {
        String file;
        System.out.print("Welcome to the maze solver. Please enter maze.txt name: ");
        Scanner in = new Scanner(System.in);
        file = in.nextLine();
        try {
            m = new MazeReader(new Scanner(new FileReader(file))); //set up map
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Program will exit.");
            System.exit(1);
        }

        System.out.println(m.toString());
        /*
        Agent[] agents = new Agent[8]; //create an agent for each algo

        for(int i = 0; i < agents.length; i++){ //construct agents
            agents[i] = new Agent(i, m);
        }
        */

    }


}
