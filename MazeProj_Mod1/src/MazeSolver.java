import java.util.Arrays;
import java.util.Stack;
/**
 * MazeSolver.java
 * A class to solve a maze stored in MazeReader
 * Jasen LaBolle
 * Illinois State University
 * IT 340 - Sept. 15 2020
 *
 * @authors Jasen LaBolle
 * @version 1.0
 * @since 2020-09-15
 */
public class MazeSolver {
    //instance vars
    private Stack<Space> stack;
    private MazeReader mr;
    private boolean[][] visited;
    private boolean hasFinish;

    /**
     * Constructor, creates flags for visiting Spaces in MazeReader, and runs solve(MazeReader.getStart())
     * @param mr The MazeReader object to be solved.
     */
    public MazeSolver(MazeReader mr) {
        this.mr = mr;
        visited = new boolean[mr.getY()][mr.getX()];
        for (boolean[] booleans : visited) {
            Arrays.fill(booleans, false);
        }
        stack = new Stack<Space>();
        hasFinish = false;
        solve(mr.getStart());
    }

    /**
     * Recursive algorithm for visiting all reachable Spaces in a maze. Sets hasFinish to true if a finish is found.
     * @param s The space to solve from.
     */
    private void solve(Space s) {
        if (s.getT() == Type.WALL) {
            visited[s.getI()][s.getJ()] = true;
        } else if (s.getT() == Type.FINISH) {
            visited[s.getI()][s.getJ()] = true;
            stack.add(s);
            hasFinish = true;
        } else {
            if (mr.hasNorth(s)) {
                if (!visited[s.getI() - 1][s.getJ()]) {
                    visited[s.getI()][s.getJ()] = true;
                    stack.add(s);
                    solve(mr.getNorth(s));
                }
            }
            if (mr.hasSouth(s)) {
                if (!visited[s.getI() + 1][s.getJ()]) {
                    visited[s.getI()][s.getJ()] = true;
                    stack.add(s);
                    solve(mr.getSouth(s));
                }
            }

            if (mr.hasEast(s)) {
                if (!visited[s.getI()][s.getJ() + 1]) {
                    visited[s.getI()][s.getJ()] = true;
                    stack.add(s);
                    solve(mr.getEast(s));
                }
            }
            if (mr.hasWest(s)) {
                if (!visited[s.getI()][s.getJ() - 1]) {
                    visited[s.getI()][s.getJ()] = true;
                    stack.add(s);
                    solve(mr.getWest(s));
                }
            }
        }
    }

    /**
     * Overridden method to print out if a finish was found or not.
     * @return String A statement on whether a solution was found.
     */
    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        for (Space s : stack) {
//            sb.append(String.format("(%d, %d)", s.getI(), s.getJ()));
//        }
//        return sb.toString();
        if(hasFinish){
            return "Finish Found";
        }
        else return "There is no finish.";
    }

}