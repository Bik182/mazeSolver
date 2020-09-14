package project.module1.src;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {

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

    private void solve(int x, int y) {



        ((LinkedList) queue).add(x);
        ((LinkedList) queue).add(y);
        while (!queue.isEmpty()) {

            int tempX = (Integer) (((LinkedList) queue).removeFirst());
            int tempY = (Integer) (((LinkedList) queue).removeFirst());


            visited[tempX][tempY] = true;

//            if (tempX == 0 || tempY == 0 || tempX == n + 1 || y == n + 1) {
//                return;
//            }
        //This draws on original map,
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(tempX + 0.5, tempY + 0.5, 0.25);
            StdDraw.show();
            StdDraw.pause(30);
        /////////////////////////////////////////////////////
            // reached middle
//            if (tempX == n / 2 && tempY == n / 2) {
//                done = true;
//            }


             if (!north[tempX][tempY] && !visited[tempX][tempY+1]) {
                //int yTemp = tempY + 1;
                ((LinkedList) queue).add(tempX);
                ((LinkedList) queue).add(tempY+1);
                //This draws on original map,

                StdDraw.setPenColor(StdDraw.GRAY);
                StdDraw.filledCircle(tempX + 0.5, tempY + 0.5, 0.25);
                StdDraw.show();
                StdDraw.pause(30);
                /////////////////////////////////////////////////////
            }
            if (!east[tempX][tempY] && !visited[tempX+1][tempY]) {
                // int xTemp = tempX + 1;
                ((LinkedList) queue).add(tempX+1);

                ((LinkedList) queue).add(tempY);
                //This draws on original map,

                StdDraw.setPenColor(StdDraw.GRAY);
                StdDraw.filledCircle(tempX + 0.5, tempY + 0.5, 0.25);
                StdDraw.show();
                StdDraw.pause(30);
                /////////////////////////////////////////////////////
            }
            if (!south[tempX][tempY] && !visited[tempX][tempY-1]) {
                // int yyTemp = tempY - 1;
                ((LinkedList) queue).add(tempX);
                ((LinkedList) queue).add(tempY-1);
                //This draws on original map,

                StdDraw.setPenColor(StdDraw.GRAY);
                StdDraw.filledCircle(tempX + 0.5, tempY + 0.5, 0.25);
                StdDraw.show();
                StdDraw.pause(30);
                /////////////////////////////////////////////////////
            }   if (!west[tempX][tempY] && !visited[tempX-1][tempY]) {
                // int xxTemp = tempX - 1;
                ((LinkedList) queue).add(tempX-1);
                ((LinkedList) queue).add(tempY);
                //This draws on original map,

                StdDraw.setPenColor(StdDraw.GRAY);
                StdDraw.filledCircle(tempX + 0.5, tempY + 0.5, 0.25);
                StdDraw.show();
                StdDraw.pause(30);
                /////////////////////////////////////////////////////
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
}
