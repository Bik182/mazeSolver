/******************************************************************************
 *  Compilation:  javac Maze.java
 *  Execution:    java Maze.java n
 *  Dependencies: StdDraw.java
 *
 *  Generates a perfect n-by-n maze using depth-first search with a stack.
 *
 *  % java Maze 62
 *
 *  % java Maze 61
 *
 *  Note: this program generalizes nicely to finding a random tree
 *        in a graph.
 *
 ******************************************************************************/

import java.util.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
public class Maze {
    private int n;                 // dimension of maze
    private boolean[][] north;     // is there a wall to north of cell i, j
    private boolean[][] east;
    private boolean[][] south;
    private boolean[][] west;
    private boolean[][] visited;
    private boolean done = false;
    private Stack myStack = new Stack();
    //private Deque deque = new LinkedList<>();
    private Queue<Integer> queue = new LinkedList<Integer>();

    public Maze(int n) {
        this.n = n;
        StdDraw.setXscale(0, n + 2);
        StdDraw.setYscale(0, n + 2);
        init();
        generate();
    }


    // a test client
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final JFrame frame = new JFrame();
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        String num = JOptionPane.showInputDialog("Enter a number");


        int n = Integer.parseInt(num);

        Maze maze = new Maze(n);
        StdDraw.enableDoubleBuffering();
        maze.draw();
        maze.solve();
    }

    private void init() {
        // initialize border cells as already visited
        visited = new boolean[n + 2][n + 2];
        for (int x = 0; x < n + 2; x++) {
            visited[x][0] = true;
            visited[x][n + 1] = true;
        }
        for (int y = 0; y < n + 2; y++) {
            visited[0][y] = true;
            visited[n + 1][y] = true;
        }


        // initialze all walls as present
        north = new boolean[n + 2][n + 2];
        east = new boolean[n + 2][n + 2];
        south = new boolean[n + 2][n + 2];
        west = new boolean[n + 2][n + 2];
        for (int x = 0; x < n + 2; x++) {
            for (int y = 0; y < n + 2; y++) {
                north[x][y] = true;
                east[x][y] = true;
                south[x][y] = true;
                west[x][y] = true;
            }
        }
    }

    // generate the maze
    private void generate(int x, int y) {



        myStack.push(x);
        myStack.push(y);


        while (!myStack.isEmpty()) {

            // while there is an unvisited neighbor

            int yIndex = (Integer) (myStack.pop());
            int xIndex = (Integer) (myStack.pop());
            visited[xIndex][yIndex] = true;
            boolean isTrue = true;
            //Check if correct coordinates are going in
            // pick random neighbor (could use Knuth's trick instead)

            if (!visited[xIndex][yIndex + 1] || !visited[xIndex + 1][yIndex] || !visited[xIndex][yIndex - 1] || !visited[xIndex - 1][yIndex]) {

                while (isTrue) {
                    double r = StdRandom.uniform(4);
                    if (r == 0 && !visited[xIndex][yIndex + 1]) {

                        north[xIndex][yIndex] = false;
                        south[xIndex][yIndex + 1] = false;


                        //cur index
                        myStack.push(xIndex);
                        myStack.push(yIndex);
                        //neighbor index
                        myStack.push(xIndex);
                        myStack.push(yIndex + 1);


                        isTrue = false;
                    } else if (r == 1.0 && !visited[xIndex + 1][yIndex]) {
                        east[xIndex][yIndex] = false;
                        west[xIndex + 1][yIndex] = false;
                        int tempX = xIndex + 1;

                        //cur index
                        myStack.push(xIndex);
                        myStack.push(yIndex);
                        //neighbor index
                        myStack.push(xIndex + 1);
                        myStack.push(yIndex);
                        isTrue = false;

                    } else if (r == 2.0 && !visited[xIndex][yIndex - 1]) {
                        south[xIndex][yIndex] = false;
                        north[xIndex][yIndex - 1] = false;
                        int tempY = yIndex - 1;

                        //cur index
                        myStack.push(xIndex);
                        myStack.push(yIndex);
                        //neighbor index
                        myStack.push(xIndex);
                        myStack.push(yIndex - 1);

                        isTrue = false;

                    } else if (r == 3.0 && !visited[xIndex - 1][yIndex]) {
                        west[xIndex][yIndex] = false;
                        east[xIndex - 1][yIndex] = false;


                        //cur index
                        myStack.push(xIndex);
                        myStack.push(yIndex);
                        //neighbor index
                        myStack.push(xIndex - 1);
                        myStack.push(yIndex);
                        isTrue = false;

                    }
                }

            }
        }
    }

    // generate the maze starting from lower left
    private void generate() {
        generate(1, 1);

/*
        // delete some random walls
        for (int i = 0; i < n; i++) {
            int x = 1 + StdRandom.uniform(n-1);
            int y = 1 + StdRandom.uniform(n-1);
            north[x][y] = south[x][y+1] = false;
        }

        // add some random walls
        for (int i = 0; i < 10; i++) {
            int x = n/2 + StdRandom.uniform(n/2);
            int y = n/2 + StdRandom.uniform(n/2);
            east[x][y] = west[x+1][y] = true;
        }
*/

    }

    // solve the maze using depth-first search
    private void solve(int x, int y) {
/////////////////////////////////////////////

        //String begIndex = Integer.toString(x) + Integer.toString(y);
        ((LinkedList) queue).add(x);
        ((LinkedList) queue).add(y);
        while (!queue.isEmpty()) {

            int tempX = (Integer) (((LinkedList) queue).removeFirst());
            int tempY = (Integer) (((LinkedList) queue).removeFirst());


            visited[tempX][tempY] = true;
            if (tempX == 0 || tempY == 0 || tempX == n + 1 || y == n + 1) {
                return;
            }

            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(tempX + 0.5, tempY + 0.5, 0.25);
            StdDraw.show();
            StdDraw.pause(30);

            // reached middle
            if (tempX == n / 2 && tempY == n / 2) {
                done = true;
            }


            else    if (!north[tempX][tempY] && !visited[tempX][tempY+1]) {
                //int yTemp = tempY + 1;
                ((LinkedList) queue).add(tempX);
                ((LinkedList) queue).add(tempY+1);
                StdDraw.setPenColor(StdDraw.GRAY);
                StdDraw.filledCircle(tempX + 0.5, tempY + 0.5, 0.25);
                StdDraw.show();
                StdDraw.pause(30);

            }
            if (!east[tempX][tempY] && !visited[tempX+1][tempY]) {
                // int xTemp = tempX + 1;
                ((LinkedList) queue).add(tempX+1);

                ((LinkedList) queue).add(tempY);
                StdDraw.setPenColor(StdDraw.GRAY);
                StdDraw.filledCircle(tempX + 0.5, tempY + 0.5, 0.25);
                StdDraw.show();
                StdDraw.pause(30);

            }
            if (!south[tempX][tempY] && !visited[tempX][tempY-1]) {
                // int yyTemp = tempY - 1;
                ((LinkedList) queue).add(tempX);
                ((LinkedList) queue).add(tempY-1);
                StdDraw.setPenColor(StdDraw.GRAY);
                StdDraw.filledCircle(tempX + 0.5, tempY + 0.5, 0.25);
                StdDraw.show();
                StdDraw.pause(30);

            }   if (!west[tempX][tempY] && !visited[tempX-1][tempY]) {
                // int xxTemp = tempX - 1;
                ((LinkedList) queue).add(tempX-1);
                ((LinkedList) queue).add(tempY);
                StdDraw.setPenColor(StdDraw.GRAY);
                StdDraw.filledCircle(tempX + 0.5, tempY + 0.5, 0.25);
                StdDraw.show();
                StdDraw.pause(30);

            }



            if (done) {
                return;
            }


        }


    }

    // solve the maze starting from the start state
    public void solve() {
        for (int x = 1; x <= n; x++)
            for (int y = 1; y <= n; y++)
                visited[x][y] = false;
        done = false;
        solve(1, 1);
    }

    // draw the maze
    public void draw() {
        StdDraw.setPenColor(StdDraw.RED);

        StdDraw.filledCircle(n / 2.0 + 0.5, n / 2.0 + 0.5, 0.375);

        StdDraw.filledCircle(1.5, 1.5, 0.375);

        StdDraw.setPenColor(StdDraw.BLACK);
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                if (south[x][y]) StdDraw.line(x, y, x + 1, y);
                if (north[x][y]) StdDraw.line(x, y + 1, x + 1, y + 1);
                if (west[x][y]) StdDraw.line(x, y, x, y + 1);
                if (east[x][y]) StdDraw.line(x + 1, y, x + 1, y + 1);
            }
        }
        StdDraw.show();
        StdDraw.pause(1000);
    }

}

