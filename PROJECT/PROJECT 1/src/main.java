import java.util.LinkedList;

public class main {
}
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