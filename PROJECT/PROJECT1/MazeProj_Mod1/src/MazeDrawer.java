package PROJECT.PROJECT1.MazeProj_Mod1.src;

import java.awt.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class MazeDrawer extends JFrame {

    private JButton button1, button2, button3;

    public MazeDrawer(){
        super("Maze");
        setLayout(new FlowLayout());

//        button1 = new JButton("First button");
//        add(button1);
//        theHandler handler = new theHandler();
//        button1.addActionListener(handler);


    }

    public void addRectangle(int x, int y, Color col){
        Painter paint = new Painter(x,y, col );
        super.add(paint);


    }

    private class theHandler implements ActionListener{

        public void actionPerformed(ActionEvent event){

            if(event.getSource() == button1){
                System.out.println("button1 pressed");
            }

        }

    }
}
