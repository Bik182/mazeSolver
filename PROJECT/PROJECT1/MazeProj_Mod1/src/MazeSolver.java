package PROJECT.PROJECT1.MazeProj_Mod1.src;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.*;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
    public JButton stepButton, animationButton, reset;
    public int numOfMoves = 0;
    public  JLabel status, solution;
    private Color gray = Color.LIGHT_GRAY;
    private long begTime, endTime, finalTime;
    public JPanel panel, mainPanel;
    public  JFrame frame;
    private  Type type;
    private String path = "";

    private  Timer t;
    private Queue<Space> q = new ArrayDeque<>();
    private Queue<Space> pathing = new ArrayDeque<>();
    private Queue<Space> holder = new ArrayDeque<>();
    /**
     * Constructor, creates flags for visiting Spaces in MazeReader, and runs solve(MazeReader.getStart())
     * @param mr The MazeReader object to be solved.
     */
    public MazeSolver(MazeReader mr, JPanel panel,JPanel panel1, JFrame frame, JLabel status, JLabel solution, JButton reset) throws InterruptedException {
        this.mr = mr;
        this.panel = panel;
        this.frame = frame;
        this.mainPanel = panel1;
        this.status = status;
        this.solution = solution;
        this.reset = reset;
        visited = new boolean[mr.getY()][mr.getX()];
        for (boolean[] booleans : visited) {
            Arrays.fill(booleans, false);
        }
        stack = new Stack<Space>();
        hasFinish = false;


        stepButton = new JButton("Step button");
        stepButton.setSize(100,50);
        stepButton.setLocation(1000,500);



        animationButton = new JButton("Animation button");
        animationButton.setSize(100,50);
        animationButton.setLocation(1000,1000);


        begTime = System.currentTimeMillis();


        BFS(mr.getStart());

        panel.setVisible(true);
        frame.add(panel);
        frame.setVisible(true);

    }



    //Function to solve the maze using BFS algorithm
    public void BFS(Space s)  {

        //we are starting
        status.setText("Solution is progress...");

        for (boolean[] booleans : visited) { 
            Arrays.fill(booleans, false);
        }

    	visited[s.getI()][s.getJ()] = true;
    	q.add(new Space(s.getI(), s.getJ(), Type.START));
    	pathing.add(new Space(s.getI(), s.getJ(), Type.START));

        ActionListener timerAction;

        //for animation button, does this every second until q.isEmpty()
        timerAction = e -> {

            Space newSpace = q.poll();
            type = newSpace.getT();


            if (type == Type.FINISH) {
                while (!pathing.isEmpty()) {
                    Space current = pathing.poll();
                    holder.add(current);
                    if (pathing.isEmpty()) {
                        //Code to reverse the queue in between comment blocks
                        Stack<Space> s2 = new Stack<Space>();
                        while (!holder.isEmpty()) {
                            s2.push(holder.poll());
                        }
                        while (!s2.isEmpty()) {
                            holder.add(s2.pop());
                        }
                        //end code for reversing queue
                        Space finish = current;
                        while (!holder.isEmpty()) {
                            Space curr = holder.poll();
                            if (canReach(finish, curr)) {
                                path += "(" + finish.getI() + "," + finish.getJ() + ")";

                                finish = curr;
                            }
                        }
                        solution.setText("Solution finish @ (" + finish.getI() + "," + finish.getJ() + ") in " + numOfMoves + " moves.");
                        solution.setVisible(true);
                        status.setVisible(false);
                        System.out.println(path);
                    }
                }

                hasFinish = true;
            }
            if (mr.hasNorth(newSpace) && !visited[newSpace.getI() - 1][newSpace.getJ()]) {
                q.add(mr.getNorth(newSpace));
                pathing.add(mr.getNorth(newSpace));
                visited[newSpace.getI()][newSpace.getJ()] = true;
                Painter paint1 = new Painter(newSpace.getJ(), newSpace.getI(), gray);
                panel.add(paint1);
            }
            if (mr.hasEast(newSpace) && !visited[newSpace.getI()][newSpace.getJ() + 1]) {
                q.add(mr.getEast(newSpace));
                pathing.add(mr.getEast(newSpace));
                visited[newSpace.getI()][newSpace.getJ()] = true;
                Painter paint1 = new Painter(newSpace.getJ(), newSpace.getI(), gray);
                panel.add(paint1);
            }
            if (mr.hasWest(newSpace) && !visited[newSpace.getI()][newSpace.getJ() - 1]) {
                q.add(mr.getWest(newSpace));
                pathing.add(mr.getWest(newSpace));
                visited[newSpace.getI()][newSpace.getJ()] = true;
                Painter paint1 = new Painter(newSpace.getJ(), newSpace.getI(), gray);
                panel.add(paint1);
            }
            if (mr.hasSouth(newSpace) && !visited[newSpace.getI() + 1][newSpace.getJ()]) {
                q.add(mr.getSouth(newSpace));
                pathing.add(mr.getSouth(newSpace));
                visited[newSpace.getI()][newSpace.getJ()] = true;

                Painter paint1 = new Painter(newSpace.getJ(), newSpace.getI(), gray);
                panel.add(paint1);
            }
            panel.repaint();
            panel.setVisible(true);
            frame.repaint();
            frame.setVisible(true);
            numOfMoves++;
            if(q.isEmpty()){

                t.stop();
            }


        };

        //create time with timerAction above
        t = new Timer(1000,timerAction);

        //actionListener for both buttons, step and animation

        ActionListener actionLis = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                if(event.getSource() == stepButton){


                    Space newSpace = q.poll();
                    type = newSpace.getT();

                    if (type == Type.FINISH) {
                        while (!pathing.isEmpty()) {
                            Space current = pathing.poll();
                            holder.add(current);
                            if (pathing.isEmpty()) {
                                //Code to reverse the queue in between comment blocks
                                Stack<Space> s2 = new Stack<Space>();
                                while (!holder.isEmpty()) {
                                    s2.push(holder.poll());
                                }
                                while (!s2.isEmpty()) {
                                    holder.add(s2.pop());
                                }
                                //end code for reversing queue
                                Space finish = current;
                                while (!holder.isEmpty()) {
                                    Space curr = holder.poll();
                                    if (canReach(finish, curr)) {
                                        path += "(" + finish.getI() + "," + finish.getJ() + ")";

                                        finish = curr;
                                    }
                                }
                                solution.setText("Solution finish @ " + path.substring(0, path.indexOf(')') +1) + " in " + numOfMoves + " moves.");
                                solution.setVisible(true);
                                status.setVisible(false);
                                System.out.println(path);
                            }
                        }

                        hasFinish = true;
                    }
                    if (mr.hasNorth(newSpace) && !visited[newSpace.getI() - 1][newSpace.getJ()]) {
                        q.add(mr.getNorth(newSpace));
                        pathing.add(mr.getNorth(newSpace));
                        visited[newSpace.getI()][newSpace.getJ()] = true;
                        Painter paint1 = new Painter(newSpace.getJ(), newSpace.getI(), gray);
                        panel.add(paint1);
                    }
                    if (mr.hasEast(newSpace) && !visited[newSpace.getI()][newSpace.getJ() + 1]) {
                        q.add(mr.getEast(newSpace));
                        pathing.add(mr.getEast(newSpace));
                        visited[newSpace.getI()][newSpace.getJ()] = true;
                        Painter paint1 = new Painter(newSpace.getJ(), newSpace.getI(), gray);
                        panel.add(paint1);
                    }
                    if (mr.hasWest(newSpace) && !visited[newSpace.getI()][newSpace.getJ() - 1]) {
                        q.add(mr.getWest(newSpace));
                        pathing.add(mr.getWest(newSpace));
                        visited[newSpace.getI()][newSpace.getJ()] = true;
                        Painter paint1 = new Painter(newSpace.getJ(), newSpace.getI(), gray);
                        panel.add(paint1);
                    }
                    if (mr.hasSouth(newSpace) && !visited[newSpace.getI() + 1][newSpace.getJ()]) {
                        q.add(mr.getSouth(newSpace));
                        pathing.add(mr.getSouth(newSpace));
                        visited[newSpace.getI()][newSpace.getJ()] = true;

                        Painter paint1 = new Painter(newSpace.getJ(), newSpace.getI(), gray);
                        panel.add(paint1);
                    }
                    numOfMoves++;
                        panel.repaint();
                        panel.setVisible(true);
                        frame.repaint();
                        frame.setVisible(true);

                }

                if(event.getSource() == animationButton){
                    if(t.isRunning()){

                        t.stop();
                        stepButton.setEnabled(true);
                        stepButton.setText("Step Button");
                        animationButton.setText("Start Animation");
                    }else{
                        stepButton.setEnabled(false);
                        stepButton.setText("Step Button (disabled)");
                        t.start();
                        animationButton.setText("Stop Animation");
                    }

                }
                if(event.getSource() == reset){

                    t.stop();
                    frame.setVisible(false); //you can't see me!
                    frame.dispose(); //Destroy the JFrame object
                }
            }
            };


        stepButton.addActionListener(actionLis);
        animationButton.addActionListener(actionLis);

        mainPanel.add(stepButton);
        mainPanel.add(animationButton);



    }




    //Helper function for BFS that accepts 2 spaces and determines if the newSpace can be reached from the old space
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
    private void solve(Space s) throws InterruptedException {

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
                    //Painter paint1 = new Painter(s.getI(),s.getJ(), gray );
                    Painter paint1 = new Painter(s.getJ(),s.getI(), gray );
                    panel.add(paint1);

                    stack.add(s);

                    solve(mr.getNorth(s));
                }
            }
            if (mr.hasSouth(s)) {
                if (!visited[s.getI() + 1][s.getJ()]) {
                    visited[s.getI()][s.getJ()] = true;
                    Painter paint3 = new Painter(s.getJ(),s.getI(), gray );
                    panel.add(paint3);
                    stack.add(s);

                    solve(mr.getSouth(s));
                }
            }

            if (mr.hasEast(s)) {
                if (!visited[s.getI()][s.getJ() + 1]) {
                    visited[s.getI()][s.getJ()] = true;
                    Painter paint3 = new Painter(s.getJ(),s.getI(), gray );
                    panel.add(paint3);
                    stack.add(s);

                    solve(mr.getEast(s));
                }
            }
            if (mr.hasWest(s)) {
                if (!visited[s.getI()][s.getJ() - 1]) {
                    visited[s.getI()][s.getJ()] = true;
                    Painter paint4 = new Painter(s.getJ(),s.getI(), gray );
                    panel.add(paint4);
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
