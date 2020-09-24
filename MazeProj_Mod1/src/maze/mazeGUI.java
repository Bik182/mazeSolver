package maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class mazeGUI {
    MazeReader mr;
    MazeSolver ms;
    JFrame frame;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem enter, load, start;
    File selectedFile;
    JFileChooser chooser;
    JOptionPane optionPane;

    public mazeGUI() {
        frame = new JFrame("Maze Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(10 * mr.getX(), 10 * mr.getY());

        frame.setVisible(true);
    }

    public void drawMaze(){
        
    }

    public void setMenu() {
        menuBar = new JMenuBar();
        menu = new JMenu("Options");
        enter = new JMenuItem("Select a File");
        load = new JMenuItem("Load");
        start = new JMenuItem("Start");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionPane = new JOptionPane("File Selection");
                chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.setCurrentDirectory(new File(Paths.get("").toAbsolutePath().toString()));
                if (chooser.showOpenDialog(optionPane) == JFileChooser.APPROVE_OPTION) {
                    selectedFile = chooser.getSelectedFile();
                }
            }
        });
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mr = new MazeReader(new Scanner(selectedFile));
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ms = new MazeSolver(mr);
            }
        });
    }
}
