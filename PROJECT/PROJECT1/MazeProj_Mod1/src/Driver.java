package PROJECT.PROJECT1.MazeProj_Mod1.src;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton startButton, loadButton, reset;
    public JTextField textField;
    public JLabel status, solution;
    public JFrame frame1;
    public JPanel panel1, panel2, panel3;

    /**
     * Main method.
     * Get filename, and pass to MazeReader, then run MazeSolver.
     * @param
     */

    public Driver(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        frame1 = new JFrame();
        panel1 = new JPanel();

        frame1.setLayout(new BorderLayout());
        frame1.setSize(1000, 1000);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        panel1.setLayout( new FlowLayout());

        startButton = new JButton("Start");
        startButton.setSize(100,50);
        startButton.setLocation(0,0);
        startButton.setVisible(false);

        loadButton = new JButton("Load");
        loadButton.setSize(100,50);
        loadButton.setLocation(150,0);

        reset = new JButton("Reset");
        reset.setSize(100,50);
        reset.setLocation(1000,500);

        theHandler handler = new theHandler();
        loadButton.addActionListener(handler);
        startButton.addActionListener(handler);
        reset.addActionListener(handler);
        textField = new JTextField("maze-2.txt");
        textField.setSize(100,50);
        textField.setLocation(350,0);

        status = new JLabel("No Maze...");
        status.setSize(100,50);
        status.setLocation(100,500);

        solution = new JLabel("solution...");
        solution.setSize(100,50);
        solution.setLocation(3000,500);
        solution.setVisible(false);

        panel1.add(status);
        panel1.add(solution);

        panel1.add(startButton);
        panel1.add(loadButton);
        panel1.add(reset);
        panel1.add(textField);


        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setSize(500,500);

        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.setSize(500,500);

        frame1.add(panel1, BorderLayout.NORTH);
        frame1.setLocation(dim.width/2-frame1.getSize().width/2, dim.height/2-frame1.getSize().height/2);
        frame1.setVisible(true);
    }
    public static void main(String[] args) {

        new Driver();


    }



    private class theHandler implements ActionListener {

        public void actionPerformed(ActionEvent event){

            if(event.getSource() == startButton){

                MazeSolver solver = null;
                try {
                    solver = new MazeSolver(m, panel2,panel1, frame1, status,solution, reset);
                    startButton.setVisible(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //System.out.println(solver.toString());
            }

            if(event.getSource() == loadButton){

                String file = textField.getText();
                System.out.println(file);
                try {
                    m = new MazeReader(new Scanner(new FileReader(file)),frame1, panel2); //set up map
                } catch (FileNotFoundException e) {
                    System.out.println("File not found. Program will exit.");
                    System.exit(1);
                }
                status.setText("Maze Loaded");
                loadButton.setVisible(false);
                startButton.setVisible(true);
                textField.setVisible(false);
                System.out.println("file");

            }
            if(event.getSource() == reset){
                frame1.setVisible(false);
                frame1.dispose();
                new Driver();

            }

        }

    }


}
