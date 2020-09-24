import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
/**
 * MazeSolver.java
 * A class to solve a maze stored in MazeReader
 * Jasen LaBolle
 * Illinois State University
 * IT 340 - Sept. 23 2020
 *
 * @authors Jasen LaBolle, Nicholas Souligne, Bikal Thapa
 * @version 1.0
 * @since 2020-09-23
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
        BFS(mr.getStart());
    }
    
    public void BFS(Space s) {
        for (boolean[] booleans : visited) { 
            Arrays.fill(booleans, false);
        }
    	Queue<Space> q = new ArrayDeque<>();
    	Queue<Space> pathing = new ArrayDeque<>();
    	Queue<Space> holder = new ArrayDeque<>();
    	visited[s.getI()][s.getJ()] = true;
    	q.add(new Space(s.getI(), s.getJ(), Type.START));
    	pathing.add(new Space(s.getI(), s.getJ(), Type.START));
    	Type type;
    	String path = "";
    	
    	while(!q.isEmpty()) {
    		Space newSpace = q.poll();
    		type = newSpace.getT();
    		if(type == Type.FINISH) {    
    			while(!pathing.isEmpty()) {
    				Space current = pathing.poll();
    				holder.add(current);
    				if(pathing.isEmpty()) {
    					//Code to reverse the queue in between comment blocks
    					Stack<Space> s2 = new Stack<Space>();
    					while(!holder.isEmpty()) {
    						s2.push(holder.poll());
    					}
    					while(!s2.isEmpty()) {
    						holder.add(s2.pop());
    					}
    					//end code for reversing queue
    					Space finish = current;
    					while(!holder.isEmpty()) {
    						Space curr = holder.poll();
    						if(canReach(finish, curr)) {
    							path += "(" + finish.getI() + "," + finish.getJ() + ")";
    							finish = curr;
    						}
    					}
    			    	
    					System.out.println(path);
    				}
    			}
    			hasFinish = true;
    		}
    			if(mr.hasNorth(newSpace) && !visited[newSpace.getI()-1][newSpace.getJ()]) {
    				q.add(mr.getNorth(newSpace));
    				pathing.add(mr.getNorth(newSpace));
    				visited[newSpace.getI()-1][newSpace.getJ()] = true;
    			}
    			if(mr.hasEast(newSpace) && !visited[newSpace.getI()][newSpace.getJ()+1]) {
    				q.add(mr.getEast(newSpace));
    				pathing.add(mr.getEast(newSpace));
    				visited[newSpace.getI()][newSpace.getJ()+1] = true;
    			}
    			if(mr.hasWest(newSpace) && !visited[newSpace.getI()][newSpace.getJ()-1]) {
    				q.add(mr.getWest(newSpace));
    				pathing.add(mr.getWest(newSpace));
    				visited[newSpace.getI()][newSpace.getJ()-1] = true;
    			}
    			if(mr.hasSouth(newSpace) && !visited[newSpace.getI()+1][newSpace.getJ()]) {
    				q.add(mr.getSouth(newSpace));
    				pathing.add(mr.getSouth(newSpace));
    				visited[newSpace.getI()+1][newSpace.getJ()] = true;
    			}
    	}
    }

    public boolean canReach(Space old, Space newSpace) {
    	if (newSpace.getI() == mr.getNorth(old).getI() && newSpace.getJ() == mr.getNorth(old).getJ()) {
    		return true;
    	}
    	else if (newSpace.getI() == mr.getEast(old).getI() && newSpace.getJ() == mr.getEast(old).getJ()) {
    		return true;
    	}
    	else if (newSpace.getI() == mr.getSouth(old).getI() && newSpace.getJ() == mr.getSouth(old).getJ()) {
    		return true;
    	}
    	else if (newSpace.getI() == mr.getWest(old).getI() && newSpace.getJ() == mr.getWest(old).getJ()) {
    		return true;
    	}
    	else {
    		return false;
    	}
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