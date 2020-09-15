import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Stack<Space> stack;
    private MazeReader mr;
    private boolean[][] visited;
    private boolean hasFinish;

    public MazeSolver(MazeReader mr) {
        this.mr = mr;
        System.out.println(mr);
        visited = new boolean[mr.getY()][mr.getX()];
        for (boolean[] booleans : visited) {
            Arrays.fill(booleans, false);
        }
        stack = new Stack<Space>();
        hasFinish = false;
        solve(mr.getStart());
    }

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